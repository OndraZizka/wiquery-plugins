package com.wiquery.plugin.antilia.roundpane;

import org.apache.wicket.Component;
import org.apache.wicket.ResourceReference;
import org.apache.wicket.markup.html.CSSPackageResource;
import org.apache.wicket.markup.html.panel.EmptyPanel;
import org.apache.wicket.markup.html.panel.Panel;

/**
 * @author Ernesto Reinaldo Barreiro
 *
 */
public class RoundPanel extends Panel {

	private static final long serialVersionUID = 1L;

	private static final ResourceReference CSS = new ResourceReference(RoundPanel.class,"test.css");
	
	
	/**
	 * @param id
	 */
	public RoundPanel(String id) {
		super(id);
		add(CSSPackageResource.getHeaderContribution(CSS));
	}
	
	@Override
	protected void onBeforeRender() {
		if(get("content")== null) {
			add(newContent("content"));
		}
		super.onBeforeRender();
	}
	
	protected Component newContent(String id) {
		return new EmptyPanel(id);
	}
}
