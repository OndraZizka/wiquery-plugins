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
package org.odlabs.wiquery.wijmo.wijsuperpanel;


/**
 * $Id$
 * 
 * <p>
 * 	Complex option for the vscroller option of the superpanel widget
 * </p>
 * 
 * Miss increaseButtonPosition and decreaseButtonPosition
 *
 * @author Julien Roche
 * @since 1.0
 */
public class WijSuperpanelHorizontalScroller extends AbstractWijSuperpanelScroller {
	/**
	 * 
	 * <p>
	 * 	Position of the scroller
	 * </p>
	 *
	 * @author Julien Roche
	 * @since 1.0
	 */
	public enum WijSuperpanelHorizontalScrollerPosition {
		/** The horizontal scroll bar is placed at the bottom of the content area */
		BOTTOM,
		/** The horizontal scroll bar is placed at the top of the content area */
		TOP;

		/**
		 * {@inheritDoc}
		 * @see java.lang.Enum#toString()
		 */
		@Override
		public String toString() {
			return super.toString().toLowerCase();
		}
	}
	
	// Constants
	/** Constant of serialization */
	private static final long serialVersionUID = 4112947399050381065L;
	
	/**
	 * @return the scrollBarPosition option
	 */
	public WijSuperpanelHorizontalScrollerPosition getScrollBarPosition() {
		String str = getOptions().getLiteral("scrollBarPosition");
		return str == null ? WijSuperpanelHorizontalScrollerPosition.BOTTOM : WijSuperpanelHorizontalScrollerPosition.valueOf(str.toUpperCase());
	}
	
	/**
	 * A value determines the position of the horizontal scroll bar. 
	 * @param scrollBarPosition
	 * @return the current instance
	 */
	public WijSuperpanelHorizontalScroller setScrollBarPosition(WijSuperpanelHorizontalScrollerPosition scrollBarPosition) {
		getOptions().putLiteral("scrollBarPosition", scrollBarPosition.toString());
		return this;
	}
}
