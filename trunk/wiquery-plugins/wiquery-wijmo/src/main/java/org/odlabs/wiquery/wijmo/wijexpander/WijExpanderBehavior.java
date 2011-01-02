/*
 * Copyright (c) 2010 WiQuery team
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package org.odlabs.wiquery.wijmo.wijexpander;

import org.odlabs.wiquery.core.behavior.WiQueryAbstractBehavior;
import org.odlabs.wiquery.core.commons.WiQueryResourceManager;
import org.odlabs.wiquery.core.javascript.JsQuery;
import org.odlabs.wiquery.core.javascript.JsStatement;
import org.odlabs.wiquery.core.options.Options;
import org.odlabs.wiquery.ui.commons.WiQueryUIPlugin;
import org.odlabs.wiquery.ui.widget.WidgetJavascriptResourceReference;
import org.odlabs.wiquery.wijmo.wijutil.WijUtilJavascriptResourceReference;
import org.odlabs.wiquery.wijmo.wijutil.WijUtilStyleSheetResourceReference;

/**
 * $Id$
 * 
 * <p>
 * 	Wijmo expander widget
 * </p>
 * 
 * <p>
 * Examples of use:
 * 
 * HTML:
 * <div wicket:id='expander'></div>
 * 
 * Java:
 * expander.add(new WijExpanderBehavior());
 * </p>
 * 
 * <p>
 * Miss the possibility to add custom effect:
 * 
 * jQuery.ui.wijexpander.animations.custom1 = function (options) {
	    this.slide(options, {
	        easing: "easeInBounce",
	        duration: 900
	    });
	}
 * </p>
 *
 * @author Julien Roche
 * @since 1.0
 */
@WiQueryUIPlugin
public class WijExpanderBehavior extends WiQueryAbstractBehavior {
	/**
	 * 
	 * <p>
	 * 	Enumeration for the expandDirection option
	 * </p>
	 *
	 * @author Julien Roche
	 * @since 1.0
	 */
	public enum ExpandDirectionEnum {
		BOTTOM,
		LEFT,
		RIGHT,
		TOP;

		/**
		 * {@inheritDoc}
		 * @see java.lang.Enum#toString()
		 */
		@Override
		public String toString() {
			return super.toString().toLowerCase();
		}
	}
	
	// Constants
	/** Constant of serialization */
	private static final long serialVersionUID = 1L;
	
	// Properties
	private Options options;
	
	/**
	 * Default constructor
	 */
	public WijExpanderBehavior() {
		super();
		this.options = new Options();
	}
	
	/**
	 * {@inheritDoc}
	 * @see org.odlabs.wiquery.core.behavior.WiQueryAbstractBehavior#contribute(org.odlabs.wiquery.core.commons.WiQueryResourceManager)
	 */
	@Override
	public void contribute(WiQueryResourceManager wiQueryResourceManager) {
		super.contribute(wiQueryResourceManager);
		wiQueryResourceManager.addJavaScriptResource(WidgetJavascriptResourceReference.get());
		wiQueryResourceManager.addJavaScriptResource(WijUtilJavascriptResourceReference.get());
		wiQueryResourceManager.addJavaScriptResource(WijExpanderJavascriptResourceReference.get());
		wiQueryResourceManager.addCssResource(WijUtilStyleSheetResourceReference.get());
		wiQueryResourceManager.addCssResource(WijExpanderStyleSheetResourceReference.get());
	}

	/**
	 * @return the animated option
	 */
	public String getAnimated() {
		String effect = getOptions().getLiteral("animated");
		return effect == null ? "slide" : effect;
	}

	/**
	 * @return the contentUrl option
	 */
	public String getContentUrl() {
		String contentUrl = getOptions().getLiteral("contentUrl");
		return contentUrl == null ? "" : contentUrl;
	}
	
	/**
	 * @return the expandDirection option
	 */
	public ExpandDirectionEnum getExpandDirection() {
		String expandDirection = getOptions().getLiteral("expandDirection");
		return expandDirection == null ? 
				ExpandDirectionEnum.BOTTOM : 
					ExpandDirectionEnum.valueOf(expandDirection.toUpperCase());
	}
	
	/**
	 * @return the options of the expander
	 */
	protected Options getOptions() {
		return this.options;
	}
	
	/**
	 * @return the allowExpand option
	 */
	public boolean isAllowExpand() {
		Boolean bool = getOptions().getBoolean("allowExpand");
		return bool == null ? true : bool;
	}
	
	/**
	 * @return the expanded option
	 */
	public boolean isExpanded() {
		Boolean bool = getOptions().getBoolean("expanded");
		return bool == null ? true : bool;
	}
	
	/**
	 * Determines if the widget can expand. Set this option to false if you want 
	 * to disable collapse/expand ability.
	 * @param allowExpand
	 * @return the current instance
	 */
	public WijExpanderBehavior setAllowExpand(boolean allowExpand) {
		getOptions().put("allowExpand", allowExpand);
		return this;
	}
	
	/**
	 * Determines the animation easing effect name
	 * @param animated
	 * @return the current instance
	 */
	public WijExpanderBehavior setAnimated(String animated) {
		getOptions().putLiteral("animated", animated);
		return this;
	}
	
	/**
	 * Determines the URL to the external content. For example, 
	 * http://componentone.com/ for the ComponentOne Web site.
	 * @param contentUrl
	 * @return the current instance
	 */
	public WijExpanderBehavior setContentUrl(String contentUrl) {
		getOptions().putLiteral("contentUrl", contentUrl);
		return this;
	}
	
	/**
	 * Determines the visibility state of the content panel. If true, the content 
	 * element is visible. 
	 * @param expanded
	 * @return the current instance
	 */
	public WijExpanderBehavior setExpanded(boolean expanded) {
		getOptions().put("expanded", expanded);
		return this;
	}
	
	/**
	 * Determines the content expand direction
	 * @param expandDirection
	 * @return the current instance
	 */
	public WijExpanderBehavior setExpanded(ExpandDirectionEnum expandDirection) {
		getOptions().putLiteral("expandDirection", expandDirection.toString());
		return this;
	}
	
	/**
	 * {@inheritDoc}
	 * @see org.odlabs.wiquery.core.behavior.WiQueryAbstractBehavior#statement()
	 */
	@Override
	public JsStatement statement() {
		return new JsQuery(getComponent()).$().chain("wijexpander", options.getJavaScriptOptions());
	}
}
