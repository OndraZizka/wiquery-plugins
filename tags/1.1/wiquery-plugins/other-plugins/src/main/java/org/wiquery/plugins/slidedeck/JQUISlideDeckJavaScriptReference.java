/**
 * 
 */
package org.wiquery.plugins.slidedeck;

import org.apache.wicket.markup.html.resources.JavascriptResourceReference;

/**
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 * @author Melinda Dweer
 */
public class JQUISlideDeckJavaScriptReference extends JavascriptResourceReference {

	private static final long serialVersionUID = 1L;

	private static JQUISlideDeckJavaScriptReference instance;
	
	/**
	 * 
	 */
	public JQUISlideDeckJavaScriptReference() {
		super(JQUISlideDeckJavaScriptReference.class,"jqui.slidedeck.jquery.lite.js");
		
	}

	 
	public static JQUISlideDeckJavaScriptReference get() {
		if (instance == null) {
			instance = new JQUISlideDeckJavaScriptReference();
		}
		return instance;
	}
}
