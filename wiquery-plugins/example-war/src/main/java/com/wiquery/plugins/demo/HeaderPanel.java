package com.wiquery.plugins.demo;

import org.apache.wicket.markup.html.panel.Panel;

/**
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class HeaderPanel extends Panel {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructor that is invoked when page is invoked without a session.
	 * 
	 * @param parameters
	 *            Page parameters
	 */
    public HeaderPanel(String id) {
    	super(id);
      	
    }
}
