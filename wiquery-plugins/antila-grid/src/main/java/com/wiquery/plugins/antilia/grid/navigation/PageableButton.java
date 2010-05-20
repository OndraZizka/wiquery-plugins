/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.wiquery.plugins.antilia.grid.navigation;

import java.io.Serializable;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.form.Form;

import com.wiquery.plugin.antilia.grid.IPageableComponent;
import com.wiquery.plugins.antilia.menu.AbstractButton;


/**
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public abstract class PageableButton<E extends Serializable> extends AbstractButton {

	private static final long serialVersionUID = 1L;

	public PageableButton(String id) {
		super(id, true);
	}
	

	
	@Override
	protected void onError(AjaxRequestTarget target, Form<?> form) {
		IPageableComponent<E> component = findPageableComponent();
		if(component.getFeedback() != null) {
			target.addComponent((Component)(component.getFeedback()));
		}
	}
	
	@SuppressWarnings("unchecked")
	protected final IPageableComponent<E> findPageableComponent() {
		return (IPageableComponent<E>)findParent(IPageableComponent.class);
	}
}
