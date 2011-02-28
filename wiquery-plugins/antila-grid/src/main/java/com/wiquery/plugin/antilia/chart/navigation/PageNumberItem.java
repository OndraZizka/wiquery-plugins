/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.wiquery.plugin.antilia.chart.navigation;

import java.io.Serializable;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;

import com.wiquery.plugin.antilia.chart.IPageableComponent;
import com.wiquery.plugin.antilia.chart.provider.IDataNavigator;
import com.wiquery.plugin.antilia.chart.provider.IPageSizeDataNavigator;
import com.wiquery.plugin.antilia.menu.IMenuItem;

/**
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public class PageNumberItem<E extends Serializable> extends Panel implements IMenuItem {

	private static final long serialVersionUID = 1L;
	
	/**
	 * @param id
	 */
	public PageNumberItem(String id) {
		super(id);		
		add(new Label("page", new Model<Integer>() {
			private static final long serialVersionUID = 1L;

			@Override
			public Integer getObject() {
				IDataNavigator< E> component = findPageableComponent().getNavigator();
				if(component  == null || !(component instanceof IPageSizeDataNavigator))
					return 0;
				IPageSizeDataNavigator<E> navigator = (IPageSizeDataNavigator<E>)component;
				if(!navigator.containsData())
					return 1;				
				return (navigator.currentPageNumber()+1);
			}
		}));
		
		add(new Label("npages", new Model<Integer>() {
			
			private static final long serialVersionUID = 1L;

			@Override
			public Integer getObject() {
				IDataNavigator< E> component = findPageableComponent().getNavigator();
				if(component  == null || !(component instanceof IPageSizeDataNavigator))
					return 0;
				IPageSizeDataNavigator<E> navigator = (IPageSizeDataNavigator<E>)component;
				if(!navigator.containsData())
					return 1;
				return navigator.getNumberOfPages();
			}
		}));
	}
	
	@Override
	protected void onInitialize() {
		super.onInitialize();
		IPageableComponent<E> component = findPageableComponent();
		setVisible(component != null && (component.getNavigator() instanceof IPageSizeDataNavigator));
	}
	
	@SuppressWarnings("unchecked")
	private IPageableComponent<E> findPageableComponent() {
		return (IPageableComponent<E>)findParent(IPageableComponent.class);
	}

}
