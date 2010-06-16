/**
 * 
 */
package org.wiquery.plugins.slidedeck;

import org.apache.wicket.ResourceReference;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.panel.Panel;
import org.odlabs.wiquery.core.commons.IWiQueryPlugin;
import org.odlabs.wiquery.core.commons.WiQueryResourceManager;
import org.odlabs.wiquery.core.javascript.JsQuery;
import org.odlabs.wiquery.core.javascript.JsStatement;
import org.odlabs.wiquery.core.options.Options;
import org.odlabs.wiquery.ui.commons.WiQueryUIPlugin;

/**
 * @author Ernesto Reinaldo Barreiro
 * @author Melinda Dweer
 */
@WiQueryUIPlugin
public class SlideDeck extends Panel implements IWiQueryPlugin  {

	private static final long serialVersionUID = 1L;

	private static final ResourceReference CSS = new ResourceReference(SlideDeck.class, "slidedeck.skin.css");
	
	private WebMarkupContainer slidedeck;
	
	private Options options;
	
	/**
	 * @param id
	 */
	public SlideDeck(String id) {
		super(id);
		setRenderBodyOnly(true);
		options = new Options();
		slidedeck = new WebMarkupContainer("slidedeck");
		slidedeck.setOutputMarkupId(true);
		add(slidedeck);
	}
	
	public void contribute(WiQueryResourceManager wiQueryResourceManager) {
		wiQueryResourceManager.addCssResource(CSS);
		wiQueryResourceManager.addJavaScriptResource(SlideDeckJavaScriptReference.get());
	}
	
	public JsStatement statement() {
		return new JsQuery(slidedeck).$().chain("slidedeck",
				options.getJavaScriptOptions());
	}
}
