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
	public PaginatorPanel(String id) {
		super(id);		
		add(newPreviousPageLink("previous"));
		add(newFirstPagePanel("first"));
		add(newExtraLinksPanel("extraLinks"));
		add(newLastPagePanel("last"));
		add(newNextPageLink("next"));		
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
		return new FirstPagePanel<E>(id);
	}
	
	protected Component newExtraLinksPanel(String id) {
		return new ExtraLinksPanel<E>(id);
	}
	
	protected Component newLastPagePanel(String id) {
		return new LastPagePanel<E>(id);
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
