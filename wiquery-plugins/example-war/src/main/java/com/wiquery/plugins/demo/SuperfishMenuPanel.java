/**
 * 
 */
package com.wiquery.plugins.demo;

import org.apache.wicket.markup.html.panel.Panel;

import com.wiquery.plugin.superfish.Menu;

/**
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class SuperfishMenuPanel extends Panel {

	private static final long serialVersionUID = 1L;
	
	/**
	 * @param id
	 */
	public SuperfishMenuPanel(String id) {
		super(id);
		add(new Menu("menu")
		.setDropShadows(false)
		.setAutoArrows(false)
		);
	}

}
