/**
 * 
 */
package org.wiquery.plugins.slidedeck;

import org.apache.wicket.markup.html.resources.JavascriptResourceReference;

/**
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 * @author Melinda Dweer
 */
public class SlideDeckJavaScriptReference extends JavascriptResourceReference {

	private static final long serialVersionUID = 1L;

	private static SlideDeckJavaScriptReference instance;
	
	/**
	 * 
	 */
	public SlideDeckJavaScriptReference() {
		super(SlideDeckJavaScriptReference.class,"slidedeck.jquery.lite.js");
		
	}

	 
	public static SlideDeckJavaScriptReference get() {
		if (instance == null) {
			instance = new SlideDeckJavaScriptReference();
		}
		return instance;
	}
}
