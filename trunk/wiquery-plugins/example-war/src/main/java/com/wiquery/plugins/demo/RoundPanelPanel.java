/**
 * 
 */
package com.wiquery.plugins.demo;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.panel.Panel;

import com.wiquery.plugin.antilia.roundpane.RoundPanel;

/**
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class RoundPanelPanel extends Panel {

	private static final long serialVersionUID = 1L;

	/**
	 * @param id
	 */
	public RoundPanelPanel(String id) {
		super(id);
		RoundPanel panel = new RoundPanel("panel") {
			
			private static final long serialVersionUID = 1L;

			@Override
			protected Component newContent(String id) {
				
				return new TablePanel(id);
			}
		};
		add(panel);
	}

}
