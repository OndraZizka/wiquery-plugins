/**
 * 
 */
package com.wiquery.plugin.scrollpane;

import org.apache.wicket.ResourceReference;
import org.odlabs.wiquery.core.behavior.WiQueryAbstractBehavior;
import org.odlabs.wiquery.core.commons.WiQueryResourceManager;
import org.odlabs.wiquery.core.javascript.JsQuery;
import org.odlabs.wiquery.core.javascript.JsStatement;
import org.odlabs.wiquery.core.options.Options;
import org.odlabs.wiquery.ui.commons.WiQueryUIPlugin;

/**
 * <p>
 * 	Behavior that integrates the scrollpane plugin available at:
 * </p>
 * 
 * <p>
 * 	http://jscrollpane.kelvinluck.com/
 * </p>
 * 
 * @author Ernesto Reinaldo Barreiro
 */
@WiQueryUIPlugin
public class ScrollPaneBehavior extends WiQueryAbstractBehavior {

	private static final long serialVersionUID = 1L;

	private static ResourceReference CSS = new ResourceReference(ScrollPaneBehavior.class,"jquery.jscrollpane.css");
	
	private Options options;
	
	
	private boolean includeDefaultCss = true;
	
	/**
	 * Constructor.
	 */
	public ScrollPaneBehavior() {
		options = new Options();
	}
	
	@Override
	public void contribute(WiQueryResourceManager wiQueryResourceManager) {
		wiQueryResourceManager.addJavaScriptResource(ScrollPaneJavascriptResourceReference.get());
		if(isIncludeDefaultCss())
			wiQueryResourceManager.addCssResource(CSS);
	}
	

	/* (non-Javadoc)
	 * @see org.odlabs.wiquery.core.behavior.WiQueryAbstractBehavior#statement()
	 */
	@Override
	public JsStatement statement() {
		return new JsQuery(this.getComponent()).$().chain("jScrollPane",
				this.options.getJavaScriptOptions());
	}
	
	/**
	 * Whether arrows should be shown on the generated scroll pane. 
	 * When set to false only the scrollbar track and drag will be 
	 * shown, if set to true then arrows buttons will also be shown.
	 * Default is false;
	 * 
	 * @param showArrows
	 * @return this behavior.
	 */
	public ScrollPaneBehavior setShowArrows(boolean showArrows) {
		options.put("showArrows", showArrows);
		return this;
	}
		

	/**
	 * @return Whether arrows should be shown or not.
	 */
	public boolean isShowArrows() {
		Boolean showArrows = options.getBoolean("showArrows");		
		return showArrows!= null?showArrows.booleanValue(): false;
	}

	
	/**
	 * Whether the scrollpane should attempt to maintain it's position whenever 
	 * it is reinitialised. If true then the viewport of the scrollpane will 
	 * remain the same when it is reinitialised, if false then the viewport will jump 
	 * back up to the top when the scrollpane is reinitialised.
	 * 
	 * Default value true.
	 * 
	 * @param maintainPosition
	 * @return this behavior.
	 */
	public ScrollPaneBehavior setMaintainPosition(boolean maintainPosition) {
		options.put("maintainPosition", maintainPosition);
		return this;
	}
	
	/**
	 * 
	 * Whether jScrollPane should automatically reinitialise itself periodically 
	 * after you have initially initialised it. This can help with instances 
	 * when the size of the content of the scrollpane (or the surrounding element) 
	 * changes. However, it does involve an overhead of running a 
	 * javascript function on a timer so it is recommended only to 
	 * activate where necessary
	 * 
	 * @param autoReinitialise  boolean (default false)
	 * @return this behavior.
	 */
	public ScrollPaneBehavior setAutoReinitialise(boolean autoReinitialise) {
		options.put("autoReinitialise", autoReinitialise);
		return this;
	}
	
	/**
	 * @return Whether should be auto reinitialised or not.
	 */
	public boolean isAutoReinitialise() {
		Boolean autoReinitialise = options.getBoolean("autoReinitialise");		
		return autoReinitialise!= null?autoReinitialise.booleanValue(): false;
	}
	
	/**
	 * The number of milliseconds between each reinitialisation (if autoReinitialise is true).
	 * 
	 * @param autoReinitialiseDelay  int (default 500)
	 * @return this behavior
	 */
	public ScrollPaneBehavior setAutoReinitialiseDelay(Integer autoReinitialiseDelay) {
		if(autoReinitialiseDelay != null)
			options.put("autoReinitialiseDelay", autoReinitialiseDelay);
		else 
			options.removeOption("autoReinitialiseDelay");
		return this;
	}
	
	
	/**
	 * @return The number of milliseconds between each reinitialisation (if autoReinitialise is true).
	 */
	public int getAutoReinitialiseDelay() {
		Integer autoReinitialiseDelay = options.getInt("autoReinitialiseDelay");		
		return autoReinitialiseDelay!= null?autoReinitialiseDelay.intValue(): 500;
	}
	
	
	/**
	 * @return Whether position should maintained or not.
	 */
	public boolean isMaintainPosition() {
		Boolean maintainPosition = options.getBoolean("maintainPosition");		
		return maintainPosition!= null?maintainPosition.booleanValue(): true;
	}
	
	/**
	 * @return if default CCS should be included.
	 */
	public boolean isIncludeDefaultCss() {
		return includeDefaultCss;
	}

	/**
	 *  Allows to set whether default CCS will be included or not.
	 * @param includeDefaultCss
	 */
	public ScrollPaneBehavior setIncludeDefaultCss(boolean includeDefaultCss) {
		this.includeDefaultCss = includeDefaultCss;
		return this;
	}
	

}
