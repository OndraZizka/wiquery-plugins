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
	
	/** 
	 * If set to true will show a div that can be used to mark active 
	 * deck.
	 * 
	 * @param activeCorner boolean value.
	 * @return instance of the current behavior
	 */
	public JQUISlideDeck setShowActiveCorner(boolean activeCorner) {
		this.options.put("activeCorner", activeCorner);
		return this;
	}
	
	/**
	 * @return Whether the active corner DIV will be show or not.
	 */
	public boolean  isShowActiveCorner() {
		Boolean activeCorner = this.options.getBoolean("activeCorner");
		return activeCorner!= null?activeCorner: true;
	}
	
	/** 
	 * If set to true will show the indexes of the decks.
	 * 
	 * @param showIndex boolean value.
	 * @return instance of the current behavior
	 */
	public JQUISlideDeck setShowIndex(boolean showIndex) {
		this.options.put("index", showIndex);
		return this;
	}
	
	/**
	 * @return Whether deck's indexes will be show or not.
	 */
	public boolean  isShowIndex() {
		Boolean index = this.options.getBoolean("index");
		return index!= null?index: true;
	}
	
	/** 
	 * Allows to set the speed of the animation.
	 * 
	 * @param speed the speed.
	 * @return instance of the current behavior
	 */
	public JQUISlideDeck setSpeed(int speed) {
		this.options.put("speed", speed);
		return this;
	}
	
	/**
	 * @return Returns the speed of the animation.
	 */
	public int getSpeed() {
		Integer speed = this.options.getInt("speed");
		return speed!= null?speed: 500;
	}
	
	/** 
	 * Allows to set the easing transition.
	 * 
	 * @param transition The easing transition (default 'swing').
	 * @return instance of the current behavior
	 */
	public JQUISlideDeck setTransition(String transition) {
		this.options.putLiteral("transition", transition);
		return this;
	}
	
	/**
	 * @return Returns the easing transition.
	 */
	public String getTransition() {
		String transition = this.options.getLiteral("transition");
		return transition!= null?transition: "'swing'";
	}
	
	/** 
	 * The start of the animation?
	 * 
	 * @param start the start of the animation.
	 * @return instance of the current behavior
	 */
	public JQUISlideDeck setStart(int start) {
		this.options.put("start", start);
		return this;
	}
	
	/**
	 * @return Returns the start of the animation.
	 */
	public int getStart() {
		Integer start = this.options.getInt("start");
		return start!= null?start: 500;
	}
	
	/** 
	 * Scroll using the mouse wheel.
	 * 
	 * @param scroll boolean value.
	 * @return instance of the current behavior
	 */
	public JQUISlideDeck setScroll(boolean scroll) {
		this.options.put("scroll", scroll);
		return this;
	}
	
	/**
	 * @return Whether deck's can be scrolled using the 
	 * 	mouse wheel or not.
	 */
	public boolean  isScroll() {
		Boolean scroll = this.options.getBoolean("scroll");
		return scroll!= null?scroll: true;
	}
	
	/** 
	 * Setup Keyboard Interaction.
	 * 
	 * @param scroll boolean value.
	 * @return instance of the current behavior
	 */
	public JQUISlideDeck setUseKeys(boolean useKeys) {
		this.options.put("keys", useKeys);
		return this;
	}
	
	/**
	 * @return Whether deck's can be scrolled using keyboard (using next/previous arrows).
	 */
	public boolean  isUseKeys() {
		Boolean keys = this.options.getBoolean("keys");
		return keys!= null?keys: true;
	}
	
	/** 
	 * Flag used to set whether the slide deck will 
	 * auto play the slides or not.
	 * 
	 * @param autoPlay boolean value.
	 * @return instance of the current behavior
	 */
	public JQUISlideDeck setAutoPlay(boolean autoPlay) {
		this.options.put("autoPlay", autoPlay);
		return this;
	}
	
	/**
	 * @return whether the slide deck will auto play the 
	 * 		   slides or not.
	 */
	public boolean  isAutoPlay() {
		Boolean autoPlay = this.options.getBoolean("autoPlay");
		return autoPlay!= null?autoPlay: false;
	}

	/** 
	 * Sets the auto-play interval
	 * 
	 * @param autoPlayInterval the delay (in mili-seconds).
	 * @return instance of the current behavior
	 */
	public JQUISlideDeck setAutoPlayInterval(int autoPlayInterval) {
		this.options.put("autoPlayInterval", autoPlayInterval);
		return this;
	}
	
	/**
	 * @return Gets the auto-play interval (default 5 seconds  = 5000).
	 */
	public int getAutoPlayInterval() {
		Integer autoPlayInterval = this.options.getInt("autoPlayInterval");
		return autoPlayInterval!= null?autoPlayInterval: 5000;
	}
	
	/** 
	 * Flag used to set whether the will be shown or not.
	 * 
	 * @param autoPlay boolean value.
	 * @return instance of the current behavior
	 */
	public JQUISlideDeck setHideSpines(boolean hideSpines) {
		this.options.put("hideSpines", hideSpines);
		return this;
	}
	
	/**
	 * @return whether the will be shown or not.
	 */
	public boolean  isHideSpines() {
		Boolean hideSpines = this.options.getBoolean("hideSpines");
		return hideSpines!= null?hideSpines: false;
	}
	
	/** 
	 * Flag to be used with auto-play to indicate it
	 * should start again at first deck after last 
	 * one was shown.
	 * 
	 * @param cycle boolean value.
	 * @return instance of the current behavior
	 */
	public JQUISlideDeck setCycle(boolean cycle) {
		this.options.put("cycle", cycle);
		return this;
	}
	
	/**
	 * @return whether cycle or not in auto play option.
	 */
	public boolean  isCycle() {
		Boolean cycle = this.options.getBoolean("cycle");
		return cycle!= null?cycle: false;
	}
	
	
}
