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

import org.apache.wicket.Component;
import org.apache.wicket.extensions.markup.html.repeater.data.sort.ISortState;
import org.apache.wicket.feedback.IFeedback;
import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.model.IModel;

/**
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public interface IPageableComponent<E extends Serializable> {

	
	/**
	 * Returns the component to be update when navigating.
	 * @return
	 */
	Component getUpdatableComponent();
	
	/**
	 * Returns the element to provide feeback when table operations fail.
	 * @return
	 */
	IFeedback getFeedback();
	
	
	/**
	 * 
	 * @return
	 */
	boolean isKeepSelectionOnNavigation();
	
	
	/**
	 * 
	 */
	public void nextPage();
	
	/**
	 * 
	 * @return
	 */
	public boolean hasNextPage();
	
	/**
	 * 
	 */
	public void previousPage();
	
	/**
	 * 
	 */
	public void firstPage();
	
	
	/**
	 * 
	 */
	public void lastPage();
	
	/**
	 * 
	 * @return
	 */
	public boolean hasPreviousPage();
	
	/**
	 * 
	 * @return
	 */
	public Iterator<IModel<E>> getCurrentPage();
	
	
	public boolean isSelected(E bean);
	
	
	public int currentPageNumber();
	
	public boolean containsData();
	
	public int getNumberOfPages();
	
	public int getPageSize();
	
	public void setPageSize(int pageSize);
	
	public void reset();
	
	public void sort(ISortState state);
	
	
	public IDataProvider<E> getDataProvider();
}
