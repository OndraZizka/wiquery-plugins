package com.wiquery.plugins.demo;

import org.apache.wicket.markup.html.panel.Panel;

import com.wiquery.plugins.antilia.link.JQIconsPanel;

/**
 * Homepage
 */
public class ButtonsPage extends BasePage {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructor that is invoked when page is invoked without a session.
	 * 
	 * @param parameters
	 *            Page parameters
	 */
    public ButtonsPage() {
        super();
    }
    
    @Override
    protected Panel getLayoutCenterComponent(String wicketId) {
    	return new JQIconsPanel(wicketId);
    }
}
