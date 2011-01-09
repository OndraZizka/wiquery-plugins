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
package org.odlabs.wiquery.wijmo.wijmenu;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.model.IModel;
import org.odlabs.wiquery.core.commons.IWiQueryPlugin;
import org.odlabs.wiquery.core.commons.WiQueryResourceManager;
import org.odlabs.wiquery.core.javascript.JsQuery;
import org.odlabs.wiquery.core.javascript.JsStatement;
import org.odlabs.wiquery.core.options.Options;
import org.odlabs.wiquery.ui.commons.WiQueryUIPlugin;
import org.odlabs.wiquery.ui.effects.CoreEffectJavaScriptResourceReference;
import org.odlabs.wiquery.ui.position.PositionJavascriptResourceReference;
import org.odlabs.wiquery.ui.position.PositionOptions;
import org.odlabs.wiquery.ui.widget.WidgetJavascriptResourceReference;
import org.odlabs.wiquery.wijmo.wijsuperpanel.WijSuperpanelJavascriptResourceReference;
import org.odlabs.wiquery.wijmo.wijsuperpanel.WijSuperpanelStyleSheetResourceReference;
import org.odlabs.wiquery.wijmo.wijutil.WijUtilJavascriptResourceReference;
import org.odlabs.wiquery.wijmo.wijutil.WijUtilStyleSheetResourceReference;

/**
 * $Id$
 * 
 * <p>
 * 	Wijmo menu widget
 * </p>
 * 
 * <p>
 * Examples of use:
 * 
 * HTML:
 * <ul wicket:id="menu">
	<li><a href="#">Breaking News</a> <!-- contains of the menu "Breaking News" -->
 		<ul>
 			<li>
 				<h3>header</h3> <!-- Optionnal title -->
 			</li>
 			<li><a href="#">Entertainment</a></li>
 			<li><a href="http://www.w3schools.com/tags/html5.asp">Politics</a></li>
 			<li><a href="#">A&amp;E</a></li>
 			<li><a href="#">Sports</a> <!-- Submenu "Sports" -->
 				<ul>
 					<li><a href="#">Baseball</a></li>
 					<li><a href="#">Basketball</a></li>
 					<li><a href="#">A really long label would wrap nicely as you can see</a></li>
 					<li><a href="#">Swimming</a>
 						<ul>
 							<li><a href="#">High School</a></li>
 							<li><a href="#">College</a></li>
 							<li><a href="#">Adult Recreational</a></li>
 							<li><a href="#">Youth Recreational</a></li>
 							<li><a href="#">Senior Recreational</a></li>
 						</ul>
 					</li>
 					<li><a href="#">Tennis</a></li>
 					<li><a href="#">Ice Skating</a></li>
 					<li><a href="#">Javascript Programming</a></li>
 					<li><a href="#">Running</a></li>
 					<li><a href="#">Walking</a></li>
 				</ul>
 			</li>
 			<li><a href="#">Local</a></li>
 			<li><a href="#">Health</a></li>
 		</ul>
 	</li>
</ul>

 * 
 * Java:
 * add(new WijMenu("menu"));
 * </p>
 * 
 * <p>Miss:
 * 	* superPanelOptions
 *</p>
 *
 * @author Julien Roche
 * @since 1.0
 */
@WiQueryUIPlugin
public class WijMenu extends WebMarkupContainer implements IWiQueryPlugin {
	/**
	 * 
	 * <p>
	 * 	Enumeration for the orientation option
	 * </p>
	 *
	 * @author Julien Roche
	 * @since 1.0
	 */
	public enum WijMenuOrientation {
		HORIZONTAL,
		VERTICAL;

		/**
		 * {@inheritDoc}
		 * @see java.lang.Enum#toString()
		 */
		@Override
		public String toString() {
			return super.toString().toLowerCase();
		}
	}
	
	/**
	 * 
	 * <p>
	 * 	Enumeration for the triggerEvent option
	 * </p>
	 *
	 * @author Julien Roche
	 * @since 1.0
	 */
	public enum WijMenuTriggerEvent {
		CLICK,
		DBCLICK,
		MOUSEENTER,
		RTCLICK;

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
	private static final long serialVersionUID = 6352249523620121165L;
	
	// Properties
	private Options options;

	/**
	 * @see Component#Component(String)
	 */
	public WijMenu(String id) {
		super(id);
		this.options = new Options();
	}

	/**
	 * @see org.apache.wicket.Component#Component(String, IModel)
	 */
	public WijMenu(String id, IModel<?> model) {
		super(id, model);
		this.options = new Options();
	}
	
	/**
	 * {@inheritDoc}
	 * @see org.odlabs.wiquery.core.commons.IWiQueryPlugin#contribute(org.odlabs.wiquery.core.commons.WiQueryResourceManager)
	 */
	public void contribute(WiQueryResourceManager wiQueryResourceManager) {
		wiQueryResourceManager.addJavaScriptResource(WidgetJavascriptResourceReference.get());
		wiQueryResourceManager.addJavaScriptResource(PositionJavascriptResourceReference.get());
		wiQueryResourceManager.addJavaScriptResource(CoreEffectJavaScriptResourceReference.get());
		wiQueryResourceManager.addJavaScriptResource(WijUtilJavascriptResourceReference.get());
		wiQueryResourceManager.addJavaScriptResource(WijSuperpanelJavascriptResourceReference.get());
		wiQueryResourceManager.addJavaScriptResource(WijMenuJavascriptResourceReference.get());
		
		wiQueryResourceManager.addCssResource(WijUtilStyleSheetResourceReference.get());
		wiQueryResourceManager.addCssResource(WijSuperpanelStyleSheetResourceReference.get());
		wiQueryResourceManager.addCssResource(WijMenuStyleSheetResourceReference.get());
	}
	
	/**
	 * @return the animated option
	 */
	public String getAnimated() {
		String str = getOptions().getLiteral("animated");
		return str == null ? "slide" : str;
	}
	
	/**
	 * @return the backLinkText option
	 */
	public String getBackLinkText() {
		String str = getOptions().getLiteral("backLinkText");
		return str == null ? "Back" : str;
	}
	
	/**
	 * @return the crumbDefaultText option
	 */
	public String getCrumbDefaultText() {
		String str = getOptions().getLiteral("crumbDefaultText");
		return str == null ? "Choose an option" : str;
	}
	
	/**
	 * @return the duration option
	 */
	public int getDuration() {
		Integer duration = getOptions().getInt("duration");
		return duration == null ? 400 : duration;
	}
	
	/**
	 * @return the hideAnimated option
	 */
	public String getHideAnimated() {
		String str = getOptions().getLiteral("hideAnimated");
		return str == null ? "slide" : str;
	}
	
	/**
	 * @return the hideDuration option
	 */
	public int getHideDuration() {
		Integer duration = getOptions().getInt("hideDuration");
		return duration == null ? 400 : duration;
	}
	
	/**
	 * @return the maxHeight option
	 */
	public int getMaxHeight() {
		Integer maxHeight = getOptions().getInt("maxHeight");
		return maxHeight == null ? 200 : maxHeight;
	}
	
	/**
	 * @return the mode option
	 */
	public String getMode() {
		String str = getOptions().getLiteral("mode");
		return str == null ? "flyout" : str;
	}
	
	/**
	 * @return the options of the expander
	 */
	protected Options getOptions() {
		return this.options;
	}
	
	/**
	 * @return the orientation option
	 */
	public WijMenuOrientation getOrientation() {
		String str = getOptions().getLiteral("orientation");
		return str == null ? WijMenuOrientation.HORIZONTAL : WijMenuOrientation.valueOf(str.toUpperCase());
	}
	
	/**
	 * @return the position option
	 */
	public PositionOptions getPosition() {
		Object obj = getOptions().getComplexOption("position");
		
		if(obj instanceof PositionOptions){
			return (PositionOptions) obj;
		}
		
		return new PositionOptions();
	}
	
	/**
	 * @return the showAnimated option
	 */
	public String getShowAnimated() {
		String str = getOptions().getLiteral("showAnimated");
		return str == null ? "slide" : str;
	}
	
	/**
	 * @return the showDuration option
	 */
	public int getShowDuration() {
		Integer duration = getOptions().getInt("showDuration");
		return duration == null ? 400 : duration;
	}
	
	/**
	 * @return the topLinkText option
	 */
	public String getTopLinkText() {
		String str = getOptions().getLiteral("topLinkText");
		return str == null ? "All" : str;
	}
	
	/**
	 * @return the trigger option
	 */
	public String getTrigger() {
		String str = getOptions().getLiteral("trigger");
		return str == null ? "" : str;
	}
	
	/**
	 * @return the triggerEvent option
	 */
	public WijMenuTriggerEvent getTriggerEvent() {
		String str = getOptions().getLiteral("triggerEvent");
		return str == null ? WijMenuTriggerEvent.CLICK : WijMenuTriggerEvent.valueOf(str.toUpperCase());
	}
	
	/**
	 * @return the backLink option
	 */
	public boolean isBackLink() {
		Boolean bool = getOptions().getBoolean("backLink");
		return bool == null ? true : bool;
	}
	
	/**
	 * @return the checkable option
	 */
	public boolean isCheckable() {
		Boolean bool = getOptions().getBoolean("checkable");
		return bool == null ? false : bool;
	}
	
	/**
	 * Sets showAnimated and hideAnimated if not specified individually
	 * @param animated
	 * @return the current instance
	 */
	public WijMenu setAnimated(String animated) {
		getOptions().putLiteral("animated", animated);
		return this;
	}
	
	/**
	 * Determines whether the i-Pod menu shows a back link or a breadcrumb header in the menu.
	 * @param backLink
	 * @return the current instance
	 */
	public WijMenu setBackLink(boolean backLink) {
		getOptions().put("backLink", backLink);
		return this;
	}
	
	/**
	 * Sets the text of the back link.
	 * @param backLinkText
	 * @return the current instance
	 */
	public WijMenu setBackLinkText(String backLinkText) {
		getOptions().putLiteral("backLinkText", backLinkText);
		return this;
	}
	
	/**
	 * Defines whether items are checkable.
	 * @param checkable
	 * @return the current instance
	 */
	public WijMenu setCheckable(boolean checkable) {
		getOptions().put("checkable", checkable);
		return this;
	}
	
	/**
	 * Sets the top breadcrumb's default Text.
	 * @param crumbDefaultText
	 * @return the current instance
	 */
	public WijMenu setCrumbDefaultText(String crumbDefaultText) {
		getOptions().putLiteral("crumbDefaultText", crumbDefaultText);
		return this;
	}
	
	/**
	 * Determines the speed to show/hide the menu in milliseconds. Sets showDuration 
	 * and hideDuration if they are not specified.
	 * @param duration
	 * @return the current instance
	 */
	public WijMenu setDuration(int duration) {
		getOptions().put("duration", duration);
		return this;
	}
	
	/**
	 * Determines the animation used during hide.
	 * @param hideAnimated
	 * @return the current instance
	 */
	public WijMenu setHideAnimated(String hideAnimated) {
		getOptions().putLiteral("hideAnimated", hideAnimated);
		return this;
	}
	
	/**
	 * Determines the speed to hide the menu,in milliseconds.
	 * @param hideDuration
	 * @return the current instance
	 */
	public WijMenu setHideDuration(int hideDuration) {
		getOptions().put("hideDuration", hideDuration);
		return this;
	}
	
	/**
	 * Determines the i-Pod-style menu's maximum height.
	 * @param maxHeight
	 * @return the current instance
	 */
	public WijMenu setMaxHeight(int maxHeight) {
		getOptions().put("maxHeight", maxHeight);
		return this;
	}
	
	/**
	 * Defines the behavior of the submenu whether it is a popup menu or an iPod-style navigation list.
	 * @param mode
	 * @return the current instance
	 */
	public WijMenu setMode(String mode) {
		getOptions().putLiteral("mode", mode);
		return this;
	}
	
	/**
	 * Controls the root menus orientation. All submenus are vertical regardless 
	 * of the orientation of the root menu.
	 * @param orientation
	 * @return the current instance
	 */
	public WijMenu setOrientation(WijMenuOrientation orientation) {
		getOptions().putLiteral("orientation", orientation.toString());
		return this;
	}
	
	/**
	 * ocation and Orientation of the menu,relative to the button/link userd to open it. 
	 * Configuration for the Position Utility,Of option excluded(always configured by widget).
	 * Collision also controls collision detection automatically too
	 * @param position
	 * @return the current instance
	 */
	public WijMenu setPosition(PositionOptions position){
		getOptions().put("position", position);
		return this;
	}
	
	/**
	 * etermines the animationn used during show.
	 * @param showAnimated
	 * @return the current instance
	 */
	public WijMenu setShowAnimated(String showAnimated) {
		getOptions().putLiteral("showAnimated", showAnimated);
		return this;
	}
	
	/**
	 * Determines the speed to show the menu,in milliseconds.
	 * @param showDuration
	 * @return the current instance
	 */
	public WijMenu setShowDuration(int showDuration) {
		getOptions().put("showDuration", showDuration);
		return this;
	}
	
	/**
	 * Sets the text of the top link.
	 * @param topLinkText
	 * @return the current instance
	 */
	public WijMenu setTopLinkText(String topLinkText) {
		getOptions().putLiteral("topLinkText", topLinkText);
		return this;
	}
	
	/**
	 * Set the jQuery selector which handle to open the menu or submenu
	 * @param trigger
	 * @return the current instance
	 */
	public WijMenu setTrigger(String trigger) {
		getOptions().putLiteral("trigger", trigger);
		return this;
	}
	
	/**
	 * Set the jQuery selector which handle to open the menu or submenu
	 * @param triggerEvent
	 * @return the current instance
	 */
	public WijMenu setTriggerEvent(WijMenuTriggerEvent triggerEvent) {
		getOptions().putLiteral("triggerEvent", triggerEvent.toString());
		return this;
	}

	/**
	 * {@inheritDoc}
	 * @see org.odlabs.wiquery.core.behavior.WiQueryAbstractBehavior#statement()
	 */
	public JsStatement statement() {
		return new JsQuery(this).$().chain("wijmenu", getOptions().getJavaScriptOptions());
	}
}
