/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.wiquery.plugins.demo;

import java.util.Arrays;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.panel.EmptyPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.GridView;
import org.apache.wicket.markup.repeater.data.ListDataProvider;

import com.wiquery.plugins.antilia.link.JQIcon;
import com.wiquery.plugins.antilia.link.JqAjaxLink;
import com.wiquery.plugins.antilia.menu.IMenuItem;

/**
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public class JQIconsPanel extends Panel implements IMenuItem {

	private static final long serialVersionUID = 1L;

	/**
	 * @param id
	 */
	public JQIconsPanel(String id) {
		super(id);
		
		add(new GridView<JQIcon>("rows", new ListDataProvider<JQIcon>(Arrays.asList(JQIcon.values()))){

			private static final long serialVersionUID = 1L;

			@Override
			protected void populateEmptyItem(Item<JQIcon> item) {
				item.add(new EmptyPanel("id"));
			}

			@Override
			protected void populateItem(Item<JQIcon> item) {
				item.add(new JqAjaxLink("id", item.getModelObject(), item.getModelObject().getCssName()) {
					
					private static final long serialVersionUID = 1L;

					@Override
					public void onClick(AjaxRequestTarget target) {
						
					}
				});
			}
			
			
		}.setColumns(10));
	}
}
