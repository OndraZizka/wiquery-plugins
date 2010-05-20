/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.wiquery.plugins.antilia.grid.resources;

import org.apache.wicket.ResourceReference;
import org.apache.wicket.markup.html.resources.JavascriptResourceReference;

import com.wiquery.plugins.antilia.grid.resources.images.ImgDummy;

/**
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public class DefaultStyle {

	public static ResourceReference CSS_MAIN = new ResourceReference(DefaultStyle.class, "main.css");
	public static ResourceReference CSS_TABLE = new ResourceReference(DefaultStyle.class, "table.css");
	public static JavascriptResourceReference JS_COMMON = new JavascriptResourceReference(DefaultStyle.class, "common.js");
	public static JavascriptResourceReference JS_TABLE = new JavascriptResourceReference(DefaultStyle.class, "table.js");	
	public static ResourceReference CC_menu = new ResourceReference(DefaultStyle.class, "menu.css");	
	
	public static ResourceReference IMG_NEXT_ENABLED  = new ResourceReference(ImgDummy.class, "next_enabled.gif");
	public static ResourceReference IMG_NEXT_ENABLED_PNG  = new ResourceReference(ImgDummy.class, "next_enabled.png");
	
	public static ResourceReference IMG_PREVIOUS_ENABLED = new ResourceReference(ImgDummy.class, "previous_enabled.gif");	
	public static ResourceReference IMG_PREVIOUS_ENABLED_PNG = new ResourceReference(ImgDummy.class, "previous_enabled.png");
	
	public static ResourceReference IMG_LAST_ENABLED  = new ResourceReference(ImgDummy.class, "last_enabled.gif");
	public static ResourceReference IMG_LAST_ENABLED_PNG  = new ResourceReference(ImgDummy.class, "last_enabled.png");
	
	public static ResourceReference IMG_FIRST_ENABLED  = new ResourceReference(ImgDummy.class, "first_enabled.gif");
	public static ResourceReference IMG_FIRST_ENABLED_PNG  = new ResourceReference(ImgDummy.class, "first_enabled.png");
	
	public static ResourceReference IMG_NEXT_DISABLED = new ResourceReference(ImgDummy.class, "next_disabled.gif");
	public static ResourceReference IMG_NEXT_DISABLED_PNG = new ResourceReference(ImgDummy.class, "next_disabled.png");
	
	public static ResourceReference IMG_PREVIOUS_DISABLED = new ResourceReference(ImgDummy.class, "previous_disabled.gif");
	public static ResourceReference IMG_PREVIOUS_DISABLED_PNG = new ResourceReference(ImgDummy.class, "previous_disabled.png");
	
	public static ResourceReference IMG_LAST_DISABLED  = new ResourceReference(ImgDummy.class, "last_disabled.gif");
	public static ResourceReference IMG_LAST_DISABLED_PNG  = new ResourceReference(ImgDummy.class, "last_disabled.png");
	
	public static ResourceReference IMG_FIRST_DISABLED  = new ResourceReference(ImgDummy.class, "first_disabled.gif");
	public static ResourceReference IMG_FIRST_DISABLED_PNG  = new ResourceReference(ImgDummy.class, "first_disabled.png");
	
	public static ResourceReference IMG_SEPARATOR = new ResourceReference(ImgDummy.class, "separator.gif");
	
	public static ResourceReference IMG_REFRESH = new ResourceReference(ImgDummy.class, "refresh_small.gif");
	public static ResourceReference IMG_REFRESH_PNG = new ResourceReference(ImgDummy.class, "refresh_small.png");
}
