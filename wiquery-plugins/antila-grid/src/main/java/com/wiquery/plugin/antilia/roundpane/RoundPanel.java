package com.wiquery.plugin.antilia.roundpane;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.Component;
import org.apache.wicket.ResourceReference;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.CSSPackageResource;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.EmptyPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.IModel;

import com.wiquery.plugins.antilia.link.JQIcon;
import com.wiquery.plugins.antilia.link.JqAjaxLink;
import com.wiquery.plugins.antilia.menu.IMenu;
import com.wiquery.plugins.antilia.menu.Menu;

/**
 * @author Ernesto Reinaldo Barreiro
 *
 */
public class RoundPanel extends Panel {

	private static final long serialVersionUID = 1L;

	private static final ResourceReference CSS = new ResourceReference(RoundPanel.class,"test.css");
	
	private WebMarkupContainer root;
	
	private Integer width = 300;
	
	private Integer heigh = 400;
	
	public Integer getHeigh() {
		return heigh;
	}

	public void setHeigh(Integer heigh) {
		this.heigh = heigh;
	}

	public Integer getWidth() {
		return width;
	}

	public void setWidth(Integer width) {
		this.width = width;
	}

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
					.append("px;");					
				}
				if(heigh != null) {
					sb.append("height: ")
					.append(heigh)
					.append("px;");
				}
				return sb.toString();
			}
		}));
		add(root);
		
		root.add(new Label("title", titleModel));				
		Menu menu = new Menu("menu");
		configureMenu(menu);
		root.add(menu);		
	}
	
	protected void configureMenu(IMenu menu) {
		menu.addMenuItem(new JqAjaxLink(menu.newItemId(), JQIcon.ui_icon_circle_close, "Close") {
			
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick(AjaxRequestTarget target) {
					
			}
		});
		
		menu.addMenuItem(new JqAjaxLink(menu.newItemId(), JQIcon.ui_icon_circle_plus, "Close") {
			
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick(AjaxRequestTarget target) {
					
			}
		});
	}
			
	protected Component newContent(String id) {
		return new EmptyPanel(id);
	}
}
