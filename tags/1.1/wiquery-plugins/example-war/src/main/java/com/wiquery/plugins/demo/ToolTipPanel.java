package com.wiquery.plugins.demo;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.CSSPackageResource;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.odlabs.wiquery.core.javascript.JsScopeContext;
import org.wiquery.plugin.jquertytools.JQueryToolsUiEvent;
import org.wiquery.plugin.jquertytools.tooltip.JQueryToolsOnBeforeShowUiEvent;
import org.wiquery.plugin.jquertytools.tooltip.TooltipAjaxBehaviour;
import org.wiquery.plugin.jquertytools.tooltip.TooltipBehavior;
import org.wiquery.plugin.jquertytools.tooltip.TooltipAjaxBehaviour.IToolTipOnBeforeShowUIEventHandler;
import org.wiquery.plugin.jquertytools.tooltip.TooltipBehavior.Offset;
import org.wiquery.plugin.jquertytools.tooltip.TooltipBehavior.Position;

/**
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class ToolTipPanel extends Panel {

	private static final long serialVersionUID = 1L;
    
	private int count = 0;
	
	private Label times;
	
    /**
	 * Constructor that is invoked when page is invoked without a session.
	 * 
	 * @param parameters
	 *            Page parameters
	 */
    public ToolTipPanel(String id) {
    	super(id);
    	
    	add(CSSPackageResource.getHeaderContribution(TooltipBehavior.CSS));
    	
    	WebMarkupContainer tooltip0 = new WebMarkupContainer("tooltip0");
    	tooltip0.add(new TooltipBehavior().setTipClass("blackarrow"));    
    	
    	add(tooltip0);
    	
    	WebMarkupContainer tooltip = new WebMarkupContainer("tooltip");
    	tooltip.add(new TooltipBehavior().setOpacity(0.9f));    	
    	add(tooltip);
    	
    	WebMarkupContainer tooltip2 = new WebMarkupContainer("tooltip2");
    	tooltip2.add(
    			new TooltipBehavior().setDelay(500)
    			.setPosition(Position.bottom_right)
    			.setPredelay(500)
    			.setRelative(true)
    			.setOffset(new Offset(-10,-10))
    			.setOnBeforeShow(new JQueryToolsOnBeforeShowUiEvent() {
					
					private static final long serialVersionUID = 1L;

					@Override
					protected void execute(JsScopeContext scopeContext) {
						scopeContext.append("$('#position').html('['+position.top + ',' + position.left+']');");
						
					}
				})
				.setOnHideEvent(new JQueryToolsUiEvent() {
					
					private static final long serialVersionUID = 1L;

					@Override
					protected void execute(JsScopeContext scopeContext) {
						scopeContext.append("$('#position').html('');");
					}
				}));    	
    	add(tooltip2);
    	
    	WebMarkupContainer withAjax = new WebMarkupContainer("withAjax");
    	withAjax.add(new TooltipAjaxBehaviour()
    	.setOffset(new Offset(30, 120))
    	.setOnBeforeShow(new IToolTipOnBeforeShowUIEventHandler() {
    	
    		private static final long serialVersionUID = 1L;

			public void onEvent(AjaxRequestTarget target, Component component, int top, int left) {
    			count++;
    			target.addComponent(times);
    		}
    		    		
    	}));
    	add(withAjax);
    	
    	times = new Label("times", new AbstractReadOnlyModel<String>() {
    		
    		private static final long serialVersionUID = 1L;

			@Override
    		public String getObject() {
    			if(count == 0)
    				return "";
    			return ":" + count + " times!";
    		}
    	});
    	times.setOutputMarkupId(true);
    	add(times);
    }
     
}
