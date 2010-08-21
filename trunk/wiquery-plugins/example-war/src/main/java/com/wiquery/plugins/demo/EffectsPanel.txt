package com.wiquery.plugins.demo;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.panel.Panel;
import org.odlabs.wiquery.core.effects.EffectSpeed;
import org.odlabs.wiquery.core.effects.basic.Hide;
import org.odlabs.wiquery.core.effects.basic.Show;
import org.odlabs.wiquery.core.effects.basic.Toggle;
import org.odlabs.wiquery.core.effects.fading.FadeIn;
import org.odlabs.wiquery.core.effects.fading.FadeOut;
import org.odlabs.wiquery.core.effects.sliding.SlideDown;
import org.odlabs.wiquery.core.effects.sliding.SlideUp;
import org.odlabs.wiquery.core.javascript.JsStatement;

import com.wiquery.plugins.antilia.link.ActionsPanel;
import com.wiquery.plugins.antilia.link.AjaxTextLinkPanel;

/**
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class EffectsPanel extends Panel {

	private static final long serialVersionUID = 1L;

    private WebMarkupContainer effects;
	
    private EffectSpeedPanel effectSpeedPanel;
    
    /**
	 * Constructor that is invoked when page is invoked without a session.
	 * 
	 * @param parameters
	 *            Page parameters
	 */
    public EffectsPanel(String id) {
    	super(id);
    	ActionsPanel actions  = new ActionsPanel("actions");    	
    	add(actions);
    	
    	actions.addItem(effectSpeedPanel = new EffectSpeedPanel(actions.newChildId(), EffectSpeed.SLOW));
    	
    	actions.addItem(new AjaxTextLinkPanel(actions.newChildId(),"Toggle") {
    		
    		private static final long serialVersionUID = 1L;

			public void onClick(AjaxRequestTarget target) {
				target.appendJavascript(new StringBuffer(new JsStatement().$(effects).chain(new Toggle(effectSpeedPanel.getSpeed())).render(true)).toString());
    		};    		
    	});

    	actions.addItem(new AjaxTextLinkPanel(actions.newChildId(),"Show") {
    		
    		private static final long serialVersionUID = 1L;

			public void onClick(AjaxRequestTarget target) {
				target.appendJavascript(new StringBuffer(new JsStatement().$(effects).chain(new Show(effectSpeedPanel.getSpeed())).render(true)).toString());
    		};    		
    	});
    	
    	
    	
    	actions.addItem(new AjaxTextLinkPanel(actions.newChildId(), "Hide"){
    		
    		private static final long serialVersionUID = 1L;

			@Override
    		public void onClick(AjaxRequestTarget target) {				
				target.appendJavascript(new StringBuffer(new JsStatement().$(effects).chain(new Hide(effectSpeedPanel.getSpeed())).render(true)).toString());
    		}
			
    	});
    	
    	
    	
    	actions.addItem(new AjaxTextLinkPanel(actions.newChildId(), "FadeIn"){
    		
    		private static final long serialVersionUID = 1L;

			@Override
    		public void onClick(AjaxRequestTarget target) {				
				target.appendJavascript(new StringBuffer(new JsStatement().$(effects).chain(new FadeIn(effectSpeedPanel.getSpeed())).render(true)).toString());
    		}
			
    	});    
    	
    	actions.addItem(new AjaxTextLinkPanel(actions.newChildId(), "FadeOut"){
    		
    		private static final long serialVersionUID = 1L;

			@Override
    		public void onClick(AjaxRequestTarget target) {				
				target.appendJavascript(new StringBuffer(new JsStatement().$(effects).chain(new FadeOut(effectSpeedPanel.getSpeed())).render(true)).toString());
    		}
			
    	});
    	
    	actions.addItem(new AjaxTextLinkPanel(actions.newChildId(), "SlideDown"){
    		
    		private static final long serialVersionUID = 1L;

			@Override
    		public void onClick(AjaxRequestTarget target) {				
				target.appendJavascript(new StringBuffer(new JsStatement().$(effects).chain(new SlideDown(effectSpeedPanel.getSpeed())).render(true)).toString());
    		}
			
    	});

    	actions.addItem(new AjaxTextLinkPanel(actions.newChildId(), "SlideUp"){
    		
    		private static final long serialVersionUID = 1L;
 
			@Override
    		public void onClick(AjaxRequestTarget target) {				
				target.appendJavascript(new StringBuffer(new JsStatement().$(effects).chain(new SlideUp(effectSpeedPanel.getSpeed())).render(true)).toString());
    		}
			
    	});
    	
    	
    	effects = new TablePanel("effects");
    	effects.setOutputMarkupId(true);
    	add(effects);
    	
    }
     
}
