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
import com.wiquery.plugins.jqgrid.model.SortOrder;


/**
 * Raised immediately after sortable column was clicked and before sorting the data.  
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public abstract class OnSortColAjaxEvent<B extends Serializable> extends AbstractAjaxGridAwareEvent<B> {

	private static final long serialVersionUID = 1L;

	/**
	 * @param event
	 */
	public OnSortColAjaxEvent() {
		super(GridEvent.onSortCol);
	}

	/* (non-Javadoc)
	 * @see com.wijqgrid.component.IAjaxGridEvent#onEvent(org.apache.wicket.ajax.AjaxRequestTarget)
	 */
	public final void onEvent(AjaxRequestTarget target) {
        String iCol = WebRequestCycle.get().getRequest().getParameter("iCol");              
        if(!StringUtils.isEmpty(iCol)) {            
            Integer originalCol = Integer.parseInt(iCol);
            int col;
            if( getGrid().getGridModel().isRownumbers() )
                col = originalCol - 1;
            else
                col = originalCol;
            String sortorder = WebRequestCycle.get().getRequest().getParameter("sortorder");
            String sortProperty = WebRequestCycle.get().getRequest().getParameter("index");         
            Grid<B> grid = getGrid();
            onSortCol(target, grid, grid.getGridModel().getColumnModel(col), col, sortProperty, SortOrder.valueOf(sortorder));
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
		return "function(index, iCol,sortorder){\n";
	}
	
	@Override
	protected void encodeAdditionalParams(Map<String, String> params) {
		params.put("index","index");
		params.put("iCol","iCol");		
		params.put("sortorder", "sortorder");
	}

	/**
	 * 
	 * Override this method to do something when a row is selected.
	 * 
	 * @param target The {@link AjaxRequestTarget}.
	 * @param row The row (starts at 0).
	 * @param rowModel The associated row model.
	 */
	protected abstract void onSortCol(AjaxRequestTarget target, Grid<B> grid, IColumn<B> column, int col, String sortProperty,  SortOrder order);
}
