package com.wiquery.plugins.antilia.link;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import com.wiquery.plugins.antilia.menu.IMenuItem;

/**
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public abstract class AjaxSubmitTextLinkPanel extends Panel implements IMenuItem {

	private static final long serialVersionUID = 1L;

	
	private AjaxSubmitLink link;
	
	/**
	 * Constructor.
	 * 
	 * @param id
	 * @param text
	 */
	public AjaxSubmitTextLinkPanel(String id, String text) {
		 this(id, new Model<String>(text));
	}
	
	public AjaxSubmitTextLinkPanel(String id, String text, String toolTip) {
		 this(id, new Model<String>(text), new Model<String>(toolTip));
	}
	
	/**
	 * Constructor.
	 * 
	 * @param id
	 * @param text
	 */
	public AjaxSubmitTextLinkPanel(String id, IModel<String> text) {
		 this(id, text, text);
	}
	 
	/**
	 * 
	 * @param id
	 * @param text
	 */
    public AjaxSubmitTextLinkPanel(String id, IModel<String> text, IModel<String> tooltip) {
    	super(id, text);
    	
    	WebMarkupContainer parent = new WebMarkupContainer("parent");
		parent.add(new AttributeModifier("class", new AbstractReadOnlyModel<String>() {
			
			private static final long serialVersionUID = 1L;

			@Override
			public String getObject() {
				if(AjaxSubmitTextLinkPanel.this.isEnabled())
					return "ui-state-default ui-corner-all";
				else 
					return "ui-state-default ui-state-disabled ui-corner-all";
			}
		}));
		
		add(parent);
		
    	link = new AjaxSubmitLink("link"){
    		
    		private static final long serialVersionUID = 1L;

    		@Override
    		protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
    			AjaxSubmitTextLinkPanel.this.onSubmit(target, form);
    		}
    		
    		@Override
    		protected void onError(AjaxRequestTarget target, Form<?> form) {
    			AjaxSubmitTextLinkPanel.this.onError(target, form);
    		}
    					
    	};
    	
    	parent.add(link);
    	
    	link.add(new Label("text", text));
    	link.add(new AttributeModifier("title", tooltip));    	
    	
    	link.add(new AttributeModifier("onmouseover", new AbstractReadOnlyModel<String>() {
			
			private static final long serialVersionUID = 1L;

			@Override
			public String getObject() {
				if(AjaxSubmitTextLinkPanel.this.isEnabled())
					return "this.parentNode.className = 'ui-state-default ui-corner-all ui-state-hover';";
				else
					return "this.parentNode.className = 'ui-state-default ui-state-disabled ui-corner-all';";
			}
		}));

		link.add(new AttributeModifier("onmouseout", new AbstractReadOnlyModel<String>() {
			
			private static final long serialVersionUID = 1L;

			@Override
			public String getObject() {
				if(AjaxSubmitTextLinkPanel.this.isEnabled())
					return "this.parentNode.className = 'ui-state-default ui-corner-all';";
				else 
					return "this.parentNode.className = 'ui-state-default ui-state-disabled ui-corner-all';";
			}
		}));
    }
    
    
    /**
     * Override to implement your onSubmit logic.
     * 
     * @param target
     * @param form
     */
    protected abstract void onSubmit(AjaxRequestTarget target, Form<?> form);
    
    /**
     * Override to implement on Error logic.
     * 
     * @param target
     * @param form
     */
    protected void onError(AjaxRequestTarget target, Form<?> form) {
    	
    }

	public AjaxSubmitLink getLink() {
		return link;
	}
	
	public Form<?> getForm() {
		return getLink().getForm();
	}
}
