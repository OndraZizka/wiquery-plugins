package com.wiquery.plugin.antilia.link;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.odlabs.wiquery.core.javascript.JsStatement;

import com.wiquery.plugin.antilia.menu.IMenuItem;

/**
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public abstract class ScriptTextLinkPanel extends Panel implements IMenuItem {

	private static final long serialVersionUID = 1L;

	
	/**
	 * Constructor.
	 * 
	 * @param id
	 * @param text
	 */
	public ScriptTextLinkPanel(String id, String text) {
		 this(id, new Model<String>(text));
	}
	
	public ScriptTextLinkPanel(String id, String text, String toolTip) {
		 this(id, new Model<String>(text), new Model<String>(toolTip));
	}
	
	/**
	 * Constructor.
	 * 
	 * @param id
	 * @param text
	 */
	public ScriptTextLinkPanel(String id, IModel<String> text) {
		 this(id, text, text);
	}
	 
	/**
	 * Constructor.
	 * 
	 * @param id
	 * @param text
	 */
    public ScriptTextLinkPanel(String id, IModel<String> text, IModel<String> tooltip) {
    	super(id, text);
    	
    	WebMarkupContainer link = new WebMarkupContainer("link");    	
    	add(link);
    	
    	link.add(new AttributeModifier("onclick", new AbstractReadOnlyModel<String>() {
    		
    		private static final long serialVersionUID = 1L;

			@Override
    		public String getObject() {
				JsStatement statement  = ScriptTextLinkPanel.this.getClickAction();
				if(statement == null)
					return "javascript:void(0);";
    			return statement.render(true).toString();
    		}
    	}));
    	
    	
    	link.add(new Label("text", text));
    	link.add(new AttributeModifier("title", tooltip));    	
    }
    
    /**
     * Ajax onclick callback.
     * 
     * @param target The {@link AjaxRequestTarget}
     */
    protected abstract JsStatement getClickAction();
    
}
