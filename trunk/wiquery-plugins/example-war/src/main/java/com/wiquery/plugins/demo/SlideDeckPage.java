package com.wiquery.plugins.demo;

import org.apache.wicket.markup.html.panel.Panel;

/**
 * Homepage
 */
public class SlideDeckPage extends BasePage {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructor that is invoked when page is invoked without a session.
	 * 
	 * @param parameters
	 *            Page parameters
	 */
    public SlideDeckPage() {
    	super();
    }
    
    @Override
    protected Panel getLayoutCenterComponent(String wicketId) {
    	return new SlideDeckPanel(wicketId);
    }
}