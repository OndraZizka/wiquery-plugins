/**
 * 
 */
package com.wiquery.plugins.jqgrid.experiment;

import java.util.List;

import org.apache.wicket.extensions.markup.html.repeater.data.grid.DataGridView;
import org.apache.wicket.extensions.markup.html.repeater.data.grid.ICellPopulator;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.model.AbstractReadOnlyModel;

/**
 * @author Ernesto Reinaldo 
 */
public class GridDataPanel<E> extends Panel {

	private static final long serialVersionUID = 1L;

	private int currentPage = 0;
	
	private int totalPages = 1 ;
	
	private int pageSize = 10 ;
	
	private int records = 10 ;
	
	private DataGridView<E>	rows;

	/**
	 * @param id
	 */
	public GridDataPanel(String id, List<ICellPopulator<E>> populators, IDataProvider<E> dataProvider) {		
		super(id);	
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
		
		rows = new DataGridView<E>("rows", populators, dataProvider);				
		add(rows);
	}

	@Override
	protected void onBeforeRender() {
		rows.setCurrentPage(getCurrentPage());
		rows.setRowsPerPage(getPageSize());
		super.onBeforeRender();		
	}

	public int getCurrentPage() {
		return currentPage;
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
	
}
