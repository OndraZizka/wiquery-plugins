package org.odlabs.wiquery.plugin.layout.test;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;

/**
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class LabelPanel extends Panel {

	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @param id
	 */
	public LabelPanel(String id, String text) {
		super(id);
		add(new Label("label", text));
	}
	
	
}
