package com.wiquery.plugin.antilia.chart.provider;

import java.io.Serializable;

import org.apache.wicket.model.IDetachable;
import org.apache.wicket.model.IModel;

/**
 * @author Ernesto Reinaldo Barreiro
 *
 */
public interface IDataNavigator<E extends Serializable> extends IDetachable {

	
	/**
	 * Allows to initialize the navigator.
	 */
	public void  initialize();
	
	/**
	 * Navigates to next page.
	 */
	public void nextPage();
	
	/**
	 * @return Can navigate to next page.
	 */
	public boolean hasNextPage();
	
	/**
	 * Navigates to previous page.
	 */
	public void previousPage();
	
	/**
	 * Navigates to first page.
	 */
	public void firstPage();
	
	
	/**
	 * Navigates to last page.
	 */
	public void lastPage();
	
	
	/**
	 * Navigates to page <code>page</code>.
	 * 
	 * @param page
	 */
	public void goToPage(int page);
	
	/**
	 *  If we have a previous page.
	 * @return
	 */
	public boolean hasPreviousPage();
	
	/**
	 * @return true if contains data.
	 */
	public boolean containsData();
	
	/**
	 * resets the navigator.
	 */
	public void reset();
	
	/**
	 * @return returns an iterable over the current page.
	 */
	public Iterable<IModel<E>> getCurrentPage();
	
}
