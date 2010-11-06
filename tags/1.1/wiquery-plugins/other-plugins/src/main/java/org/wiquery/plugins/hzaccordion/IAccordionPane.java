/**
 * 
 */
package org.wiquery.plugins.hzaccordion;

import org.apache.wicket.Component;
import org.apache.wicket.model.IModel;

/**
 * @author Ernesto Reinaldo Barreiro
 *
 */
public interface IAccordionPane {
	
	IModel<String> getPaneTitle();
	
	/**
	 * Returns the panel for this deck.
	 * 
	 * @param id
	 * @return
	 */
	Component getPaneContents(String id);
}
