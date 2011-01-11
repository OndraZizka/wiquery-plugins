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

import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.odlabs.wiquery.core.commons.IWiQueryPlugin;
import org.odlabs.wiquery.core.commons.WiQueryResourceManager;
import org.odlabs.wiquery.core.javascript.JsQuery;
import org.odlabs.wiquery.core.javascript.JsStatement;
import org.odlabs.wiquery.core.options.Options;
import org.odlabs.wiquery.glob.GlobalizationJavascriptResourceReference;
import org.odlabs.wiquery.glob.JGlobJavascriptResourceReference;
import org.odlabs.wiquery.glob.JGlobTranslationJavascriptResourceReference;
import org.odlabs.wiquery.ui.commons.WiQueryUIPlugin;
import org.odlabs.wiquery.ui.datepicker.DateOption;
import org.odlabs.wiquery.ui.effects.EffectsHelper;
import org.odlabs.wiquery.ui.widget.WidgetJavascriptResourceReference;
import org.odlabs.wiquery.wijmo.wijcheckboxdecorator.WijCheckboxDecoratorStyleSheetResourceReference;
import org.odlabs.wiquery.wijmo.wijpopup.WijPopupJavascriptResourceReference;

/**
 * $Id$
 * 
 * <p>
 * Binding of the WijCalendar widget
 * </p>
 * 
 * <p>
 * Examples of use:
 * 
 * HTML:
 * <div id="calendar"></div>
 * 
 * Java:
 * add(new WijCalendar("calendar"));
 * 
 * </p>
 * 
 * @author Julien Roche
 * @since 1.0
 */
@WiQueryUIPlugin
public class WijCalendar extends WebMarkupContainer implements IWiQueryPlugin {
	/**
	 * 
	 * <p>
	 * 	Enumeration for the calendarWeekRule option
	 * </p>
	 * 
	 * @author Julien Roche
	 * @since 1.0
	 *
	 */
	public enum WijCalendarCalendarWeekRule {
		firstDay,
		firstFourDay,
		firstFullDay;
	}
	
	/**
	 * 
	 * <p>
	 * 	Enumeration for the orientation option
	 * </p>
	 *
	 * @author Julien Roche
	 * @since 1.0
	 */
	public enum WijCalendarDirection {
		HORIZONTAL,
		VERTICAL;

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
	 * 	Enumeration for the navButtons option
	 * </p>
	 *
	 * @author Julien Roche
	 * @since 1.0
	 */
	public enum WijCalendarNavButtons {
		DEFAULT,
		NONE,
		QUICK;

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
	 * 	Enumeration for the weekDayFormat option
	 * </p>
	 * 
	 * @author Julien Roche
	 * @since 1.0
	 *
	 */
	public enum WijCalendarWeekDayFormat {
		ABBREVIATED,
		FIRSTLETTER,
		FULL,
		SHORT;

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
	private static final long serialVersionUID = 581231516253888311L;
	
	// Properties
	private Options options;

	/**
	 * @see Component#Component(String)
	 */
	public WijCalendar(String id) {
		super(id);
		this.options = new Options();
	}

	/**
	 * {@inheritDoc}
	 * @see org.odlabs.wiquery.core.commons.IWiQueryPlugin#contribute(org.odlabs.wiquery.core.commons.WiQueryResourceManager)
	 */
	public void contribute(WiQueryResourceManager wiQueryResourceManager) {
		wiQueryResourceManager.addJavaScriptResource(WidgetJavascriptResourceReference.get());
		wiQueryResourceManager.addJavaScriptResource(WijPopupJavascriptResourceReference.get());
		
		EffectsHelper.blind(wiQueryResourceManager);
		EffectsHelper.slide(wiQueryResourceManager);
		EffectsHelper.scale(wiQueryResourceManager);
		
		wiQueryResourceManager.addJavaScriptResource(GlobalizationJavascriptResourceReference.get());
		wiQueryResourceManager.addJavaScriptResource(JGlobTranslationJavascriptResourceReference.get());
		wiQueryResourceManager.addJavaScriptResource(JGlobJavascriptResourceReference.get());
		
		wiQueryResourceManager.addJavaScriptResource(WijCalendarJavascriptResourceReference.get());
		wiQueryResourceManager.addCssResource(WijCheckboxDecoratorStyleSheetResourceReference.get());
	}
	
	/**
	 * @return the calendarWeekRule option
	 */
	public WijCalendarCalendarWeekRule getCalendarWeekRule() {
		String str = getOptions().getLiteral("calendarWeekRule");
		return str == null ? WijCalendarCalendarWeekRule.firstDay : WijCalendarCalendarWeekRule.valueOf(str.toUpperCase());
	}
	
	/**
	 * @return the culture option
	 */
	public String getCulture() {
		String str = getOptions().getLiteral("culture");
		return str == null ? "" : str;
	}
	
	/**
	 * @return the dayCols option
	 */
	public int getDayCols() {
		Integer integer = getOptions().getInt("dayCols");
		return integer == null ? 7 : integer;
	}
	
	/**
	 * @return the dayRows option
	 */
	public int getDayRows() {
		Integer integer = getOptions().getInt("dayRows");
		return integer == null ? 6 : integer;
	}
	
	/**
	 * @return the direction option
	 */
	public WijCalendarDirection getDirection() {
		String str = getOptions().getLiteral("direction");
		return str == null ? WijCalendarDirection.HORIZONTAL : WijCalendarDirection.valueOf(str.toUpperCase());
	}
	
	/**
	 * @return the displayDate option
	 */
	public Date getDisplayDate() {
		Object object = getOptions().getComplexOption("displayDate");
		return object instanceof DateOption ? ((DateOption) object).getDateParam() : null ;
	}
	
	/**
	 * @return the duration option
	 */
	public int getDuration() {
		Integer integer = getOptions().getInt("duration");
		return integer == null ? 250 : integer;
	}
	
	/**
	 * @return the easing option
	 */
	public String getEasing() {
		String str = getOptions().getLiteral("easing");
		return str == null ? "easeInQuad" : str;
	}
	
	/**
	 * @return the maxDate option
	 */
	public Date getMaxDate() {
		Object object = getOptions().getComplexOption("maxDate");
		return object instanceof DateOption ? ((DateOption) object).getDateParam() : new GregorianCalendar(2099, 11, 31).getTime() ;
	}
	
	/**
	 * @return the minDate option
	 */
	public Date getMinDate() {
		Object object = getOptions().getComplexOption("minDate");
		return object instanceof DateOption ? ((DateOption) object).getDateParam() : new GregorianCalendar(1900, 0, 1).getTime() ;
	}
	
	/**
	 * @return the monthCols option
	 */
	public int getMonthCols() {
		Integer integer = getOptions().getInt("monthCols");
		return integer == null ? 1 : integer;
	}
	
	/**
	 * @return the monthRows option
	 */
	public int getMonthRows() {
		Integer integer = getOptions().getInt("monthRows");
		return integer == null ? 1 : integer;
	}
	
	/**
	 * @return the navButtons option
	 */
	public WijCalendarNavButtons getNavButtons() {
		String str = getOptions().getLiteral("navButtons");
		return str == null ? WijCalendarNavButtons.DEFAULT : WijCalendarNavButtons.valueOf(str.toUpperCase());
	}
	
	/**
	 * @return the nextTooltip option
	 */
	public String getNextTooltip() {
		String str = getOptions().getLiteral("nextTooltip");
		return str == null ? "Next" : str;
	}
	
	/**
	 * @return the options of the expander
	 */
	protected Options getOptions() {
		return this.options;
	}
	
	/**
	 * @return the prevTooltip option
	 */
	public String getPrevTooltip() {
		String str = getOptions().getLiteral("prevTooltip");
		return str == null ? "Previous" : str;
	}
	
	/**
	 * @return the quickNavStep option
	 */
	public int getQuickNavStep() {
		Integer integer = getOptions().getInt("quickNavStep");
		return integer == null ? 12 : integer;
	}
	
	/**
	 * @return the quickNextTooltip option
	 */
	public String getQuickNextTooltip() {
		String str = getOptions().getLiteral("quickNextTooltip");
		return str == null ? "Quick Next" : str;
	}
	
	/**
	 * @return the nextTooltip option
	 */
	public String getQuickPrevTooltip() {
		String str = getOptions().getLiteral("quickPrevTooltip");
		return str == null ? "Quick Previous" : str;
	}
	
	/**
	 * @return the selectionMode option
	 */
	public WijmoSelectionMode getSelectionMode() {
		Object obj = getOptions().getComplexOption("selectionMode");
		return obj instanceof WijmoSelectionMode ? (WijmoSelectionMode) obj : new WijmoSelectionMode(true, true);
	}
	
	/**
	 * @return the titleFormat option
	 */
	public String getTitleFormat() {
		String str = getOptions().getLiteral("titleFormat");
		return str == null ? "MMMM yyyy" : str;
	}
	
	/**
	 * @return the toolTipFormat option
	 */
	public String getToolTipFormat() {
		String str = getOptions().getLiteral("toolTipFormat");
		return str == null ? "dddd, MMMM dd, yyyy" : str;
	}
	
	/**
	 * @return the weekDayFormat option
	 */
	public WijCalendarWeekDayFormat getWeekDayFormat() {
		String str = getOptions().getLiteral("weekDayFormat");
		return str == null ? WijCalendarWeekDayFormat.SHORT : WijCalendarWeekDayFormat.valueOf(str.toUpperCase());
	}
	
	/**
	 * @return the allowPreview option
	 */
	public boolean isAllowPreview() {
		Boolean bool = getOptions().getBoolean("allowPreview");
		return bool == null ? false : bool;
	}
	
	/**
	 * @return the allowQuickPick option
	 */
	public boolean isAllowQuickPick() {
		Boolean bool = getOptions().getBoolean("allowQuickPick");
		return bool == null ? true : bool;
	}
	
	/**
	 * @return the autoHide option
	 */
	public boolean isAutoHide() {
		Boolean bool = getOptions().getBoolean("autoHide");
		return bool == null ? true : bool;
	}
	
	/**
	 * @return the popupMode option
	 */
	public boolean isPopuMode() {
		Boolean bool = getOptions().getBoolean("popupMode");
		return bool == null ? false : bool;
	}
	
	/**
	 * @return the showDayPadding option
	 */
	public boolean isShowDayPadding() {
		Boolean bool = getOptions().getBoolean("showDayPadding");
		return bool == null ? false : bool;
	}
	
	/**
	 * @return the showOtherMonthDays option
	 */
	public boolean isShowOtherMonthDays() {
		Boolean bool = getOptions().getBoolean("showOtherMonthDays");
		return bool == null ? true : bool;
	}
	
	/**
	 * @return the showTitle option
	 */
	public boolean isShowTitle() {
		Boolean bool = getOptions().getBoolean("showTitle");
		return bool == null ? true : bool;
	}
	
	/**
	 * @return the showWeekDays option
	 */
	public boolean isShowWeekDays() {
		Boolean bool = getOptions().getBoolean("showWeekDays");
		return bool == null ? true : bool;
	}
	
	/**
	 * @return the showWeekNumbers option
	 */
	public boolean isShowWeekNumbers() {
		Boolean bool = getOptions().getBoolean("showWeekNumbers");
		return bool == null ? false : bool;
	}
	
	/**
	 * Determines whether you can change the view to month/year/decade after clicking on the calendar title.
	 * @param allowPreview
	 * @return the current instance
	 */
	public WijCalendar setAllowPreview(boolean allowPreview) {
		getOptions().put("allowPreview", allowPreview);
		return this;
	}
	
	/**
	 * Determines whether users can change the view to month/year/decade while clicking on the calendar title.
	 * @param allowQuickPick
	 * @return the current instance
	 */
	public WijCalendar setAllowQuickPick(boolean allowQuickPick) {
		getOptions().put("allowQuickPick", allowQuickPick);
		return this;
	}
	
	/**
	 * A Boolean property that determines whether to autohide the calendar in pop-up mode when clicking outside of the calendar.
	 * @param autoHide
	 * @return the current instance
	 */
	public WijCalendar setAutoHide(boolean autoHide) {
		getOptions().put("autoHide", autoHide);
		return this;
	}
	
	/**
	 * Defines different rules for determining the first week of the year. 
	 * @param calendarWeekRule
	 * @return the current instance
	 */
	public WijCalendar setCalendarWeekRule(WijCalendarCalendarWeekRule calendarWeekRule) {
		getOptions().putLiteral("calendarWeekRule", calendarWeekRule.toString());
		return this;
	}
	
	/**
	 * Set the culture ID
	 * @param culture
	 * @return the current instance
	 */
	public WijCalendar setCulture(String culture) {
		getOptions().putLiteral("culture", culture);
		return this;
	}
	
	/**
	 * Set the number of day cols.
	 * @param dayCols
	 * @return the current instance
	 */
	public WijCalendar setDayCols(int dayCols) {
		getOptions().put("dayCols", dayCols);
		return this;
	}
	
	/**
	 * Set the number of day rows.
	 * @param dayRows
	 * @return the current instance
	 */
	public WijCalendar setDayRows(int dayRows) {
		getOptions().put("dayRows", dayRows);
		return this;
	}
	
	/**
	 * Determines the month slide direction. 
	 * @param direction
	 * @return the current instance
	 */
	public WijCalendar setDirection(WijCalendarDirection direction) {
		getOptions().putLiteral("direction", direction.toString());
		return this;
	}
	
	/**
	 * Set the culture ID
	 * @param displayDate
	 * @return the current instance
	 */
	public WijCalendar setDisplayDate(Date displayDate) {
		getOptions().put("displayDate", new DateOption(displayDate));
		return this;
	}
	
	/**
	 * Set the animation duration in milliseconds.
	 * @param duration
	 * @return the current instance
	 */
	public WijCalendar setDuration(int duration) {
		getOptions().put("duration", duration);
		return this;
	}
	
	/**
	 * Determines the animations easing effect.
	 * @param easing
	 * @return the current instance
	 */
	public WijCalendar setEasing(String easing) {
		getOptions().putLiteral("easing", easing);
		return this;
	}
	
	/**
	 * Set the maximum date to display
	 * @param maxDate
	 * @return the current instance
	 */
	public WijCalendar setMaxDate(Date maxDate) {
		getOptions().put("maxDate", new DateOption(maxDate));
		return this;
	}
	
	/**
	 * Set the minimum date to display
	 * @param minDate
	 * @return the current instance
	 */
	public WijCalendar setMinDate(Date minDate) {
		getOptions().put("minDate", new DateOption(minDate));
		return this;
	}
	
	/**
	 * Set the number of calendar months to display in the widget.
	 * @param monthCols
	 * @return the current instance
	 */
	public WijCalendar setMonthCols(int monthCols) {
		getOptions().put("monthCols", monthCols);
		return this;
	}
	
	/**
	 * Set the number of calendar months to display in the widget.
	 * @param monthRows
	 * @return the current instance
	 */
	public WijCalendar setMonthRows(int monthRows) {
		getOptions().put("monthRows", monthRows);
		return this;
	}
	
	/**
	 * Defines different rules for determining the first week of the year. 
	 * @param navButtons
	 * @return the current instance
	 */
	public WijCalendar setNavButtons(WijCalendarNavButtons navButtons) {
		getOptions().putLiteral("navButtons", navButtons.toString());
		return this;
	}
	
	/**
	 * Set the text for the 'next' button's ToolTip
	 * @param nextTooltip
	 * @return the current instance
	 */
	public WijCalendar setNextTooltip(String nextTooltip) {
		getOptions().putLiteral("nextTooltip", nextTooltip);
		return this;
	}
	
	/**
	 * A Boolean property that determines whether the c1calendarwijcalendar widget is a pop-up calendar.
	 * @param popupMode
	 * @return the current instance
	 */
	public WijCalendar setPopupMode(boolean popupMode) {
		getOptions().put("popupMode", popupMode);
		return this;
	}
	
	/**
	 * Set the text for the 'previous' button's ToolTip
	 * @param prevTooltip
	 * @return the current instance
	 */
	public WijCalendar setPrevTooltip(String prevTooltip) {
		getOptions().putLiteral("prevTooltip", prevTooltip);
		return this;
	}
	
	/**
	 * Detemines the inc/dec steps when clicking the quick navigation button.
	 * @param quickNavStep
	 * @return the current instance
	 */
	public WijCalendar setQuickNavStep(int quickNavStep) {
		getOptions().put("quickNavStep", quickNavStep);
		return this;
	}
	
	/**
	 * Set the text for the quick 'next' button's ToolTip
	 * @param quickNextTooltip
	 * @return the current instance
	 */
	public WijCalendar setQuickNextTooltip(String quickNextTooltip) {
		getOptions().putLiteral("quickNextTooltip", quickNextTooltip);
		return this;
	}
	
	/**
	 * Set the text for the quick 'prev' button's ToolTip
	 * @param quickPrevTooltip
	 * @return the current instance
	 */
	public WijCalendar setQuickPrevTooltip(String quickPrevTooltip) {
		getOptions().putLiteral("quickPrevTooltip", quickPrevTooltip);
		return this;
	}
	
	/**
	 * Sets the date selection mode on the calendar control that specifies whether 
	 * the user can select a single day, a week, or an entire month. 
	 * @param selectionMode
	 * @return the current instance
	 */
	public WijCalendar setSelectionMode(WijmoSelectionMode selectionMode) {
		getOptions().put("selectionMode", selectionMode);
		return this;
	}
	
	/**
	 * Determines whether to add zeroes to days with only one digit 
	 * (for example, "1" would become "01" if this property were set to "true").
	 * @param showDayPadding
	 * @return the current instance
	 */
	public WijCalendar setShowDayPadding(boolean showDayPadding) {
		getOptions().put("showDayPadding", showDayPadding);
		return this;
	}
	
	/**
	 * Determines whether to display the days of the next and/or previous month.
	 * @param showOtherMonthDays
	 * @return the current instance
	 */
	public WijCalendar setShowOtherMonthDays(boolean showOtherMonthDays) {
		getOptions().put("showOtherMonthDays", showOtherMonthDays);
		return this;
	}
	
	/**
	 * A Boolean property that determines whether to display calendar title.
	 * @param showTitle
	 * @return the current instance
	 */
	public WijCalendar setShowTitle(boolean showTitle) {
		getOptions().put("showTitle", showTitle);
		return this;
	}
	
	/**
	 * A Boolean property that determines whether to display week days.
	 * @param showWeekDays
	 * @return the current instance
	 */
	public WijCalendar setShowWeekDays(boolean showWeekDays) {
		getOptions().put("showTitshowWeekDaysle", showWeekDays);
		return this;
	}
	
	/**
	 * Determines whether to display week numbers. 
	 * @param showWeekNumbers
	 * @return the current instance
	 */
	public WijCalendar setShowWeekNumbers(boolean showWeekNumbers) {
		getOptions().put("showWeekNumbers", showWeekNumbers);
		return this;
	}
	
	/**
	 * Set the culture ID
	 * @param titleFormat
	 * @return the current instance
	 */
	public WijCalendar setTitleFormat(String titleFormat) {
		getOptions().putLiteral("titleFormat", titleFormat);
		return this;
	}

	/**
	 * Set the format for the ToolTip.
	 * @param toolTipFormat
	 * @return the current instance
	 */
	public WijCalendar setToolTipFormat(String toolTipFormat) {
		getOptions().putLiteral("toolTipFormat", toolTipFormat);
		return this;
	}
	
	/**
	 * Set the culture ID
	 * @param weekDayFormat
	 * @return the current instance
	 */
	public WijCalendar setWeekDayFormat(WijCalendarWeekDayFormat weekDayFormat) {
		getOptions().putLiteral("weekDayFormat", weekDayFormat.toString());
		return this;
	}
	
	/**
	 * {@inheritDoc}
	 * @see org.odlabs.wiquery.core.commons.IWiQueryPlugin#statement()
	 */
	public JsStatement statement() {
		return new JsQuery(this).$().chain("wijcalendar", getOptions().getJavaScriptOptions());
	}
}
