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

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

import com.wiquery.plugins.antilia.grid.model.SelectionMode;
import com.wiquery.plugins.antilia.menu.IMenu;
import com.wiquery.plugins.antilia.menu.Menu;

/**
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public class FirstBodyCell<E extends Serializable> extends Panel {

	private static final long serialVersionUID = 1L;

	private WebMarkupContainer imagePanel;
	
	private Table<E> table;
	
	int row;
	/**
	 * @param id
	 * @param model
	 */
	public FirstBodyCell(String id, int row, Table<E> table, final RowItem<E> item) {
		super(id);
		this.table = table;
		this.row = row;			
		imagePanel = new WebMarkupContainer("imagePanel");
		imagePanel.setOutputMarkupId(true);
		
		/*
		Image image = new Image("checkboxImage") {
			private static final long serialVersionUID = 1L;

			@Override
			protected ResourceReference getImageResourceReference() {
				return FirstBodyCell.this.getImage();
			}
		};
		*/
		
		SelectionMode mode = table.getTableModel().getSelectionMode();
		
		if(mode.equals(SelectionMode.MULTIPLE) || mode.equals(SelectionMode.SINGLE)) {
			ToggleSelectRowButton<E> selectRowButton = new ToggleSelectRowButton<E>("checkboxImage", table, row);
			imagePanel.add(selectRowButton);
		} else {
			Label label = new Label("checkboxImage", "");
			imagePanel.add(label);
		}
		
		
		/*
		Label label = new Label("checkboxImage", "");
		imagePanel.add(label);
	
		*/
		table.addRowCheckBox(imagePanel);
		add(imagePanel);
		
		/*
		Menu menu = Menu.createMenu("menu", null,new IMenuItemsFactory() {
			
			private static final long serialVersionUID = 1L;

			public void populateMenuItems(String menuId, IMenuItemHolder itemHolder) {				
				E bean = (E)item.getModel().getObject();
				FirstBodyCell.this.table.populateRowMenu(itemHolder,FirstBodyCell.this.row, bean) ;
			}
		});
		menu.setMenuStyle("width: auto; background: transparent; height: 18px;");
		menu.setRenderBodyOnly(true);
		menu.setOutputMarkupId(false);
		imagePanel.add(menu);
		*/
		
		Menu menu = new Menu("menu");
		menu.setMenuStyle("width: auto; background: transparent; height: 18px;");
		menu.setRenderBodyOnly(true);
		menu.setOutputMarkupId(false);
		imagePanel.add(menu);
		
		populateMenu(menu, row, item.getModel());
	}
	
	/**
	 * Allows to populate the Menu with items.
	 * 
	 * @param menu
	 */
	protected void populateMenu(IMenu menu, final int row, final IModel<E> rowModel) {
		
	}
	
		
	public Table<E> getTable() {
		return table;
	}

}
