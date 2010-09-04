package com.wiquery.plugin.antilia.roundpane;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.Component;
import org.apache.wicket.ResourceReference;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.CSSPackageResource;
import org.apache.wicket.markup.html.JavascriptPackageResource;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.EmptyPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.IModel;
import org.odlabs.wiquery.core.commons.IWiQueryPlugin;
import org.odlabs.wiquery.core.commons.WiQueryResourceManager;
import org.odlabs.wiquery.core.javascript.JsStatement;
import org.odlabs.wiquery.ui.commons.WiQueryUIPlugin;

import com.wiquery.plugin.antilia.link.JQIcon;
import com.wiquery.plugin.antilia.link.JqAjaxLink;
import com.wiquery.plugin.antilia.link.JqScriptLink;
import com.wiquery.plugin.antilia.menu.IMenu;
import com.wiquery.plugin.antilia.menu.Menu;

/**
 * @author Ernesto Reinaldo Barreiro
 *
 */
@WiQueryUIPlugin
public class RoundPanel extends Panel implements IWiQueryPlugin  {

	private static final long serialVersionUID = 1L;

	private static final ResourceReference CSS = new ResourceReference(RoundPanel.class,"roundpanel.css");
	
	private static final ResourceReference JS = new ResourceReference(RoundPanel.class,"roundpanel.js");
	
	
	private WebMarkupContainer root;
	
	private WebMarkupContainer  body;
	
	private Integer width = 300;
	
	private Integer heigh = -1;
	
	private boolean foldable = true;
	
	private boolean ajaxFoldable = false;
	
	private boolean folded = false;
	
	private Menu menu;
			

	/**
	 * @param id
	 */
	public RoundPanel(String id, IModel<String> titleModel) {
		super(id);
		setRenderBodyOnly(true);
		add(CSSPackageResource.getHeaderContribution(CSS));
		add(JavascriptPackageResource.getHeaderContribution(JS));
		
		root = new WebMarkupContainer("root");
		root.setOutputMarkupId(true);
		
		body  = new WebMarkupContainer("body") {
			
			private static final long serialVersionUID = 1L;

			@Override
			protected void onBeforeRender() {
				if(get("content")== null) {
					add(RoundPanel.this.newContent("content"));
				}
				super.onBeforeRender();
			}
			
			@Override
			public String getMarkupId() {
				return root.getMarkupId()+"_body";
			}
		};
		body.setOutputMarkupId(true);		
		root.add(body);
		
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
					if(heigh.intValue() < 0) {
						sb.append("height: auto;");
					} else {
						sb.append("height: ")
						.append(heigh)
						.append("px;");						
					}
				}
				return sb.toString();
			}
		}));
		add(root);
		
		root.add(new Label("title", titleModel));								
	}
	
	@Override
	protected void onBeforeRender() {
		if(menu == null) {
			menu = new Menu("menu");
			menu.setOutputMarkupId(true);
			configureMenu(menu);
			root.addOrReplace(menu);
		}
		addOrReplace(new Label("script", new AbstractReadOnlyModel<String>(){
			
			private static final long serialVersionUID = 1L;

			@Override
			public String getObject() {
				return renderScript();
			}
		}).setEscapeModelStrings(false));
		super.onBeforeRender();
	}
	
	protected void configureMenu(IMenu menu) {
		configureFoldable(menu);
	}
	
	protected void configureFoldable(IMenu menu) {
		if(!isFoldable()) {
			return;
		}
		if(isAjaxFoldable()) {
			menu.addMenuItem(new JqAjaxLink(menu.newItemId(), JQIcon.ui_icon_circle_minus, "Fold") {
	    		
	    		private static final long serialVersionUID = 1L;
	
				
				@Override
				public void onClick(AjaxRequestTarget target) {
					setFolded(true);
					target.addComponent(RoundPanel.this.menu);
					//target.appendJavascript(new JsStatement().$(body).chain(new FadeOut(EffectSpeed.FAST)).render(true).toString());				
					target.appendJavascript(root.getMarkupId()+".toggleFold();");
				}
				
				@Override
				public boolean isVisible() {
					return !folded;
				}
	    	});
			
			menu.addMenuItem(new JqAjaxLink(menu.newItemId(), JQIcon.ui_icon_circle_plus, "Unfold") {
	    		
	    		private static final long serialVersionUID = 1L;
	
				
				@Override
				public void onClick(AjaxRequestTarget target) {
					setFolded(false);
					target.addComponent(RoundPanel.this.menu);
					//target.appendJavascript(new JsStatement().$(body).chain(new FadeIn(EffectSpeed.FAST)).render(true).toString());
					target.appendJavascript(root.getMarkupId()+".toggleFold();");
				}
				
				@Override
				public boolean isVisible() {
					return folded;
				}
	    	});
		} else {		
			menu.addMenuItem(new JqScriptLink(menu.newItemId(), isFolded()?JQIcon.ui_icon_circle_minus:JQIcon.ui_icon_circle_plus, "Toggle fold") {    		
	    		private static final long serialVersionUID = 1L;
	
	    		@Override
	    		protected String getClickAction() {
	    			String linkId = getLink().getMarkupId();
	    			String rootId = root.getMarkupId();
	    			return new StringBuffer()
	    				.append(rootId)
	    				.append(".toggleFold();")
	    				.append("var link = document.getElementById('")
	    				.append(linkId)
	    				.append("'); if(")
	    				.append(rootId)
	    				.append(".isFolded()){")
	    				.append("link.className='ui-icon ui-icon-circle-plus';")
	    				.append("} else {")
	    				.append("link.className='ui-icon ui-icon-circle-minus';")    				
	    				.append("};")
	    				.toString();
	    		}    	
			});
		}
	}
	
	private String renderScript() {
		StringBuffer sb = new StringBuffer();
		sb.append("var ")
		.append(root.getMarkupId())
		.append(" = new RoundPanel('")
		.append(root.getMarkupId())
		.append("',")
		.append(isFolded())
		.append(");");		
		return sb.toString();
	}
			
	protected Component newContent(String id) {
		return new EmptyPanel(id);
	}

	public boolean isFoldable() {
		return foldable;
	}

	public void setFoldable(boolean folded) {
		this.foldable = folded;
	}
	
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

	public boolean isFolded() {
		return folded;
	}

	public void setFolded(boolean folded) {
		this.folded = folded;
	}

	public boolean isAjaxFoldable() {
		return ajaxFoldable;
	}

	public void setAjaxFoldable(boolean ajaxFoldable) {
		this.ajaxFoldable = ajaxFoldable;
	}

	public void contribute(WiQueryResourceManager wiQueryResourceManager) {
		
	}

	public JsStatement statement() {
		return null;
	}
}
