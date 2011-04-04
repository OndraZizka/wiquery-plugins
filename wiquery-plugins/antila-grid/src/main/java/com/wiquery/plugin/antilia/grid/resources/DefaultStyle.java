/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.wiquery.plugin.antilia.grid.resources;

import org.apache.wicket.ResourceReference;
import org.odlabs.wiquery.core.commons.WiQueryJavaScriptResourceReference;
import org.odlabs.wiquery.core.commons.WiQueryStyleSheetResourceReference;

import com.wiquery.plugin.antilia.grid.resources.images.ImgDummy;

/**
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public class DefaultStyle {

	public static ResourceReference CSS_MAIN = new WiQueryStyleSheetResourceReference(DefaultStyle.class, "main.css");
	public static ResourceReference CSS_TABLE = new WiQueryStyleSheetResourceReference(DefaultStyle.class, "table.css");
	
	public static ResourceReference CSS_MENU = new WiQueryStyleSheetResourceReference(DefaultStyle.class, "menu.css");
	
	public static WiQueryJavaScriptResourceReference JS_COMMON = new WiQueryJavaScriptResourceReference(DefaultStyle.class, "common.js");
	public static WiQueryJavaScriptResourceReference JS_TABLE = new WiQueryJavaScriptResourceReference(DefaultStyle.class, "table.js");	
	
	public static ResourceReference IMG_SEPARATOR = new ResourceReference(ImgDummy.class, "separator.gif");
		
	public static ResourceReference IMG_CHECKBOX_CHECKED = new ResourceReference(ImgDummy.class, "checkboxChecked.gif");
	public static ResourceReference IMG_CHECKBOX_UNCHECKED = new ResourceReference(ImgDummy.class, "checkboxUnchecked.gif");
	
	public static ResourceReference IMG_RADIO_CHECKED = new ResourceReference(ImgDummy.class, "radioButtonChecked.png");
	public static ResourceReference IMG_RADIO_UNCHECKED = new ResourceReference(ImgDummy.class, "radioButton.png");
	
	public static ResourceReference IMG_LAST_ENABLED_PNG  = new ResourceReference(ImgDummy.class, "last_enabled.png");
	public static ResourceReference IMG_LAST_DISABLED_PNG  = new ResourceReference(ImgDummy.class, "last_disabled.png");
	
	
}
