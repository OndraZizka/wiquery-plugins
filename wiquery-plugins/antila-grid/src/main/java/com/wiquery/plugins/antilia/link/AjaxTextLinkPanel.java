package com.wiquery.plugins.antilia.link;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import com.wiquery.plugins.antilia.menu.IMenuItem;

/**
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public abstract class AjaxTextLinkPanel extends Panel implements IMenuItem {

	private static final long serialVersionUID = 1L;

	
	/**
	 * Constructor.
	 * 
	 * @param id
	 * @param text
	 */
	public AjaxTextLinkPanel(String id, String text) {
		 this(id, new Model<String>(text));
	}
	
	public AjaxTextLinkPanel(String id, String text, String toolTip) {
		 this(id, new Model<String>(text), new Model<String>(toolTip));
	}
	
	/**
	 * Constructor.
	 * 
	 * @param id
	 * @param text
	 */
	public AjaxTextLinkPanel(String id, IModel<String> text) {
		 this(id, text, text);
	}
	 
	/**
	 * 
	 * @param id
	 * @param text
	 */
    public AjaxTextLinkPanel(String id, IModel<String> text, IModel<String> tooltip) {
    	super(id, text);
    	
    	AjaxLink<Void> link = new AjaxLink<Void>("link"){
    		
    		private static final long serialVersionUID = 1L;

			@Override
    		public void onClick(AjaxRequestTarget target) {				
				AjaxTextLinkPanel.this.onClick(target);
    		}
			
    	};
    	
    	add(link);
    	
    	link.add(new Label("text", text));
    	link.add(new AttributeModifier("title", tooltip));    	
    }
    
    /**
     * Ajax onclick callback.
     * 
     * @param target The {@link AjaxRequestTarget}
     */
    public abstract void onClick(AjaxRequestTarget target);
    
}
