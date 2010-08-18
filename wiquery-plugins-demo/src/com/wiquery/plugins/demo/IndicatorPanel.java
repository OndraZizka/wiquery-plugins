/**
 * 
 */
package com.wiquery.plugins.demo;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.IAjaxIndicatorAware;
import org.apache.wicket.markup.html.panel.Panel;

/**
 * @author Ernesto Reinaldo Barreiro
 *
 */
public abstract class IndicatorPanel extends Panel implements IAjaxIndicatorAware {

	private static final long serialVersionUID = 1L;

	/**
	 * @param id
	 */
	public IndicatorPanel(String id) {
		super(id);
		add(newContents("contents").setRenderBodyOnly(true));
	}

	protected abstract Component newContents(String id);
	
	/* (non-Javadoc)
	 * @see org.apache.wicket.ajax.IAjaxIndicatorAware#getAjaxIndicatorMarkupId()
	 */
	public String getAjaxIndicatorMarkupId() {
		return "veil";
	}

}
