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

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * This annotation can be used to convey extra information regarding 
 * BeanTable's column properties. They should be applied to 
 * fields or getter methods.
 * 
 * TODO: this a legacy from the original Antilia table. Shall we still use it to 
 * define a table that builds itself out of a bean?
 * 
 * @author Ernesto Reinaldo Barreiro (reirn70@gmail.com)
 *
 */
@Target(FIELD) 
@Retention(RUNTIME)
public @interface TableColumn {

	/**
	 * The width of a column.
	 * @return
	 */
	int width() default 200; 
	
	/**
	 * @return If columns is sortable or not.
	 */
	boolean sortable() default true; 
	
	
	/**
	 * @return Whether the column is resizable of not.
	 */
	boolean resizable() default true; 
	
}
