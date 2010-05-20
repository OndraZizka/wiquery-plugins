/**
 * 
 */
package com.wiquery.plugins.antilia.link;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.RepeatingView;

import com.wiquery.plugins.antilia.menu.IMenuItem;

/**
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public class ActionsPanel extends Panel {

	private static final long serialVersionUID = 1L;

	private RepeatingView items;
	
	/**
	 * @param id
	 */
	public ActionsPanel(String id) {
		super(id);
		
		items = new RepeatingView("items");
		add(items);
		items.newChildId();
	}
	
	/**
	 * Adds an item.
	 * 
	 * @param item
	 */
	public void addItem(IMenuItem item) {
		items.add((Component)item);
	}

	/**
	 * Return a new child ID.
	 * 
	 * @return
	 */
	public String newChildId() {
		return items.newChildId();
	}
}
