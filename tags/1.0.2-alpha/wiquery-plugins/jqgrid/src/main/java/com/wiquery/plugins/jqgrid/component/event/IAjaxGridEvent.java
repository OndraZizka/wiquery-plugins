/**
 * 
 */
package com.wiquery.plugins.jqgrid.component.event;

import java.io.Serializable;

import org.apache.wicket.ajax.AjaxRequestTarget;

/**
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public interface IAjaxGridEvent<B extends Serializable> extends IGridEvent<B> {

	public void onEvent(AjaxRequestTarget target);
}
