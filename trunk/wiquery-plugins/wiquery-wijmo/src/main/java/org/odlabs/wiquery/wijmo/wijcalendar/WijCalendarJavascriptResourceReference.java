/*
 * Copyright (c) 20010 WiQuery team
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

import org.odlabs.wiquery.core.commons.WiQueryJavaScriptResourceReference;

/**
 * $Id$
 * 
 * <p>
 * 	{@link WiQueryJavaScriptResourceReference} for the wijCalendar widget
 * </p>
 *
 * @author Julien Roche
 * @since 1.0
 */
public class WijCalendarJavascriptResourceReference extends WiQueryJavaScriptResourceReference {
	// Constants
	/** Constant of serialization */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Singleton instance.
	 */
	private static final WijCalendarJavascriptResourceReference INSTANCE = new WijCalendarJavascriptResourceReference();

	/**
	 * Default constructor
	 */
	private WijCalendarJavascriptResourceReference() {
		super(WijCalendarJavascriptResourceReference.class, "../javascript/jquery.ui.wijcalendar.js");
	}

	/**
	 * Returns the {@link WijCalendarJavascriptResourceReference} instance.
	 */
	public static WijCalendarJavascriptResourceReference get() {
		return INSTANCE;
	}
}
