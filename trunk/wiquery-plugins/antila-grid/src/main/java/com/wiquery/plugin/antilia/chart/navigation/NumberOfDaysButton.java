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
import com.wiquery.plugin.antilia.chart.provider.IDateBasedDataNavigator;
import com.wiquery.plugin.antilia.chart.provider.IPageSizeDataNavigator;
import com.wiquery.plugin.antilia.menu.IMenuItem;

/**
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public class NumberOfDaysButton<E extends Serializable> extends Panel implements IMenuItem {

	private static final long serialVersionUID = 1L;

	private TextField<Serializable> textField;
	/**
	 * @param id
	 */
	public NumberOfDaysButton(String id) {
		super(id);		
		textField  = new TextField<Serializable>("field",  new Model<Serializable>() {
			
			private static final long serialVersionUID = 1L;

			@Override
			public Serializable getObject() {
				IPageableComponent<E> pageableComponent = findPageableComponent();
				if(pageableComponent != null && pageableComponent.getNavigator() instanceof IDateBasedDataNavigator<?>) {
					return ((IDateBasedDataNavigator<E>)pageableComponent.getNavigator()).getDaysStep();
				}
				return 10;
			}
			
			@Override
			public void setObject(Serializable object) {
				IPageableComponent<E> pageableComponent = findPageableComponent();
				if(pageableComponent != null && pageableComponent.getNavigator() instanceof IPageSizeDataNavigator<?>) {
					IDateBasedDataNavigator<E> navigator = (IDateBasedDataNavigator<E>)pageableComponent.getNavigator();
					try {						
						navigator.setDaysStep(Integer.parseInt(object.toString()));
					}
					catch (NumberFormatException e) {
						navigator.setDaysStep(10);
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
		setVisible(pageableComponent != null && (pageableComponent.getNavigator() instanceof IDateBasedDataNavigator<?>));
	}
	
	@SuppressWarnings("unchecked")
	private IPageableComponent<E> findPageableComponent() {
		return (IPageableComponent<E>)findParent(IPageableComponent.class);
	}

}
