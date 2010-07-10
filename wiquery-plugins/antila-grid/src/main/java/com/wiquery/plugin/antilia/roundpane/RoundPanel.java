package com.wiquery.plugin.antilia.roundpane;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.Component;
import org.apache.wicket.ResourceReference;
import org.apache.wicket.markup.html.CSSPackageResource;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.EmptyPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.IModel;

/**
 * @author Ernesto Reinaldo Barreiro
 *
 */
public class RoundPanel extends Panel {

	private static final long serialVersionUID = 1L;

	private static final ResourceReference CSS = new ResourceReference(RoundPanel.class,"test.css");
	
	private WebMarkupContainer root;
	
	private Integer width = 300;
	
	/**
	 * @param id
	 */
	public RoundPanel(String id, IModel<String> titleModel) {
		super(id);
		setRenderBodyOnly(true);
		add(CSSPackageResource.getHeaderContribution(CSS));
		root = new WebMarkupContainer("root") {
			
			private static final long serialVersionUID = 1L;

			@Override
			protected void onBeforeRender() {
				if(get("content")== null) {
					add(RoundPanel.this.newContent("content"));
				}
				super.onBeforeRender();
			}
		};
		root.setOutputMarkupId(true);
		root.add(new AttributeModifier("style", new AbstractReadOnlyModel<String>() {
			
			private static final long serialVersionUID = 1L;

			@Override
			public String getObject() {				
				StringBuffer sb = new StringBuffer();
				if(width != null) {
					sb.append("width: ")
					.append(width)
					.append("px");					
				}
				return sb.toString();
			}
		}));
		add(root);
		
		root.add(new Label("title", titleModel));
		
	}
			
	protected Component newContent(String id) {
		return new EmptyPanel(id);
	}
}
