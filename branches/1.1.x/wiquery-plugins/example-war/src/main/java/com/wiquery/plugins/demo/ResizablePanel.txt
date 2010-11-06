package com.wiquery.plugins.demo;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.odlabs.wiquery.ui.resizable.ResizableAjaxBehavior;

/**
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class ResizablePanel extends Panel {

	private static final long serialVersionUID = 1L;

	private int height=200, width=200;
	
	/**
	 * Constructor that is invoked when page is invoked without a session.
	 * 
	 * @param parameters
	 *            Page parameters
	 */
    public ResizablePanel(String id) {
    	super(id);
    	
    	Label size = new Label("size", new AbstractReadOnlyModel<String>(){
    		
			private static final long serialVersionUID = 1L;

			@Override
    		public String getObject() {
    			return "Current height is <span class=\"ui-state-highlight\">"+ height 
    			+ "</span> and current width is <span class=\"ui-state-highlight\">" + width 
    			+ "</span>";
    		}
    	}); 
    	
    	add(size);
    	size.setOutputMarkupId(true);
    	size.setEscapeModelStrings(false);
    	
    	WebMarkupContainer resizable = new WebMarkupContainer("resizable");
    	ResizableAjaxBehavior ajaxBehavior = new ResizableAjaxBehavior(){
    		
    		private static final long serialVersionUID = 1L;

			@Override
    		public void onResize(int height, int width, AjaxRequestTarget ajaxRequestTarget) {
				ResizablePanel.this.height = height;
				ResizablePanel.this.width = width;
				ajaxRequestTarget.addComponent(ResizablePanel.this.get("size"));
    		}
    	};    	
    	
    	ajaxBehavior.getResizableBehavior()
    					 .setGhost(true)
    					 .setAnimate(true)
    					 .setAutoHide(true);
    	
    	resizable.add(ajaxBehavior);
    	add(resizable);
    	
    }
}
