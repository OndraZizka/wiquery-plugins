/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.wiquery.plugin.antilia.grid.navigation;

import java.io.Serializable;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;

import com.wiquery.plugin.antilia.grid.IPageableComponent;
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
				IPageableComponent< E> component = findPageableComponent();
				if(component  == null)
					return 0;
				if(!component.containsData())
					return 1;
				return (component.currentPageNumber()+1);
			}
		}));
		
		add(new Label("npages", new Model<Integer>() {
			
			private static final long serialVersionUID = 1L;

			@Override
			public Integer getObject() {
				IPageableComponent< E> component = findPageableComponent();
				if(component  == null)
					return 0;
				if(!component.containsData())
					return 1;
				return component.getNumberOfPages();
			}
		}));
	}
	
	@SuppressWarnings("unchecked")
	private IPageableComponent<E> findPageableComponent() {
		return (IPageableComponent<E>)findParent(IPageableComponent.class);
	}

}
