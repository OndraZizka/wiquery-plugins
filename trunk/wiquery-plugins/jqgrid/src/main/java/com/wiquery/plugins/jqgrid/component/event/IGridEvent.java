/**
 * 
 */
package com.wiquery.plugins.jqgrid.component.event;

import java.io.Serializable;


/**
 * Represents an event of the grid.
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public interface IGridEvent<B extends Serializable> extends Serializable {
	
	/**
	 * Enumeration with the events supported by the grid.
	 * 
	 * See http://www.trirand.com/jqgridwiki/doku.php?id=wiki:events
	 *	
	 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
	 */
	public static enum GridEvent {
		onSelectRow,
		gridComplete,
		ondblClickRow,
		onRightClickRow,
		onHeaderClick,
		onSortCol,
		onPaging,
		resizeStop;
	}

	public abstract String getGridEvent();
	
	/**
	 * 
	 * @param callBackURL
	 * @return
	 */
	public abstract String statement(String callBackURL);
}
