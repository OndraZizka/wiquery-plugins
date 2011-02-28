/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.wiquery.plugin.antilia.chart.navigation;

import java.io.Serializable;
import java.text.DateFormat;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.AbstractReadOnlyModel;

import com.wiquery.plugin.antilia.chart.IPageableComponent;
import com.wiquery.plugin.antilia.chart.provider.IDataNavigator;
import com.wiquery.plugin.antilia.chart.provider.IDateBasedDataNavigator;
import com.wiquery.plugin.antilia.menu.IMenuItem;

/**
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public class DateFromToItem<E extends Serializable> extends Panel implements IMenuItem {

	private static final long serialVersionUID = 1L;
	
	/**
	 * @param id
	 */
	public DateFromToItem(String id) {
		super(id);		
		add(new Label("startDate", new AbstractReadOnlyModel<String>() {
			private static final long serialVersionUID = 1L;

			public String getObject() {
				IDataNavigator< E> component = findPageableComponent().getNavigator();
				if(component  == null || !(component instanceof IDateBasedDataNavigator<?>))
					return "-";
				IDateBasedDataNavigator<E> navigator = (IDateBasedDataNavigator<E>)component;
				return DateFormat.getDateInstance(1, DateFromToItem.this.getLocale()).format(navigator.getCurrentStartDate());
			}
		}));
		
		add(new Label("endDate", new AbstractReadOnlyModel<String>() {
			private static final long serialVersionUID = 1L;

			public String getObject() {
				IDataNavigator< E> component = findPageableComponent().getNavigator();
				if(component  == null || !(component instanceof IDateBasedDataNavigator<?>))
					return "-";
				IDateBasedDataNavigator<E> navigator = (IDateBasedDataNavigator<E>)component;
				return DateFormat.getDateInstance(1, DateFromToItem.this.getLocale()).format(navigator.getCurrentEndDate());
			}
		}));
	}
	
	@Override
	protected void onInitialize() {
		super.onInitialize();
		IPageableComponent<E> component = findPageableComponent();
		setVisible(component != null && (component.getNavigator() instanceof IDateBasedDataNavigator<?>));
	}
	
	@SuppressWarnings("unchecked")
	private IPageableComponent<E> findPageableComponent() {
		return (IPageableComponent<E>)findParent(IPageableComponent.class);
	}

}
