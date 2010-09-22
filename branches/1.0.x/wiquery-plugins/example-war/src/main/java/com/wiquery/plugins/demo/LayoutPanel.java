/**
 * 
 */
package com.wiquery.plugins.demo;

import org.apache.wicket.markup.html.panel.Panel;
import org.odlabs.wiquery.plugin.layout.Layout;
import org.odlabs.wiquery.plugin.layout.test.LabelPanel;

/**
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class LayoutPanel extends Panel {

	private static final long serialVersionUID = 1L;

	/**
	 * @param id
	 */
	public LayoutPanel(String id) {
		super(id);
		
		add(new Layout("layout", false) {
    		
    		private static final long serialVersionUID = 1L;

			@Override
    		public Panel getLayoutCenterComponent(String wicketId) {
    			return new LabelPanel(wicketId, "Center");
    		}
			
			@Override
			public Panel getLayoutEastComponent(String wicketId) {
				return new LabelPanel(wicketId, "East");
			}
			
			@Override
			public Panel getLayoutNorthComponent(String wicketId) {				
				return new LabelPanel(wicketId, "North");
			}
			
			@Override
			public Panel getLayoutSouthComponent(String wicketId) {
				return new LabelPanel(wicketId, "South");
			}
			
			@Override
			public Panel getLayoutWestComponent(String wicketId) {
				return new LabelPanel(wicketId, "West");
			}
    	});
	}

}
