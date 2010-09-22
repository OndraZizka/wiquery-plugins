package com.wiquery.plugins.demo;

import java.util.List;

import org.apache.wicket.markup.html.panel.Panel;

import com.wiquery.plugins.demo.code.SourceInfo;
import com.wiquery.plugins.demo.code.SourceInfo.FORMAT;

/**
 * Homepage
 */
public class EffectsPage extends BasePage {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructor that is invoked when page is invoked without a session.
	 * 
	 * @param parameters
	 *            Page parameters
	 */
    public EffectsPage() {
        super();
    }
    
    @Override
    protected Panel getLayoutCenterComponent(String wicketId) {
    	return new EffectsPanel(wicketId);
    }
    
    @Override
    protected void addSourceCode(List<SourceInfo> codeInfos) {
    	codeInfos.add(new SourceInfo(EffectsPage.class));
    	codeInfos.add(new SourceInfo(EffectsPanel.class));
		codeInfos.add(new SourceInfo(EffectsPanel.class, FORMAT.HTML, "EffectsPanel.html", "EffectsPanel.html"));
		codeInfos.add(new SourceInfo(EffectSpeedPanel.class));
		codeInfos.add(new SourceInfo(EffectSpeedPanel.class, FORMAT.HTML, "EffectSpeedPanel.html", "EffectSpeedPanel.html"));
    }
}
