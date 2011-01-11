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
package org.odlabs.wiquery.wijmo.wijcalendar;

import org.odlabs.wiquery.core.options.IComplexOption;

/**
 * $Id$
 * 
 * <p>
 * 	Complex option for the selectionMode option
 * </p>
 * 
 * <p>
 * 	Sets the date selection mode on the calendar control that specifies whether 
 * 	the user can select a single day, a week, or an entire month. 
 * </p>
 *
 * @author Julien Roche
 * @since 1.0
 */
public class WijmoSelectionMode implements IComplexOption {
	// Constants
	/** Constant of serialization */
	private static final long serialVersionUID = 1L;
	
	// Properties
	private boolean day;
	private boolean days;
	
	/**
	 * Constructor
	 */
	public WijmoSelectionMode(boolean day, boolean days){
		super();
		this.day = day;
		this.days = days;
	}
	
	/**
	 * {@inheritDoc}
	 * @see org.odlabs.wiquery.core.options.IComplexOption#getJavascriptOption()
	 */
	public CharSequence getJavascriptOption() {
		return "{ day:" + this.day + ", days:" + this.days + " }";
	}

	public boolean isDay() {
		return day;
	}

	public boolean isDays() {
		return days;
	}

	public void setDay(boolean day) {
		this.day = day;
	}

	public void setDays(boolean days) {
		this.days = days;
	}
}
