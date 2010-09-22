/**
 * 
 */
package org.wiquery.plugins.hzaccordion;

import org.apache.wicket.markup.html.resources.JavascriptResourceReference;

/**
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class HorizontaAccordionJavaScriptReference extends JavascriptResourceReference {

	private static final long serialVersionUID = 1L;

	private static HorizontaAccordionJavaScriptReference instance;
	
	/**
	 * 
	 */
	public HorizontaAccordionJavaScriptReference() {
		super(HorizontaAccordionJavaScriptReference.class,"jquery.hrzAccordion.min.js");
		
	}

	 
	public static HorizontaAccordionJavaScriptReference get() {
		if (instance == null) {
			instance = new HorizontaAccordionJavaScriptReference();
		}
		return instance;
	}
}
