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
package org.odlabs.wiquery.wijmo.wijpopup;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.odlabs.wiquery.core.behavior.WiQueryAbstractBehavior;
import org.odlabs.wiquery.core.javascript.JsQuery;
import org.odlabs.wiquery.core.javascript.JsStatement;
import org.odlabs.wiquery.core.options.ListItemOptions;
import org.odlabs.wiquery.core.options.Options;
import org.odlabs.wiquery.ui.position.PositionBehavior;
import org.odlabs.wiquery.ui.position.PositionOptions;
import org.odlabs.wiquery.ui.position.PositionOptions.Position;

/**
 * $Id$
 * 
 * <p>
 * 	Binds the wijpopup widget
 * </p>
 *
 * @author Julien Roche
 * @since 1.0
 */
public class WijPopup extends WiQueryAbstractBehavior {
	/**
	 * Enumeration of the possibles effect
	 * @author Julien Roche
	 * @since 1.0
	 */
	public enum WijPopupEffect {
		BLIND,
		CLIP,
		DROP,
		FADE,
		FOLD,
		SLIDE,
		PULSATE;

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
	private static final long serialVersionUID = 6851373002835196026L;
	
	// Properties
	private Options options;

	/**
	 * Default constructor
	 */
	public WijPopup() {
		super();
		this.options = new Options();
	}
	
	/**
	 * @return the hideDuration option
	 */
	public int getHideDuration() {
		Integer value = getOptions().getInt("hideDuration");
		return value == null ? 100 : value;
	}
	
	/**
	 * @return the hideEffect option
	 */
	public WijPopupEffect getHideEffect() {
		Object effect = getOptions().get("hideEffect");
		return effect == null ? null : WijPopupEffect.valueOf(effect.toString().toUpperCase());
	}
	
	/**
	 * @return the hideOptions option
	 */
	public ListItemOptions<?> getHideOptions() {
		return (ListItemOptions<?>) getOptions().getComplexOption("hideOptions");
	}
	
	/**
	 * @return the {@link Options} of the widget
	 */
	protected Options getOptions() {
		return this.options;
	}
	
	/**
	 * @return the position option
	 */
	public PositionOptions getPosition() {
		Object obj = getOptions().getComplexOption("position");
		
		if(obj instanceof PositionOptions){
			return (PositionOptions) obj;
		}
		
		PositionOptions pos = new PositionOptions();
		pos.setMy(Position.LEFT_TOP);
		pos.setAt(Position.LEFT_BOTTOM);
		
		return pos;
	}
	
	/**
	 * @return the showDuration option
	 */
	public int getShowDuration() {
		Integer value = getOptions().getInt("showDuration");
		return value == null ? 300 : value;
	}
	
	/**
	 * @return the showEffect option
	 */
	public WijPopupEffect getShowEffect() {
		Object effect = getOptions().get("showEffect");
		return effect == null ? null : WijPopupEffect.valueOf(effect.toString().toUpperCase());
	}
	
	/**
	 * @return the showOptions option
	 */
	public ListItemOptions<?> getShowOptions() {
		return (ListItemOptions<?>) getOptions().getComplexOption("showOptions");
	}
	
	/**Hide the popup
	 * This will return the element back to its pre-init state.
	 * @return the associated JsStatement
	 */
	public JsStatement hide() {
		return new JsQuery(getComponent()).$().chain("wijpopup", "'hide'");
	}
	
	/**Hide the popup
	 * @param ajaxRequestTarget
	 */
	public void hide(AjaxRequestTarget ajaxRequestTarget) {
		ajaxRequestTarget.appendJavascript(this.hide().render().toString());
	}
	
	/**Is the popup animated ?
	 * This will return the element back to its pre-init state.
	 * @return the associated JsStatement
	 */
	public JsStatement isAnimating() {
		return new JsQuery(getComponent()).$().chain("wijpopup", "'isAnimating'");
	}
	
	/**Is the popup animated ?
	 * @param ajaxRequestTarget
	 */
	public void isAnimating(AjaxRequestTarget ajaxRequestTarget) {
		ajaxRequestTarget.appendJavascript(this.isAnimating().render().toString());
	}
	
	/**
	 * @return the autoHide option
	 */
	public boolean isAutoHide() {
		Boolean bool = getOptions().getBoolean("autoHide");
		return bool == null ? false : bool;
	}
	
	/**
	 * @return the ensureOutermost option
	 */
	public boolean isEnsureOutermost() {
		Boolean bool = getOptions().getBoolean("ensureOutermost");
		return bool == null ? false : bool;
	}
	
	/**Is the popup visible ?
	 * This will return the element back to its pre-init state.
	 * @return the associated JsStatement
	 */
	public JsStatement isVisible() {
		return new JsQuery(getComponent()).$().chain("wijpopup", "'isVisible'");
	}
	
	/**Is the popup visible ?
	 * @param ajaxRequestTarget
	 */
	public void isVisible(AjaxRequestTarget ajaxRequestTarget) {
		ajaxRequestTarget.appendJavascript(this.isVisible().render().toString());
	}
	
	/**
	 * Determines whether to automatically hide the popup when clicking outside the element.
	 * @param autoHide
	 * @return the current instance
	 */
	public WijPopup setAutoHide(boolean autoHide) {
		getOptions().put("autoHide", autoHide);
		return this;
	}
	
	/**
	 * Determines if the element's parent element is the outermost element.
	 * @param ensureOutermost If true, the element's parent element will be changed to the body or outermost form element.
	 * @return the current instance
	 */
	public WijPopup setEnsureOutermost(boolean ensureOutermost) {
		getOptions().put("ensureOutermost", ensureOutermost);
		return this;
	}
	
	/**
	 * Defines how long (in milliseconds) the animation duration for hiding the popup will last.
	 * @param hideDuration
	 * @return the current instance
	 */
	public WijPopup setHideDuration(int hideDuration){
		getOptions().put("hideDuration", hideDuration);
		return this;
	}
	
	/**
	 * Specifies the effect to be used when the popup is hidden.
	 * @param hideEffect
	 * @return the current instance
	 */
	public WijPopup setHideEffect(WijPopupEffect hideEffect){
		getOptions().put("hideEffect", hideEffect.toString());
		return this;
	}
	
	/**
	 * Specified the object/hash including specific options for the hide effect.
	 * @param showOptions
	 * @return the instance
	 */
	public WijPopup setHideOptions(ListItemOptions<?> hideOptions) {
		getOptions().put("hideOptions", hideOptions);
		return this;
	}

	/**
	 * Options for positioning the element (see the jQuery UI position {@link PositionBehavior})
	 * @param position
	 * @return the current instance
	 */
	public WijPopup setPosition(PositionOptions position){
		getOptions().put("position", position);
		return this;
	}
	
	/**
	 * Defines how long (in milliseconds) the animation duration for showing the popup will last.
	 * @param showDuration
	 * @return the current instance
	 */
	public WijPopup setShowDuration(int showDuration){
		getOptions().put("showDuration", showDuration);
		return this;
	}

	/**
	 * Specifies the effect to be used when the popup is shown.
	 * @param showEffect
	 * @return the current instance
	 */
	public WijPopup setShowEffect(WijPopupEffect showEffect){
		getOptions().put("showEffect", showEffect.toString());
		return this;
	}
	
	/**
	 * Specified the object/hash including specific options for the show effect.
	 * @param showOptions
	 * @return the instance
	 */
	public WijPopup setShowOptions(ListItemOptions<?> showOptions) {
		getOptions().put("showOptions", showOptions);
		return this;
	}

	/**Show the popup
	 * This will return the element back to its pre-init state.
	 * @return the associated JsStatement
	 */
	public JsStatement show() {
		return new JsQuery(getComponent()).$().chain("wijpopup", "'show'");
	}
	
	/**Show the popup
	 * @param ajaxRequestTarget
	 */
	public void show(AjaxRequestTarget ajaxRequestTarget) {
		ajaxRequestTarget.appendJavascript(this.show().render().toString());
	}

	/**Show the popup at the specified position
	 * @param ajaxRequestTarget
	 */
	public void showAt(AjaxRequestTarget ajaxRequestTarget, int x, int y) {
		ajaxRequestTarget.appendJavascript(this.showAt(x, y).render().toString());
	}
	
	/**Show the popup at the specified position
	 * This will return the element back to its pre-init state.
	 * @return the associated JsStatement
	 */
	public JsStatement showAt(int x, int y) {
		return new JsQuery(getComponent()).$().chain("wijpopup", "'showAt'"
				, Integer.toString(x), Integer.toString(y));
	}

	/**
	 * {@inheritDoc}
	 * @see org.odlabs.wiquery.core.behavior.WiQueryAbstractBehavior#statement()
	 */
	@Override
	public JsStatement statement() {
		return new JsQuery(getComponent()).$().chain(
				"wijpopup", getOptions().getJavaScriptOptions());
	}
}
