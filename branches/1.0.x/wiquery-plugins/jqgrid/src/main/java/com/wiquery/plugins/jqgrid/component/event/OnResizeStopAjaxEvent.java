/**
 * 
 */
package com.wiquery.plugins.jqgrid.component.event;

import java.io.Serializable;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.protocol.http.WebRequestCycle;

import com.wiquery.plugins.jqgrid.component.Grid;
import com.wiquery.plugins.jqgrid.model.IColumn;


/**
 * Event which is called after the column is resized. 
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public abstract class OnResizeStopAjaxEvent<B extends Serializable> extends AbstractAjaxGridAwareEvent<B> {

	private static final long serialVersionUID = 1L;

	/**
	 * @param event
	 */
	public OnResizeStopAjaxEvent() {
		super(GridEvent.resizeStop);
	}

	/* (non-Javadoc)
	 * @see com.wijqgrid.component.IAjaxGridEvent#onEvent(org.apache.wicket.ajax.AjaxRequestTarget)
	 */
	public final void onEvent(AjaxRequestTarget target) {
		String iIndex = WebRequestCycle.get().getRequest().getParameter("index");				
		if(!StringUtils.isEmpty(iIndex)) {			
			Integer col = Integer.parseInt(iIndex);		
			String iNewwidth = WebRequestCycle.get().getRequest().getParameter("newwidth");
			Integer newwidth = Integer.parseInt(iNewwidth);
			Grid<B> grid = getGrid();
			resizeStop(target, grid, grid.getGridModel().getColumnModel(col), newwidth);
		}
	}
	
	
	/*
	 * index is the index name from colModel, 
	 * iCol is the index of column, 
	 * sortorder is the new sorting order - can be 'asc' or 'desc'. 
	 * @see com.wijqgrid.component.AbstractAjaxEvent#getFunctionSignature()
	 */
	@Override
	protected String getFunctionSignature() {
		return "function(newwidth, index){\n";
	}
	
	@Override
	protected void encodeAdditionalParams(Map<String, String> params) {
		params.put("newwidth","newwidth");
		params.put("index","index");
	}

	/**
	 * 
	 * Override this method to do something when the column is resized.
	 * 
	 * @param target The {@link AjaxRequestTarget}.
	 * @param row The row (starts at 0).
	 * @param rowModel The associated row model.
	 */
	protected abstract void resizeStop(AjaxRequestTarget target, Grid<B> grid, IColumn<B> column, int newwidth);
}
