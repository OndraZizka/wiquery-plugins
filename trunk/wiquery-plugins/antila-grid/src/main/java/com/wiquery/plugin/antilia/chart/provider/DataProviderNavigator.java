package com.wiquery.plugin.antilia.chart.provider;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.model.IModel;

/**
 * @author Ernesto Reinaldo Barreiro
 *
 */
public class DataProviderNavigator<E extends Serializable> implements IPageSizeDataNavigator<E> {

	private static final long serialVersionUID = 1L;
	
	private int currentPage = 0;
	
	private IDataProvider<E> dataProvider;
	
	private int numberOfPages;
	
	private List<IModel<E>> rowModels;
	
	private int size = -1;
	
	/**
	 * The page size.
	 */
	private int pageSize = 10;
	
	/**
	 * 
	 */
	public DataProviderNavigator(IDataProvider<E> dataProvider) {
		this.dataProvider = dataProvider;
	}
	
	public void initialize() {
		if(size == -1) {
			size = dataProvider.size();
			numberOfPages = (size/pageSize)+((size%pageSize==0)?0:1); 
		}
	}

	public void nextPage() {
		currentPage++;
		rowModels = null;
	}

	public void previousPage() {
		currentPage--;
		rowModels = null;
	}


	public boolean hasNextPage() {
		return (currentPage + 1) < numberOfPages;
	}

	public boolean hasPreviousPage() {
		return currentPage > 0;
	}


	public void firstPage() {
		currentPage = 0;
		rowModels = null;
	}
	
	

	public void lastPage() {
		currentPage = numberOfPages-1;
		rowModels = null;
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.inqle.qa.web.chart.IDataNavigator#goToPage(int)
	 */
	public void goToPage(int page) {
		if(page >= 0 && page < numberOfPages) {
			currentPage = page;
			rowModels = null;
		}		
	}

	public Iterable<IModel<E>> getCurrentPage() {
		if(rowModels == null || rowModels.size() == 0) {
			rowModels = new ArrayList<IModel<E>>();
			int start = currentPage *pageSize;
			Iterator<? extends E> it = this.dataProvider.iterator(start, pageSize);
			while(it.hasNext()) {
				rowModels.add(dataProvider.model(it.next()));
			}
		}
		return rowModels;
	}

	public boolean containsData() {
		return getCurrentPage().iterator().hasNext();
	}

	public void reset() {
		this.size =-1;
		firstPage();
	}
	
	/* (non-Javadoc)
	 * @see org.apache.wicket.model.IDetachable#detach()
	 */
	public void detach() {
		if(rowModels!= null) {
			for(IModel<E> model: rowModels){
				model.detach();
			}
		}
		if(this.dataProvider != null) {
			this.dataProvider.detach();
		}
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

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

}
