/**
 * 
 */
package org.wiquery.plugins.slidedeck;

import java.util.List;

import org.apache.wicket.ResourceReference;
import org.odlabs.wiquery.core.commons.IWiQueryPlugin;
import org.odlabs.wiquery.core.commons.WiQueryResourceManager;
import org.odlabs.wiquery.core.javascript.JsStatement;
import org.odlabs.wiquery.ui.commons.WiQueryUIPlugin;

/**
 * 
 * @author Ernesto Reinaldo Barreiro
 * @author Melinda Dweer
 */
@WiQueryUIPlugin
public class SlideDeck extends JQUISlideDeck implements IWiQueryPlugin  {

	private static final long serialVersionUID = 1L;

	private static final ResourceReference CSS = new ResourceReference(SlideDeck.class, "slidedeck.skin.css");
	
	/**
	 * @param id
	 */
	public SlideDeck(String id, List<IDeck> decks) {
		super(id, decks);
	}
	
	public void contribute(WiQueryResourceManager wiQueryResourceManager) {
		wiQueryResourceManager.addCssResource(CSS);
		wiQueryResourceManager.addJavaScriptResource(SlideDeckJavaScriptReference.get());
		if(isScroll()) {
			wiQueryResourceManager.addJavaScriptResource(MouseWheelJavaScriptReference.get());
		}
	}
	
	public JsStatement statement() {
		return super.statement();
	}
}
