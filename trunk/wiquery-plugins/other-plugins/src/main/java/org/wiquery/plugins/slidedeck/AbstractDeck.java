/**
 * 
 */
package org.wiquery.plugins.slidedeck;

import org.apache.wicket.model.IModel;

/**
 * @author Ernesto Reinaldo Barreiro
 * @author Melinda Dweer
 */
public abstract class AbstractDeck implements IDeck {

	private static final long serialVersionUID = 1L;

	private IModel<String> title;
	
	/**
	 * @param id
	 */
	public AbstractDeck(IModel<String> title) {
		super();
		this.title = title;
	}

	/* (non-Javadoc)
	 * @see org.wiquery.plugins.slidedeck.IDeck#getDeckTitle()
	 */
	@Override
	public IModel<String> getDeckTitle() {
		return this.title;
	}

}
