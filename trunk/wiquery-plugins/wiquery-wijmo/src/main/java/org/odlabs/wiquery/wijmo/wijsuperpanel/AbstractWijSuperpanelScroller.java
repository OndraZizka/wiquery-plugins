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

import org.odlabs.wiquery.core.options.IComplexOption;
import org.odlabs.wiquery.core.options.Options;

/**
 * $Id$
 * 
 * <p>
 * 	Complex option for the hscroller and vscroller option of the superpanel widget
 * </p>
 * 
 * Miss increaseButtonPosition and decreaseButtonPosition
 *
 * @author Julien Roche
 * @since 1.0
 */
public abstract class AbstractWijSuperpanelScroller implements IComplexOption {
	/**
	 * 
	 * <p>
	 * 	Mode of the scroller
	 * </p>
	 *
	 * @author Julien Roche
	 * @since 1.0
	 */
	public enum WijSuperpanelHorizontalScrollerMode {
		/** Scroll buttons are used for scrolling. Scrolling occurs only when scroll buttons are clicked. */
		BUTTONS,
		/** Scroll buttons are used for scrolling. Scrolling occurs only when scroll buttons are hovered. */
		BUTTONSHOVER,
		/** Scrolling occurs when the mouse is moving to the edge of the content area. */
		EDGE,
		/** Scroll bars are used for scrolling */
		SCROLLBAR;

		/**
		 * {@inheritDoc}
		 * @see java.lang.Enum#toString()
		 */
		@Override
		public String toString() {
			return super.toString().toLowerCase();
		}
	}
	
	/**
	 * 
	 * <p>
	 * 	Visibility of the scroller
	 * </p>
	 *
	 * @author Julien Roche
	 * @since 1.0
	 */
	public enum WijSuperpanelHorizontalScrollerVisibility {
		/** Shows the scroll when needed */
		AUTO,
		/** Scroll bar will be hidden. */
		HIDDEN,
		/** Scroll bar will always be visible. It"s disabled when not needed. */
		VISIBILITY;

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
	
	// Properties
	private Options options;

	/**
	 * Default constructor
	 */
	public AbstractWijSuperpanelScroller() {
		this.options = new Options();
	}
	
	/**
	 * @return the firstStepChangeFix option
	 */
	public int getFirstStepChangeFix() {
		Integer integer =  getOptions().getInt("firstStepChangeFix");
		return integer == null ? 0 : integer;
	}
	
	/**
	 * @return the hoverEdgeSpan option
	 */
	public int getHoverEdgeSpan() {
		Integer integer =  getOptions().getInt("hoverEdgeSpan");
		return integer == null ? 20 : integer;
	}
	
	/**
	 * {@inheritDoc}
	 * @see org.odlabs.wiquery.core.options.IComplexOption#getJavascriptOption()
	 */
	public CharSequence getJavascriptOption() {
		return this.options.getJavaScriptOptions();
	}
	
	/**
	 * @return the options of the expander
	 */
	protected Options getOptions() {
		return this.options;
	}
	
	/**
	 * @return the scrollBarVisibility option
	 */
	public WijSuperpanelHorizontalScrollerVisibility getScrollBarVisibility() {
		String str = getOptions().getLiteral("scrollBarVisibility");
		return str == null ? WijSuperpanelHorizontalScrollerVisibility.AUTO : WijSuperpanelHorizontalScrollerVisibility.valueOf(str.toUpperCase());
	}
	
	/**
	 * @return the scrollLargeChange option
	 */
	public Integer getScrollLargeChange() {
		return getOptions().getInt("scrollLargeChange");
	}
	
	/**
	 * @return the scrollMax option
	 */
	public int getScrollMax() {
		Integer integer =  getOptions().getInt("scrollMax");
		return integer == null ? 100 : integer;
	}
	
	/**
	 * @return the scrollMin option
	 */
	public int getScrollMin() {
		Integer integer =  getOptions().getInt("scrollMin");
		return integer == null ? 0 : integer;
	}
	
	/**
	 * @return the scrollMinDragLength option
	 */
	public int getScrollMinDragLength() {
		Integer integer =  getOptions().getInt("scrollMinDragLength");
		return integer == null ? 6 : integer;
	}
	
	/**
	 * @return the scrollMode option
	 */
	public WijSuperpanelHorizontalScrollerMode getScrollMode() {
		String str = getOptions().getLiteral("scrollMode");
		return str == null ? WijSuperpanelHorizontalScrollerMode.SCROLLBAR : WijSuperpanelHorizontalScrollerMode.valueOf(str.toUpperCase());
	}
	
	/**
	 * @return the scrollSmallChange option
	 */
	public Integer getScrollSmallChange() {
		return getOptions().getInt("scrollSmallChange");
	}
	
	/**
	 * @return the scrollValue option
	 */
	public Integer getScrollValue() {
		return getOptions().getInt("scrollValue");
	}
	
	/**
	 *The number specifies the value to add to smallchange or largechange when scrolling the first step(scrolling from scrollMin).
	 * @param firstStepChangeFix
	 * @return the current instance
	 */
	public AbstractWijSuperpanelScroller setFirstStepChangeFix(int firstStepChangeFix) {
		getOptions().put("firstStepChangeFix", firstStepChangeFix);
		return this;
	}
	
	/**
	 * A value sets the width of horizontal hovering edge which will trigger the horizontal scrolling.
	 * @param hoverEdgeSpan
	 * @return the current instance
	 */
	public AbstractWijSuperpanelScroller setHoverEdgeSpan(int hoverEdgeSpan) {
		getOptions().put("hoverEdgeSpan", hoverEdgeSpan);
		return this;
	}
	
	/**
	 * A value determines the visibility of the horizontal scroll bar
	 * @param scrollBarVisibility
	 * @return the current instance
	 */
	public AbstractWijSuperpanelScroller setScrollBarVisibility(WijSuperpanelHorizontalScrollerVisibility scrollBarVisibility) {
		getOptions().putLiteral("scrollBarVisibility", scrollBarVisibility.toString());
		return this;
	}
	
	/**
	 * Wijsuperpanel will scroll a large change when a user clicks on the tracks 
	 * of scroll bars or presses left or right arrow keys on the keyboard with the shift key down.
	 * When scrollLargeChange is null, wijsuperpanel will scroll the width of content.
	 * @param scrollValue
	 * @return the current instance
	 */
	public AbstractWijSuperpanelScroller setScrollLargeChange(int scrollLargeChange) {
		getOptions().put("scrollLargeChange", scrollLargeChange);
		return this;
	}
	
	/**
	 *  A value sets the minimum value of horizontal scroller.
	 * @param scrollMin
	 * @return the current instance
	 */
	public AbstractWijSuperpanelScroller setScrollMain(int scrollMin) {
		getOptions().put("scrollMin", scrollMin);
		return this;
	}
	
	/**
	 *  A value sets the maximum value of horizontal scroller.
	 * @param scrollMax
	 * @return the current instance
	 */
	public AbstractWijSuperpanelScroller setScrollMax(int scrollMax) {
		getOptions().put("scrollMax", scrollMax);
		return this;
	}
	
	/**
	 *  A value sets the minimum value of horizontal scroller.
	 * @param scrollMinDragLength
	 * @return the current instance
	 */
	public AbstractWijSuperpanelScroller setScrollMinDragLength(int scrollMinDragLength) {
		getOptions().put("scrollMinDragLength", scrollMinDragLength);
		return this;
	}
	
	/**
	 * A value determines the scroll mode of horizontal scrolling.
	 * @param scrollMode
	 * @return the current instance
	 */
	public AbstractWijSuperpanelScroller setScrollMode(WijSuperpanelHorizontalScrollerMode scrollMode) {
		getOptions().putLiteral("scrollMode", scrollMode.toString());
		return this;
	}
	/**
	 * Wijsuperpanel will scroll a small change when a user clicks on the arrows 
	 * of scroll bars, clicks or hovers scroll buttons, presses left or right 
	 * arrow keys on keyboard, and hovers on the edge of wijsuperpanel.
	 * When scrollSmallChange is null, wijsuperpanel will scroll half of the width of content.
	 * @param scrollSmallChange
	 * @return the current instance
	 */
	public AbstractWijSuperpanelScroller setScrollSmallChange(int scrollSmallChange) {
		getOptions().put("scrollSmallChange", scrollSmallChange);
		return this;
	}
	
	/**
	 * A value determines the horizontal scrolling position of wijsuperpanel.
	 * @param scrollValue
	 * @return the current instance
	 */
	public AbstractWijSuperpanelScroller setScrollValue(int scrollValue) {
		getOptions().put("scrollValue", scrollValue);
		return this;
	}
}
