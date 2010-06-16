/**
 * 
 */
package org.wiquery.plugins.slidedeck;

import org.apache.wicket.Component;
import org.apache.wicket.model.IModel;

/**
 * 
 * @author Ernesto Reinaldo Barreiro
 * @author Melinda Dweer
 */
public interface IDeck {

	IModel<String> getDeckTitle();
	
	/**
	 * Returns the panel for this deck.
	 * 
	 * @param id
	 * @return
	 */
	Component getDeckContents(String id);
	
}
