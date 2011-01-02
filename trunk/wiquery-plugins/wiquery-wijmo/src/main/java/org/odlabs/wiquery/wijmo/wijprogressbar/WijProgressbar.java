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
package org.odlabs.wiquery.wijmo.wijprogressbar;

import org.odlabs.wiquery.core.commons.WiQueryResourceManager;
import org.odlabs.wiquery.ui.progressbar.ProgressBar;

/**
 * $Id$
 * 
 * <p>
 * 	Wijmo progressbar widget
 * </p>
 * 
 * <p>
 * Examples of use:
 * 
 * HTML:
 * <div wicket:id='progressbar'></div>
 * 
 * Java:
 * add(new WijProgressbar("progressbar"));
 * </p>
 * 
 * <p>
 * 	Nota Bene: this is only an override of the stylesheet. There is no new methods.
 * </p>
 *
 * @author Julien Roche
 * @since 1.0
 */
public class WijProgressbar extends ProgressBar {
	// Constants
	/** Constant of serialization */
	private static final long serialVersionUID = -8639584445746983013L;

	/**
	 * Builds a new progress bar.
	 */
	public WijProgressbar(String id) {
		super(id);
	}

	/**
	 * {@inheritDoc}
	 * @see org.odlabs.wiquery.ui.progressbar.ProgressBar#contribute(org.odlabs.wiquery.core.commons.WiQueryResourceManager)
	 */
	@Override
	public void contribute(WiQueryResourceManager wiQueryResourceManager) {
		super.contribute(wiQueryResourceManager);
		wiQueryResourceManager.addCssResource(WijProgressbarStyleSheetResourceReference.get());
	}
}
