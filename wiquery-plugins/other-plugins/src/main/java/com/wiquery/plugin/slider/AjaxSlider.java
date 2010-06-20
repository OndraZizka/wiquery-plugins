/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package com.wiquery.plugin.slider;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import org.apache.wicket.ajax.AbstractDefaultAjaxBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.behavior.AbstractAjaxBehavior;
import org.apache.wicket.protocol.http.WebRequestCycle;
import org.odlabs.wiquery.core.javascript.JsScopeContext;
import org.odlabs.wiquery.ui.core.JsScopeUiEvent;
import org.odlabs.wiquery.ui.slider.Slider;

/**
 * AJAX enabled slider.
 * 
 * @see Slider
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public class AjaxSlider extends Slider {

	private static final long serialVersionUID = 1L;

	private AbstractAjaxBehavior sliderContext;
	
	public static final String SLIDER_EVENT = "sliderEvent";
	
	public static final String SLIDER_VALUE = "sliderValue";
	
	public static final String SLIDER_VALUES = "sliderValues";
	
	/**
	 * Enumeration with the slider events.
	 * 
	 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
	 */
	public static enum SliderAjaxEvent {
		ajaxStopEvent,
		ajaxStartEvent,
		ajaxSlideEvent,
		ajaxChangeEvent		
	}
	
	/**
	 * Call back interface for AJAX Events.
	 * 
	 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
	 *
	 */
	public static interface ISliderAjaxEvent extends Serializable {
		
		/**
		 * Call-back method for slider AJAX events.
		 * 
		 * @param target The AjaxRequestTarget.
		 * @param slider The slider to which the event is attached.
		 * @param value The value of the first handle.
		 * @param values In case with have more than one handle an array with handle values. Otherwise it is null.
		 */
		public void onEvent(AjaxRequestTarget target, AjaxSlider slider, int value, int[] values);
	}
	
	/**
	 * Utility class for handling slider AJAX events.
	 * 
	 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
	 */
	private static class SliderAjaxJsScopeUiEvent extends JsScopeUiEvent {
	
		private static final long serialVersionUID = 1L;

		private SliderAjaxEvent event;
		
		private AjaxSlider slider;
		
		public SliderAjaxJsScopeUiEvent(AjaxSlider slider, SliderAjaxEvent event) {
			super();
			this.slider = slider;
			this.event = event;			
		}
		
		/*
		 * (non-Javadoc)
		 * @see org.odlabs.wiquery.core.javascript.JsScope#execute(org.odlabs.wiquery.core.javascript.JsScopeContext)
		 */
		@Override
		protected void execute(JsScopeContext scopeContext) {
			scopeContext.append(
					new StringBuffer()
					.append("var values = $(this).slider('values');")
					.append("var url = '").append(slider.sliderContext.getCallbackUrl(true))
					.append("&").append(SLIDER_EVENT).append("=").append(event.name())
					.append("&").append(SLIDER_VALUE).append("=").append("'+").append(Slider.UI_VALUE)
					.append("+'&").append(SLIDER_VALUES).append("=").append("'+").append("values")
					.append(";")					
					.append("wicketAjaxGet(url, null,null, function() {return true;})").toString());
			
		}
		
	}
	
	
	private Map<SliderAjaxEvent, ISliderAjaxEvent> ajaxEvents = new HashMap<SliderAjaxEvent, ISliderAjaxEvent>();
	
	
	/** 
	 * Constructor
	 * 
	 * @param id Markup identifiant
	 * @param min Minimum value
	 * @param max Maximum value
	 */
	public AjaxSlider(String id, Number min, Number max) {
		super(id, min, max);
		
		// Create a behavior needed for AJAX call-backs.		
		sliderContext = new AbstractDefaultAjaxBehavior() {
		
			private static final long serialVersionUID = 1L;

			@Override
			protected void respond(AjaxRequestTarget target) {
				String sliderEvent = WebRequestCycle.get().getRequest().getParameter(SLIDER_EVENT);			
				// if we have an event execute it.
				if(!isEmpty(sliderEvent)) {
					// calculate the value			
					int value = parseInteger(WebRequestCycle.get().getRequest().getParameter(SLIDER_VALUE), getMin().intValue());
					int[] values = processValues(WebRequestCycle.get().getRequest().getParameter(SLIDER_VALUES));
					ISliderAjaxEvent ajaxEvent = ajaxEvents.get(SliderAjaxEvent.valueOf(sliderEvent));
					if(ajaxEvent != null) {
						ajaxEvent.onEvent(target, AjaxSlider.this, value, values);
					}
				}
			}			
		};
		add(sliderContext);
	}
	
	protected int[] processValues(String svalues) {
		if(isEmpty(svalues) || svalues.indexOf(',')<0)
			return null;
		StringTokenizer st = new StringTokenizer(svalues,",");
		int size = st.countTokens();
		int[] values = new int[size];
		int i=0;
		while(st.hasMoreTokens()){
			values[i] = parseInteger(st.nextToken(), 0);
			i++;
		}
		return values;	
	}
	
	@Override
	public Number getMin() {
		if(this.getOptions().containsKey("min")){
			return this.getOptions().getFloat("min");
		}		
		return 0;
	}

	public static boolean isEmpty(String str) {
		return (str == null || str.trim().length() == 0);
	}
	
	/**
	 * Parses an integer.
	 * 
	 * @param value The value to parse.
	 * @return The parsed integer.
	 */
	private int parseInteger(String value, int defaultValue) {
		try {
			return Integer.parseInt(value);
		} catch (NumberFormatException e) {
			return 0;
		}
	}

	/**
	 * Sets the call-back for the AJAX stop event.
	 * 
	 * @param ajaxStopEvent The ISliderAjaxEvent.
	 */
	public void setAjaxStopEvent(ISliderAjaxEvent ajaxStopEvent) {
		this.ajaxEvents.put(SliderAjaxEvent.ajaxStopEvent, ajaxStopEvent);		
		setStopEvent(new SliderAjaxJsScopeUiEvent(this, SliderAjaxEvent.ajaxStopEvent));		
	}		
	
	/**
	 * Sets the call-back for the AJAX Slide Event.
	 * 
	 * @param ajaxSlideEvent The ISliderAjaxEvent.
	 */
	public void setAjaxSlideEvent(ISliderAjaxEvent ajaxSlideEvent) {
		this.ajaxEvents.put(SliderAjaxEvent.ajaxSlideEvent, ajaxSlideEvent);		
		setSlideEvent(new SliderAjaxJsScopeUiEvent(this, SliderAjaxEvent.ajaxSlideEvent));		
	}

	/**
	 * Sets the call-back for the AJAX Start Event.
	 * 
	 * @param ajaxStartEvent The ISliderAjaxEvent.
	 */
	public void setAjaxStartEvent(ISliderAjaxEvent ajaxStartEvent) {
		this.ajaxEvents.put(SliderAjaxEvent.ajaxStartEvent, ajaxStartEvent);		
		setSlideEvent(new SliderAjaxJsScopeUiEvent(this, SliderAjaxEvent.ajaxStartEvent));		
	}
	
	/**
	 * Sets the call-back for the AJAX Change Event.
	 * 
	 * @param ajaxChangeEvent
	 */
	public void setAjaxChangeEvent(ISliderAjaxEvent ajaxChangeEvent) {
		this.ajaxEvents.put(SliderAjaxEvent.ajaxChangeEvent, ajaxChangeEvent);		
		setChangeEvent(new SliderAjaxJsScopeUiEvent(this, SliderAjaxEvent.ajaxChangeEvent));		
	}
	
}