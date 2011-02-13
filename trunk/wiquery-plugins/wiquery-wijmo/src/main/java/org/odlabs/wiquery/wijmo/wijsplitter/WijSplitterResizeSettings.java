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
 * 	ResizeSettings for the WijSplitter
 * </p>
 *
 * @author Julien Roche
 * @since 1.0
 */
public class WijSplitterResizeSettings implements IComplexOption {
	// Constants
	/** Constant of serialization */
	private static final long serialVersionUID = 5692224010029857915L;
	
	// Properties
	private Options options;
	
	/**
	 * Default constructor
	 */
	public WijSplitterResizeSettings() {
		super();
		this.options = new Options();
	}
	
	public int getAnimationDuration() {
		Integer value = getOptions().getInt("animationDuration");
		return value == null ? 100 : value;
	}

	public String getEasing() {
		String str = getOptions().getLiteral("easing");
		return str == null ? "swing" : str;
	}

	public int getIncrement() {
		Integer value = getOptions().getInt("animationDuration");
		return value == null ? 1 : value;
	}

	/**
	 * {@inheritDoc}
	 * @see org.odlabs.wiquery.core.options.IComplexOption#getJavascriptOption()
	 */
	public CharSequence getJavascriptOption() {
		return this.options.getJavaScriptOptions();
	}

	/**
	 * @return the options of the list
	 */
	protected Options getOptions() {
		return this.options;
	}

	public boolean isGhost() {
		Boolean bool = getOptions().getBoolean("ghost");
		return bool == null ? false : bool;
	}

	/**
	 * Define how long (in milliseconds) the animation of the sliding will run.
	 * @param animationDuration
	 * @return the instance
	 */
	public WijSplitterResizeSettings setAnimationDuration(int animationDuration) {
		getOptions().put("animationDuration", animationDuration);
		return this;
	}

	/**
	 * The easing that is applied to the animation.
	 * @param easing
	 * @return the instance
	 */
	public WijSplitterResizeSettings setEasing(String easing) {
		getOptions().putLiteral("easing", easing);
		return this;
	}

	/**
	 * A value that determines whether an outline of the element is sized.
	 * @param ghost
	 * @return the instance
	 */
	public WijSplitterResizeSettings setGhost(boolean ghost) {
		getOptions().put("ghost", ghost);
		return this;
	}

	/**
	 * A value that determines the movement span of incremental resizing. 
	 * @param increment
	 * @return the instance
	 */
	public WijSplitterResizeSettings setIncrement(int increment) {
		getOptions().put("increment", increment);
		return this;
	}
}
