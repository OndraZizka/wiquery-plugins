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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IComponentInheritedModel;
import org.apache.wicket.model.IModel;

import com.wiquery.plugin.antilia.grid.model.IColumn;
import com.wiquery.plugin.antilia.grid.model.SelectionMode;

/**
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public class TableModel<E extends Serializable> implements ITableModel<E> {

	private static final long serialVersionUID = 1L;

	private Class<E> beanClass;
	
	private List<IColumn<E>> models;
	
	private List<IColumn<E>> hiddenModels;
		
	private SelectionMode selectionMode = SelectionMode.NONE;
	
	/**
	 * @param object
	 */
	public TableModel(Class<E> beanClass) {
		this.beanClass = beanClass;
		this.models = new ArrayList<IColumn<E>>();
		this.hiddenModels = new ArrayList<IColumn<E>>();
	}					
	
	public IComponentInheritedModel<E> newModel(IModel<E> object) {
		return new CompoundPropertyModel<E>(object);
	}
	
	public ITableModel<E> addColumnModel(IColumn<E> columnModel) {
		models.add(columnModel);
		columnModel.setTableModel(this);
		return this;
	}
	
	protected void addHiddenColumnModel(IColumn<E> model) {
		hiddenModels.add(model);
	}
	
	public SelectionMode getSelectionMode() {
		return selectionMode;
	}

	public void setSelectionMode(SelectionMode selectionMode) {
		this.selectionMode = selectionMode;
	}

	public Iterator<IColumn<E>> getColumnModels() {
		return models.iterator();
	}
	
	public IColumn<E> getColumnModel(int index) {
		return models.get(index);
	}
	
	public Iterator<IColumn<E>> getHiddenModels() {
		return hiddenModels.iterator();
	}
	
	public boolean hideColumn(int i) {
		if(i>=0 && i < models.size()) {
			IColumn<E> model = models.remove(i);
			hiddenModels.add(model);
			return true;
		}
		return false;
	}
	
	public boolean swapColumns(int i, int j) {
		if(i==j) {
			return false;
		}
		if(i>=0 && i< models.size())	{
			IColumn<E> tempi = models.get(i);
			if(j>=0 && j< models.size())	{
				IColumn<E> tempj = models.get(j);
				models.set(i, tempj);
				models.set(j, tempi);
			}
		}
		return false;
	}
	
	public boolean moveColumnBefore(int toMove, int before) {		
		if(toMove==before || toMove==before-1 ) {
			return false;		
		}
		if(toMove>=0 && toMove< models.size())	{
			if(before>=0 && before<models.size())	{
				int pos = before;
				if(toMove < before) 
					pos = before -1;
				IColumn<E> tempi = models.remove(toMove);			
				models.add(before>0?pos:0, tempi);
			} else {
				IColumn<E> tempi = models.remove(toMove);			
				models.add(tempi);			
			}
			return true;
		}
		return false;
	}

	public void setColumnModels(List<IColumn<E>> models) {
		this.models = models;
	}
	
	public int getColumns() {
		return models.size();
	}
	

	public Class<E> getBeanClass() {
		return beanClass;
	}

	/**
	 * @param beanClass the beanClass to set
	 */
	public void setBeanClass(Class<E> beanClass) {
		this.beanClass = beanClass;
	}

	/**
	 * @param hiddenModels the hiddenModels to set
	 */
	public void setHiddenModels(List<IColumn<E>> hiddenModels) {
		this.hiddenModels = hiddenModels;
	}
}
