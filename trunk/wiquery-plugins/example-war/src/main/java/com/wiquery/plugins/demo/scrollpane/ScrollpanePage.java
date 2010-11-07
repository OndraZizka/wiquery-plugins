package com.wiquery.plugins.demo.scrollpane;

import java.util.List;

import org.apache.wicket.markup.html.panel.Panel;

import com.wiquery.plugins.demo.BasePage;
import com.wiquery.plugins.demo.code.SourceInfo;
import com.wiquery.plugins.demo.code.SourceInfo.FORMAT;

/**
 * Homepage
 */
public class ScrollpanePage extends BasePage {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructor that is invoked when page is invoked without a session.
	 * 
	 * @param parameters
	 *            Page parameters
	 */
    public ScrollpanePage() {
    	super();
    }
    
    @Override
    protected Panel getLayoutCenterComponent(String wicketId) {
    	return new ScrollpanePanel(wicketId);
    }
    
    @Override
    protected void addSourceCode(List<SourceInfo> codeInfos) {
    	codeInfos.add(new SourceInfo(ScrollpanePage.class));
    	codeInfos.add(new SourceInfo(ScrollpanePanel.class));
		codeInfos.add(new SourceInfo(ScrollpanePanel.class, FORMAT.HTML, "ScrollpanePanel.html", "ScrollpanePanel.html"));
    }
}
