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
package org.odlabs.wiquery.wijmo.options;

import org.apache.wicket.WicketRuntimeException;
import org.odlabs.wiquery.core.javascript.JsScope;
import org.odlabs.wiquery.core.javascript.JsUtils;
import org.odlabs.wiquery.core.options.IComplexOption;

/**
 * $Id$
 * 
 * <p>
 * 	Some Wijmo parameters can have a String or a Function
 * </p>
 *
 * @author Julien Roche
 * @since 1.0
 */
public class WijmoString implements IComplexOption {
	// Constants
	/** Constant of serialization */
	private static final long serialVersionUID = 1L;
	
	// Properties
	private JsScope scope;
	private String string;
	
	/**
	 * Constructor
	 * @param scope Scope that return the string to use
	 */
	public WijmoString(JsScope scope){
		this(scope, null);
	}
	
	/**
	 * Private constructor
	 * @param scope
	 * @param string
	 */
	private WijmoString(JsScope scope, String string) {
		setParam(scope, string);
	}
	
	/**
	 * Constructor
	 * @param string String
	 */
	public WijmoString(String string){
		this(null, string);
	}

	/**
	 * {@inheritDoc}
	 * @see org.odlabs.wiquery.core.options.IComplexOption#getJavascriptOption()
	 */
	public CharSequence getJavascriptOption() {
		if(string == null || scope == null){
			throw new WicketRuntimeException("You must specify a value for the WijmoString");
		}
		
		return string == null ? scope.render() : JsUtils.quotes(string);
	}

	public JsScope getScope() {
		return scope;
	}

	public String getString() {
		return string;
	}

	/**
	 * Method setting the right parameter
	 * @param scope
	 * @param string
	 */
	private void setParam(JsScope scope, String string) {
		this.scope = scope;
		this.string = string;
	}

	public void setScope(JsScope scope) {
		setParam(scope, null);
	}
	
	public void setString(String string) {
		setParam(null, string);
	}
}
