package com.wiquery.plugins.demo;

import org.apache.wicket.markup.html.CSSPackageResource;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.panel.Panel;
import org.odlabs.wiquery.plugin.layout.Layout;

import com.wiquery.plugins.antilia.grid.resources.DefaultStyle;

/**
 * Base page.
 */
public abstract class BasePage extends WebPage {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructor that is invoked when page is invoked without a session.
	 * 
	 * @param parameters
	 *            Page parameters
	 */
    public BasePage() {
    	add(CSSPackageResource.getHeaderContribution(DefaultStyle.CSS_MAIN));
    	Layout layout = new Layout("layout", true) {
        	
        	private static final long serialVersionUID = 1L;


        	@Override
        	public Panel getLayoutEastComponent(String wicketId) {
        		return new NavigationPanel(wicketId);
        	}
        	
        	@Override
        	public Panel getLayoutCenterComponent(String wicketId) {
        		return BasePage.this.getLayoutCenterComponent(wicketId);
        	}
        	
        };
        add(layout);
    }
    
    protected abstract Panel getLayoutCenterComponent(String wicketId);
}
