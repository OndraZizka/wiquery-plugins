/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.wiquery.plugin.antilia.chart.navigation;

import org.apache.wicket.ResourceReference;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.panel.Panel;

import com.wiquery.plugin.antilia.grid.resources.DefaultStyle;
import com.wiquery.plugin.antilia.menu.IMenuItem;


/**
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public class SeparatorButton extends Panel implements IMenuItem {

	private static final long serialVersionUID = 1L;
	
	public SeparatorButton(String id) {
		super(id);
		add(new Image("image", getImage()));
	}
	
	protected ResourceReference getImage() {
		return DefaultStyle.IMG_SEPARATOR;
	}

}
