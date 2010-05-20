/**
 * 
 */
package com.wiquery.plugins.jqgrid.component.event;

import java.io.Serializable;

import org.apache.wicket.ajax.AjaxRequestTarget;

import com.wiquery.plugins.jqgrid.component.Grid;


/**
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public abstract class OnGridCompleteAjaxEvent<B extends Serializable> extends AbstractAjaxGridAwareEvent<B> {

	private static final long serialVersionUID = 1L;

	/**
	 * @param event
	 */
	public OnGridCompleteAjaxEvent() {
		super(GridEvent.gridComplete);
	}

	/* (non-Javadoc)
	 * @see com.wijqgrid.component.IAjaxGridEvent#onEvent(org.apache.wicket.ajax.AjaxRequestTarget)
	 */
	public final void onEvent(AjaxRequestTarget target) {
		Grid<B> grid = getGrid();
		onGridComplete(target, grid);		
	}
	
	@Override
	protected String getFunctionSignature() {
		return "function(){\n";
	}
	/**
	 * 
	 * Override this method to do something when a row is selected.
	 * 
	 * @param target The {@link AjaxRequestTarget}.
	 * @param row The row (starts at 0).
	 * @param rowModel The associated row model.
	 */
	protected abstract void onGridComplete(AjaxRequestTarget target, Grid<B> grid);
}
