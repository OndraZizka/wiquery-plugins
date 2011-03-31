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

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.form.HiddenField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.Model;

import com.wiquery.plugin.antilia.menu.Menu;

/**
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public class FirstHeaderCell<E extends Serializable> extends Panel {

	private static final long serialVersionUID = 1L;

	private Table<E> table;

	private int column;
	/**
	 * @param id
	 * @param model
	 */
	public FirstHeaderCell(String id, int column, Table<E> table) {
		super(id);
		this.table = table;
		this.column = column;
		setRenderBodyOnly(true);		
		
		WebMarkupContainer drop = new WebMarkupContainer("drop");		
		add(drop);
		add(new HiddenField<Integer >("colWidth", new Model<Integer>() {
			
			private static final long serialVersionUID = 1L;

			@Override
			public Integer getObject() {
				return FirstHeaderCell.this.getTable().getFirstColumnModel().getWidth();
			}
			
			@Override
			public void setObject(Integer object) {
				FirstHeaderCell.this.getTable().getFirstColumnModel().setWidth(object);
			}
			
			
		}, Integer.class));
		//MenuItemsFactory factory = getTable().getFirstHeaderMenuItemsFactory();		
		Menu menu = new Menu("headerMenu", false);
		menu.setHorizontalStyleClass("trans-menu firstHeaderMenu");
		add(menu);
		WebMarkupContainer dragTd = new WebMarkupContainer("dragTd");
		dragTd.add(new AttributeModifier("id", new Model<String>() {
			private static final long serialVersionUID = 1L;

			@Override
			public String getObject() {
				if(FirstHeaderCell.this.getTable().isFirstColumnResizable())
					return FirstHeaderCell.this.getTable().getMarkupId()+"_c_"+FirstHeaderCell.this.getColumn();
				return FirstHeaderCell.this.getTable().getMarkupId()+"_cND_"+FirstHeaderCell.this.getColumn();
			}
		}));
		//dragTd.add(new Image("dragImage",  DefaultStyle.IMG_RESIZE));
		dragTd.add(new AttributeModifier("class", new AbstractReadOnlyModel<String>() {
			
			private static final long serialVersionUID = 1L;

			@Override
			public String getObject() {
				if(FirstHeaderCell.this.getTable().isFirstColumnResizable())
					return "fresCol";
				return "fnoResCol";
			}
		}));
		add(dragTd);
	}
	public Table<E> getTable() {
		return table;
	}
	public void setTable(Table<E> table) {
		this.table = table;
	}
	public int getColumn() {
		return column;
	}
	public void setColumn(int column) {
		this.column = column;
	}
}
