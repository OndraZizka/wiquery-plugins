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
package org.odlabs.wiquery.wijmo.wijtabs;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.odlabs.wiquery.core.ajax.JQueryAjaxOption;
import org.odlabs.wiquery.core.commons.IWiQueryPlugin;
import org.odlabs.wiquery.core.commons.WiQueryResourceManager;
import org.odlabs.wiquery.core.events.MouseEvent;
import org.odlabs.wiquery.core.javascript.JsQuery;
import org.odlabs.wiquery.core.javascript.JsScope;
import org.odlabs.wiquery.core.javascript.JsStatement;
import org.odlabs.wiquery.core.jqueryplugins.CookieJavaScriptResourceReference;
import org.odlabs.wiquery.core.jqueryplugins.JQueryCookieOption;
import org.odlabs.wiquery.core.options.IComplexOption;
import org.odlabs.wiquery.core.options.Options;
import org.odlabs.wiquery.glob.GlobalizationJavascriptResourceReference;
import org.odlabs.wiquery.glob.JGlobJavascriptResourceReference;
import org.odlabs.wiquery.glob.JGlobTranslationJavascriptResourceReference;
import org.odlabs.wiquery.ui.commons.WiQueryUIPlugin;
import org.odlabs.wiquery.ui.effects.CoreEffectJavaScriptResourceReference;
import org.odlabs.wiquery.ui.position.PositionJavascriptResourceReference;
import org.odlabs.wiquery.ui.widget.WidgetJavascriptResourceReference;

/**
 * $Id$
 * 
 * <p>
 * 	Bind the Wijmo tabs widget
 * </p>
 * 
 * <p>
 * Examples of use:
 * 
 * HTML:
 * <div id="tabs">
		<ul>
			<li><a href="#tabs-1">Nunc tincidunt</a></li>
			<li><a href="#tabs-2">Proin dolor</a></li>
			<li><a href="#tabs-3">Aenean lacinia</a></li>
		</ul>
		<div id="tabs-1">
			<p>
				Proin elit arcu, rutrum commodo, vehicula tempus, commodo a, risus. Curabitur nec
				arcu. Donec sollicitudin mi sit amet mauris. Nam elementum quam ullamcorper ante.
				Etiam aliquet massa et lorem. Mauris dapibus lacus auctor risus. Aenean tempor ullamcorper
				leo. Vivamus sed magna quis ligula eleifend adipiscing. Duis orci. Aliquam sodales
				tortor vitae ipsum. Aliquam nulla. Duis aliquam molestie erat. Ut et mauris vel
				pede varius sollicitudin. Sed ut dolor nec orci tincidunt interdum. Phasellus ipsum.
				Nunc tristique tempus lectus.</p>
		</div>
		<div id="tabs-2">
			<p>
				Morbi tincidunt, dui sit amet facilisis feugiat, odio metus gravida ante, ut pharetra
				massa metus id nunc. Duis scelerisque molestie turpis. Sed fringilla, massa eget
				luctus malesuada, metus eros molestie lectus, ut tempus eros massa ut dolor. Aenean
				aliquet fringilla sem. Suspendisse sed ligula in ligula suscipit aliquam. Praesent
				in eros vestibulum mi adipiscing adipiscing. Morbi facilisis. Curabitur ornare consequat
				nunc. Aenean vel metus. Ut posuere viverra nulla. Aliquam erat volutpat. Pellentesque
				convallis. Maecenas feugiat, tellus pellentesque pretium posuere, felis lorem euismod
				felis, eu ornare leo nisi vel felis. Mauris consectetur tortor et purus.</p>
		</div>
		<div id="tabs-3">
			<p>
				Mauris eleifend est et turpis. Duis id erat. Suspendisse potenti. Aliquam vulputate,
				pede vel vehicula accumsan, mi neque rutrum erat, eu congue orci lorem eget lorem.
				Vestibulum non ante. Class aptent taciti sociosqu ad litora torquent per conubia
				nostra, per inceptos himenaeos. Fusce sodales. Quisque eu urna vel enim commodo
				pellentesque. Praesent eu risus hendrerit ligula tempus pretium. Curabitur lorem
				enim, pretium nec, feugiat nec, luctus a, lacus.</p>
			<p>
				Duis cursus. Maecenas ligula eros, blandit nec, pharetra at, semper at, magna. Nullam
				ac lacus. Nulla facilisi. Praesent viverra justo vitae neque. Praesent blandit adipiscing
				velit. Suspendisse potenti. Donec mattis, pede vel pharetra blandit, magna ligula
				faucibus eros, id euismod lacus dolor eget odio. Nam scelerisque. Donec non libero
				sed nulla mattis commodo. Ut sagittis. Donec nisi lectus, feugiat porttitor, tempor
				ac, tempor vitae, pede. Aenean vehicula velit eu tellus interdum rutrum. Maecenas
				commodo. Pellentesque nec elit. Fusce in lacus. Vivamus a libero vitae lectus hendrerit
				hendrerit.</p>
		</div>
	</div>
 * 
 * Java:
 * add(new WijTabs("tabs"));
 * </p>
 * 
 * MISS:
 * 	* hideOption
 * 	* showOption
 *  * disabled
 *
 * @author Julien Roche
 * @since 1.0
 */
@WiQueryUIPlugin
public class WijTabs extends WebMarkupContainer implements IWiQueryPlugin {
	/**
	 * 
	 * <p>
	 * 	Enumeration for the alignment option
	 * </p>
	 *
	 * @author Julien Roche
	 * @since 1.0
	 */
	public enum WijTabsAlignment {
		BOTTOM,
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
	private static final long serialVersionUID = 6715042200942879346L;
	
	// Properties
	private Options options;

	/**
	 * @see Component#Component(String)
	 */
	public WijTabs(String id) {
		super(id);
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
		wiQueryResourceManager.addJavaScriptResource(CookieJavaScriptResourceReference.get());
		
		wiQueryResourceManager.addJavaScriptResource(GlobalizationJavascriptResourceReference.get());
		wiQueryResourceManager.addJavaScriptResource(JGlobTranslationJavascriptResourceReference.get());
		wiQueryResourceManager.addJavaScriptResource(JGlobJavascriptResourceReference.get());
		
		wiQueryResourceManager.addJavaScriptResource(WijTabsJavascriptResourceReference.get());
		wiQueryResourceManager.addCssResource(WijTabsStyleSheetResourceReference.get());
	}
	
	/**
	 * @return the ajaxOptions option value
	 */
	public JQueryAjaxOption getAjaxOptions() {
		IComplexOption ajaxOptions = this.options.getComplexOption("ajaxOptions");
		
		if(ajaxOptions != null && ajaxOptions instanceof JQueryAjaxOption) {
			return (JQueryAjaxOption)ajaxOptions;
		}
		
		return null;
	}
	
	/**
	 * @return the aligment option
	 */
	public WijTabsAlignment getAlignment() {
		String str = getOptions().getLiteral("alignment");
		return str == null ? WijTabsAlignment.BOTTOM : WijTabsAlignment.valueOf(str.toUpperCase());
	}
	
	/**
	 * @return the cookie option value
	 */
	public JQueryCookieOption getCookie() {
		IComplexOption cookie = this.options.getComplexOption("cookie");
		
		if(cookie != null && cookie instanceof JQueryCookieOption) {
			return (JQueryCookieOption) cookie;
		}
		
		return null;
	}
	
	/**
	 * @return the event option
	 */
	public String getEvent() {
		String str = getOptions().getLiteral("event");
		return str == null ? MouseEvent.CLICK.getEventLabel() : str;
	}
	
	/**
	 * @return the idPrefix option
	 */
	public String getIdPrefix() {
		String str = getOptions().getLiteral("idPrefix");
		return str == null ? "ui-tabs-" : str;
	}
	
	/**
	 * @return the options of the expander
	 */
	protected Options getOptions() {
		return this.options;
	}
	
	/**
	 * @return the panelTemplate option
	 */
	public String getPanelTemplate() {
		String str = getOptions().getLiteral("panelTemplate");
		return str == null ? "<div></div>" : str;
	}
	
	/**
	 * @return the spinner option
	 */
	public String getSpinner() {
		String str = getOptions().getLiteral("spinner");
		return str == null ? "<em>Loading&#8230;</em>" : str;
	}
	
	/**
	 * @return the tabTemplate option
	 */
	public String getTabTemplate() {
		String str = getOptions().getLiteral("tabTemplate");
		return str == null ? "<em>Loading&#8230;</em>" : str;
	}
	
	/**
	 * @return the cache option
	 */
	public boolean isCache() {
		Boolean bool = getOptions().getBoolean("cache");
		return bool == null ? false : bool;
	}
	
	/**
	 * @return the cache option
	 */
	public boolean isCollapsible() {
		Boolean bool = getOptions().getBoolean("collapsible");
		return bool == null ? false : bool;
	}
	
	/**
	 * @return the scrollable option
	 */
	public boolean isScrollable() {
		Boolean bool = getOptions().getBoolean("scrollable");
		return bool == null ? false : bool;
	}
	
	/**
	 * @return the sortable option
	 */
	public boolean isSortable() {
		Boolean bool = getOptions().getBoolean("sortable");
		return bool == null ? false : bool;
	}
	
	/**
	 * This event is triggered when a tab is added.
	 * @param add
	 * @return the current instance
	 */
	public WijTabs setAddEvent(JsScope add) {
		getOptions().put("add", add);
		return this;
	}
	
	/**
	 * Additional Ajax options to consider when loading tab content (see $.ajax).
	 * @param ajaxOptions
	 */
	public WijTabs setAjaxOptions(JQueryAjaxOption ajaxOptions) {
		this.options.put("ajaxOptions", ajaxOptions);
		return this;
	}
	
	/**
	 * Determines the tabs' alignment to the content
	 * @param alignment
	 * @return the current instance
	 */
	public WijTabs setAlignment(WijTabsAlignment alignment) {
		getOptions().putLiteral("alignment", alignment.toString());
		return this;
	}
	
	/**
	 * Whether or not to cache remote tabs content, e.g. load only once or with every click. 
	 * Cached content is being lazy loaded, e.g once and only once for the first click. 
	 * @param cache
	 * @return the current instance
	 */
	public WijTabs setCache(boolean cache) {
		getOptions().put("cache", cache);
		return this;
	}
	
	/**
	 * Determines whether a tab can be collapsed by a user. When this is set to 
	 * true, an already selected tab will be collapsed upon reselection. 
	 * @param collapsible
	 * @return the current instance
	 */
	public WijTabs setCollapsible(boolean collapsible) {
		getOptions().put("collapsible", collapsible);
		return this;
	}
	
	/**
	 * Method to store the latest selected tab in a cookie. The cookie is 
	 * then used to determine the initially selected tab if the 
	 * selected option is not defined. Requires cookie plugin. 
	 * The object needs to have key/value pairs of the form the 
	 * cookie plugin expects as options. Available options (example): 
	 * { expires: 7, path: '/', domain: 'jquery.com', secure: true }. 
	 * 
	 * Since jQuery UI 1.7 it is also possible to define the cookie name 
	 * being used via name property.
	 * 
	 * @param cookie
	 */
	public WijTabs setCookie(JQueryCookieOption cookie) {
		this.options.put("cookie", cookie);
		return this;
	}
	
	/**
	 * This event is triggered when a tab is disabled.
	 * @param disable
	 * @return the current instance
	 */
	public WijTabs setDisableEvent(JsScope disable) {
		getOptions().put("disable", disable);
		return this;
	}
	
	/**
	 * This event is triggered when a tab is enabled.
	 * @param enable
	 * @return the current instance
	 */
	public WijTabs setEnableEvent(JsScope enable) {
		getOptions().put("enable", enable);
		return this;
	}
	
	/**
	 * Determines the event that triggers the accordion.
	 * @param event
	 * @return the current instance
	 */
	public WijTabs setEvent(String event) {
		getOptions().putLiteral("event", event);
		return this;
	}
	
	/**
	 * Determines the event that triggers the accordion.
	 * @param idPrefix
	 * @return the current instance
	 */
	public WijTabs setIdPrefix(String idPrefix) {
		getOptions().putLiteral("idPrefix", idPrefix);
		return this;
	}
	
	/**
	 * This event is triggered after the content of a remote tab has been loaded.
	 * @param load
	 * @return the current instance
	 */
	public WijTabs setLoadEvent(JsScope load) {
		getOptions().put("load", load);
		return this;
	}
	
	/**
	 * HTML template from which a new tab panel is created in case of adding a 
	 * tab with the add method or when creating a panel for a remote tab on the fly.
	 * @param panelTemplate
	 * @return the current instance
	 */
	public WijTabs setPanelTemplate(String panelTemplate) {
		getOptions().putLiteral("panelTemplate", panelTemplate);
		return this;
	}
	
	/**
	 * This event is triggered when a tab is removed.
	 * @param remove
	 * @return the current instance
	 */
	public WijTabs setRemoveEvent(JsScope remove) {
		getOptions().put("remove", remove);
		return this;
	}
	
	/**
	 * Determines whether to wrap to the next line or scrolling is enabled when 
	 * the tabs exceed the width
	 * @param scrollable
	 * @return the current instance
	 */
	public WijTabs setScrollable(boolean scrollable) {
		getOptions().put("scrollable", scrollable);
		return this;
	}
	
	/**
	 * This event is triggered when clicking a tab.
	 * @param select
	 * @return the current instance
	 */
	public WijTabs setSelectEvent(JsScope select) {
		getOptions().put("select", select);
		return this;
	}
	
	/**
	 * This event is triggered when a tab is shown.
	 * @param show
	 * @return the current instance
	 */
	public WijTabs setShowEvent(JsScope show) {
		getOptions().put("show", show);
		return this;
	}
	
	/**
	 * Determines whether the tab can be dragged to a new position
	 * @param sortable
	 * @return the current instance
	 */
	public WijTabs setSortable(boolean sortable) {
		getOptions().put("sortable", sortable);
		return this;
	}
	
	/**
	 * The HTML content of this string is shown in a tab title while remote content is loading. 
	 * Pass in empty string to deactivate that behavior. 
	 * An span element must be present in the A tag of the title, for the spinner content to be visible.
	 * @param spinner
	 * @return the current instance
	 */
	public WijTabs setSpinner(String spinner) {
		getOptions().putLiteral("spinner", spinner);
		return this;
	}
	
	/**
	 * HTML template from which a new tab is created and added. 
	 * The placeholders #{href} and #{label} are replaced with the url and tab 
	 * label that are passed as arguments to the add method.
	 * @param tabTemplate
	 * @return the current instance
	 */
	public WijTabs setTabTemplate(String tabTemplate) {
		getOptions().putLiteral("tabTemplate", tabTemplate);
		return this;
	}

	/**
	 * {@inheritDoc}
	 * @see org.odlabs.wiquery.core.commons.IWiQueryPlugin#statement()
	 */
	public JsStatement statement() {
		return new JsQuery(this).$().chain("wijtabs", getOptions().getJavaScriptOptions());
	}
}
