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
import java.util.Iterator;
import java.util.List;

import org.apache.wicket.model.IComponentInheritedModel;
import org.apache.wicket.model.IModel;

import com.wiquery.plugin.antilia.grid.model.IColumn;
import com.wiquery.plugin.antilia.grid.model.SelectionMode;

/**
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public interface ITableModel<E extends Serializable> extends Serializable {
	
	Iterator<IColumn<E>> getColumnModels();
	
	IColumn<E> getColumnModel(int index);
	
	void setColumnModels(List<IColumn<E>> models);
	
	ITableModel<E> addColumnModel(IColumn<E> columnModel);
	
	Iterator<IColumn<E>> getHiddenModels();
	
	void setHiddenModels(List<IColumn<E>> models);
	
	boolean swapColumns(int i, int j);
	
	boolean moveColumnBefore(int toMove, int before);
	
	boolean hideColumn(int i);
	
	int getColumns();
	
	void setSelectionMode(SelectionMode selectionModel);
	
	SelectionMode getSelectionMode();
	
	Class<E> getBeanClass();
	
	public IComponentInheritedModel<E> newModel(IModel<E> object);

}
