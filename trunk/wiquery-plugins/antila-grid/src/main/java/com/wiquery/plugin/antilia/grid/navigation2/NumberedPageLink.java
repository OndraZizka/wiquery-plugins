package com.wiquery.plugin.antilia.grid.navigation2;

import java.io.Serializable;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.model.Model;

import com.wiquery.plugin.antilia.grid.IPageableComponent;
import com.wiquery.plugin.antilia.grid.IPageableNavigationListener;

/**
 * @author Ernesto Reinaldo Barreiro
 *
 */
public class NumberedPageLink<E extends Serializable> extends PaginatorLinkPanel<E> {

	private static final long serialVersionUID = 1L;
	
	int number;
	
	public NumberedPageLink(String id, int number) {
		super(id, null, new Model<String>(Integer.toString(number)));
		this.number = number; 
	}
	
	
	@Override
	public void onClick(AjaxRequestTarget target) {
		IPageableComponent<E> component = findPageableComponent();
		component.goToPage(number-1);
		target.addComponent(component.getUpdatableComponent());
		if(component instanceof IPageableNavigationListener) {
			IPageableNavigationListener listener = (IPageableNavigationListener)component;
			listener.onGoToPage(target, number-1);
		}
	}
}
