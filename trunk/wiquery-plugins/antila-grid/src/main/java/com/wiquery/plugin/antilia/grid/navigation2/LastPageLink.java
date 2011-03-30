package com.wiquery.plugin.antilia.grid.navigation2;

import java.io.Serializable;

import org.apache.wicket.ajax.AjaxRequestTarget;

import com.wiquery.plugin.antilia.grid.IPageableComponent;
import com.wiquery.plugin.antilia.grid.IPageableNavigationListener;

/**
 * @author Ernesto Reinaldo Barreiro
 *
 */
public class LastPageLink<E extends Serializable> extends PaginatorLinkPanel<E> {

	private static final long serialVersionUID = 1L;
	
	
	public LastPageLink(String id) {
		super(id, "Last", "&gt;&gt;");
	}
	
	@Override
	public void onClick(AjaxRequestTarget target) {
		IPageableComponent<E> component = findPageableComponent();
		component.lastPage();		
		target.addComponent(component.getUpdatableComponent());
		if(component instanceof IPageableNavigationListener) {
			IPageableNavigationListener listener = (IPageableNavigationListener)component;
			listener.onNextPage(target);
		}
	}
	
	@Override
	public boolean isEnabled() {
		IPageableComponent<E> component = findPageableComponent();
		if(component != null)
			return component.hasNextPage();
		return false;
	}
}
