/**
 * 
 */
package org.wiquery.plugins.slidedeck;

import java.util.List;

import org.apache.wicket.ResourceReference;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.RepeatingView;
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
public class JQUISlideDeck extends Panel implements IWiQueryPlugin  {

	private static final long serialVersionUID = 1L;

	private static final ResourceReference CSS = new ResourceReference(JQUISlideDeck.class, "jqui.slidedeck.skin.css");
	
	private WebMarkupContainer slidedeck;
	
	private Options options;
	
	/**
	 * @param id
	 */
	public JQUISlideDeck(String id, List<IDeck> decks) {
		super(id);
		setRenderBodyOnly(true);
		options = new Options();
		//options.put("activeCorner", false);
		//options.put("index", false);
		slidedeck = new WebMarkupContainer("slidedeck");
		slidedeck.setOutputMarkupId(true);
		add(slidedeck);
		
		RepeatingView repeater = new RepeatingView("repeater");
		slidedeck.add(repeater);
		for(IDeck deck: decks) {
			WebMarkupContainer deckPanel = new WebMarkupContainer(repeater.newChildId());
			repeater.add(deckPanel);
			deckPanel.add(new Label("title", deck.getDeckTitle()).setRenderBodyOnly(true));
			deckPanel.add(deck.getDeckContents("content"));
			
		}
	}
	
	public void contribute(WiQueryResourceManager wiQueryResourceManager) {
		wiQueryResourceManager.addCssResource(CSS);
		wiQueryResourceManager.addJavaScriptResource(JQUISlideDeckJavaScriptReference.get());
	}
	
	public JsStatement statement() {
		return new JsQuery(slidedeck).$().chain("slidedeck",options.getJavaScriptOptions());
	}
}
