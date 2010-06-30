package com.wiquery.plugins.demo;

import org.apache.wicket.markup.html.panel.Panel;

import com.wiquery.plugin.dynamicdrive.menu.test.TestMenu;

/**
 * Homepage
 */
public class MenuPage extends BasePage {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructor that is invoked when page is invoked without a session.
	 * 
	 * @param parameters
	 *            Page parameters
	 */
    public MenuPage() {
        super();
    }
    
    @Override
    protected Panel getLayoutCenterComponent(String wicketId) {
    	return new TestMenu(wicketId);
    }
}
