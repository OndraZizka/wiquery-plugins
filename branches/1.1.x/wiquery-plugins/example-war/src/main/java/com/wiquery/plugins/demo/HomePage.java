package com.wiquery.plugins.demo;

import org.apache.wicket.markup.html.panel.Panel;
import org.odlabs.wiquery.plugin.layout.test.LabelPanel;

/**
 * Homepage
 */
public class HomePage extends BasePage {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructor that is invoked when page is invoked without a session.
	 * 
	 * @param parameters
	 *            Page parameters
	 */
    public HomePage() {
        super();
    }
    
    @Override
    protected Panel getLayoutCenterComponent(String wicketId) {
    	return new LabelPanel(wicketId, "Examples");
    }
}
