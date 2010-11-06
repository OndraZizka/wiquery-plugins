package com.wiquery.plugins.demo;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.IAjaxCallDecorator;
import org.apache.wicket.ajax.calldecorator.AjaxCallDecorator;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.panel.Panel;
import org.odlabs.wiquery.core.effects.EffectSpeed;
import org.odlabs.wiquery.core.effects.fading.FadeOut;
import org.odlabs.wiquery.core.effects.sliding.SlideDown;
import org.odlabs.wiquery.core.javascript.JsScope;
import org.odlabs.wiquery.core.javascript.JsScopeContext;
import org.odlabs.wiquery.core.javascript.JsStatement;
import org.odlabs.wiquery.plugin.layout.test.LabelPanel;

public class TestLinkEffectsPanel extends Panel {

	private static final long serialVersionUID = 1L;

	private WebMarkupContainer context;
	
	private Component contents;
	
	private int counter = 1;
	
	public TestLinkEffectsPanel(String id) {
		super(id);
		context = new WebMarkupContainer("context");
		context.setOutputMarkupId(true);
		contents = new LabelPanel("content", Integer.toString(counter)).setOutputMarkupId(true);
		context.add(contents);
		add(context);
		
		AjaxLink<Void> link = new AjaxLink<Void>("link") {
			
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick(AjaxRequestTarget target) {
				counter++;
				contents = new LabelPanel("content", Integer.toString(counter)).setOutputMarkupId(true);
				context.replace(contents);
				target.addComponent(context);
			}
			
			@Override
			protected IAjaxCallDecorator getAjaxCallDecorator() {
				return new AjaxCallDecorator(){
					
					private static final long serialVersionUID = 1L;
					
					@Override
					public CharSequence decorateOnSuccessScript(final CharSequence script) {
						SlideDown effect = new SlideDown(EffectSpeed.SLOW);						
						return new JsStatement().$(contents).chain(effect).render(true).toString()+script+ ";";
					}
					
					@Override
					public CharSequence decorateScript(final CharSequence script) {						 
						FadeOut effect = new FadeOut(EffectSpeed.SLOW);
						effect.setCallback(new JsScope(){							
							private static final long serialVersionUID = 1L;

							@Override
							protected void execute(JsScopeContext scopeContext) {
								scopeContext.append(script);
							}
						});
						return new JsStatement().$(contents).chain(effect).render(true);
					}
				};
			}
		};
		add(link);
		
	}
}
