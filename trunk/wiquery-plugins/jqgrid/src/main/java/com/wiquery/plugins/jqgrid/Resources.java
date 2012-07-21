package com.wiquery.plugins.jqgrid;

import org.apache.wicket.ResourceReference;
import org.apache.wicket.markup.html.resources.JavascriptResourceReference;

/**
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class Resources {
	

	public static final ResourceReference CSS_JQUERY_JQGRID = new ResourceReference(Resources.class, "css/ui.jqgrid.css");
	public static final JavascriptResourceReference JS_JQUERY_LOC_EN = new JavascriptResourceReference(Resources.class, "js/i18n/grid.locale-en.js");	
	public static final JavascriptResourceReference JS_JQUERY_JQGRID = new JavascriptResourceReference(Resources.class, "js/jquery.jqGrid.min.js");	
	public static final JavascriptResourceReference JS_JQUERY_CONTEXT_MENU = new JavascriptResourceReference(Resources.class, "plugins/jquery.contextmenu.js");
	public static final ResourceReference JS_MULTISELECT = new ResourceReference(Resources.class, "plugins/ui.multiselect.js");
}
