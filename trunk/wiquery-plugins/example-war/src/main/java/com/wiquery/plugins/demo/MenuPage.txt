package com.wiquery.plugins.demo;

import java.util.List;

import org.apache.wicket.markup.html.panel.Panel;

import com.wiquery.plugin.dynamicdrive.menu.test.TestMenu;
import com.wiquery.plugins.demo.code.SourceInfo;

/**
 * Homepage
 */
public class MenuPage extends BasePage {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructor that is invoked when page is invoked without a session.
	 * 
	 * @param parameters
	 *            Page parameters
	 */
    public MenuPage() {
        super();
    }
    
    @Override
    protected Panel getLayoutCenterComponent(String wicketId) {
    	return new TestMenu(wicketId);
    }
    
    @Override
    protected void addSourceCode(List<SourceInfo> codeInfos) {
    	codeInfos.add(new SourceInfo(MenuPage.class));
    }
}
