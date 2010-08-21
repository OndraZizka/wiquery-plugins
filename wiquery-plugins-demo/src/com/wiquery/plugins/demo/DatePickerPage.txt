package com.wiquery.plugins.demo;

import java.util.List;

import org.apache.wicket.markup.html.panel.Panel;

import com.wiquery.plugins.demo.code.SourceInfo;
import com.wiquery.plugins.demo.code.SourceInfo.FORMAT;

/**
 * Homepage
 */
public class DatePickerPage extends BasePage {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructor that is invoked when page is invoked without a session.
	 * 
	 * @param parameters
	 *            Page parameters
	 */
    public DatePickerPage() {
    	super();
    }
    
    @Override
    protected Panel getLayoutCenterComponent(String wicketId) {
    	return new DatePickerPanel(wicketId);
    }
    
    @Override
    protected void addSourceCode(List<SourceInfo> codeInfos) {
    	codeInfos.add(new SourceInfo(DatePickerPage.class));
    	codeInfos.add(new SourceInfo(DatePickerPanel.class));
		codeInfos.add(new SourceInfo(DatePickerPanel.class, FORMAT.HTML, "DatePickerPanel.html", "DatePickerPanel.html"));
    }
}
