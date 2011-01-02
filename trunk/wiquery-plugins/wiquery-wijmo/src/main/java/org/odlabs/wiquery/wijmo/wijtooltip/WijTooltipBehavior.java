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
package org.odlabs.wiquery.wijmo.wijtooltip;

import org.odlabs.wiquery.core.behavior.WiQueryAbstractBehavior;
import org.odlabs.wiquery.core.commons.WiQueryResourceManager;
import org.odlabs.wiquery.core.javascript.JsQuery;
import org.odlabs.wiquery.core.javascript.JsStatement;
import org.odlabs.wiquery.core.options.Options;
import org.odlabs.wiquery.ui.commons.WiQueryUIPlugin;
import org.odlabs.wiquery.ui.position.PositionBehavior;
import org.odlabs.wiquery.ui.position.PositionJavascriptResourceReference;
import org.odlabs.wiquery.ui.position.PositionOptions;
import org.odlabs.wiquery.ui.position.PositionOptions.Position;
import org.odlabs.wiquery.ui.widget.WidgetJavascriptResourceReference;
import org.odlabs.wiquery.wijmo.options.WijmoAnimation;
import org.odlabs.wiquery.wijmo.options.WijmoString;

/**
 * $Id$
 * 
 * <p>
 * 	Wijmo tooltip widget
 * </p>
 * 
 * <p>
 * Examples of use:
 * 
 * HTML:
 * <div title='tooltip content' wicket:id='componentWithTooltip'></div>
 * 
 * Java:
 * componentWithTooltip.add(new WijTooltipBehavior());
 * 
 * Nota Bene: the content can specified by twice methods:
 * 1) on the title attribut of the HTML element
 * 2) with the content paremeter of this behavior
 * 
 * </p>
 * 
 * @author Julien Roche
 * @since 1.0
 */
@WiQueryUIPlugin
public class WijTooltipBehavior extends WiQueryAbstractBehavior {
	/**
	 * 
	 * Enumeration for the close behavior option
	 */
	public enum CloseBehaviorEnum {
		auto,
		sticky;
	}
	
	/**
	 * 
	 * Enumeration for the triggers option
	 */
	public enum TriggersEnum {
		click,
		custom,
		focus,
		hover,
		rightClick;
	}
	
	// Constants
	/** Constant of serialization */
	private static final long serialVersionUID = 1L;
	
	// Properties
	private Options options;
	
	/**
	 * Default constructor
	 */
	public WijTooltipBehavior() {
		super();
		options = new Options();
	}
	
	/**
	 * {@inheritDoc}
	 * @see org.odlabs.wiquery.core.behavior.WiQueryAbstractBehavior#contribute(org.odlabs.wiquery.core.commons.WiQueryResourceManager)
	 */
	@Override
	public void contribute(WiQueryResourceManager wiQueryResourceManager) {
		super.contribute(wiQueryResourceManager);
		
		wiQueryResourceManager.addCssResource(WijTooltipStyleSheetResourceReference.get());
		wiQueryResourceManager.addJavaScriptResource(WidgetJavascriptResourceReference.get());
		wiQueryResourceManager.addJavaScriptResource(PositionJavascriptResourceReference.get());
		wiQueryResourceManager.addJavaScriptResource(WijTooltipJavascriptResourceReference.get());
	}
	
	/**
	 * @return the calloutAnimation option
	 */
	public WijmoAnimation getCalloutAnimation() {
		return (WijmoAnimation) getOptions().getComplexOption("calloutAnimation");
	}

	/**
	 * @return the closeBehavior parameter
	 */
	public CloseBehaviorEnum getCloseBehavior() {
		String obj = getOptions().get("closeBehavior");
		return obj == null ? CloseBehaviorEnum.auto : CloseBehaviorEnum.valueOf(obj);
	}
	
	/**
	 * @return the content option
	 */
	public WijmoString getContent() {
		Object obj = getOptions().getComplexOption("content");
		return obj == null || !(obj instanceof WijmoString) ? new WijmoString("") : (WijmoString) obj;
	}
	
	/**
	 * @return the hideAnimation option
	 */
	public WijmoAnimation getHideAnimation() {
		return (WijmoAnimation) getOptions().getComplexOption("hideAnimation");
	}
	
	/**
	 * @return the hideDelay option
	 */
	public int getHideDelay() {
		Integer val = getOptions().getInt("hideDelay");
		return val == null ? 150 : val;
	}
	
	/**
	 * @return the options of the tooltip
	 */
	protected Options getOptions() {
		return options;
	}
	
	/**
	 * @return the position options
	 */
	public PositionOptions getPosition() {
		Object obj = getOptions().getComplexOption("position");
		
		if(obj instanceof PositionOptions){
			return (PositionOptions) obj;
		}
		
		PositionOptions pos = new PositionOptions();
		pos.setMy(Position.LEFT_TOP);
		pos.setAt(Position.RIGHT_TOP);
		
		return pos;
	}
	
	/**
	 * @return the showAnimation option
	 */
	public WijmoAnimation getShowAnimation() {
		return (WijmoAnimation) getOptions().getComplexOption("showAnimation");
	}
	
	/**
	 * @return the showDelay option
	 */
	public int getShowDelay() {
		Integer val = getOptions().getInt("showDelay");
		return val == null ? 150 : val;
	}
	
	/**
	 * @return the title option
	 */
	public WijmoString getTitle() {
		Object obj = getOptions().getComplexOption("title");
		return obj == null || !(obj instanceof WijmoString) ? new WijmoString("") : (WijmoString) obj;
	}
	
	/**
	 * @return the triggers parameter
	 */
	public TriggersEnum getTriggers() {
		String obj = getOptions().get("triggers");
		return obj == null ? TriggersEnum.hover : TriggersEnum.valueOf(obj);
	}
	
	/**
	 * @return the calloutFilled option
	 */
	public boolean isCalloutFilled() {
		Boolean val = getOptions().getBoolean("calloutFilled");
		return val == null ? true : val;
	}
	
	/**
	 * @return the modal option
	 */
	public boolean isModal() {
		Boolean val = getOptions().getBoolean("modal");
		return val == null ? false : val;
	}
	
	/**
	 * @return the mouseTrailing option
	 */
	public boolean isMouseTrailing() {
		Boolean val = getOptions().getBoolean("mouseTrailing");
		return val == null ? false : val;
	}
	
	/**
	 * @return the showCallOut option
	 */
	public boolean isShowCallOut() {
		Boolean val = getOptions().getBoolean("showCallOut");
		return val == null ? true : val;
	}
	
	/**
	 * Set the calloutAnimation
	 * @param calloutAnimation
	 * @return the current behavior
	 */
	public WijTooltipBehavior setCalloutAnimation(WijmoAnimation calloutAnimation) {
		getOptions().put("calloutAnimation", calloutAnimation);
		return this;
	}
	
	/**
	 * Determines the callout's class style.If true,then the callout triangle will be filled.
	 * @param calloutFilled
	 * @return the current behavior
	 */
	public WijTooltipBehavior setCalloutFilled(boolean calloutFilled) {
		getOptions().put("calloutFilled", calloutFilled);
		return this;
	}
	
	/**
	 * Set the method to close the tooltip
	 * @param closeBehavior
	 * @return the current behavior
	 */
	public WijTooltipBehavior setCloseBehavior(CloseBehaviorEnum closeBehavior) {
		getOptions().put("closeBehavior", closeBehavior.toString());
		return this;
	}
	
	/**
	 * Set the content of the tooltip
	 * @param content
	 * @return the current behavior
	 */
	public WijTooltipBehavior setContent(WijmoString content) {
		getOptions().put("content", content);
		return this;
	}
	
	/**
	 * Set the hideAnimation
	 * @param hideAnimation
	 * @return the current behavior
	 */
	public WijTooltipBehavior setHideAnimation(WijmoAnimation hideAnimation) {
		getOptions().put("hideAnimation", hideAnimation);
		return this;
	}
	
	/**
	 * Determines the length of the delay before the tooltip disappears.
	 * @param hideDelay
	 * @return the current behavior
	 */
	public WijTooltipBehavior setHideDelay(int hideDelay) {
		getOptions().put("hideDelay", hideDelay);
		return this;
	}
	
	/**
	 * Set true if you want a modal tooltip
	 * @param modal
	 * @return the current behavior
	 */
	public WijTooltipBehavior setModal(boolean modal) {
		getOptions().put("modal", modal);
		return this;
	}
	
	/**
	 * Set true and the tooltip moves with the mouse
	 * @param mouseTrailing
	 * @return the current behavior
	 */
	public WijTooltipBehavior setMouseTrailing(boolean mouseTrailing) {
		getOptions().put("mouseTrailing", mouseTrailing);
		return this;
	}
	
	/**
	 * Sets the tooltip's position mode (see the jQuery UI position {@link PositionBehavior})
	 * @param position
	 * @return the current behavior
	 */
	public WijTooltipBehavior setPosition(PositionOptions position){
		getOptions().put("position", position);
		return this;
	}
	
	/**
	 * Set the showAnimation
	 * @param showAnimation
	 * @return the current behavior
	 */
	public WijTooltipBehavior setShowAnimation(WijmoAnimation showAnimation) {
		getOptions().put("showAnimation", showAnimation);
		return this;
	}
	
	/**
	 * Determines whether to show the callout element.
	 * @param showCallOut
	 * @return the current behavior
	 */
	public WijTooltipBehavior setShowCallOut(boolean showCallOut) {
		getOptions().put("showCallOut", showCallOut);
		return this;
	}
	
	/**
	 * Determines the length of the delay before the tooltip appears.
	 * @param showDelay
	 * @return the current behavior
	 */
	public WijTooltipBehavior setShowDelay(int showDelay) {
		getOptions().put("showDelay", showDelay);
		return this;
	}
	
	/**
	 * Set the title of the tooltip
	 * @param title
	 * @return the current behavior
	 */
	public WijTooltipBehavior setTitle(WijmoString title) {
		getOptions().put("title", title);
		return this;
	}
	
	/**
	 * Set the tooltip's event
	 * @param triggers
	 * @return the current behavior
	 */
	public WijTooltipBehavior setTriggers(TriggersEnum triggers) {
		getOptions().put("triggers", triggers.toString());
		return this;
	}
	
	/**
	 * {@inheritDoc}
	 * @see org.odlabs.wiquery.core.behavior.WiQueryAbstractBehavior#statement()
	 */
	@Override
	public JsStatement statement() {
		return new JsQuery(getComponent()).$().chain("wijtooltip", options.getJavaScriptOptions());
	}
}
