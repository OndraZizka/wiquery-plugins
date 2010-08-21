package com.wiquery.plugins.demo;

import java.util.List;

import org.apache.wicket.markup.html.panel.Panel;

import com.wiquery.plugins.demo.code.SourceInfo;
import com.wiquery.plugins.demo.code.SourceInfo.FORMAT;
import com.wiquery.plugins.demo.watermark.WatermarkPanel;

/**
 * Homepage
 */
public class WatermarkPage extends BasePage {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructor that is invoked when page is invoked without a session.
	 * 
	 * @param parameters
	 *            Page parameters
	 */
    public WatermarkPage() {
        super();
    }
    
    @Override
    protected Panel getLayoutCenterComponent(String wicketId) {
    	return new WatermarkPanel(wicketId);    	
    }
    
    @Override
    protected void addSourceCode(List<SourceInfo> codeInfos) {
    	codeInfos.add(new SourceInfo(WatermarkPage.class));
    	codeInfos.add(new SourceInfo(WatermarkPanel.class));
		codeInfos.add(new SourceInfo(WatermarkPanel.class, FORMAT.HTML, "WatermarkPanel.html", "WatermarkPanel.html"));		
    }
}
