package com.wiquery.plugins.demo;

import org.apache.wicket.markup.html.CSSPackageResource;
import org.apache.wicket.markup.html.JavascriptPackageResource;
import org.apache.wicket.markup.html.panel.Panel;
import org.odlabs.wiquery.core.javascript.JsStatement;
import org.odlabs.wiquery.ui.effects.BounceEffect;
import org.odlabs.wiquery.ui.effects.BounceEffectJavaScriptResourceReference;
import org.odlabs.wiquery.ui.effects.ExplodeEffect;
import org.odlabs.wiquery.ui.effects.ExplodeEffectJavaScriptResourceReference;
import org.odlabs.wiquery.ui.effects.HighlightEffect;
import org.odlabs.wiquery.ui.effects.HighlightEffectJavaScriptResourceReference;
import org.odlabs.wiquery.ui.effects.PulsateEffect;
import org.odlabs.wiquery.ui.effects.PulsateEffectJavaScriptResourceReference;
import org.odlabs.wiquery.ui.effects.ScaleEffect;
import org.odlabs.wiquery.ui.effects.ScaleEffectJavaScriptResourceReference;
import org.odlabs.wiquery.ui.effects.ShakeEffect;
import org.odlabs.wiquery.ui.effects.ShakeEffectJavaScriptResourceReference;
import org.odlabs.wiquery.ui.effects.BounceEffect.BounceDirection;
import org.odlabs.wiquery.ui.effects.BounceEffect.BounceMode;
import org.odlabs.wiquery.ui.effects.ExplodeEffect.Mode;
import org.odlabs.wiquery.ui.effects.ScaleEffect.Scale;
import org.odlabs.wiquery.ui.effects.ShakeEffect.Direction;
import org.wiquery.plugin.jquertytools.tooltip.TooltipBehavior;

import com.wiquery.plugins.antilia.link.ActionsPanel;
import com.wiquery.plugins.antilia.link.ScriptTextLinkPanel;

/**
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class UIEffectsPanel extends Panel {

	private static final long serialVersionUID = 1L;
    
    /**
	 * Constructor that is invoked when page is invoked without a session.
	 * 
	 * @param parameters
	 *            Page parameters
	 */
    public UIEffectsPanel(String id) {
    	super(id);
    	
    	add(CSSPackageResource.getHeaderContribution(TooltipBehavior.CSS));
    	//add(JavascriptPackageResource.getHeaderContribution(PuffEffect.get()));
    	add(JavascriptPackageResource.getHeaderContribution(ExplodeEffectJavaScriptResourceReference.get()));
    	add(JavascriptPackageResource.getHeaderContribution(ShakeEffectJavaScriptResourceReference.get()));
    	add(JavascriptPackageResource.getHeaderContribution(ScaleEffectJavaScriptResourceReference.get()));
    	add(JavascriptPackageResource.getHeaderContribution(PulsateEffectJavaScriptResourceReference.get()));
    	add(JavascriptPackageResource.getHeaderContribution(BounceEffectJavaScriptResourceReference.get()));
    	add(JavascriptPackageResource.getHeaderContribution(HighlightEffectJavaScriptResourceReference.get()));
    	
    	ActionsPanel actions = new ActionsPanel("actions");
    	
    	add(actions);
    	
    	actions.addItem(new ScriptTextLinkPanel(actions.newChildId(), "Show Explode") {
    		
    		private static final long serialVersionUID = 1L;

			@Override
    		protected JsStatement getClickAction() {
    			return new JsStatement().$(null, "#explode").chain(new ExplodeEffect(Mode.show, 16, 2000));
    		}
    	});

    	actions.addItem(new ScriptTextLinkPanel(actions.newChildId(), "Shake") {
    		
    		private static final long serialVersionUID = 1L;

			@Override
    		protected JsStatement getClickAction() {
				return new JsStatement().$(null, "#explode").chain(new ShakeEffect(Direction.down));
    		}
    	});  
    	
    	actions.addItem(new ScriptTextLinkPanel(actions.newChildId(), "Scale UP") {
    		
    		private static final long serialVersionUID = 1L;

			@Override
    		protected JsStatement getClickAction() {
    			return new JsStatement().$(null, "#explode").chain(
    					new ScaleEffect(org.odlabs.wiquery.ui.effects.ScaleEffect.Direction.both, 
    							Scale.box,
    							120, 1000));
    		}
    	});
    	
    	actions.addItem(new ScriptTextLinkPanel(actions.newChildId(), "Scale Down") {
    		
    		private static final long serialVersionUID = 1L;

			@Override
    		protected JsStatement getClickAction() {
				return new JsStatement().$(null, "#explode").chain(
    					new ScaleEffect(org.odlabs.wiquery.ui.effects.ScaleEffect.Direction.both, 
    							Scale.box,
    							80, 1000));
    		}
    	});
    	
    	actions.addItem(new ScriptTextLinkPanel(actions.newChildId(), "Pulsate") {
    		
    		private static final long serialVersionUID = 1L;

			@Override
    		protected JsStatement getClickAction() {
				return new JsStatement().$(null, "#explode").chain(new PulsateEffect());
    		}
    	});
    	
    	actions.addItem(new ScriptTextLinkPanel(actions.newChildId(), "Bounce") {
    		
    		private static final long serialVersionUID = 1L;

			@Override
    		protected JsStatement getClickAction() {
				return new JsStatement().$(null, "#explode")
				.chain(new BounceEffect(BounceDirection.up, BounceMode.show, 5, 40, 200));
    		}
    	});
    	
    	actions.addItem(new ScriptTextLinkPanel(actions.newChildId(), "Highlight") {
    		
    		private static final long serialVersionUID = 1L;

			@Override
    		protected JsStatement getClickAction() {
				return new JsStatement().$(null, "#explode")
				.chain(new HighlightEffect());
    		}
    	});
    }
     
}
