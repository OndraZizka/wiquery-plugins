package com.wiquery.plugins.demo;

import org.apache.wicket.markup.html.panel.Panel;

import com.wiquery.plugin.slider.test.SliderPanel;

/**
 * Homepage
 */
public class SliderPage extends BasePage {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructor that is invoked when page is invoked without a session.
	 * 
	 * @param parameters
	 *            Page parameters
	 */
    public SliderPage() {
        super();
    }
    
    @Override
    protected Panel getLayoutCenterComponent(String wicketId) {
    	return new SliderPanel(wicketId);
    }
}
