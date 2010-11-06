/**
 * 
 */
package com.wiquery.plugins.demo;

import org.apache.wicket.Request;
import org.apache.wicket.protocol.http.WebSession;

import com.wiquery.plugins.demo.themes.RedmondTheme;
import com.wiquery.plugins.demo.themes.UITheme;

/**
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class DemoSession extends WebSession {

	private static final long serialVersionUID = 1L;

	private UITheme theme;
	
	/**
	 * @param request
	 */
	public DemoSession(Request request) {
		super(request);
		theme = RedmondTheme.getInstance();
	}

	public UITheme getTheme() {
		return theme;
	}

	public void setTheme(UITheme theme) {
		this.theme = theme;
	}
	
	/**
	 * Returns the session.
	 * @return
	 */
	public static DemoSession getSession() {
		return (DemoSession)get();
	}

}
