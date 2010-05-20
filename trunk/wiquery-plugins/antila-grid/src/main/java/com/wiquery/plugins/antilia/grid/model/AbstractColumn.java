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
package com.wiquery.plugins.antilia.grid.model;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import com.wiquery.plugin.antilia.grid.DefaultHeaderCell;
import com.wiquery.plugin.antilia.grid.ITableModel;
import com.wiquery.plugin.antilia.grid.Table;

/**
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public abstract class AbstractColumn<E extends Serializable> extends Model<E> implements IColumn<E> {

	private static final long serialVersionUID = 1L;

	public static final int DEFAULT_WIDTH = 200;
	
	private int width = DEFAULT_WIDTH;
		
	private ITableModel<E> tableModel;
	
	/**
	 * This property says if a column is sortable;
	 */
	private boolean sortable = false;
	
	/**
	 * The property used to sort the column.
	 */
	private  String sortProperty;
	
	/**
	 * Whether the column is re-sizable or not;
	 */
	private boolean resizable = true;
	
	
	private IModel<String> titleModel;
	
			
	/**
	 * 
	 */
	public AbstractColumn(final String sortProperty, IModel<String> titleModel) {
		this(DEFAULT_WIDTH, sortProperty, titleModel);
	}
	
	
	
	/**
	 * 
	 */
	public AbstractColumn(int width,  final String sortProperty, IModel<String> titleModel) {
		super();
		this.width = width;		
		this.sortProperty = sortProperty;
		this.titleModel = titleModel;		
		this.sortable = !StringUtils.isEmpty(sortProperty);
	}

	
	public int getWidth() {
		return width;
	}

	/**
	 * @param width the width to set
	 */
	public IColumn<E> setWidth(int width) {
		this.width = width;
		return this;
	}

	public ITableModel<E> getTableModel() {
		return tableModel;
	}

	public void setTableModel(ITableModel<E> tableModel) {
		this.tableModel = tableModel;
	}

	/**
	 * @return the sortable
	 */
	public boolean isSortable() {
		return sortable;
	}

	/**
	 * @param sortable the sortable to set
	 */
	public void setSortable(boolean sortable) {
		this.sortable = sortable;
	}

	/**
	 * @return the resizable
	 */
	public boolean isResizable() {
		return resizable;
	}

	/**
	 * @param resizable the resizable to set
	 */
	public IColumn<E> setResizable(boolean resizable) {
		this.resizable = resizable;
		return this;
	}

	public IModel<String> getTitleModel() {
		return this.titleModel;
	}


	public void setTitleModel(IModel<String> titleModel) {
		this.titleModel = titleModel;
	}
	
	public void populateRowCell(Item<E> cellItem, String id, E bean, int row, int column) {		
		cellItem.add(new Label(id, newBodyCellModel(bean)));
	}

	protected abstract IModel<E> newBodyCellModel(E object);

	
	public void populateHeaderCell(Item<E> cellItem, String componentId, int column, Table<E> table) {
		cellItem.add(new DefaultHeaderCell<E>(componentId, column, table, this.getTitleModel(), this));
	};
	
	public String getSortProperty() {
		return this.sortProperty;
	}



	public void setSortProperty(String sortProperty) {
		this.sortProperty = sortProperty;
	}
}
