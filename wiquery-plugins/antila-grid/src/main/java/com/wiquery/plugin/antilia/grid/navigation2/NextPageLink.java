package com.wiquery.plugin.antilia.grid.navigation2;

import java.io.Serializable;

import org.apache.wicket.ajax.AjaxRequestTarget;

import com.wiquery.plugin.antilia.grid.IPageableComponent;
import com.wiquery.plugin.antilia.grid.IPageableNavigationListener;

/**
 * @author Ernesto Reinaldo Barreiro
 *
 */
public class NextPageLink<E extends Serializable> extends PaginatorLinkPanel<E> {

	private static final long serialVersionUID = 1L;
	
	public NextPageLink(String id) {
		super(id, "Next", "&gt;");
	}
	
	@Override
	public void onClick(AjaxRequestTarget target) {
		IPageableComponent<E> component = findPageableComponent();
		component.nextPage();
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
