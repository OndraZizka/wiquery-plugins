/**
 * 
 */
package com.wiquery.plugins.demo;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.wiquery.plugins.slidedeck.IDeck;
import org.wiquery.plugins.slidedeck.SlideDeck;

/**
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class SlideDeckPanel extends Panel {

	private static final long serialVersionUID = 1L;

	/**
	 * @param id
	 */
	public SlideDeckPanel(String id) {
		super(id);
		
		List<IDeck> decks = new ArrayList<IDeck>();
		decks.add(new IDeck() {
			
			private static final long serialVersionUID = 1L;

			public IModel<String> getDeckTitle() {
				return new Model<String>("Slide 1");
			}
			
			public Component getDeckContents(String id) {
				return new Label(id, "Slide 1");
			}
		});
		decks.add(new IDeck() {
			
			private static final long serialVersionUID = 1L;

			public IModel<String> getDeckTitle() {
				return new Model<String>("Slide 2");
			}
			
			public Component getDeckContents(String id) {
				return new Label(id, "Slide 2");
			}
		});
		decks.add(new IDeck() {
			
			private static final long serialVersionUID = 1L;

			public IModel<String> getDeckTitle() {
				return new Model<String>("Slide 3");
			}
			
			public Component getDeckContents(String id) {
				return new Label(id, "Slide 3");
			}
		});
		SlideDeck slidedeck = new SlideDeck("slidedeck", decks);
		//slidedeck.setHideSpines(true);
		slidedeck.setAutoPlay(true);
		slidedeck.setCycle(true);
		add(slidedeck);
		
	}

}
