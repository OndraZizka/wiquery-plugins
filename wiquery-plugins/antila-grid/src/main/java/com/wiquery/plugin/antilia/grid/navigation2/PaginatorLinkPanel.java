/**
 * 
 */
package com.wiquery.plugin.antilia.grid.navigation2;

import java.io.Serializable;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import com.wiquery.plugin.antilia.grid.IPageableComponent;

/**
 * @author Ernesto Reinaldo Barreiro
 *
 */
public abstract class PaginatorLinkPanel<E extends Serializable> extends Panel {

	private static final long serialVersionUID = 1L;

	AjaxLink<Void> link;
	
	private String cssClass;
	
	/**
	 * 
	 * @param id The id of the component.
	 * @param cssClass The css class of the link (it might be null)
	 * @param text The text of the link.
	 */
	public PaginatorLinkPanel(String id, String cssClass, IModel<String> text) {
		super(id, text);
		this.cssClass = cssClass;
		setRenderBodyOnly(true);				
	}
	
	@Override
	protected void onBeforeRender() {
		if(link == null) {
			link = new AjaxLink<Void>("link") {
				
				private static final long serialVersionUID = 1L;
	
				@Override
				public void onClick(AjaxRequestTarget target) {
					PaginatorLinkPanel.this.onClick(target);
				}
			};
			//link.setAfterDisabledLink("</b>");
			//link.setBeforeDisabledLink("<b>");
			if(cssClass != null)
				link.add(new AttributeModifier("class", true, new Model<String>(cssClass)));
			add(link);		
			link.add(new Label("text", getTextModel()).setEscapeModelStrings(false).setRenderBodyOnly(true));
		}
		super.onBeforeRender();
	}
	
	@SuppressWarnings("unchecked")
	protected IModel<String> getTextModel() {
		return (IModel<String>)getDefaultModel();
	}
	
	/**
	 * 
	 * @param id The id of the component.
	 * @param cssClass The css class of the link (it might be null)
	 * @param text The text of the link.
	 */
	public PaginatorLinkPanel(String id, String cssClass, String text) {
		this(id, cssClass, new Model<String>(text));		
	}
	
	public abstract void onClick(AjaxRequestTarget target);
	
	@SuppressWarnings("unchecked")
	protected final IPageableComponent<E> findPageableComponent() {
		return (IPageableComponent<E>)findParent(IPageableComponent.class);
	}
}
