/**
 * 
 */
package com.wiquery.plugin.antilia.grid.navigation2;

import java.io.Serializable;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.panel.Panel;

import com.wiquery.plugin.antilia.grid.IPageableComponent;
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
	public PaginatorPanel(String id, int steps) {
		super(id);		
		add(newFirstPagePanel("first"));
		add(newPreviousPageLink("previous"));		
		
		add(newExtraLinksPanel("extraLinks", steps));
		
		add(newNextPageLink("next"));		
		add(newLastPagePanel("last"));
	}
	@Override
	protected void onConfigure() {
		setVisible(hasMoreThanOnePage());
		super.onConfigure();
	}
	
	
	protected Component newPreviousPageLink(String id) {
		return new PreviousPageLink<E>(id);
	}
	
	protected Component newFirstPagePanel(String id) {
		return new FirstPageLink<E>(id);
	}
	
	protected Component newExtraLinksPanel(String id, int steps) {
		return new ExtraLinksPanel<E>(id, steps);
	}
	
	protected Component newLastPagePanel(String id) {
		return new LastPageLink<E>(id);
	}
	
	protected Component newNextPageLink(String id) {
		return new NextPageLink<E>(id);
	}

	public boolean hasMoreThanOnePage() {
		IPageableComponent<E> component = findPageableComponent();
		if(component != null)
			return component.getNumberOfPages()>1;
		return false;
	}
	
	@SuppressWarnings("unchecked")
	protected final IPageableComponent<E> findPageableComponent() {
		return (IPageableComponent<E>)findParent(IPageableComponent.class);
	}
	
}
