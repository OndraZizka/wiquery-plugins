/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.wiquery.plugin.antilia.grid;

import java.io.Serializable;

import org.apache.wicket.markup.html.panel.Panel;

import com.wiquery.plugin.antilia.grid.navigation.JqFirstPageButton;
import com.wiquery.plugin.antilia.grid.navigation.JqLastPageButton;
import com.wiquery.plugin.antilia.grid.navigation.JqNextPageButton;
import com.wiquery.plugin.antilia.grid.navigation.JqPreviousPageButton;
import com.wiquery.plugin.antilia.grid.navigation.JqRefreshLink;
import com.wiquery.plugin.antilia.grid.navigation.PageNumberItem;
import com.wiquery.plugin.antilia.grid.navigation.PageSizeButton;
import com.wiquery.plugin.antilia.grid.navigation.SeparatorButton;
import com.wiquery.plugin.antilia.menu.IMenu;
import com.wiquery.plugin.antilia.menu.Menu;

/**
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public class DefaultTableHeader<E extends Serializable> extends Panel {

	private static final long serialVersionUID = 1L;

	/**
	 * @param id
	 */
	public DefaultTableHeader(String id, Table<E> table) {
		super(id);
		
		add(newTableMenu("menu", table));
	}
	
	protected Menu newTableMenu(String id, Table<E> table) {
		Menu menu =  new Menu(id); 
		
		//add before navigation.		
		addBeforeNavigation(menu);
		
		// add navigation.		
		addNavigation(menu);
			
		// add after navigation.		
		addAfterNavigation(menu);
		return menu;
	}
	
	protected void addNavigation(IMenu menu) {
		menu.addMenuItem(new JqFirstPageButton<E>(menu.newItemId()));
		menu.addMenuItem(new JqPreviousPageButton<E>(menu.newItemId()));
		menu.addMenuItem(new PageNumberItem<E>(menu.newItemId()));
		menu.addMenuItem(new JqNextPageButton<E>(menu.newItemId()));
		menu.addMenuItem(new JqLastPageButton<E>(menu.newItemId()));
		

		menu.addMenuItem(new SeparatorButton(menu.newItemId()));		
		menu.addMenuItem(new PageSizeButton<E>(menu.newItemId()));
		menu.addMenuItem(new JqRefreshLink<E>(menu.newItemId()));	
	}
	
	/**
	 * Add items before navigation panel.
	 * 
	 * @param menu
	 */
	protected void addBeforeNavigation(IMenu menu) {
		
	}
	
	/**
	 * Add it
	 * @param menu
	 */
	protected void addAfterNavigation(IMenu menu) {
		
	}
}
