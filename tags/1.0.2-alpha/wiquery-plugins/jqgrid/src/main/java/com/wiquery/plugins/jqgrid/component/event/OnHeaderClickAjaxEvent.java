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


/**
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public abstract class OnHeaderClickAjaxEvent<B extends Serializable> extends AbstractAjaxGridAwareEvent<B> {

	private static final long serialVersionUID = 1L;

	public static enum GridState {
		visible,
		hidden;
	}
	/**
	 * @param event
	 */
	public OnHeaderClickAjaxEvent() {
		super(GridEvent.onHeaderClick);
	}

	/* (non-Javadoc)
	 * @see com.wijqgrid.component.IAjaxGridEvent#onEvent(org.apache.wicket.ajax.AjaxRequestTarget)
	 */
	public final void onEvent(AjaxRequestTarget target) {
		Grid<B> grid = getGrid();
		String gridstate = WebRequestCycle.get().getRequest().getParameter("gridstate");			
		if(!StringUtils.isEmpty(gridstate)) {
			onHeaderClick(target, grid,  GridState.valueOf(gridstate));	
		}
	}
	
	@Override
	protected String getFunctionSignature() {
		return "function(gridstate){\n";
	}
	
	@Override
	protected void encodeAdditionalParams(Map<String, String> params) {
		params.put("gridstate", "gridstate");
	}
	/**
	 * 
	 * Override this method to do something when the header is clicked.
	 * 
	 * @param target The {@link AjaxRequestTarget}.
	 * @param row The row (starts at 0).
	 * @param rowModel The associated row model.
	 */
	protected abstract void onHeaderClick(AjaxRequestTarget target, Grid<B> grid, GridState gridState);
}
