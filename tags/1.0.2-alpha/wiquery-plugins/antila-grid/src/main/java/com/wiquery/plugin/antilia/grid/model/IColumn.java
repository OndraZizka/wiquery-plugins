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
package com.wiquery.plugin.antilia.grid.model;

import java.io.Serializable;

import org.apache.wicket.model.IModel;

import com.wiquery.plugin.antilia.grid.ITableModel;

/**
 * Represents a column of a table.
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public interface IColumn<E extends Serializable> extends IModel<E>, ICellPopulator<E> {
	
	/**
	 * Gets the width (in pixels) of the column
	 * @return
	 */
	int getWidth();
	
	
	/**
	 * Set the with 
	 * @param width
	 */
	IColumn<E> setWidth(int width);
	
	
	/**
	 * If the column is Sortable....
	 * @return
	 */
	public boolean isSortable();

	/**
	 * Returns the name of the property that this header sorts. If null is returned the header will
	 * be unsortable.
	 * 
	 * @return a string representing the sort property
	 */
	public String getSortProperty();
	
		
	
	/**
	 * @return Whether the column is re-sizable or not;	 
	 */
	public boolean isResizable();
	
	
	/**
	 * @return Returns the table model.
	 */
	public ITableModel<E> getTableModel();
	
	/**
	 * Allows to set the table model.
	 * @param tableModel The able mode to set.
	 */
	void setTableModel(ITableModel<E> tableModel);
	
	
	/**
	 * Set title model.
	 * @param model
	 */
	IColumn<E> setTitleModel(IModel<String> model);
	
	/**
	 * @return Returns the model used to create the title.
	 */
	IModel<String> getTitleModel();
		
	
	/**
	 * @return Returns an additional CSS class (or classes) to be appended to 
	 * the body cells.
	 */
	String getBodyAddionalCssClasses();

	/**
	 * Allows to set additional CSS classes for the body cells.
	 * @param classes
	 * @return
	 */
	IColumn<E> addBodyAddionalCssClass(String clazz);
}
