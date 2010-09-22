/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.wiquery.plugin.antilia.menu;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.Component;
import org.apache.wicket.markup.html.CSSPackageResource;
import org.apache.wicket.markup.html.JavascriptPackageResource;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.model.Model;

import com.wiquery.plugin.antilia.grid.resources.DefaultStyle;

/**
 * A Menu class
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public  class Menu extends Panel implements IMenuItem, IMenu {

	private static final long serialVersionUID = 1L;
	
	private RepeatingView toolBar;
	
	private String horizontalStyleClass = "nav-menu";
		
	private String menuStyle = "";
	
			
	
	/**
	 * @param id
	 */
	public Menu(String id) {
		super(id);
	
		
		add(CSSPackageResource.getHeaderContribution(DefaultStyle.CSS_MAIN));	
		add(JavascriptPackageResource.getHeaderContribution(DefaultStyle.JS_COMMON));
		
		setOutputMarkupId(true);
		
		toolBar = new RepeatingView("menu");	
		
		WebMarkupContainer mainDiv = new WebMarkupContainer("mainDiv");		
		mainDiv.add(new AttributeModifier("class", new Model<String>() {
			private static final long serialVersionUID = 1L;

			@Override
			public String getObject() {
				return Menu.this.getHorizontalStyleClass();						
			}
		}));
		mainDiv.add(new AttributeModifier("style", new Model<String>() {
			private static final long serialVersionUID = 1L;

			@Override
			public String getObject() {
				return menuStyle;							
			}
		}));
		add(mainDiv);
		mainDiv.add(toolBar);
		
		toolBar.newChildId();
	}	
	
	public String getHorizontalStyleClass() {
		return horizontalStyleClass;
	}

	
	public void setHorizontalStyleClass(String horizontalStyleClass) {
		this.horizontalStyleClass = horizontalStyleClass;
	}

	public String getMenuStyle() {
		return menuStyle;
	}
	public void setMenuStyle(String menuStyle) {
		this.menuStyle = menuStyle;
	}
	
	public String newItemId() {
		return toolBar.newChildId();
	}

	public IMenu addMenuItem(IMenuItem menuItem) {
		toolBar.add((Component)menuItem);
		return this;
	}
	
	
}
