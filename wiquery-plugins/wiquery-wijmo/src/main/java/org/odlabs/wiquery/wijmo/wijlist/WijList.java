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
package org.odlabs.wiquery.wijmo.wijlist;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.odlabs.wiquery.core.commons.IWiQueryPlugin;
import org.odlabs.wiquery.core.commons.WiQueryResourceManager;
import org.odlabs.wiquery.core.javascript.JsQuery;
import org.odlabs.wiquery.core.javascript.JsScope;
import org.odlabs.wiquery.core.javascript.JsStatement;
import org.odlabs.wiquery.core.options.Options;
import org.odlabs.wiquery.ui.commons.WiQueryUIPlugin;
import org.odlabs.wiquery.ui.widget.WidgetJavascriptResourceReference;
import org.odlabs.wiquery.wijmo.wijsuperpanel.WijSuperpanelJavascriptResourceReference;
import org.odlabs.wiquery.wijmo.wijsuperpanel.WijSuperpanelStyleSheetResourceReference;

/**
 * $Id$
 * 
 * <p>
 * 	Bind the Wijmo list widget
 * </p>
 * 
 * <p>
 * Examples of use:
 * 
 * HTML:
 * <div id="list"></div>
 * 
 * Java:
 * add(new WijList("list"));
 * </p>
 * 
 * MISS: superPanelOptions
 * method move
 * method select
 * method selectItems
 * method unselectItems
 * method previous
 * method next
 * method activate
 * method setItems
 *
 * @author Julien
 * @since 1.0
 */
@WiQueryUIPlugin
public class WijList extends WebMarkupContainer implements IWiQueryPlugin {
	/**
	 * <p>
	 * 	Enumeration for the selectionMode
	 * </p>
	 *
	 * @author Julien Roche
	 * @since 1.0
	 */
	public enum WijListSelectionMode {
		SINGLE,
		MULTIPLE;

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
	private static final long serialVersionUID = 709572900879612009L;
	
	// Properties
	private Options options;

	/**
	 * @see Component#Component(String)
	 */
	public WijList(String id) {
		super(id);
		this.options = new Options();
	}

	/**
	 * {@inheritDoc}
	 * @see org.odlabs.wiquery.core.commons.IWiQueryPlugin#contribute(org.odlabs.wiquery.core.commons.WiQueryResourceManager)
	 */
	public void contribute(WiQueryResourceManager wiQueryResourceManager) {
		wiQueryResourceManager.addJavaScriptResource(WidgetJavascriptResourceReference.get());
		wiQueryResourceManager.addJavaScriptResource(WijSuperpanelJavascriptResourceReference.get());
		wiQueryResourceManager.addJavaScriptResource(WijListJavascriptResourceReference.get());
		
		wiQueryResourceManager.addCssResource(WijSuperpanelStyleSheetResourceReference.get());
		wiQueryResourceManager.addCssResource(WijListStyleSheetResourceReference.get());
	}
	
	/**
	 * Deactivates activated items.
	 * This will return the element back to its pre-init state.
	 * @return the associated JsStatement
	 */
	public JsStatement deactivate() {
		return new JsQuery(this).$().chain("wijlist", "'deactivate'");
	}

	/**
	 * Deactivates activated items.
	 * @param ajaxRequestTarget
	 */
	public void deactivate(AjaxRequestTarget ajaxRequestTarget) {
		ajaxRequestTarget.appendJavascript(this.deactivate().render().toString());
	}
	
	/**
	 * Destroys wijlist.
	 * This will return the element back to its pre-init state.
	 * @return the associated JsStatement
	 */
	public JsStatement destroy() {
		return new JsQuery(this).$().chain("wijlist", "'destroy'");
	}
	
	/**
	 * Destroys wijlist.
	 * @param ajaxRequestTarget
	 */
	public void destroy(AjaxRequestTarget ajaxRequestTarget) {
		ajaxRequestTarget.appendJavascript(this.destroy().render().toString());
	}
	
	/**
	 * Tests that the focus is at the first item.
	 * This will return the element back to its pre-init state.
	 * @return the associated JsStatement
	 */
	public JsStatement first() {
		return new JsQuery(this).$().chain("wijlist", "'first'");
	}
	
	/**
	 * Tests that the focus is at the first item.
	 * @param ajaxRequestTarget
	 */
	public void first(AjaxRequestTarget ajaxRequestTarget) {
		ajaxRequestTarget.appendJavascript(this.first().render().toString());
	}
	
	/**
	 * Gets the JQuery object reference of the ul element of wijlist. Return a jQuery Object
	 * This will return the element back to its pre-init state.
	 * @return the associated JsStatement
	 */
	public JsStatement getList() {
		return new JsQuery(this).$().chain("wijlist", "'getList'");
	}
	
	/**
	 * Gets the JQuery object reference of the ul element of wijlist. Return a jQuery Object
	 * @param ajaxRequestTarget
	 */
	public void getList(AjaxRequestTarget ajaxRequestTarget) {
		ajaxRequestTarget.appendJavascript(this.getList().render().toString());
	}
	
	/**
	 * @return the maxItemsCount option
	 */
	public int getMaxItemsCount() {
		Integer nb = getOptions().getInt("maxItemsCount");
		return nb == null ? 5 : nb;
	}
	
	/**
	 * @return the options of the list
	 */
	protected Options getOptions() {
		return this.options;
	}
	
	/**
	 * @return the selectionMode option
	 */
	public WijListSelectionMode getSelectionMode() {
		String str = getOptions().getLiteral("selectionMode");
		return str == null ? WijListSelectionMode.SINGLE : WijListSelectionMode.valueOf(str.toUpperCase());
	}
	
	/**
	 * @return the addHoverItemClass option
	 */
	public boolean isAddHoverItemClass() {
		Boolean bool = getOptions().getBoolean("addHoverItemClass");
		return bool == null ? true : bool;
	}
	
	/**
	 * @return the autoSize option
	 */
	public boolean isAutoSize() {
		Boolean bool = getOptions().getBoolean("autoSize");
		return bool == null ? false : bool;
	}
	
	/**
	 * @return the disabled option
	 */
	public boolean isDisabled() {
		Boolean bool = getOptions().getBoolean("disabled");
		return bool == null ? false : bool;
	}
	
	/**
	 * @return the keepHightlightOnMouseLeave option
	 */
	public boolean isKeepHightlightOnMouseLeave() {
		Boolean bool = getOptions().getBoolean("keepHightlightOnMouseLeave");
		return bool == null ? false : bool;
	}
	
	/**
	 * Tests that the focus is at the last item.
	 * This will return the element back to its pre-init state.
	 * @return the associated JsStatement
	 */
	public JsStatement last() {
		return new JsQuery(this).$().chain("wijlist", "'last'");
	}
	
	/**
	 * Tests that the focus is at the last item.
	 * @param ajaxRequestTarget
	 */
	public void last(AjaxRequestTarget ajaxRequestTarget) {
		ajaxRequestTarget.appendJavascript(this.last().render().toString());
	}
	
	/**
	 * Turns to the next page of the list.
	 * This will return the element back to its pre-init state.
	 * @return the associated JsStatement
	 */
	public JsStatement nextPage() {
		return new JsQuery(this).$().chain("wijlist", "'nextPage'");
	}
	
	/**
	 * Turns to the next page of the list.
	 * @param ajaxRequestTarget
	 */
	public void nextPage(AjaxRequestTarget ajaxRequestTarget) {
		ajaxRequestTarget.appendJavascript(this.nextPage().render().toString());
	}
	
	/**
	 * Turns to the previous page of the wijlist.
	 * This will return the element back to its pre-init state.
	 * @return the associated JsStatement
	 */
	public JsStatement previousPage() {
		return new JsQuery(this).$().chain("wijlist", "'previousPage'");
	}
	
	/**
	 * Turns to the previous page of the wijlist.
	 * @param ajaxRequestTarget
	 */
	public void previousPage(AjaxRequestTarget ajaxRequestTarget) {
		ajaxRequestTarget.appendJavascript(this.previousPage().render().toString());
	}
	
	/**
	 * Reset the layout of superpanel to reflect the change in content.
	 * This will return the element back to its pre-init state.
	 * @return the associated JsStatement
	 */
	public JsStatement refreshSuperPanel() {
		return new JsQuery(this).$().chain("wijlist", "'refreshSuperPanel'");
	}
	
	/**
	 * Reset the layout of superpanel to reflect the change in content.
	 * @param ajaxRequestTarget
	 */
	public void refreshSuperPanel(AjaxRequestTarget ajaxRequestTarget) {
		ajaxRequestTarget.appendJavascript(this.refreshSuperPanel().render().toString());
	}
	
	/**
	 * Render items of wijlist.
	 * This will return the element back to its pre-init state.
	 * @return the associated JsStatement
	 */
	public JsStatement renderList() {
		return new JsQuery(this).$().chain("wijlist", "'renderList'");
	}
	
	/**
	 * Render items of wijlist.
	 * @param ajaxRequestTarget
	 */
	public void renderList(AjaxRequestTarget ajaxRequestTarget) {
		ajaxRequestTarget.appendJavascript(this.renderList().render().toString());
	}
	
	/**
	 * A value determines whether to add ui-state-hover class to list item when mouse enters.
	 * @param addHoverItemClass
	 * @return the current instance
	 */
	public WijList setAddHoverItemClass(boolean addHoverItemClass) {
		getOptions().put("addHoverItemClass", addHoverItemClass);
		return this;
	}
	
	/**
	 * A value determines whether to auto-size wijlist.
	 * @param autoSize
	 * @return the current instance
	 */
	public WijList setAutoSize(boolean autoSize) {
		getOptions().put("autoSize", autoSize);
		return this;
	}
	
	/**
	 * A function called when an item loses focus.
	 * Two parameters for the scope: e (event object) and item (list item).
	 * @param blur
	 * @return the current instance
	 */
	public WijList setBlurEvent(JsScope blur){
		getOptions().put("blur", blur);
		return this;
	}
	
	/**
	 * A value indicates whether wijlist is disabled.
	 * @param disabled
	 * @return the current instance
	 */
	public WijList setDisabled(boolean disabled) {
		getOptions().put("disabled", disabled);
		return this;
	}
	
	/**
	 * A function called after an item is focused.
	 * Two parameters for the scope: e (event object) and item (list item).
	 * @param focus
	 * @return the current instance
	 */
	public WijList setFocusEvent(JsScope focus){
		getOptions().put("focus", focus);
		return this;
	}
	
	/**
	 * A function called before an item is focused.
	 * Two parameters for the scope: e (event object) and item (list item).
	 * returns false to cancel item focusing.
	 * @param focusing
	 * @return the current instance
	 */
	public WijList setFocusingEvent(JsScope focusing){
		getOptions().put("focusing", focusing);
		return this;
	}
	
	/**
	 * A function called after a item is rendered.
	 * Two parameters for the scope: e (event object) and item (list item).
	 * @param itemrendered
	 * @return the current instance
	 */
	public WijList setItemrenderedEvent(JsScope itemrendered){
		getOptions().put("itemrendered", itemrendered);
		return this;
	}
	
	/**
	 * A function called before an item is rendered.
	 * Two parameters for the scope: e (event object) and item (list item).
	 * item to be rendered.
	 * item.element: LI element with this item.
	 * item.list: wijlist instance.
	 * item.label: label of item.
	 * item.value: value of item.
	 * item.text: could be set in handler to override rendered label of item.
	 * @param itemrendering
	 * @return the current instance
	 */
	public WijList setItemrenderingEvent(JsScope itemrendering){
		getOptions().put("itemrendering", itemrendering);
		return this;
	}
	
	/**
	 * A value determines whether to keep item highlight when mouse is leaving list.
	 * @param keepHightlightOnMouseLeave
	 * @return the current instance
	 */
	public WijList setKeepHightlightOnMouseLeave(boolean keepHightlightOnMouseLeave) {
		getOptions().put("keepHightlightOnMouseLeave", keepHightlightOnMouseLeave);
		return this;
	}
	
	/**
	 * A function called after list is rendered
	 * Two parameters for the scope: e (event object) and list.
	 * @param listrendered
	 * @return the current instance
	 */
	public WijList setListrenderedEvent(JsScope listrendered){
		getOptions().put("listrendered", listrendered);
		return this;
	}
	
	/**
	 * A value specifies the max items count to display if autoSize is set to true.
	 * @param maxItemsCount
	 * @return the current instance
	 */
	public WijList setMaxItemsCount(int maxItemsCount) {
		getOptions().put("maxItemsCount", maxItemsCount);
		return this;
	}
	
	/**
	 * Select event handler of wijlist. A function will be called when any item in the list is selected
	 * Two parameters for the scope: e (event object) and data.
	 * By data.item to obtain the item selected. 
	 * By data.item.element to obtain the li DOM element selected.
	 * @param selected
	 * @return the current instance
	 */
	public WijList setSelectedEvent(JsScope selected){
		getOptions().put("selected", selected);
		return this;
	}
	
	/**
	 * A value indicates the selection mode of wijlist.
	 * @param selectionMode
	 * @return the current instance
	 */
	public WijList setSelectionMode(WijListSelectionMode selectionMode) {
		getOptions().putLiteral("selectionMode", selectionMode.toString());
		return this;
	}
	
	/**
	 * {@inheritDoc}
	 * @see org.odlabs.wiquery.core.commons.IWiQueryPlugin#statement()
	 */
	public JsStatement statement() {
		return new JsQuery(this).$().chain("wijlist", getOptions().getJavaScriptOptions());
	}
}
