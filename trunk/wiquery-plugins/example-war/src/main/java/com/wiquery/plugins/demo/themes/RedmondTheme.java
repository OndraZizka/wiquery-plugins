package com.wiquery.plugins.demo.themes;

import org.apache.wicket.ResourceReference;

/**
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class RedmondTheme extends UITheme {

	private static final long serialVersionUID = 1L;
	
	public static final ResourceReference THEME = new ResourceReference(Themes.class, "redmond/jquery-ui-1.7.1.custom.css");
	
	private static RedmondTheme instance;
	
	
	/**
	 * @param name
	 */
	private RedmondTheme() {
		super("Redmond");
	}

	/* (non-Javadoc)
	 * @see es.liberty.fwkdemo.web.jquery.themes.UITheme#getTheme()
	 */
	@Override
	public ResourceReference getTheme() {
		return THEME;
	}

	public static RedmondTheme getInstance() {
		if(instance==null) {
			instance = new RedmondTheme();
		}
		return instance;
	}

}
