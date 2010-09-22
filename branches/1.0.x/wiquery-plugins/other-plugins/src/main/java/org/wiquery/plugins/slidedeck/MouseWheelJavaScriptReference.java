/**
 * 
 */
package org.wiquery.plugins.slidedeck;

import org.apache.wicket.markup.html.resources.JavascriptResourceReference;

/**
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 * @author Melinda Dweer
 */
public class MouseWheelJavaScriptReference extends JavascriptResourceReference {

	private static final long serialVersionUID = 1L;

	private static MouseWheelJavaScriptReference instance;
	
	/**
	 * 
	 */
	public MouseWheelJavaScriptReference() {
		super(MouseWheelJavaScriptReference.class,"jquery.mousewheel.min.js");
		
	}

	 
	public static MouseWheelJavaScriptReference get() {
		if (instance == null) {
			instance = new MouseWheelJavaScriptReference();
		}
		return instance;
	}
}
