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
package org.odlabs.wiquery.wijmo.wijdialog;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.odlabs.wiquery.core.commons.WiQueryResourceManager;
import org.odlabs.wiquery.core.javascript.JsQuery;
import org.odlabs.wiquery.core.javascript.JsStatement;
import org.odlabs.wiquery.ui.dialog.Dialog;
import org.odlabs.wiquery.wijmo.wijutil.WijUtilJavascriptResourceReference;
import org.odlabs.wiquery.wijmo.wijutil.WijUtilStyleSheetResourceReference;

/**
 * $Id$
 * 
 * <p>
 * 	Override the {@link Dialog} widget and add mores options
 * </p>
 * 
 * Miss :
 * *captionButtons
 * *collapsingAnimation
 * *expandingAnimation
 *
 * @author Julien Roche
 * @since 1.0
 */
public class WijDialog extends Dialog {
	// Constants
	/** Constant of serialization */
	private static final long serialVersionUID = -4316290161481981884L;

	/**
	 * Builds a new instance of {@link WijDialog} for the given wicket id.
	 * 
	 * @param id the given wicket id.
	 */
	public WijDialog(String id) {
		super(id);
	}

	/**
	 * {@inheritDoc}
	 * @see org.odlabs.wiquery.ui.dialog.Dialog#contribute(org.odlabs.wiquery.core.commons.WiQueryResourceManager)
	 */
	@Override
	public void contribute(WiQueryResourceManager wiQueryResourceManager) {
		super.contribute(wiQueryResourceManager);
		
		wiQueryResourceManager.addCssResource(WijUtilStyleSheetResourceReference.get());
		wiQueryResourceManager.addCssResource(WijDialogStyleSheetResourceReference.get());
		wiQueryResourceManager.addJavaScriptResource(WijUtilJavascriptResourceReference.get());
		wiQueryResourceManager.addJavaScriptResource(WijDialogJavascriptResourceReference.get());
	}

	/**
	 * @return the contentUrl option
	 */
	public String getContentUrl() {
		return getOptions().getLiteral("contentUrl");
	}
	
	/**
	 * @return the minimizeZoneElementId option
	 */
	public String getMinimizeZoneElementId() {
		return getOptions().getLiteral("minimizeZoneElementId");
	}
	
	/**Maximizes wijWindow.
	 * This will return the element back to its pre-init state.
	 * @return the associated JsStatement
	 */
	public JsStatement maximize() {
		return new JsQuery(this).$().chain("wijdialog", "'maximize'");
	}
	
	/**Maximizes wijWindow.
	 * @param ajaxRequestTarget
	 */
	public void maximize(AjaxRequestTarget ajaxRequestTarget) {
		ajaxRequestTarget.appendJavascript(this.maximize().render().toString());
	}
	
	/**Minimizes wijWindow.
	 * This will return the element back to its pre-init state.
	 * @return the associated JsStatement
	 */
	public JsStatement minimize() {
		return new JsQuery(this).$().chain("wijdialog", "'minimize'");
	}
	
	/**Minimizes wijWindow.
	 * @param ajaxRequestTarget
	 */
	public void minimize(AjaxRequestTarget ajaxRequestTarget) {
		ajaxRequestTarget.appendJavascript(this.minimize().render().toString());
	}

	/**Pins the wijwindow instance so that it could not be moved.
	 * This will return the element back to its pre-init state.
	 * @return the associated JsStatement
	 */
	public JsStatement pin() {
		return new JsQuery(this).$().chain("wijdialog", "'pin'");
	}
	
	/**Pins the wijwindow instance so that it could not be moved.
	 * @param ajaxRequestTarget
	 */
	public void pin(AjaxRequestTarget ajaxRequestTarget) {
		ajaxRequestTarget.appendJavascript(this.pin().render().toString());
	}

	/**Refreshes the iframe content in C1Window.
	 * This will return the element back to its pre-init state.
	 * @return the associated JsStatement
	 */
	public JsStatement refresh() {
		return new JsQuery(this).$().chain("wijdialog", "'refresh'");
	}
	
	/**Refreshes the iframe content in C1Window.
	 * @param ajaxRequestTarget
	 */
	public void refresh(AjaxRequestTarget ajaxRequestTarget) {
		ajaxRequestTarget.appendJavascript(this.refresh().render().toString());
	}

	/**Restores wijdialog to normal size from minimized and maximized state.
	 * This will return the element back to its pre-init state.
	 * @return the associated JsStatement
	 */
	public JsStatement restore() {
		return new JsQuery(this).$().chain("wijdialog", "'restore'");
	}
	
	/**Restores wijdialog to normal size from minimized and maximized state.
	 * @param ajaxRequestTarget
	 */
	public void restore(AjaxRequestTarget ajaxRequestTarget) {
		ajaxRequestTarget.appendJavascript(this.restore().render().toString());
	}

	/**
	 * Specifies the URL for the iframe element inside wijdialog
	 * @param contentUrl
	 * @return the instance of the component
	 */
	public WijDialog setContentUrl(String contentUrl) {
		getOptions().putLiteral("contentUrl", contentUrl);
		return this;
	}
	
	/**
	 * Specifies the ID of the DOM element to dock to when wijdialog is minimized.
	 * @param minimizeZoneElementId
	 * @return the instance of the component
	 */
	public WijDialog setMinimizeZoneElementId(String minimizeZoneElementId) {
		getOptions().putLiteral("minimizeZoneElementId", minimizeZoneElementId);
		return this;
	}

	/**
	 * {@inheritDoc}
	 * @see org.odlabs.wiquery.ui.dialog.Dialog#statement()
	 */
	@Override
	public JsStatement statement() {
		return new JsQuery(this).$().chain("wijdialog", getOptions().getJavaScriptOptions());
	}
	
	/**Toggles the dialog.
	 * This will return the element back to its pre-init state.
	 * @return the associated JsStatement
	 */
	public JsStatement toggle() {
		return new JsQuery(this).$().chain("wijdialog", "'toggle'");
	}

	/**Toggles the dialog.
	 * @param ajaxRequestTarget
	 */
	public void toggle(AjaxRequestTarget ajaxRequestTarget) {
		ajaxRequestTarget.appendJavascript(this.toggle().render().toString());
	}
}
