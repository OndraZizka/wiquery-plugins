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
package org.odlabs.wiquery.wijmo.wijaccordion;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.odlabs.wiquery.core.commons.IWiQueryPlugin;
import org.odlabs.wiquery.core.commons.WiQueryResourceManager;
import org.odlabs.wiquery.core.events.MouseEvent;
import org.odlabs.wiquery.core.javascript.JsQuery;
import org.odlabs.wiquery.core.javascript.JsStatement;
import org.odlabs.wiquery.core.options.Options;
import org.odlabs.wiquery.ui.commons.WiQueryUIPlugin;
import org.odlabs.wiquery.ui.widget.WidgetJavascriptResourceReference;
import org.odlabs.wiquery.wijmo.wijutil.WijUtilJavascriptResourceReference;

/**
 * $Id$
 * 
 * <p>
 * 	Bind the Wijmo accordion widget
 * </p>
 * 
 * <p>
 * Examples of use:
 * 
 * HTML:
 * <div id="accordion">
        <div>
            <h3>
                <a href="#">First</a></h3>
            <div>
                <p>
                    Mauris mauris ante, blandit et, ultrices a, suscipit eget, quam. Integer ut neque.
                    Vivamus nisi metus, molestie vel, gravida in, condimentum sit amet, nunc. Nam a
                    nibh. Donec suscipit eros. Nam mi. Proin viverra leo ut odio. Curabitur malesuada.
                    Vestibulum a velit eu ante scelerisque vulputate.
                </p>
            </div>
        </div>
        <div>
            <h3>
                <a href="#">Second</a></h3>
            <div>
                <p>
                    Sed non urna. Donec et ante. Phasellus eu ligula. Vestibulum sit amet purus. Vivamus
                    hendrerit, dolor at aliquet laoreet, mauris turpis porttitor velit, faucibus interdum
                    tellus libero ac justo. Vivamus non quam. In suscipit faucibus urna.
                </p>
            </div>
        </div>
        <div>
            <h3>
                <a href="#">Third</a></h3>
            <div>
                <p>
                    Nam enim risus, molestie et, porta ac, aliquam ac, risus. Quisque lobortis. Phasellus
                    pellentesque purus in massa. Aenean in pede. Phasellus ac libero ac tellus pellentesque
                    semper. Sed ac felis. Sed commodo, magna quis lacinia ornare, quam ante aliquam
                    nisi, eu iaculis leo purus venenatis dui.
                </p>
            </div>
        </div>
    </div>
 * 
 * Java:
 * add(new WijAccordion("accordion"));
 * </p>
 * 
 * MISS the global animation registration. Example:
 * jQuery.ui.wijaccordion.animations.custom1
 *
 * @author Julien Roche
 * @since 1.0
 */
@WiQueryUIPlugin
public class WijAccordion extends WebMarkupContainer implements IWiQueryPlugin {
	/**
	 * 
	 * <p>
	 * 	Enumeration for the expandDirection option
	 * </p>
	 *
	 * @author Julien Roche
	 * @since 1.0
	 */
	public enum WijMenuExpandDirection {
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
	private static final long serialVersionUID = 4155210080836025133L;
	
	// Properties
	private Options options;

	/**
	 * @see Component#Component(String)
	 */
	public WijAccordion(String id) {
		super(id);
		this.options = new Options();
	}

	/**
	 * {@inheritDoc}
	 * @see org.odlabs.wiquery.core.commons.IWiQueryPlugin#contribute(org.odlabs.wiquery.core.commons.WiQueryResourceManager)
	 */
	public void contribute(WiQueryResourceManager wiQueryResourceManager) {
		wiQueryResourceManager.addJavaScriptResource(WidgetJavascriptResourceReference.get());
		wiQueryResourceManager.addJavaScriptResource(WijUtilJavascriptResourceReference.get());
		wiQueryResourceManager.addJavaScriptResource(WijAccordionJavascriptResourceReference.get());
		
		wiQueryResourceManager.addCssResource(WijAccordionStyleSheetResourceReference.get());
	}
	
	/**
	 * @return the animated option
	 */
	public String getAnimated() {
		String effect = getOptions().getLiteral("animated");
		return effect == null ? "slide" : effect;
	}
	
	/**
	 * @return the duration option
	 */
	public int getDuration() {
		Integer integer = getOptions().getInt("duration");
		return integer == null ? 0 : integer;
	}
	
	/**
	 * @return the event option
	 */
	public String getEvent() {
		String str = getOptions().getLiteral("event");
		return str == null ? MouseEvent.CLICK.getEventLabel() : str;
	}
	
	/**
	 * @return the expandDirection option
	 */
	public WijMenuExpandDirection getExpandDirection() {
		String str = getOptions().getLiteral("expandDirection");
		return str == null ? WijMenuExpandDirection.BOTTOM : WijMenuExpandDirection.valueOf(str.toUpperCase());
	}
	
	/**
	 * @return the header option
	 */
	public String getHeader() {
		String str = getOptions().getLiteral("header");
		return str == null ? "> li > :first-child,> :not(li):even" : str;
	}
	
	/**
	 * @return the options of the expander
	 */
	protected Options getOptions() {
		return this.options;
	}
	
	/**
	 * @return the selectedIndex option
	 */
	public int getSelectedIndex() {
		Integer integer = getOptions().getInt("selectedIndex");
		return integer == null ? 0 : integer;
	}
	
	/**
	 * @return the disabled option
	 */
	public boolean isDisabled() {
		Boolean bool = getOptions().getBoolean("disabled");
		return bool == null ? false : bool;
	}
	
	/**
	 * @return the requireOpenedPane option
	 */
	public boolean isRequireOpenedPane() {
		Boolean bool = getOptions().getBoolean("requireOpenedPane");
		return bool == null ? true : bool;
	}
	
	/**
	 * Determines the animation easing effect name
	 * @param animated
	 * @return the current instance
	 */
	public WijAccordion setAnimated(String animated) {
		getOptions().putLiteral("animated", animated);
		return this;
	}
	
	/**
	 * Determines whether the widget behavior is disabled.
	 * @param disabled
	 * @return the current instace
	 */
	public WijAccordion setDisabled(boolean disabled) {
		getOptions().put("disabled", disabled);
		return this;
	}
	
	/**
	 * Set the animation duration in milliseconds.
	 * @param duration
	 * @return the current instance
	 */
	public WijAccordion setDuration(int duration) {
		getOptions().put("duration", duration);
		return this;
	}
	
	/**
	 * Determines the event that triggers the accordion.
	 * @param event
	 * @return the current instance
	 */
	public WijAccordion setEvent(String event) {
		getOptions().putLiteral("event", event);
		return this;
	}
	
	/**
	 * Determines the direction in which the content area of the control expands.
	 * @param expandDirection
	 * @return the current instance
	 */
	public WijAccordion setExpandDirection(WijMenuExpandDirection expandDirection) {
		getOptions().putLiteral("expandDirection", expandDirection.toString());
		return this;
	}
	
	/**
	 * Sets the index of the currently expanded accordion pane
	 * @param header
	 * @return the current instance
	 */
	public WijAccordion setHeader(String header) {
		getOptions().putLiteral("header", header);
		return this;
	}
	
	/**
	 * Determines whether clicking the header will close the currently opened pane 
	 * (leaving all the accordion's panes closed).
	 * @param requireOpenedPane
	 * @return the current instace
	 */
	public WijAccordion setRequireOpenedPane(boolean requireOpenedPane) {
		getOptions().put("requireOpenedPane", requireOpenedPane);
		return this;
	}
	
	/**
	 * Sets the index of the currently expanded accordion pane
	 * @param selectedIndex
	 * @return the current instance
	 */
	public WijAccordion setSelectedIndex(int selectedIndex) {
		getOptions().put("selectedIndex", selectedIndex);
		return this;
	}

	/**
	 * {@inheritDoc}
	 * @see org.odlabs.wiquery.core.commons.IWiQueryPlugin#statement()
	 */
	public JsStatement statement() {
		return new JsQuery(this).$().chain("wijaccordion", getOptions().getJavaScriptOptions());
	}
}
