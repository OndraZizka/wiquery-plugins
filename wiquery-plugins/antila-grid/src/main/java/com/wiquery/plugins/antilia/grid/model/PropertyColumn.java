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

import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;

/**
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public class PropertyColumn<E extends Serializable> extends AbstractColumn<E> implements IColumn<E> {

	private static final long serialVersionUID = 1L;

	private String propertyPath;
	
			
	/**
	 * Constructor.
	 * 
	 */
	public PropertyColumn(final String propertyPath, final String sortProperty, IModel<String> titleModel) {
		this(DEFAULT_WIDTH, propertyPath, sortProperty, titleModel);
	}
	
	
	
	/**
	 * Constructor.
	 * 
	 */
	public PropertyColumn(int width,  final String propertyPath, final String sortProperty, IModel<String> titleModel) {
		super(width, sortProperty, titleModel);
		this.propertyPath = propertyPath;				
	}

	public String getPropertyPath() {
		return propertyPath;
	}

	/**
	 * @param propertyPath the propertyPath to set
	 */
	public void setPropertyPath(String propertyPath) {
		this.propertyPath = propertyPath;
	}

	

	protected IModel<E> newBodyCellModel(E object) {
		return new PropertyModel<E>(object, getPropertyPath());
	}	

}
