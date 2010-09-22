/**
 * 
 */
package com.wiquery.plugins.jqgrid.model;

import java.io.Serializable;

/**
 * @author  Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public class SortInfo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * The property to use as sort index.
	 */
	private String property;
		
	/**
	 * The oder to use for sorting.
	 */
	private SortOrder sortOrder;

	/**
	 * 
	 * @param property
	 * @param sortOrder
	 */
	public SortInfo(String property, SortOrder sortOrder) {
		super();
		this.property = property;
		this.sortOrder = sortOrder;
	}
	

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public SortOrder getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(SortOrder sortOrder) {
		this.sortOrder = sortOrder;
	}

}
