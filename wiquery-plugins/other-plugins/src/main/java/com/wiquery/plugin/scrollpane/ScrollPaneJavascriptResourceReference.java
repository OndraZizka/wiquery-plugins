package com.wiquery.plugin.scrollpane;

import org.odlabs.wiquery.core.commons.WiQueryJavaScriptResourceReference;

/**
 * $Id: $
 * <p>
 * 	References the JavaScript resource to get the widget utilities.
 * </p>
 * @author Ernesto Reinaldo Barreiro
 * @since 1.1
 */
public class ScrollPaneJavascriptResourceReference extends
		WiQueryJavaScriptResourceReference {
	private static final long serialVersionUID = 1;
	
	/**
	 * Singleton instance.
	 */
	private static ScrollPaneJavascriptResourceReference instance = new ScrollPaneJavascriptResourceReference();;

	/**
	 * Builds a new instance of {@link ScrollPaneJavascriptResourceReference}.
	 */
	private ScrollPaneJavascriptResourceReference() {
		super(ScrollPaneJavascriptResourceReference.class, "jquery.jscrollpane.min.js");
	}

	/**
	 * Returns the {@link ScrollPaneJavascriptResourceReference} instance.
	 */
	public static ScrollPaneJavascriptResourceReference get() {
		return instance;
	}
}

