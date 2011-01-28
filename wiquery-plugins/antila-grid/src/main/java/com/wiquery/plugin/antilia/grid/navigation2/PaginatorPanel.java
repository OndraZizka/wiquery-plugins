/**
 * 
 */
package com.wiquery.plugin.antilia.grid.navigation2;

import java.io.Serializable;

import org.apache.wicket.markup.html.panel.Panel;

import com.wiquery.plugin.antilia.menu.IMenuItem;

/**
 * @author Ernesto Reinaldo Barreiro
 *
 */
public class PaginatorPanel<E extends Serializable> extends Panel implements IMenuItem  {

	private static final long serialVersionUID = 1L;

	/**
	 * @param id
	 */
	public PaginatorPanel(String id) {
		super(id);		
		add(new PreviousPageLink<E>("previous"));
		add(new FirstPagePanel<E>("first"));
		add(new ExtraLinksPanel<E>("extraLinks"));
		add(new LastPagePanel<E>("last"));
		add(new NextPageLink<E>("next"));
		
	}
}
