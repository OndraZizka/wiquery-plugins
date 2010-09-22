/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.wiquery.plugins.jqgrid.component;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.RequestCycle;
import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.model.IModel;
import org.apache.wicket.protocol.http.WebRequest;
import org.apache.wicket.protocol.http.WebRequestCycle;

import com.wiquery.plugins.jqgrid.model.GridModel;
import com.wiquery.plugins.jqgrid.model.SortInfo;
import com.wiquery.plugins.jqgrid.model.SortOrder;

public class GridXMLData<B extends Serializable> extends XMLResource {

	private static final long serialVersionUID = 1L;

	private IDataProvider<B> dataProvider;
	
	private GridModel<B> gridModel;
	
	private B searchBean;
	
	private SortInfo sortInfo;
	
	private String[] searchFields;
	
	private List<IModel<B>> models;
	
	private GridDataPanel<B> dataPanel;
	
	public GridXMLData(IDataProvider<B> dataProvider, GridModel<B> gridModel, B searchBean, GridDataPanel<B> dataPanel, String... searchFields) {
		this.dataProvider = dataProvider;
		this.gridModel = gridModel;
		this.searchBean = searchBean;		
		this.searchFields = searchFields;
		this.models = new ArrayList<IModel<B>>();
		this.dataPanel = dataPanel;
		
	}

	@Override
	protected String getXml() {		
		String encoding = ((WebRequest)(WebRequestCycle.get().getRequest())).getHttpServletRequest().getCharacterEncoding();
		//TODO: write directly to the output stream?
		StringBuffer writer = new StringBuffer();
		writer.append("<?xml version='1.0' encoding='");
		writer.append(encoding);
		writer.append("'?>");
		XMLDataRequestTarget target = new XMLDataRequestTarget(dataPanel.getPage());
		dataPanel.setOutputMarkupId(true);
		dataPanel.setVisible(true);
		target.addComponent(dataPanel);
		StringBuffer temp = new StringBuffer();
		target.respond(RequestCycle.get(), temp);				
		/*
		final BufferedWebResponse response = (BufferedWebResponse)RequestCycle.get().getResponse();		
		*/		
		dataPanel.setVisible(false);
		String content = temp.toString();
		content = content.substring(content.indexOf("<rows"), content.indexOf("</rows>")+7);
		content = content.replaceAll("<cell><span>", "<cell><![CDATA[");
		content = content.replaceAll("</span></cell>", "]]></cell>");
		writer.append(content);
		return writer.toString();
	}

	/**
	 * Give a chance to configure the provider.
	 * 
	 * @param dataProvider
	 */
	protected void configureSort() {
		SortOrder sortOrder = getSortOrder();
		String propertyPath = getSortColumn();
		if(propertyPath != null) {
			this.sortInfo = new SortInfo(propertyPath, sortOrder);
		} else {
			this.sortInfo = null;
		}
	}	
	
	private SortOrder getSortOrder() {
		try {
			return SortOrder.valueOf(WebRequestCycle.get().getRequest().getParameter("sord"));
		} catch (Exception e) {
			return SortOrder.asc;
		}
	}
	
	private String getSortColumn() {
		try {
			return WebRequestCycle.get().getRequest().getParameter("sidx");
		} catch (Exception e) {
			return null;
		}
	}
	
	public IDataProvider<B> getDataProvider() {
		return dataProvider;
	}

	public void setDataProvider(IDataProvider<B> dataProvider) {
		this.dataProvider = dataProvider;
	}

	public GridModel<B> getGridModel() {
		return gridModel;
	}

	public void setGridModel(GridModel<B> gridModel) {
		this.gridModel = gridModel;
	}

	public B getSearchBean() {
		return searchBean;
	}

	public void setSearchBean(B searchBean) {
		this.searchBean = searchBean;
	}

	public SortInfo getSortInfo() {
		return sortInfo;
	}

	public void setSortInfo(SortInfo sortInfo) {
		this.sortInfo = sortInfo;
	}

	public String[] getSearchFields() {
		return searchFields;
	}

	public void setSearchFields(String[] searchFields) {
		this.searchFields = searchFields;
	}

	public List<IModel<B>> getModels() {
		return models;
	}

}
