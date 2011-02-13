/*
 * Copyright (c) 2011 WiQuery team
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
package org.odlabs.wiquery.wijmo.wijsplitter;

import org.odlabs.wiquery.core.options.IComplexOption;
import org.odlabs.wiquery.core.options.Options;

/**
 * $Id$
 * 
 * <p>
 * 	Panel for the WijSplitter
 * </p>
 *
 * @author Julien Roche
 * @since 1.0
 */
public class WijSplitterPanel implements IComplexOption {
	// Constants
	/** Constant of serialization */
	private static final long serialVersionUID = 5692224010029857915L;
	
	// Properties
	private Options options;
	
	/**
	 * Default constructor
	 */
	public WijSplitterPanel() {
		super();
		this.options = new Options();
	}

	/**
	 * {@inheritDoc}
	 * @see org.odlabs.wiquery.core.options.IComplexOption#getJavascriptOption()
	 */
	public CharSequence getJavascriptOption() {
		return this.options.getJavaScriptOptions();
	}

	public int getMinSize() {
		Integer value = getOptions().getInt("minSize");
		return value == null ? 1 : value;
	}

	/**
	 * @return the options of the list
	 */
	protected Options getOptions() {
		return this.options;
	}
	
	public String getScrollBars() {
		String value = getOptions().getLiteral("scrollBars");
		return value == null ? "auto" : value;
	}
	
	public boolean isCollapsed() {
		Boolean bool = getOptions().getBoolean("collapsed");
		return bool == null ? false : bool;
	}
	
	/**
	 * A value determining whether splitter panel is collapsed or expanded.
	 * @param collapsed
	 * @return the current instance
	 */
	public WijSplitterPanel setCollapsed(boolean collapsed) {
		getOptions().put("collapsed", collapsed);
		return this;
	}
	
	/**
	 * Sets the minimum distance in pixels when resizing the splitter.
	 * @param minSize
	 * @return the current instance
	 */
	public WijSplitterPanel setMinSize(int minSize) {
		getOptions().put("minSize", minSize);
		return this;
	}
	
	/**
	 * Sets the type of scroll bars to display for splitter panel.
	 * @param scrollBars
	 * @return the current instance
	 */
	public WijSplitterPanel setMinSize(String scrollBars) {
		getOptions().putLiteral("scrollBars", scrollBars);
		return this;
	}
}
