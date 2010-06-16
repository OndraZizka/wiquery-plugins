/**
 * 
 */
package com.wiquery.plugins.demo;

import org.apache.wicket.markup.html.panel.Panel;
import org.wiquery.plugins.slidedeck.SlideDeck;

/**
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class SlideDeckPanel extends Panel {

	private static final long serialVersionUID = 1L;

	/**
	 * @param id
	 */
	public SlideDeckPanel(String id) {
		super(id);
		
		SlideDeck slidedeck = new SlideDeck("slidedeck");
		add(slidedeck);
		
	}

}
