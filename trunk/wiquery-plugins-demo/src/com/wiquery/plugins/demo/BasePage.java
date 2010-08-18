package com.wiquery.plugins.demo;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.CSSPackageResource;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.panel.Panel;
import org.odlabs.wiquery.plugin.layout.Layout;
import org.odlabs.wiquery.plugin.layout.Layout.PanePositionEnum;

import com.wiquery.plugins.antilia.grid.resources.DefaultStyle;
import com.wiquery.plugins.demo.resources.Styles;

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
    	add(CSSPackageResource.getHeaderContribution(Styles.CSS_MAIN));
    	Layout layout = new Layout("layout", true) {
        	
        	private static final long serialVersionUID = 1L;

        	@Override
        	public Panel getLayoutWestComponent(String wicketId) {
        		return new NavigationPanel(wicketId);
        	}
        	
        	@Override
        	public Panel getLayoutNorthComponent(String wicketId) {
        		return new HeaderPanel(wicketId);
        	}
        	
        	@Override
        	public Panel getLayoutCenterComponent(final String wicketId) {
        		return new IndicatorPanel(wicketId) {
        			
        			private static final long serialVersionUID = 1L;

					@Override
        			protected Component newContents(String id) {
        				return BasePage.this.getLayoutCenterComponent(id);
        			}
        		};        		
        	}
        	
        };
        add(layout);
        layout.setResizable(PanePositionEnum.NORTH, false)
        .setClosable(PanePositionEnum.NORTH, false)
        .setSpacingOpen(PanePositionEnum.NORTH, 0)
        .setMinSize(PanePositionEnum.NORTH, 80)
        .setMaxSize(PanePositionEnum.NORTH, 82);
        
    }
    
    protected abstract Panel getLayoutCenterComponent(String wicketId);
}
