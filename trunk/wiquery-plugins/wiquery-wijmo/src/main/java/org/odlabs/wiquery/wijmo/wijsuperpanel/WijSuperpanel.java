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
package org.odlabs.wiquery.wijmo.wijsuperpanel;

import org.odlabs.wiquery.core.behavior.WiQueryAbstractBehavior;
import org.odlabs.wiquery.core.commons.IWiQueryPlugin;
import org.odlabs.wiquery.core.commons.WiQueryResourceManager;
import org.odlabs.wiquery.core.effects.AnimateDuration;
import org.odlabs.wiquery.core.javascript.JsQuery;
import org.odlabs.wiquery.core.javascript.JsScope;
import org.odlabs.wiquery.core.javascript.JsStatement;
import org.odlabs.wiquery.core.options.LiteralOption;
import org.odlabs.wiquery.core.options.Options;
import org.odlabs.wiquery.mousewheel.MouseWheelJavascriptResourceReference;
import org.odlabs.wiquery.ui.commons.WiQueryUIPlugin;
import org.odlabs.wiquery.ui.draggable.DraggableJavaScriptResourceReference;
import org.odlabs.wiquery.ui.effects.CoreEffectJavaScriptResourceReference;
import org.odlabs.wiquery.ui.mouse.MouseJavascriptResourceReference;
import org.odlabs.wiquery.ui.resizable.ResizableHandles;
import org.odlabs.wiquery.ui.resizable.ResizableJavaScriptResourceReference;
import org.odlabs.wiquery.ui.widget.WidgetJavascriptResourceReference;

/**
 * $Id$
 * 
 * <p>
 * 	Binding of the superpanel wijmo widget
 * </p>
 * 
 * <p>
 * Examples of use
 * 
 * HTML:
 * 
 * 
 	<style>
 	.elements ul {
		padding: 8px 0 0 8px;
		margin: 0px;
		width: 840px;
		float: left;
		position:relative;
		zoom:1;
	}
	
	.elements ul li {
		background: #fff;
		color: #fff;
		font-weight: 900;
		height: 70px;
		margin: 0 8px 8px 0;
		padding: 0;
		padding-top: 62px;
		position: relative;
		text-align: center;
		width: 132px;
		text-shadow: 1px 1px 2px #000000;
	}
	
	.elements li {
		float: left;
		list-style: none;
	}
 	</style>
 	<div id="superPanel" style="width: 300px; height: 300px;">
		<div class="elements" >
			<ul>
				<li style="background: none repeat scroll 0% 0% activeborder;">
					<span>ActiveBorder</span>
				</li>
				<li style="background: none repeat scroll 0% 0% activecaption;">
					<span>ActiveCaption</span>
				</li>
				<li style="background: none repeat scroll 0% 0% captiontext;">
					<span>ActiveCaptionText</span>
				</li>
				<li style="background: none repeat scroll 0% 0% appworkspace;">
					<span>AppWorkspace</span>
				</li>
			</ul>
		</div>
	</div>

 * 
 * Java:
 * component.add(new WijSuperpanel());
 * </p>
 * 
 * Missing methods:
 * doScrolling
 * scrollChildIntoVie
 * hScrollTo
 * vScrollTo
 * scrollPxToValue
 * scrollTo
 * paintPanel
 * getContentElement
 *
 * @author Julien Roche
 * @since 1.0
 */
@WiQueryUIPlugin
public class WijSuperpanel extends WiQueryAbstractBehavior implements
		IWiQueryPlugin {

	// Constants
	/** Constant of serialization */
	private static final long serialVersionUID = -2063937222613116412L;
	
	// Properties
	private Options options;
	
	/**
	 * Default constructor
	 */
	public WijSuperpanel() {
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
		wiQueryResourceManager.addJavaScriptResource(MouseJavascriptResourceReference.get());
		wiQueryResourceManager.addJavaScriptResource(ResizableJavaScriptResourceReference.get());
		wiQueryResourceManager.addJavaScriptResource(DraggableJavaScriptResourceReference.get());
		wiQueryResourceManager.addJavaScriptResource(CoreEffectJavaScriptResourceReference.get());
		wiQueryResourceManager.addJavaScriptResource(MouseWheelJavascriptResourceReference.get());
		
		wiQueryResourceManager.addJavaScriptResource(WijSuperpanelJavascriptResourceReference.get());
		wiQueryResourceManager.addCssResource(WijSuperpanelStyleSheetResourceReference.get());
	}

	/**
	 * @return the animationOptions option
	 */
	public WijSuperpanelAnimationOptions getAnimationOptions() {
		Object obj = getOptions().getComplexOption("animationOptions");
		return obj instanceof WijSuperpanelAnimationOptions ? 
				(WijSuperpanelAnimationOptions) obj : new WijSuperpanelAnimationOptions(false, new AnimateDuration(250), null);
	}

	/**
	 * @return the hScroller option
	 */
	public WijSuperpanelHorizontalScroller getHScroller() {
		return (WijSuperpanelHorizontalScroller) getOptions().getComplexOption("hScroller");
	}

	/**
	 * @return the keyDownInterval option
	 */
	public int getKeyDownInterval() {
		Integer integer = getOptions().getInt("keyDownInterval");
		return integer == null ? 100 : integer;
	}
	
	/**
	 * @return the options of the expander
	 */
	protected Options getOptions() {
		return this.options;
	}
	
	/**
	 * @return the resizableOptions option
	 */
	public WijSuperpanelResizableOptions getResizableOptions() {
		Object obj = getOptions().getComplexOption("resizableOptions");
		return obj instanceof WijSuperpanelResizableOptions ? 
				(WijSuperpanelResizableOptions) obj : new WijSuperpanelResizableOptions(new ResizableHandles(new LiteralOption("all")),  "ui-widget-content ui-wijsuperpanel-helper"
);
	}
	
	/**
	 * @return the vScroller option
	 */
	public WijSuperpanelVerticalScroller getVScroller() {
		return (WijSuperpanelVerticalScroller) getOptions().getComplexOption("vScroller");
	}
	
	/**
	 * @return the allowResize option
	 */
	public boolean isAllowResize() {
		Boolean bool = getOptions().getBoolean("allowResize");
		return bool == null ? false : bool;
	}
	
	/**
	 * @return the autoRefresh option
	 */
	public boolean isAutoRefresh() {
		Boolean bool = getOptions().getBoolean("autoRefresh");
		return bool == null ? false : bool;
	}
	
	/**
	 * @return the bubbleScrollingEvent option
	 */
	public boolean isBubbleScrollingEvent() {
		Boolean bool = getOptions().getBoolean("bubbleScrollingEvent");
		return bool == null ? true : bool;
	}
	
	/**
	 * @return the keyboardSupport option
	 */
	public boolean isKeyboardSupport() {
		Boolean bool = getOptions().getBoolean("keyboardSupport");
		return bool == null ? false : bool;
	}
	
	/**
	 * @return the mouseWheelSupport option
	 */
	public boolean isMouseWheelSupport() {
		Boolean bool = getOptions().getBoolean("mouseWheelSupport");
		return bool == null ? true : bool;
	}
	
	/**
	 * @return the showRounder option
	 */
	public boolean isShowRounder() {
		Boolean bool = getOptions().getBoolean("showRounder");
		return bool == null ? true : bool;
	}
	
	/**
	 * A value determines whether wijsuperpanel can be resized.
	 * @param allowResize
	 * @return the current instance
	 */
	public WijSuperpanel setAllowResize(boolean allowResize) {
		getOptions().put("allowResize", allowResize);
		return this;
	}
	
	/**
	 * The animation properties of wijsuperpanel scrolling.
	 * @param animationOptions
	 * @return the current instance
	 */
	public WijSuperpanel setAnimationOptions(WijSuperpanelAnimationOptions animationOptions) {
		getOptions().put("animationOptions", animationOptions);
		return this;
	}
	
	/**
	 * A value determines whether wijsuperpanel to automatically refresh when 
	 * content size or wijsuperpanel size are changed.
	 * @param autoRefresh
	 * @return the current instance
	 */
	public WijSuperpanel setAutoRefresh(boolean autoRefresh) {
		getOptions().put("autoRefresh", autoRefresh);
		return this;
	}
	
	/**
	 * A function gets called when thumb buttons of scrollbars dragging stops.
	 * @param dragstop
	 * @return the current instance
	 */
	public WijSuperpanel setAutoRefreshEvent(JsScope dragstop) {
		getOptions().put("dragstop", dragstop);
		return this;
	}
	
	/**
	 * A value determines whether to fire the mouse wheel event when wijsuperpanel 
	 * is scrolled to the end.
	 * @param bubbleScrollingEvent
	 * @return the current instance
	 */
	public WijSuperpanel setBubbleScrollingEvent(boolean bubbleScrollingEvent) {
		getOptions().put("bubbleScrollingEvent", bubbleScrollingEvent);
		return this;
	}
	
	/**
	 * This option contains horiztonal scroller settings.
	 * @param hScroller
	 * @return the current instance
	 */
	public WijSuperpanel setHScroller(WijSuperpanelHorizontalScroller hScroller) {
		getOptions().put("hScroller", hScroller);
		return this;
	}
	
	/**
	 * A value determins whether wijsuperpanel provides keyboard scrolling support.
	 * @param keyboardSupport
	 * @return the current instance
	 */
	public WijSuperpanel setKeyboardSupport(boolean keyboardSupport) {
		getOptions().put("keyboardSupport", keyboardSupport);
		return this;
	}
	
	/**
	 * A value determines the time interval to call the scrolling function when doing continuous scrolling.
	 * @param keyDownInterval
	 * @return the current instance
	 */
	public WijSuperpanel setKeyDownInterval(int keyDownInterval) {
		getOptions().put("keyDownInterval", keyDownInterval);
		return this;
	}
	
	/**
	 * A value determines whether wijsuperpanel has mouse wheel support.
	 * @param mouseWheelSupport
	 * @return the current instance
	 */
	public WijSuperpanel setMouseWheelSupport(boolean mouseWheelSupport) {
		getOptions().put("mouseWheelSupport", mouseWheelSupport);
		return this;
	}
	
	/**
	 * A function gets called after panel is painted.
	 * @param painted
	 * @return the current instance
	 */
	public WijSuperpanel setPaintedEvent(JsScope painted) {
		getOptions().put("painted", painted);
		return this;
	}
	
	/**
	 * An option determines the behavior of resizable widget
	 * @param resizableOptions
	 * @return the current instance
	 */
	public WijSuperpanel setResizableOptions(WijSuperpanelResizableOptions resizableOptions) {
		getOptions().put("resizableOptions", resizableOptions);
		return this;
	}
	
	/**
	 * Resized event handler. A function gets called when resized event is fired.
	 * @param resized
	 * @return the current instance
	 */
	public WijSuperpanel setResizedEvent(JsScope resized) {
		getOptions().put("resized", resized);
		return this;
	}
	
	/**
	 * Scrolled event handler.  A function called after scrolling occurs.
	 * An event object is passed and some data are passed. It gots some values:
	 * - dir: The direction of the scrolling action. Possible values: "v"(vertical) and "h"(horizontal).
	 * - beforePosition: The position of content before scrolling occurs.
	 * - afterPosition: The position of content after scrolling occurs.
	 * 
	 * @param scrolled
	 * @return the current instance
	 */
	public WijSuperpanel setScrolledEvent(JsScope scrolled) {
		getOptions().put("scrolled", scrolled);
		return this;
	}
	
	/**
	 * Scrolling event handler. A function called before scrolling occurs.
	 * An event object is passed and some data are passed. It gots some values:
	 * - oldValue: The scrollValue before scrolling occurs.
	 * - newValue: The scrollValue after scrolling occurs.
	 * - dir: The direction of the scrolling action. Possible values: "v"(vertical) and "h"(horizontal).
	 * - beforePosition: The position of content before scrolling occurs.
	 * 
	 * @param scrolled
	 * @return the current instance
	 */
	public WijSuperpanel setScrollingEvent(JsScope scrolling) {
		getOptions().put("scrolling", scrolling);
		return this;
	}
	
	/**
	 * A value determines whether to show the rounded corner of wijsuperpanel.
	 * @param showRounder
	 * @return the current instance
	 */
	public WijSuperpanel setShowRounder(boolean showRounder) {
		getOptions().put("showRounder", showRounder);
		return this;
	}
	
	/**
	 * This option contains vertical scroller settings.
	 * @param vScroller
	 * @return the current instance
	 */
	public WijSuperpanel setVScroller(WijSuperpanelVerticalScroller vScroller) {
		getOptions().put("vScroller", vScroller);
		return this;
	}
	
	/**
	 * {@inheritDoc}
	 * @see org.odlabs.wiquery.core.behavior.WiQueryAbstractBehavior#statement()
	 */
	@Override
	public JsStatement statement() {
		return new JsQuery(getComponent()).$().chain("wijsuperpanel", getOptions().getJavaScriptOptions());
	}
}
