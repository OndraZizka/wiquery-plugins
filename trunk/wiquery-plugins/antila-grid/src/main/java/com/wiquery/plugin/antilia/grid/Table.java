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
package com.wiquery.plugin.antilia.grid;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.Component;
import org.apache.wicket.ResourceReference;
import org.apache.wicket.ajax.AbstractDefaultAjaxBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.IAjaxIndicatorAware;
import org.apache.wicket.extensions.markup.html.repeater.data.sort.ISortState;
import org.apache.wicket.extensions.markup.html.repeater.data.sort.ISortStateLocator;
import org.apache.wicket.feedback.IFeedback;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.RefreshingView;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.IComponentInheritedModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.protocol.http.WebRequestCycle;
import org.odlabs.wiquery.core.commons.IWiQueryPlugin;
import org.odlabs.wiquery.core.commons.WiQueryResourceManager;
import org.odlabs.wiquery.core.javascript.JsStatement;
import org.odlabs.wiquery.ui.commons.WiQueryUIPlugin;

import com.wiquery.plugins.antilia.grid.model.IColumn;
import com.wiquery.plugins.antilia.grid.resources.DefaultStyle;

/**
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
@WiQueryUIPlugin
public class Table<E extends Serializable> extends Panel implements IPageableComponent<E>, IPageableNavigationListener, IWiQueryPlugin, IAjaxIndicatorAware {

	private static final long serialVersionUID = 1L;
	
	public static final int AUTO = -1;
	
	private ITableModel<E> tableModel;	
	
	/**
	 * Component used to display navigation errors
	 */
	private IFeedback feedback;	
			
	private FirstColumnModel firstColumnModel;
		
	private List<WebMarkupContainer> rowCheckboxes = new ArrayList<WebMarkupContainer>();
			
	private List<IPageableNavigationListener> navigationListeners = new ArrayList<IPageableNavigationListener>();
	
	
	/**
	 * Flag to set first column re-sizable or not.
	 */
	private boolean firstColumnResizable = true;
	
	/**
	 * Flag to set all columns re-sizable or not.
	 */
	private boolean columnsResizable = true;
	
	/**
	 * This variable is needed t fix a problem with drag and drop not working for IE
	 */
	private int rendringCount = 0;
	
	
	/**
	 * True to keep selection when navigating (the default). False to clear it.
	 */
	private boolean keepSelectionOnNavigation = true;
	
	/**
	 * Flag to disable/enable column draggability.
	 */
	private boolean dragableColumns = true;

	/**
	 * The page size.
	 */
	private int pageSize = 10;
	
	/**
	 * The current page;
	 */
	private int currentPage = 0;

	private int size = -1;
	
	private int numberOfPages;
	
	/**
	 * The data provider used to retrieve grid's data.
	 */
	private IDataProvider<E> dataProvider;
	
	private List<IModel<E>> rowModels;
	
	/**
	 * Table height.
	 */
	private int height = AUTO;
	
	/**
	 * Table width
	 */
	private int width = AUTO;
	
	/**
	 * AJAX behavior used to process some of the client side event on
	 * the server side.
	 */
	private AbstractDefaultAjaxBehavior tableEventContext;
	
	/**
	 * Constructor.
	 * 
	 * @param id
	 */
	public Table(String id, ITableModel<E> tableModel, IDataProvider<E> dataProvider)  {
		super(id);
		this.dataProvider = dataProvider;
		setOutputMarkupId(true);		
		add(tableEventContext = new AbstractDefaultAjaxBehavior() {
			
			private static final long serialVersionUID = 1L;

			@Override
			protected void respond(AjaxRequestTarget target) {
				if(target != null) {
					String targetId = WebRequestCycle.get().getRequest().getParameter("targetId");
					if(targetId != null && targetId.equals("resize")) {
						try {
							int width = Integer.parseInt(WebRequestCycle.get().getRequest().getParameter("sourceId"));
							int column = Integer.parseInt(WebRequestCycle.get().getRequest().getParameter("number"));
							if(column == 0) {
								Table.this.getFirstColumnModel().setWidth(width);
							} else {							
								Table.this.getTableModel().getColumnModel(column-1).setWidth(width);
							}																
						} catch (NumberFormatException e) {
							// do nothing
						} 
						return;							
					}
				}
			}
		});
		this.tableModel = tableModel;
		this.firstColumnModel = new FirstColumnModel(70);
	}
	
	
	@Override
	protected void onBeforeRender() {			
		rendringCount++;
		if(size == -1) {
			size = dataProvider.size();
			numberOfPages = (size/pageSize)+((size%pageSize==0)?0:1); 
		}
		
		WebMarkupContainer tRoot = new WebMarkupContainer("tRoot");
		addOrReplace(tRoot);
		tRoot.add(new AttributeModifier("style",new AbstractReadOnlyModel<String>(){
			
			private static final long serialVersionUID = 1L;

			@Override
			public String getObject() {
				String height = "auto";
				if(Table.this.height >0)
					height = Table.this.height+"px;";
				String width = "auto;";
				if(Table.this.width >0)
					width = Table.this.width+"px;";
				
				return new StringBuilder()
						.append("position: relative; width: ").append(width)
						.append(";height:")
						.append(height)
						.toString();				
			}
		}));
		 
		WebMarkupContainer tHead = new WebMarkupContainer("tHead");
		tRoot.addOrReplace(tHead);
		tHead.add(new AttributeModifier("style",new AbstractReadOnlyModel<String>(){
			
			private static final long serialVersionUID = 1L;

			@Override
			public String getObject() {
				String width = "auto;";
				if(Table.this.width >0)
					width = Table.this.width+2+"px;";
				
				return new StringBuilder()
						.append("width:").append(width)
						.toString();				
			}
		}));
		
		tHead.addOrReplace(newTableHeader("header"));
		

		WebMarkupContainer tBody = new WebMarkupContainer("tBody") {
			
			private static final long serialVersionUID = 1L;

			@Override
			public String getMarkupId() {
				return Table.this.getMarkupId()+"_tBody";
			}
			
			@Override
			public boolean isVisible() {
				return size > 0;
			}
		};
		tBody.setOutputMarkupId(true);
		tBody.add(new AttributeModifier("style",new AbstractReadOnlyModel<String>(){
			
			private static final long serialVersionUID = 1L;

			@Override
			public String getObject() {
				String height = "auto;";
				if(Table.this.height >0)
					height = (Table.this.height - 28)+"px;";
				String width = "auto;";
				if(Table.this.width >0)
					width = (Table.this.width)+"px;";
				
				return new StringBuilder()
						.append("position: relative; width: ").append(width)
						.append("; overflow: hidden; height: ")
						.append(height)
						.append(" vertical-align: top")
						.toString();				
			}
		}));
		
		tRoot.addOrReplace(tBody);
		
		WebMarkupContainer tTHeader = new WebMarkupContainer("tTHeader") {
			private static final long serialVersionUID = 1L;

			@Override
			public String getMarkupId() {
				return Table.this.getMarkupId()+"_tTH";
			}
		};
		tTHeader.setOutputMarkupId(true);
		tBody.add(tTHeader);
		
		WebMarkupContainer tNoRecords = new WebMarkupContainer("tNoRecords") {
			
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isVisible() {
				return size <= 0;
			}
			
		};
		tNoRecords.add(new AttributeModifier("style",new AbstractReadOnlyModel<String>(){
			
			private static final long serialVersionUID = 1L;

			@Override
			public String getObject() {
				String width = "auto;";
				if(Table.this.width >0)
					width = Table.this.width+"px;";
				
				return new StringBuilder()
						.append("width: ").append(width)
						.append(";")
						.toString();				
			}
		}));
		
		tRoot.add(tNoRecords);
		
		WebMarkupContainer tTBody = new WebMarkupContainer("tTBody") {
			private static final long serialVersionUID = 1L;

			@Override
			public String getMarkupId() {
				return Table.this.getMarkupId()+"_tTB";
			}						
		};
		tTBody.add(new AttributeModifier("onscroll", new AbstractReadOnlyModel<String>() {
			
			private static final long serialVersionUID = 1L;

			@Override
			public String getObject() {
				return new StringBuilder()
				.append("var  t = document.getElementById('")
				.append(Table.this.getMarkupId()+"_tTH")
				.append("'); t.style.left=(-this.scrollLeft)+'px'").toString();
			}
		}));
		tTBody.setOutputMarkupId(true);
		tBody.add(tTBody);
		
		// add table header
		tTHeader.addOrReplace(getHeaderRows("hcols"));
			
		// add table body
		tTBody.addOrReplace(getBodyRows("rows"));		
		
		WebMarkupContainer tFoot = new WebMarkupContainer("tFoot");
		tFoot.add(new AttributeModifier("style",new AbstractReadOnlyModel<String>(){
			
			private static final long serialVersionUID = 1L;

			@Override
			public String getObject() {
				String width = "auto;";
				if(Table.this.width >0)
					width = "100%;";
				
				return new StringBuilder()
						.append("width:").append(width)
						.toString();				
			}
		}));
		tRoot.addOrReplace(tFoot);
		super.onBeforeRender();
	}
	
	
	public JsStatement statement() {
		String tableId =  Table.this.getMarkupId()!=null?Table.this.getMarkupId():"table";
		StringBuffer sb = new StringBuffer();
		sb.append("new Table('" + tableId + "','");
		sb.append(tableEventContext.getCallbackUrl()+ "',");
		sb.append("new Array(");
		Iterator<IModel<E>> it = getCurrentPage();
		int i=0;
		while(it.hasNext()) {
			boolean selected = isSelected(it.next().getObject());										
			sb.append("new Row('");
			sb.append(tableId);
			sb.append("',");
			sb.append(i);
			sb.append(",");
			sb.append(selected);
			sb.append(")");
			if(it.hasNext())
				sb.append(",");
			i++;					
		}		
		sb.append(")");
		sb.append("," + (Table.this.getTableModel().getColumns()+1));
		sb.append("," + (Table.this.getRendringCount()));
		sb.append("," + "false");
		sb.append("," + Table.this.isDragableColumns());
		sb.append(",'");
		sb.append(getString("antilia.grid.loading"));
		sb.append("'");
		sb.append(");");
		JsStatement jsStatement = new JsStatement().append(sb.toString());
		return jsStatement;
	}
	
	
	public void contribute(WiQueryResourceManager wiQueryResourceManager) {
		wiQueryResourceManager.addCssResource(getTableCSS());
		wiQueryResourceManager.addJavaScriptResource(DefaultStyle.JS_COMMON);
		wiQueryResourceManager.addJavaScriptResource(DefaultStyle.JS_TABLE);
	}
	

	protected ResourceReference getTableCSS() {		
		return DefaultStyle.CSS_TABLE;
	}
	
	protected RefreshingView<E> getHeaderRows(String id) {
		return new HeaderRows(id, this);
	}
	
	protected RefreshingView<E> getBodyRows(String id) {
		return new BodyRows(id, this);
	}
	
	
	private class HeaderRows extends RefreshingView<E> {

		private static final long serialVersionUID = 1L;
		
		private Table<E> table;
		
		public HeaderRows(String id, Table<E> table) {
			super(id);
			this.table = table;
		}

		@Override
		protected Iterator<IModel<E>> getItemModels() {
			ITableModel<E> tableModel = getTable().getTableModel();		
			List<IModel<E>> models = new ArrayList<IModel<E>>();
			models.add(new Model<E>(null));
			Iterator<IColumn<E>> it =  tableModel.getColumnModels();
			while(it.hasNext()) {
				IModel<E> model = it.next();
				models.add(model);
			}
			return models.iterator();
		}
		
		@Override
		protected Item<E> newItem(String id, int index, final IModel<E> model) {
			Item<E> item = super.newItem(id, index, model);
			String tableId = table.getMarkupId();
			if(model instanceof IColumn<?>) {		
				final IColumn<E> column = (IColumn<E>) model;
				item.add(new AttributeModifier("id", new Model<String>(tableId+"_tdh_"+index)));
				item.add(new AttributeModifier("width", true, new Model<String>() {
					
					private static final long serialVersionUID = 1L;
	
					@Override
					public String getObject() {
						String width = column.getWidth()+"px";
						return width;
					}
					
				}));
				item.add(new AttributeModifier("onmouseover", true, new Model<String>() {
					
					private static final long serialVersionUID = 1L;
	
					@Override
					public String getObject() {
						if(column.isSortable())
							return "this.className='ui-state-default ui-state-hover defaultHeaderOver sortableCol';";
						return "this.className='ui-state-default ui-state-hover defaultHeaderOver';";
					}
					
				}));
				item.add(new AttributeModifier("onmouseout", true, new Model<String>() {
					
					private static final long serialVersionUID = 1L;
	
					@Override
					public String getObject() {
						if(column.isSortable())
							return "this.className='ui-state-default defaultHeader sortableCol';";
						return "this.className='ui-state-default defaultHeader';";
					}
					
				}));
				//onmouseover="this.className='ui-state-default ui-state-hover defaultHeaderOver';" 
				//onmouseout="this.className='ui-state-default defaultHeader';"
			} else {
				item.add(new AttributeModifier("id", new Model<String>(tableId+"_tdh_0")));
				item.add(new AttributeModifier("width", new Model<String>() {
					
					private static final long serialVersionUID = 1L;
	
					@Override
					public String getObject() {
						String width = Table.this.getFirstColumnModel().getWidth()+ "px";
						return width;
					}
					
				}));
			}
			return item;
		}

		@Override
		protected void populateItem(Item<E> item) {			
			IModel<E> model = (IModel<E>)item.getModel();
			if(model instanceof IColumn<?>) {
				IColumn<E> column = (IColumn<E>)model;
				//item.add(Table.this.newHeaderCell("hcell", item.getIndex(), getTable(), columnModel, columnModel.getTableModel().getBeanClass()));
				//item.add(columnModel.populateHeaderCell("hcell", item.getIndex(), getTable()));
				column.populateHeaderCell(item, "hcell", item.getIndex(), getTable());
			} else {
				item.add(Table.this.newFirstHeaderCell("hcell", item.getIndex(), getTable()));
			}
		}
		
		private Table<E> getTable() {
			return table;
		}
		
	}
	
	protected void addRowCheckBox(WebMarkupContainer rowCheckBox) {
		rowCheckboxes.add(rowCheckBox);
	}
	
	protected void clearRowCheckBoxes() {
		rowCheckboxes.clear();
	}
	
	protected Iterator<WebMarkupContainer> getRowCheckBoxes() {
		return rowCheckboxes.iterator();
	}
	
	/**
	 * Called when a row is clicked.
	 * @param target
	 * @param row
	 */
	protected void onRowClickedEvent(AjaxRequestTarget target, int row, E bean, boolean selected) {
		
	}
		
	private class BodyRows extends RefreshingView<E> {

		private static final long serialVersionUID = 1L;
	
		private List<IModel<E>> models;
		
		private Table<E> table;
		
		
		public BodyRows(String id, Table<E> table) {
			super(id);
			this.table = table;			
		}
		
		
		private void initialize() {
			Table.this.clearRowCheckBoxes();
			models = new ArrayList<IModel<E>>();
			ITableModel<E> tableModel = getTable().getTableModel();
			if(tableModel == null)
				return;	
			Iterator<IModel<E>> it = getTable().getCurrentPage();
			while(it.hasNext()) {
				IModel<E> object = it.next();
				models.add(tableModel.newModel(object));				
			}
		}

		@Override
		protected Item<E> newItem(String id, int index, IModel<E> model) {
			return new RowItem<E>(id, index, (IComponentInheritedModel<E>)model, table);
		}
		
		@Override
		protected Iterator<IModel<E>> getItemModels() {
			initialize();
			return models.iterator();
		}

		@Override
		protected void populateItem(Item<E> item) {		
			ITableModel<E> tableModel = getTable().getTableModel();
			if(tableModel == null)
				return;
			RepeatingView rowView = new RepeatingView("cols");			
			item.add(rowView);
			
			String tableId = table.getMarkupId();
			
			RowItem<E> rowItem = (RowItem<E>)item;
			
			Iterator<IColumn<E>> it = tableModel.getColumnModels();
			E bean = item.getModelObject();
			Item<E> cellItem = new Item<E>(rowView.newChildId(), 1);
			rowView.add(cellItem);
			cellItem.add(newFirstBodyCell("cell", item.getIndex(),getTable(), rowItem));
			if(rowItem.getIndex()==0) {				 
				cellItem.add(new AttributeModifier("id", new Model<String>(tableId+"_tdb_0")));
				cellItem.add(new AttributeModifier("width", true, new AbstractReadOnlyModel<String>() {
					
					private static final long serialVersionUID = 1L;
	
					@Override
					public String getObject() {
						String width = getTable().getFirstColumnModel().getWidth()+"px";
						return width;
					}
					
				}));
				
			}	
			String cssClass = "ui-widget-content tbodycol";
			cellItem.add(new AttributeModifier("class", new Model<String>(cssClass)));
			// Populate the row
			int column = 1;
			while (it.hasNext()) {
				final IColumn<E> columnModel = it.next();
				cellItem = new Item<E>(rowView.newChildId(), 1);				
				rowView.add(cellItem);
				columnModel.populateRowCell(cellItem, "cell", bean, item.getIndex(), column);
				if(rowItem.getIndex()==0) {
					cellItem.add(new AttributeModifier("id", new Model<String>(tableId+"_tdb_"+column)));
					cellItem.add(new AttributeModifier("width", true, new AbstractReadOnlyModel<String>() {
						
						private static final long serialVersionUID = 1L;
	
						@Override
						public String getObject() {
							String width = columnModel.getWidth()+"px";
							return width;
						}
						
					}));
				}
				cssClass = "ui-widget-content tbodycol " + columnModel.getBodyAddionalCssClasses();
				cellItem.add(new AttributeModifier("class", new Model<String>(cssClass)));
				//class="ui-widget-content tbodycol"
				column++;
			}
			
		}
		
		private Table<E> getTable() {
			return table;
		}
		
	}
	
	/**
	 * Creates the table header. Override if you want to provide 
	 * a different header.
	 * 
	 * @param id
	 * @return
	 */
	protected Panel newTableHeader(String id) {
		return new DefaultTableHeader<E>(id, this);
	}
	
	protected WebMarkupContainer newFirstBodyCell(String id, int row, Table<E> table, RowItem<E> item) {
		return new FirstBodyCell<E>(id, row, table, item);
	}
	
	protected  WebMarkupContainer newFirstHeaderCell(String id, int index, Table<E> table) {
		return new FirstHeaderCell<E>(id, index, table);
	}

	
	/**
	 * @return the tableModel
	 */
	public ITableModel<E> getTableModel() {
		return tableModel;
	}
	

	public FirstColumnModel getFirstColumnModel() {
		return firstColumnModel;
	}

	public void setFirstColumnModel(FirstColumnModel firstColumnModel) {
		this.firstColumnModel = firstColumnModel;
	}
	
	public Component getUpdatableComponent() {
		return this;
	}

	/**
	 * @return the rendringCount
	 */
	public int getRendringCount() {
		return rendringCount;
	}

	/**
	 * @param rendringCount the rendringCount to set
	 */
	public void setRendringCount(int rendringCount) {
		this.rendringCount = rendringCount;
	}


	/**
	 * @return the feedback
	 */
	public IFeedback getFeedback() {
		return feedback;
	}

	/**
	 * @param feedback the feedback to set
	 */
	public void setFeedback(IFeedback feedback) {
		this.feedback = feedback;
	}

	public boolean isFirstColumnResizable() {
		return firstColumnResizable;
	}

	public void setFirstColumnResizable(boolean firstColumnResizable) {
		this.firstColumnResizable = firstColumnResizable;
	}

	public boolean isColumnsResizable() {
		return columnsResizable;
	}

	public void setColumnsResizable(boolean columnsResizable) {
		this.columnsResizable = columnsResizable;
	}
	
	public void addNavigationListener(IPageableNavigationListener listener) {
		navigationListeners.add(listener);
	}
	
	public void removeNavigationListener(IPageableNavigationListener listener) {
		navigationListeners.remove(listener);
	}

	public final void onFirstPage(AjaxRequestTarget target) {
		firstPage(target);
		for(IPageableNavigationListener listener: navigationListeners) {
			listener.onFirstPage(target);
		}
	}
	
	/**
	 * Override this method to do something when first page button is clicked.
	 * 
	 * @param target
	 */
	public void firstPage(AjaxRequestTarget target) {
		
	}


	public final void onLastPage(AjaxRequestTarget target) {
		lastPage(target);
		for(IPageableNavigationListener listener: navigationListeners) {
			listener.onLastPage(target);
		}
	}
	
	/**
	 * Override this method to do something when last page button is clicked.
	 * 
	 * @param target
	 */
	public void lastPage(AjaxRequestTarget target) {
		
	}

	public final void onNextPage(AjaxRequestTarget target) {
		nextPage(target);
		for(IPageableNavigationListener listener: navigationListeners) {
			listener.onNextPage(target);
		}
	}
	
	/**
	 * Override this method to do something when next page button is clicked.
	 * 
	 * @param target
	 */
	public void nextPage(AjaxRequestTarget target) {
		
	}

	public final void onPreviousPage(AjaxRequestTarget target) {
		previousPage(target);
		for(IPageableNavigationListener listener: navigationListeners) {
			listener.onPreviousPage(target);
		}
	}
	
	/**
	 * Override this method to do something when previous page button is clicked.
	 * 
	 * @param target
	 */
	public void previousPage(AjaxRequestTarget target) {
	
	}

	/**
	 * @return the keepSelectionOnNavigation
	 */
	public boolean isKeepSelectionOnNavigation() {
		return keepSelectionOnNavigation;
	}

	/**
	 * @param keepSelectionOnNavigation the keepSelectionOnNavigation to set
	 */
	public void setKeepSelectionOnNavigation(boolean keepSelectionOnNavigation) {
		this.keepSelectionOnNavigation = keepSelectionOnNavigation;
	}

	/**
	 * @return the dragableColumns
	 */
	public boolean isDragableColumns() {
		return dragableColumns;
	}

	/**
	 * @param dragableColumns the dragableColumns to set
	 */
	public void setDragableColumns(boolean dragableColumns) {
		this.dragableColumns = dragableColumns;
	}

	public boolean hasNextPage() {
		return (currentPage + 1) < numberOfPages;
	}

	public boolean hasPreviousPage() {
		return currentPage > 0;
	}

	public void nextPage() {
		currentPage++;
		rowModels = null;
	}

	public void previousPage() {
		currentPage--;
		rowModels = null;
	}



	public Iterator<IModel<E>> getCurrentPage() {
		//TODO: delete collection after rendering
		if(rowModels == null || rowModels.size() == 0) {
			rowModels = new ArrayList<IModel<E>>();
			int start = currentPage *pageSize;
			Iterator<? extends E> it = this.dataProvider.iterator(start, pageSize);
			while(it.hasNext()) {
				rowModels.add(dataProvider.model(it.next()));
			}
		}
		return rowModels.iterator();		
	}



	public boolean isSelected(E bean) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public void firstPage() {
		currentPage = 0;
		rowModels = null;
	}
	
	public void lastPage() {
		currentPage = numberOfPages-1;
		rowModels = null;
	}



	public boolean containsData() {
		return getCurrentPage().hasNext();
	}



	public int currentPageNumber() {
		return currentPage;
	}



	public int getNumberOfPages() {
		
		return numberOfPages;
	}



	public int getPageSize() {
		return pageSize;
	}



	public void reset() {
		this.size =-1;
		firstPage();
	}



	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	public void sort(ISortState state) {
		if(this.dataProvider instanceof ISortStateLocator) {
			
		}
	}



	public IDataProvider<E> getDataProvider() {
		return dataProvider;
	}



	public int getHeight() {
		return height;
	}



	public void setHeight(int height) {
		this.height = height;
	}



	public int getWidth() {
		return width;
	}



	public void setWidth(int width) {
		this.width = width;
	}
	
	public String getAjaxIndicatorMarkupId() {
		return getMarkupId()+"_loading";
	}
}
