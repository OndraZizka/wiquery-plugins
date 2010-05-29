/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package com.wiquery.plugins.jqgrid.experiment;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.MissingResourceException;

import org.apache.commons.lang.StringUtils;
import org.apache.wicket.ajax.AbstractDefaultAjaxBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.behavior.AbstractAjaxBehavior;
import org.apache.wicket.extensions.markup.html.repeater.data.grid.ICellPopulator;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.model.IModel;
import org.apache.wicket.protocol.http.WebRequestCycle;
import org.odlabs.wiquery.core.commons.IWiQueryPlugin;
import org.odlabs.wiquery.core.commons.WiQueryResourceManager;
import org.odlabs.wiquery.core.javascript.JsQuery;
import org.odlabs.wiquery.core.javascript.JsStatement;
import org.odlabs.wiquery.ui.core.CoreUIJavaScriptResourceReference;
import org.odlabs.wiquery.ui.draggable.DraggableJavaScriptResourceReference;
import org.odlabs.wiquery.ui.droppable.DroppableJavaScriptResourceReference;
import org.odlabs.wiquery.ui.resizable.ResizableJavaScriptResourceReference;
import org.odlabs.wiquery.ui.sortable.SortableJavaScriptResourceReference;

import com.wiquery.plugins.jqgrid.Resources;
import com.wiquery.plugins.jqgrid.component.event.IAjaxGridEvent;
import com.wiquery.plugins.jqgrid.component.event.IGridEvent;
import com.wiquery.plugins.jqgrid.component.event.IGridEvent.GridEvent;
import com.wiquery.plugins.jqgrid.model.GridModel;
import com.wiquery.plugins.jqgrid.model.IColumn;

/**
 * This component is a Wicket wrapper for jqgrid (version 3.6) JS widget.
 * 
 * http://www.trirand.com/blog/jqgrid/jqgrid.html
 * 
 * Usage:
 * 
 * TODO: place an example here
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 * @author steve.mactaggart (contributor)
 * @author samzilverberg (contributor)
 */
public class Grid<B extends Serializable> extends Panel  implements IWiQueryPlugin {
	
	private static final long serialVersionUID = 1L;

	public static final String TEMPLATE = "grid";
  
	private WebMarkupContainer grid; 
	
	private DocumentResourceListener navigator;
	
	private IDataProvider<B> dataProvider;
	
	private AbstractAjaxBehavior gridContext;
	
	private GridXMLData<B> gridData;
	
	private GridModel<B> model;
	
	private GridDataPanel<B> data;
	
	private static String[] TEXT_PROPERTIES = {"recordtext", "emptyrecords", "loadtext", "pgtext"};
	
	private Map<IGridEvent.GridEvent, IGridEvent<B>> gridEvents  = new Hashtable<IGridEvent.GridEvent, IGridEvent<B>>();
	
	public Grid(String id, GridModel<B> gridModel, IDataProvider<B> dataProvider) {
		super(id, gridModel);
		this.dataProvider = dataProvider;
		setOutputMarkupId(true);
		
		//This behavior is used as a call-back for grid events.
		gridContext = new AbstractDefaultAjaxBehavior() {
			
			private static final long serialVersionUID = 1L;

			@SuppressWarnings("unchecked")
			@Override
			protected void respond(AjaxRequestTarget target) {
				// look for the request parameter identifying the event.
				String gridEventName = WebRequestCycle.get().getRequest().getParameter("gridEvent");
				if(!StringUtils.isEmpty(gridEventName)) {
					// locate the event.
					IGridEvent gridEvent = gridEvents.get(GridEvent.valueOf(gridEventName));
					if(gridEvent != null && gridEvent instanceof IAjaxGridEvent) {
						//if the event exists and is a AJAX event do the onEven!
						IAjaxGridEvent ajaxGridEvent = (IAjaxGridEvent)gridEvent;
						// set the grid if it is IGridAware.
						if(ajaxGridEvent instanceof IGridAware<?>) {							
							IGridAware<B> aware = (IGridAware<B>) ajaxGridEvent;
							aware.setGrid(Grid.this);
						}
						try {
							ajaxGridEvent.onEvent(target);
						} finally {
							// unset the grid
							if(ajaxGridEvent instanceof IGridAware<?>) {							
								IGridAware<B> aware = (IGridAware<B>) ajaxGridEvent;
								aware.setGrid(null);
							}
						}
					}
				}
			}
		};
		
		add(gridContext);
		
		List<ICellPopulator<B>> populators = new ArrayList<ICellPopulator<B>>();
		for(final IColumn<B> column: gridModel.getColumnModels()){
			ICellPopulator<B> cellPopulator = column.getCellPopulator();
			if(cellPopulator != null) {
				populators.add(cellPopulator);
			} else {
				populators.add(new ICellPopulator<B>() {
					
					private static final long serialVersionUID = 1L;

					@Override
					public void populateItem(Item<ICellPopulator<B>> cellItem, String componentId, IModel<B> rowModel) {
						cellItem.add(new Label(componentId, column.renderCell(1, 2, rowModel)));
					}
					
					@Override
					public void detach() {
						
					}
				});
			}
		}
		data = new GridDataPanel<B>("data", populators, dataProvider);
		data.setVisible(false);
		add(data);
	}
	
	@Override
	protected void onBeforeRender() {
		if(grid == null) {
			grid = new WebMarkupContainer("grid");
			grid.setOutputMarkupId(true);
			addOrReplace(grid);			
			// navigator will also be used to stream back grids data
			gridData = new GridXMLData<B>(dataProvider, getGridModel(), null, data);
			navigator = new DocumentResourceListener("navigator", gridData);
			navigator.setOutputMarkupId(true);
			addOrReplace(navigator);
		}
		super.onBeforeRender();
	}

	private String generateStript() {	  
		StringBuffer sb = new StringBuffer();		
		sb.append("{");
		if(getGridModel().isSortable()) {
			sb.append("sortable: true,"); 
		}
		
		if(getGridModel().getScroll() != null) {
			sb.append("scroll: ");
			sb.append(getGridModel().getScroll());
			sb.append(",");
		}
		
		sb.append("url:'"+this.navigator.getURL()+"',");
		// datatype parameter defines the format of data returned from the server
		sb.append("datatype: '");
		sb.append(getGridModel().getTransferProtocol().name());
		sb.append("',");
		// colNames parameter is a array in which we describe the names
		// in the columns. This is the text that apper in the head of the grid.
		sb.append("colNames:[");
		for(IColumn<?> columnModel: getGridModel().getColumnModels()) {
			sb.append("'");
			// get title from models.
			String name = columnModel.getTitleModel().getObject();
			sb.append(name);
			sb.append("',");
		}	  	
		sb.append("],");
		 
		// colModel array describes the model of the column.
		// name is the name of the column,
		// index is the name passed to the server to sort data
		// note that we can pass here numbers too.
		// width is the width of the column
		// align is the align of the column (default is left)
		// sortable defines if this column can be sorted (default true)
		sb.append("colModel:[");
		for(IColumn<?> columnModel: getGridModel().getColumnModels()) {
			sb.append("{");
			sb.append("name:'");		 
			sb.append(this.getId()+columnModel.getPropertyPath());		  
			sb.append("',index:'");
			sb.append(columnModel.getSortPath());
			sb.append("', width:");
			sb.append(columnModel.getWidth());
			sb.append(", sortable:");
			sb.append(columnModel.isSortable());
			sb.append(", resizable:");
			sb.append(columnModel.isResizable());
			if(columnModel.getAlignment()!= null) {
				sb.append(", align:'");
				sb.append(columnModel.getAlignment().name().toLowerCase());
				sb.append("'");
			}
			sb.append("},");			  
		  }	
		  sb.append("],");
		  // pager parameter define that we want to use a pager bar
		  // in this case this must be a valid html element.
		  // note that the pager can have a position where you want
		  sb.append("pager: jQuery('#"+this.navigator.getMarkupId()+"'),");
		  // rowNum parameter describes how many records we want to
		  // view in the grid. We use this in example.php to return
		  // the needed data.
		  if((getGridModel().getRowList() != null) && getGridModel().getRowList().length > 0) {
			  if(getGridModel().getRowNum()>0 && inArray(getGridModel().getRowList(), getGridModel().getRowNum())) {				 
				  sb.append("rowNum: ");
				  sb.append(getGridModel().getRowNum());
				  sb.append(",");
			  } else {
				  sb.append("rowNum:");
				  sb.append(getGridModel().getRowList()[0]);
				  sb.append(",");  
			  }			  		  
			  // rowList parameter construct a select box element in the pager
			  //in which we can change the number of the visible rows
			  sb.append("rowList:[");
			  for(int i: getGridModel().getRowList()) {
				  sb.append(i);
				  sb.append(",");
			  }
			  sb.append("],");
		  }  else if(getGridModel().getRowNum()>0) {
			  sb.append("rowNum: ");
			  sb.append(getGridModel().getRowNum());
			  sb.append(",");	  
		  }
		  // path to mage location needed for the grid
		  sb.append("imgpath: 'themes/sand/images',");
		  	// sortname sets the initial sorting column. Can be a name or number.
		  	// this parameter is added to the url
		  sb.append("altRows: ");
		  sb.append(getGridModel().isAlternateRows());
		  sb.append(",");
		  
		  if (getGridModel().getMType() != null) {
			  sb.append("mtype: '");
			  sb.append(getGridModel().getMType());
			  sb.append("',");
		  }
		  
		  if(getGridModel().getHeight() != null) {
			  sb.append("height: '");
			  sb.append(getGridModel().getHeight());
			  sb.append("',");
		  }
		  
		  if(getGridModel().getWidth() != null) {
			  sb.append("width: ");
			  sb.append(getGridModel().getWidth());
			  sb.append(",");
		  }
		  
		  if(!getGridModel().isShrinkToFit()) {
			  sb.append("shrinkToFit: ");
			  sb.append(getGridModel().isShrinkToFit());
			  sb.append(",");
		  }
		  
		  if(getGridModel().isRownumbers()) {
			  sb.append("rownumbers: ");
			  sb.append(getGridModel().isRownumbers());
			  sb.append(",");		   
			  
			  sb.append("rownumWidth: ");
			  sb.append(getGridModel().getRownumWidth());
			  sb.append(",");
			  
		  }		  		  		   
		  
		  
		  if(getGridModel().isAutowidth()) {
			  sb.append("autowidth: ");
			  sb.append(getGridModel().isAutowidth());
			  sb.append(",");		   
		  }
		  
		  if(getGridModel().isMultiselect()) {
			  sb.append("multiselect: ");
			  sb.append(getGridModel().isMultiselect());
			  sb.append(",");
		  }
		  
		  if(getGridModel().isMultiboxonly()) {
			  sb.append("multiboxonly: ");
			  sb.append(getGridModel().isMultiboxonly());
			  sb.append(",");
		  }
		  
		  
		  if(!StringUtils.isEmpty(getGridModel().getMultikey())) {
			  sb.append("multikey: ");
			  sb.append(getGridModel().getMultikey());
			  sb.append(",");
		  }
		  
		  
		  
		  if(!getGridModel().isHidegrid()) {
			  sb.append("hidegrid: ");
			  sb.append(getGridModel().isHidegrid());
			  sb.append(",");		   
		  }
		  
		  if(!StringUtils.isEmpty(getGridModel().getResizeclass())) {
			  sb.append("resizeclass: '");
			  sb.append(getGridModel().getResizeclass());
			  sb.append("',");		   
		  }
		  
		  // we add the events registered by the grid.
		  String url = gridContext.getCallbackUrl().toString();
		  for(GridEvent event: gridEvents.keySet()) {
			  IGridEvent<B> gridEvent = gridEvents.get(event);
			  sb.append(event.name());
			  sb.append(": ");
			  sb.append(gridEvent.statement(url));
			  sb.append("},\n");
		  }
		  
		  IColumn<?> sortModel = getGridModel().getInitialSort();
		  sb.append("sortname: '");
		  if(sortModel != null)
			  sb.append(sortModel.getSortPath());	  
		  sb.append("',");	  	  
		
		  // resource related properties.
		  // read them from within the Wicket layer
		  // so that they can be configured the same as 
		  // other Wicket components
		  for(String property: TEXT_PROPERTIES) {
			  String text = getResource(property); 
			  if(StringUtils.isNotEmpty(text)) {
				  sb.append(property);
				  sb.append(": '");
				  sb.append(text.trim());
				  sb.append("',");
			  }
		  }
		  
		  
		  sb.append("viewrecords: ");
		  sb.append(getGridModel().isViewrecords());
		  sb.append(",");
		  
		  if(getGridModel().getRecordpos() != null) {			   
			  sb.append("recordpos: '");
			  sb.append(getGridModel().getRecordpos());
			  sb.append("',");
		  } 
		  
		  if(getGridModel().getPagerpos() != null) {			   
			  sb.append("pagerpos: '");
			  sb.append(getGridModel().getPagerpos());
			  sb.append("',");
		  }
		  
		  if(!getGridModel().isPginput()) {			   
			  sb.append("pginput: ");
			  sb.append(getGridModel().isPginput());
			  sb.append(",");
		  }
			  
		  //sets the sorting order. Default is asc. This parameter is added to the url
		  sb.append("sortorder: \"");
		  sb.append(getGridModel().getSortOrder().name());
		  sb.append("\",");
		  
		  if (getGridModel().getCaption() != null && StringUtils.isNotEmpty(getGridModel().getCaption().getObject())) {
			  sb.append("caption: \"");
			  //Got it form the resources
			  String caption = getGridModel().getCaption().getObject();
			  sb.append(caption);
			  sb.append("\"");
		  }
		  
		  //sb.append("});");
		  sb.append("}");
		  //sb.append("});");
		  return sb.toString();	  
	}
	
	private boolean inArray(int[] numbers, int number) {
		if(numbers == null || numbers.length == 0)
			return false;
		for(int n: numbers) {
			if(n == number)
				return true;
		}
		return false;
	}
	
	private String getResource(String key) {
		try {
			return getString(key);
		} catch (MissingResourceException e) {
			return null;
		}
		
	}
	/**
	 * Adds an event to the grid.
	 * 
	 * @param event
	 * @return
	 */
	public Grid<B> addEvent(IGridEvent<B> event) {
		gridEvents.put(event.getGridEvent(), event);
		return this;
	}
	
	/**
	 * Remove an event.
	 * 
	 * @param event
	 * @return
	 */
	public Grid<B> removeEvent(IGridEvent<B> event) {
		gridEvents.remove(event.getGridEvent());
		return this;
	}

	public GridXMLData<B> getGridData() {
		return gridData;
	}

	public void setGridData(GridXMLData<B> gridData) {
		this.gridData = gridData;
	}

	@SuppressWarnings("unchecked")
	public GridModel<B> getGridModel() {
		if(model == null) {
			model = (GridModel<B>)getDefaultModel();
		}
		return model;
	}

	public void contribute(WiQueryResourceManager wiQueryResourceManager) {
		wiQueryResourceManager.addCssResource(Resources.CSS_JQUERY_JQGRID);	
		
		
	
		wiQueryResourceManager.addJavaScriptResource(CoreUIJavaScriptResourceReference.get());
		wiQueryResourceManager.addJavaScriptResource(ResizableJavaScriptResourceReference.get());
		wiQueryResourceManager.addJavaScriptResource(DraggableJavaScriptResourceReference.get());	
		wiQueryResourceManager.addJavaScriptResource(DroppableJavaScriptResourceReference.get());	
		wiQueryResourceManager.addJavaScriptResource(SortableJavaScriptResourceReference.get());
		
	
		wiQueryResourceManager.addJavaScriptResource(Resources.JS_JQUERY_LOC_EN);
		wiQueryResourceManager.addJavaScriptResource(Resources.JS_JQUERY_JQGRID);
		wiQueryResourceManager.addJavaScriptResource(Resources.JS_JQUERY_CONTEXT_MENU);
	}

	public JsStatement statement() {
		return new JsQuery(this.grid).$().chain("jqGrid",generateStript());
	}

}
