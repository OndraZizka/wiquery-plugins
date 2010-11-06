package com.wiquery.plugins.demo;

import java.util.List;

import org.apache.wicket.markup.html.panel.Panel;

import com.wiquery.plugins.demo.code.SourceInfo;
import com.wiquery.plugins.demo.code.SourceInfo.FORMAT;

/**
 * Homepage
 */
public class NewGridPage extends BasePage {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructor that is invoked when page is invoked without a session.
	 * 
	 * @param parameters
	 *            Page parameters
	 */
    public NewGridPage() {
    	super();
    }
    
    @Override
    protected Panel getLayoutCenterComponent(String wicketId) {
    	return new NewGridPanel(wicketId);
    }
    
    @Override
    protected void addSourceCode(List<SourceInfo> codeInfos) {
    	codeInfos.add(new SourceInfo(NewGridPage.class));
    	codeInfos.add(new SourceInfo(NewGridPanel.class));
		codeInfos.add(new SourceInfo(NewGridPanel.class, FORMAT.HTML, "NewGridPanel.html", "NewGridPanel.html"));		
		codeInfos.add(new SourceInfo(ExampleCellLink.class));
		codeInfos.add(new SourceInfo(ExampleCellLink.class, FORMAT.HTML, "ExampleCellLink.html", "ExampleCellLink.html"));
    }
}
