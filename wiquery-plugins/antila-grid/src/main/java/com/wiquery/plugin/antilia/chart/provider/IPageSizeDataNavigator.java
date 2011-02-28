package com.wiquery.plugin.antilia.chart.provider;

import java.io.Serializable;

/**
 * @author Ernesto Reinaldo Barreiro
 *
 */
public interface IPageSizeDataNavigator<E extends Serializable> extends IDataNavigator<E> {
	
	/**
	 * Sets the page size.
	 * 
	 * @param pageSize
	 */
	public void setPageSize(int pageSize);
	
	public int getPageSize();
	
	
	public int currentPageNumber();
	
	
	
	public int getNumberOfPages();
}
