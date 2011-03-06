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
package org.odlabs.wiquery.wijmo.wijsuperpanel;

import org.odlabs.wiquery.core.effects.AnimateDuration;
import org.odlabs.wiquery.core.options.IComplexOption;
import org.odlabs.wiquery.core.options.Options;

/**
 * $Id$
 * 
 * <p>
 * 	Wijmo type for the animation
 * </p>
 *
 * @author Julien Roche
 * @since 1.0
 */
public class WijSuperpanelAnimationOptions implements IComplexOption {
	// Constants
	/** Constant of serialization */
	private static final long serialVersionUID = 1L;
	
	// Properties
	private Options options;
	
	/**
	 * Default constructor
	 */
	public WijSuperpanelAnimationOptions() {
		this.options = new Options();
	}
	
	/**
	 * Constructor
	 */
	public WijSuperpanelAnimationOptions(boolean queue, AnimateDuration duration, String easing){
		this();
		setQueue(queue);
		setDuration(duration);
		setEasing(easing);
	}

	public AnimateDuration getDuration() {
		return (AnimateDuration) this.options.getComplexOption("duration");
	}
	
	public String getEasing() {
		return this.options.getLiteral("easing");
	}
	
	/**
	 * {@inheritDoc}
	 * @see org.odlabs.wiquery.core.options.IComplexOption#getJavascriptOption()
	 */
	public CharSequence getJavascriptOption() {
		return this.options.getJavaScriptOptions();
	}
	
	public boolean getQueue() {
		return this.options.getBoolean("queue");
	}
	
	public WijSuperpanelAnimationOptions setDuration(AnimateDuration duration) {
		this.options.put("duration", duration);
		return this;
	}
	
	public WijSuperpanelAnimationOptions setEasing(String easing) {
		this.options.putLiteral("easing", easing);
		return this;
	}
	
	public WijSuperpanelAnimationOptions setQueue(Boolean queue) {
		this.options.put("queue", queue);
		return this;
	}
}
