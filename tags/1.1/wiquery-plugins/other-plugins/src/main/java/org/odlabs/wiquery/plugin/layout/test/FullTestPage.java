package org.odlabs.wiquery.plugin.layout.test;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.panel.Panel;
import org.odlabs.wiquery.plugin.layout.Layout;


/**
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class FullTestPage extends WebPage {

	private static final long serialVersionUID = 1L;


    public FullTestPage() {
    	add(new Layout("layout", true) {
    		
    		private static final long serialVersionUID = 1L;

			@Override
    		public Panel getLayoutCenterComponent(String wicketId) {
    			return new LabelPanel(wicketId, "Center");
    		}
    		
			@Override
			public Panel getLayoutWestComponent(String wicketId) {
				return new LabelPanel(wicketId, "West");
			}
			
			@Override
			public Panel getLayoutNorthComponent(String wicketId) {
				return new LabelPanel(wicketId, "North");
			}
			
			@Override
			public Panel getLayoutEastComponent(String wicketId) {
				return new LabelPanel(wicketId, "East");
			}
			
			@Override
			public Panel getLayoutSouthComponent(String wicketId) {
				return new LabelPanel(wicketId, "South");
			}
    		
    	});
    }
    
}
