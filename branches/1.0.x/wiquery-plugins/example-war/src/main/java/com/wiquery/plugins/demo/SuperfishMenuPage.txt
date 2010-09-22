package com.wiquery.plugins.demo;

import java.util.List;

import org.apache.wicket.markup.html.panel.Panel;

import com.wiquery.plugins.demo.code.SourceInfo;
import com.wiquery.plugins.demo.code.SourceInfo.FORMAT;

/**
 * Homepage
 */
public class SuperfishMenuPage extends BasePage {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructor that is invoked when page is invoked without a session.
	 * 
	 * @param parameters
	 *            Page parameters
	 */
    public SuperfishMenuPage() {
    	super();
    }
    
    @Override
    protected Panel getLayoutCenterComponent(String wicketId) {
    	return new SuperfishMenuPanel(wicketId);
    }
    
    @Override
    protected void addSourceCode(List<SourceInfo> codeInfos) {
    	codeInfos.add(new SourceInfo(SuperfishMenuPage.class));
    	codeInfos.add(new SourceInfo(SuperfishMenuPanel.class));
		codeInfos.add(new SourceInfo(SuperfishMenuPanel.class, FORMAT.HTML, "SuperfishMenuPanel.html", "SuperfishMenuPanel.html"));
    }
}
