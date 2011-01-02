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
package org.odlabs.wiquery.wijmo.wijdropdowndecorator;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.odlabs.wiquery.core.behavior.WiQueryAbstractBehavior;
import org.odlabs.wiquery.core.commons.WiQueryResourceManager;
import org.odlabs.wiquery.core.javascript.JsQuery;
import org.odlabs.wiquery.core.javascript.JsStatement;
import org.odlabs.wiquery.core.options.Options;
import org.odlabs.wiquery.ui.commons.WiQueryUIPlugin;
import org.odlabs.wiquery.ui.widget.WidgetJavascriptResourceReference;

/**
 * $Id$
 * 
 * <p>
 * 	Behavior binding the WijDropdownDecorator widget
 * </p>
 * 
 * <p>
 * Examples of use:
 * 
 * HTML:
 * <select wicket:id='component'></select>
 * 
 * Java:
 * component.add(new WijDropdownDecoratorBehavior());
 * 
 * </p>
 *
 * @author Julien Roche
 * @since 1.0
 */
@WiQueryUIPlugin
public class WijDropdownDecoratorBehavior extends WiQueryAbstractBehavior {
	// Constants
	/** Constant of serialization */
	private static final long serialVersionUID = 1L;
	
	// Properties
	private Options options;
	
	/**
	 * Default constructor
	 */
	public WijDropdownDecoratorBehavior() {
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
		
		wiQueryResourceManager.addCssResource(WijDropdownDecoratorStyleSheetResourceReference.get());
		wiQueryResourceManager.addJavaScriptResource(WidgetJavascriptResourceReference.get());
		wiQueryResourceManager.addJavaScriptResource(WijDropdownDecoratorJavascriptResourceReference.get());
	}
	
	/**
	 * @return the height option
	 */
	public int getHeight() {
		Integer height = getOptions().getInt("height");
		return height == null ? 250 : height;
	}
	
	/**
	 * @return the {@link Options} of the behavior
	 */
	protected Options getOptions() {
		return this.options;
	}
	
	/**
	 * @return the width option
	 */
	public int getWidth() {
		Integer width = getOptions().getInt("width");
		return width == null ? 300 : width;
	}
	
	/**Refreshes the element
	 * This will return the element back to its pre-init state.
	 * @return the associated JsStatement
	 */
	public JsStatement refresh() {
		return new JsQuery(getComponent()).$().chain("wijdropdowndecorator", "'refresh'");
	}

	/**Refreshes the element
	 * @param ajaxRequestTarget
	 */
	public void refresh(AjaxRequestTarget ajaxRequestTarget) {
		ajaxRequestTarget.appendJavascript(this.refresh().render().toString());
	}

	/**
	 * Set the height of the element
	 * @param height Height of the DropDown element
	 * @return the current instance of the behavior
	 */
	public WijDropdownDecoratorBehavior setHeight(int height) {
		getOptions().put("height", height);
		return this;
	}
	
	/**
	 * Set the width of the element
	 * @param width Width of the DropDown element
	 * @return the current instance of the behavior
	 */
	public WijDropdownDecoratorBehavior setWidth(int width) {
		getOptions().put("width", width);
		return this;
	}

	/**
	 * {@inheritDoc}
	 * @see org.odlabs.wiquery.core.behavior.WiQueryAbstractBehavior#statement()
	 */
	@Override
	public JsStatement statement() {
		return new JsQuery(getComponent()).$().chain("wijdropdowndecorator", getOptions().getJavaScriptOptions());
	}
}
