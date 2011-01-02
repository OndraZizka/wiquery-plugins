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
package org.odlabs.wiquery.wijmo.wijutil;

import org.odlabs.wiquery.core.javascript.ChainableStatement;
import org.odlabs.wiquery.core.javascript.DefaultChainableStatement;
import org.odlabs.wiquery.core.javascript.JsUtils;

/**
 * $Id$
 * <p>
 * 	Helper to bind wijmo utitilies functions.
 * </p>
 * 
 * <p>
 * Miss all charValidator methods
 * </p>
 * 
 * @author Julien Roche
 * @since 1.0
 */
public class WijUtilHelper {

	/**
	 * Binds the <code>borderSize</code> statement.
	 */
	public static ChainableStatement borderSize() {
		return new DefaultChainableStatement("borderSize");
	}
	
	/**
	 * Binds the <code>bottomBorderWidth</code> statement.
	 */
	public static ChainableStatement bottomBorderWidth() {
		return new DefaultChainableStatement("bottomBorderWidth");
	}
	
	/**
	 * Binds the <code>getWidget</code> statement.
	 */
	public static ChainableStatement getWidget() {
		return new DefaultChainableStatement("getWidget");
	}
	
	/**
	 * Binds the <code>leftBorderWidth</code> statement.
	 */
	public static ChainableStatement leftBorderWidth() {
		return new DefaultChainableStatement("leftBorderWidth");
	}
	
	/**
	 * Binds the <code>rightBorderWidth</code> statement.
	 */
	public static ChainableStatement rightBorderWidth() {
		return new DefaultChainableStatement("rightBorderWidth");
	}
	
	/**
	 * Binds the <code>setOutHeight</code> statement.
	 */
	public static ChainableStatement setOutHeight(int height) {
		return new DefaultChainableStatement("setOutHeight", Integer.toString(height));
	}

	/**
	 * Binds the <code>setOutWidth</code> statement.
	 */
	public static ChainableStatement setOutWidth(int width) {
		return new DefaultChainableStatement("setOutWidth", Integer.toString(width));
	}
	
	/**
	 * Binds the <code>topBorderWidth</code> statement.
	 */
	public static ChainableStatement topBorderWidth() {
		return new DefaultChainableStatement("topBorderWidth");
	}
	
	/**
	 * Binds the <code>wijContent</code> statement.
	 */
	public static ChainableStatement wijContent(String url) {
		return new DefaultChainableStatement("wijContent", JsUtils.quotes(url));
	}
}
