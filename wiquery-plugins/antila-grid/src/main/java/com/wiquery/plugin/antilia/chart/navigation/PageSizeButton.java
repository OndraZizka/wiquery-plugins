/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.wiquery.plugin.antilia.chart.navigation;

import java.io.Serializable;

import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;

import com.wiquery.plugin.antilia.chart.IPageableComponent;
import com.wiquery.plugin.antilia.chart.provider.IPageSizeDataNavigator;
import com.wiquery.plugin.antilia.menu.IMenuItem;

/**
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public class PageSizeButton<E extends Serializable> extends Panel implements IMenuItem {

	private static final long serialVersionUID = 1L;

	private TextField<Serializable> textField;
	/**
	 * @param id
	 */
	public PageSizeButton(String id) {
		super(id);		
		textField  = new TextField<Serializable>("field",  new Model<Serializable>() {
			
			private static final long serialVersionUID = 1L;

			@Override
			public Serializable getObject() {
				IPageableComponent<E> pageableComponent = findPageableComponent();
				if(pageableComponent != null && pageableComponent.getNavigator() instanceof IPageSizeDataNavigator) {
					return ((IPageSizeDataNavigator<E>)pageableComponent.getNavigator()).getPageSize();
				}
				return 10;
			}
			
			@Override
			public void setObject(Serializable object) {
				IPageableComponent<E> pageableComponent = findPageableComponent();
				if(pageableComponent != null && pageableComponent.getNavigator() instanceof IPageSizeDataNavigator) {
					IPageSizeDataNavigator<E> navigator = (IPageSizeDataNavigator<E>)pageableComponent.getNavigator();
					try {						
						navigator.setPageSize(Integer.parseInt(object.toString()));
					}
					catch (NumberFormatException e) {
						navigator.setPageSize(10);
					}
				}
			}										
		});
		add(textField);
	}
	
	@Override
	protected void onInitialize() {
		super.onInitialize();
		IPageableComponent<E> pageableComponent = findPageableComponent();
		setVisible(pageableComponent != null && (pageableComponent.getNavigator() instanceof IPageSizeDataNavigator));
	}
	
	@SuppressWarnings("unchecked")
	private IPageableComponent<E> findPageableComponent() {
		return (IPageableComponent<E>)findParent(IPageableComponent.class);
	}

}
