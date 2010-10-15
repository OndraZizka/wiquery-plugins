/**
 * 
 */
package com.wiquery.plugins.jqgrid.component;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.extensions.markup.html.repeater.data.sort.ISortState;
import org.apache.wicket.extensions.markup.html.repeater.data.sort.ISortStateLocator;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.RefreshingView;
import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.protocol.http.WebRequestCycle;

import com.wiquery.plugins.jqgrid.model.ICellPopulator;
import com.wiquery.plugins.jqgrid.model.SortInfo;
import com.wiquery.plugins.jqgrid.model.SortOrder;

/**
 * @author Ernesto Reinaldo 
 */
public class GridDataPanel<E extends Serializable> extends Panel {

	private static final long serialVersionUID = 1L;

	private int currentPage = 0;
	
	private int totalPages = 1 ;
	
	private int pageSize = 10 ;
	
	private int records = 10 ;
	
	private int start = 0;
	
	private RefreshingView<E>	rows;

	private List<ICellPopulator<E>> populators;
	
	private SortInfo sortInfo;
		
	private IDataProvider<E> dataProvider;
	
	private List<IModel<ICellPopulator<E>>> COLUMNS = new ArrayList<IModel<ICellPopulator<E>>>();
	
	private List<IModel<E>> rowModels;
	/**
	 * @param id
	 */
	public GridDataPanel(String id, List<ICellPopulator<E>> populators, IDataProvider<E> dataProvider) {				
		super(id);	
		this.rowModels = new ArrayList<IModel<E>>();
		this.populators = populators;
		this.dataProvider = dataProvider;
		int columns = populators.size();
		for(int i=0; i < columns;i++) {
			COLUMNS.add(Model.of(populators.get(i)));
		}
		add(new Label("currentPage", new AbstractReadOnlyModel<String>(){
			private static final long serialVersionUID = 1L;

			@Override
			public String getObject() {
				return Integer.toString(getCurrentPage());
			}
		}).setRenderBodyOnly(true));
		
		add(new Label("totalPages", new AbstractReadOnlyModel<String>(){
			private static final long serialVersionUID = 1L;

			@Override
			public String getObject() {
				return Integer.toString(getTotalPages());
			}
		}).setRenderBodyOnly(true));
		
		add(new Label("records", new AbstractReadOnlyModel<String>(){
			private static final long serialVersionUID = 1L;

			@Override
			public String getObject() {
				return Integer.toString(getRecords());
			}
		}).setRenderBodyOnly(true));
		
		rows = new RefreshingView<E>("rows") {
			
			private static final long serialVersionUID = 1L;

			@Override
			protected Iterator<IModel<E>> getItemModels() {
				List<IModel<E>> temp = new ArrayList<IModel<E>>();
				Iterator<? extends E> it = GridDataPanel.this.getRows(start, pageSize,  GridDataPanel.this.sortInfo);
				while(it.hasNext()) {
					IModel<E> model = GridDataPanel.this.dataProvider.model(it.next());
					rowModels.add(model);
					temp.add(model);
				}
				return temp.iterator();
			}
			
			@Override
			protected Item<E> newItem(String id, int index, IModel<E> model) {
				Item<E> item = super.newItem(id, index, model);
				item.add(new AttributeModifier("id", Model.of("row"+index)));
				return item;
			}
			
			@Override
			protected void populateItem(final Item<E> item) {	
				
				RefreshingView<ICellPopulator<E>> cells = new RefreshingView<ICellPopulator<E>>("cells") {
					
					private static final long serialVersionUID = 1L;

					@Override
					protected Iterator<IModel<ICellPopulator<E>>> getItemModels() {												
						return COLUMNS.iterator();
					}
					
					@Override
					protected void populateItem(Item<ICellPopulator<E>> cellItem) {
						ICellPopulator<E> cellPopulator = cellItem.getModel().getObject();
						cellPopulator.populateItem(cellItem, "cell", item.getIndex(), cellItem.getIndex(), item.getModel());
					}
				};
				item.add(cells);
			}
			
		};				
		add(rows);
	}

	private Iterator<? extends E> getRows(int start, int rows, SortInfo sortInfo) {
		if(this.dataProvider instanceof ISortStateLocator) {
			ISortStateLocator locator = (ISortStateLocator)dataProvider;
			ISortState sortState = locator.getSortState();
			if(sortState != null && sortInfo != null && sortInfo.getSortOrder() != null) {
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
	
	private int totalRecords(SortInfo sortInfo) {
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
	
	private int readCurrentPage() {
		try {
			return Integer.parseInt(WebRequestCycle.get().getRequest().getParameter("page"));
		} catch (Exception e) {
			return 10;
		}
	}
	
	private int readNumberOfRows() {
		try {
			return Integer.parseInt(WebRequestCycle.get().getRequest().getParameter("rows"));
		} catch (Exception e) {
			return 10;
		}
	}
	
	@Override
	protected void onBeforeRender() {
		configureSort();
		rowModels.clear();
		int rows = readNumberOfRows();
		int page = readCurrentPage();
		int records = totalRecords(this.sortInfo);
		int start = rows*(page-1);
		long totalPages = (records/rows)+1;
		setCurrentPage(page);
		setTotalPages((int)totalPages);
		setPageSize(rows);
		setStart(start);
		super.onBeforeRender();		
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getRecords() {
		return records;
	}

	public void setRecords(int records) {
		this.records = records;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public List<ICellPopulator<E>> getPopulators() {
		return populators;
	}

	public void setPopulators(List<ICellPopulator<E>> populators) {
		this.populators = populators;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public List<IModel<E>> getRowModels() {
		return rowModels;
	}
	
}
