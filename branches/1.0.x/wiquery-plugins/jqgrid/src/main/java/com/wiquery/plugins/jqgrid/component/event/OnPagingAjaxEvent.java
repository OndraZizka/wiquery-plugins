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
public abstract class OnPagingAjaxEvent<B extends Serializable> extends AbstractAjaxGridAwareEvent<B> {

	private static final long serialVersionUID = 1L;

	public static enum PageButton {
		first,last,prev,next, 
		// user changes the current page on the input field provided by the grid.
		user;
	}
	
	/**
	 * @param event
	 */
	public OnPagingAjaxEvent() {
		super(GridEvent.onPaging);
	}

	/* (non-Javadoc)
	 * @see com.wijqgrid.component.IAjaxGridEvent#onEvent(org.apache.wicket.ajax.AjaxRequestTarget)
	 */
	public final void onEvent(AjaxRequestTarget target) {
		Grid<B> grid = getGrid();
		String pgButton = WebRequestCycle.get().getRequest().getParameter("pgButton");
		if(!StringUtils.isEmpty(pgButton)) {
			PageButton button = PageButton.valueOf(pgButton);
			onPaging(target, grid, button);
		}				
	}
	
	@Override
	protected String getFunctionSignature() {
		return "function(pgButton){\n";
	}
	
	@Override
	protected void encodeAdditionalParams(Map<String, String> params) {
		params.put("pgButton", "pgButton");
	}
	/**
	 * 
	 * Override this method to do something when a row is selected.
	 * 
	 * @param target The {@link AjaxRequestTarget}.
	 * @param row The row (starts at 0).
	 * @param rowModel The associated row model.
	 */
	protected abstract void onPaging(AjaxRequestTarget target, Grid<B> grid, PageButton button);
}
