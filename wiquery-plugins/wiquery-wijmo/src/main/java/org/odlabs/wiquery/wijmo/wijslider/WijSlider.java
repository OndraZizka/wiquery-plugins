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
package org.odlabs.wiquery.wijmo.wijslider;

import org.odlabs.wiquery.core.javascript.JsQuery;
import org.odlabs.wiquery.core.javascript.JsStatement;
import org.odlabs.wiquery.ui.slider.Slider;

/**
 * $Id$
 * 
 * <p>
 * 	Wijmo slider widget
 * </p>
 * 
 * <p>
 * Examples of use:
 * 
 * HTML:
 * <div wicket:id='slider'></div>
 * 
 * Java:
 * add(new WijSlider());
 * </p>
 *
 * @author Julien Roche
 * @since 1.0
 */
public class WijSlider extends Slider {
	// Constants
	/** Constant of serialization */
	private static final long serialVersionUID = -5679899267440518604L;

	/** 
	 * Constructor
	 * @param id Markup identifiant
	 * @param min Minimum value
	 * @param max Maximum value
	 */
	public WijSlider(String id, Number min, Number max) {
		super(id, min, max);
	}
	
	/**
	 * @return the dragFill option
	 */
	public boolean isDragFill() {
		Boolean bool = getOptions().getBoolean("dragFill");
		return bool == null ? true : bool;
	}
	
	/**
	 * Determines whether the fill may be dragged between the buttons
	 * @param dragFill
	 * @return the current instance
	 */
	public WijSlider setDragFill(boolean dragFill) {
		getOptions().put("dragFill", dragFill);
		return this;
	}

	/**
	 * {@inheritDoc}
	 * @see org.odlabs.wiquery.ui.dialog.Dialog#statement()
	 */
	@Override
	public JsStatement statement() {
		return new JsQuery(this).$().chain("wijslider", getOptions().getJavaScriptOptions());
	}
}
