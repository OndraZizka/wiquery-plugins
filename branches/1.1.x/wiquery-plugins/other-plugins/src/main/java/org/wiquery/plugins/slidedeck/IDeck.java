/**
 * 
 */
package org.wiquery.plugins.slidedeck;

import java.io.Serializable;

import org.apache.wicket.Component;
import org.apache.wicket.model.IModel;

/**
 * 
 * @author Ernesto Reinaldo Barreiro
 * @author Melinda Dweer
 */
public interface IDeck extends Serializable {

	IModel<String> getDeckTitle();
	
	/**
	 * Returns the panel for this deck.
	 * 
	 * @param id
	 * @return
	 */
	Component getDeckContents(String id);
	
}
