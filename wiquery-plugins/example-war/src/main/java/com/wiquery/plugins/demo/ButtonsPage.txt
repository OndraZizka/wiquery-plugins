package com.wiquery.plugins.demo;

import java.util.List;

import org.apache.wicket.markup.html.panel.Panel;

import com.wiquery.plugins.demo.code.SourceInfo;
import com.wiquery.plugins.demo.code.SourceInfo.FORMAT;


/**
 * Homepage
 */
public class ButtonsPage extends BasePage {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructor that is invoked when page is invoked without a session.
	 * 
	 * @param parameters
	 *            Page parameters
	 */
    public ButtonsPage() {
        super();
    }
    
    @Override
    protected Panel getLayoutCenterComponent(String wicketId) {
    	return new JQIconsPanel(wicketId);
    }
    
    @Override
    protected void addSourceCode(List<SourceInfo> codeInfos) {
    	codeInfos.add(new SourceInfo(ButtonsPage.class));
    	codeInfos.add(new SourceInfo(JQIconsPanel.class));
		codeInfos.add(new SourceInfo(JQIconsPanel.class, FORMAT.HTML, "JQIconsPanel.html", "JQIconsPanel.html"));
    }
}
