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
package com.wiquery.plugins.jqgrid.experiment;

import java.io.BufferedOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.wicket.RequestCycle;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.extensions.markup.html.repeater.data.sort.ISortState;
import org.apache.wicket.extensions.markup.html.repeater.data.sort.ISortStateLocator;
import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.model.IModel;
import org.apache.wicket.protocol.http.BufferedWebResponse;
import org.apache.wicket.protocol.http.WebRequest;
import org.apache.wicket.protocol.http.WebRequestCycle;
import org.apache.wicket.protocol.http.WebResponse;

import com.wiquery.plugins.jqgrid.model.GridModel;
import com.wiquery.plugins.jqgrid.model.IColumn;
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
		configureSort();
		int rows = getNumberOfRows();
		int page = getCurrentPage();
		long records = totalRecords(getSearchBean(),this.sortInfo, getSearchFields());
		int start = rows*(page-1);
		long totalPages = (records/rows)+1;
		// READ the encoding form request (this might cause grid not rendering data on IE).
		String encoding = ((WebRequest)(WebRequestCycle.get().getRequest())).getHttpServletRequest().getCharacterEncoding();
		//TODO: write directly to the output stream?
		StringBuffer writer = new StringBuffer();
		writer.append("<?xml version='1.0' encoding='");
		writer.append(encoding);
		writer.append("'?>");
		writer.append("<rows>");
		writer.append("<page>");
		writer.append(page);
		writer.append("</page>");
		writer.append("<total>");
		writer.append(totalPages);
		writer.append("</total>");
		writer.append("<records>");
		writer.append(records);;		
		writer.append("</records>");		
		int row = 1;
		Iterator<? extends B> it = getRows(start, rows, getSearchBean(), sortInfo, getSearchFields());
		models.clear();
		while(it.hasNext()) {
			B bean = it.next();
			//TODO: cache them and call detach after rendering.
			IModel<B> model = dataProvider.model(bean);
			models.add(model);
			writer.append("<row id=\"");
			writer.append("row"+row);
			writer.append("\">");
		    int column = 1;
		    for(IColumn<B> columnModel: this.gridModel.getColumnModels()) {
		    	// warp contents in a CDATA.
		    	writer.append("<cell><![CDATA[");	
		    	writer.append(columnModel.renderCell(row,column,  model));
		    		
		    	writer.append("]]></cell>");			     
		    }
		    writer.append("</row>");
		    row++;
		}				
		writer.append("</rows>");
		dataProvider.detach();
		for(IModel<B> model: models) {
			model.detach();
		}
		AjaxRequestTarget target = new AjaxRequestTarget(dataPanel.getPage());
		dataPanel.setOutputMarkupId(true);
		dataPanel.setVisible(true);
		target.addComponent(dataPanel);
		target.respond(RequestCycle.get());
		final BufferedWebResponse response = (BufferedWebResponse)RequestCycle.get().getResponse();		
		dataPanel.setVisible(false);
		String content = response.toString();
		content = content.substring(content.indexOf("<rows"), content.indexOf("</rows>")+7);
		System.out.println(content);
		
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
		/*
		getQuery().clearOrders();
		IOrder<B> order = Order.des(propertyPath);
		if(sortOrder.equals(SortOrder.asc)) {
			order = Order.asc(propertyPath);			
		}
		getQuery().addOrder(order);
		int rows = getNumberOfRows(ServletActionContext.getRequest());
		int page = getCurrentPage(ServletActionContext.getRequest());
		getQuery().setFirstResult(rows*(page-1));
		getQuery().setMaxResults(rows);
		*/
	}
	
	
	private Iterator<? extends B> getRows(int start, int rows, B searchBean, SortInfo sortInfo, String[] searchFields) {
		if(this.dataProvider instanceof ISortStateLocator) {
			ISortStateLocator locator = (ISortStateLocator)dataProvider;
			ISortState sortState = locator.getSortState();
			if(sortState != null) {
				if(sortInfo.getSortOrder().equals(SortOrder.asc))
					sortState.setPropertySortOrder(sortInfo.getProperty(), ISortState.ASCENDING);
				else if(sortInfo.getSortOrder().equals(SortOrder.desc))
					sortState.setPropertySortOrder(sortInfo.getProperty(), ISortState.DESCENDING);
				else 
					sortState.setPropertySortOrder(sortInfo.getProperty(), ISortState.ASCENDING);
				locator.setSortState(sortState);
			}			
		}
		return this.dataProvider.iterator(start, rows);
	}
	
	private int totalRecords(B searchBean, SortInfo sortInfo, String[] searchFields) {
		if(this.dataProvider instanceof ISortStateLocator) {
			ISortStateLocator locator = (ISortStateLocator)dataProvider;
			ISortState sortState = locator.getSortState();
			if(sortState != null) {
				if(sortInfo.getSortOrder().equals(SortOrder.asc))
					sortState.setPropertySortOrder(sortInfo.getProperty(), ISortState.ASCENDING);
				else if(sortInfo.getSortOrder().equals(SortOrder.desc))
					sortState.setPropertySortOrder(sortInfo.getProperty(), ISortState.DESCENDING);
				else 
					sortState.setPropertySortOrder(sortInfo.getProperty(), ISortState.ASCENDING);
				locator.setSortState(sortState);
			}
		}
		return this.dataProvider.size();
	}
	
	private int getCurrentPage() {
		try {
			return Integer.parseInt(WebRequestCycle.get().getRequest().getParameter("page"));
		} catch (Exception e) {
			return 10;
		}
	}
	
	private int getNumberOfRows() {
		try {
			return Integer.parseInt(WebRequestCycle.get().getRequest().getParameter("rows"));
		} catch (Exception e) {
			return 10;
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
