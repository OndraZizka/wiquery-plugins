/**
 * 
 */
package com.wiquery.plugins.jqgrid.component.event;

import java.io.Serializable;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.model.IModel;
import org.apache.wicket.protocol.http.WebRequestCycle;

import com.wiquery.plugins.jqgrid.component.Grid;


/**
 * Raised immediately after row was right clicked. 
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public abstract class OnRightClickRowAjaxEvent<B extends Serializable> extends AbstractAjaxGridAwareEvent<B> {

	private static final long serialVersionUID = 1L;

	/**
	 * @param event
	 */
	public OnRightClickRowAjaxEvent() {
		super(GridEvent.onRightClickRow);
	}

	/* (non-Javadoc)
	 * @see com.wijqgrid.component.IAjaxGridEvent#onEvent(org.apache.wicket.ajax.AjaxRequestTarget)
	 */
	public final void onEvent(AjaxRequestTarget target) {
		String param = WebRequestCycle.get().getRequest().getParameter("iRow");			
		if(!StringUtils.isEmpty(param)) {			
			Integer row = Integer.parseInt(param);
			String iCol = WebRequestCycle.get().getRequest().getParameter("iCol");
			Integer col = Integer.parseInt(iCol);
			Grid<B> grid = getGrid();
			IModel<B> model = grid.getRowModels().get(row);
			onRightClickRow(target, row, col, model);
		}
	}
	
	
	/*
	 * rowid is the id of the row, 
	 * iRow is the index of the row (do not mix this with the rowid),
	 * iCol is the index of the cell.
	 * e is the event object
	 * @see com.wijqgrid.component.AbstractAjaxEvent#getFunctionSignature()
	 */
	@Override
	protected String getFunctionSignature() {
		return "function(rowid, iRow, iCol, e){\n";
	}
	
	@Override
	protected void encodeAdditionalParams(Map<String, String> params) {
		params.put("rowid","rowid");
		params.put("iRow","iRow");		
		params.put("iCol", "iCol");
	}

	/**
	 * 
	 * Override this method to do something when a row is selected.
	 * 
	 * @param target The {@link AjaxRequestTarget}.
	 * @param row The row (starts at 0).
	 * @param rowModel The associated row model.
	 */
	protected abstract void onRightClickRow(AjaxRequestTarget target, int row, int col, IModel<B> rowModel);
}
