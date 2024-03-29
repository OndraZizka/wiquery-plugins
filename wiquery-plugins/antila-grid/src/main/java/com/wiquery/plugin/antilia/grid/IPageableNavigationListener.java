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

import org.apache.wicket.ajax.AjaxRequestTarget;

/**
 * Defines a listener for a pageable component navigation.
 * 
 * @author Ernesto Reinaldo Barreiro (reirn70@gmail.com)
 */
public interface IPageableNavigationListener {
			
	/**
	 * Called when navigating to next page.
	 * 
	 * @param target
	 */
	void onFirstPage(AjaxRequestTarget target);	
	
	/**
	 * 
	 * @param target
	 * @param page
	 */
	void onGoToPage(AjaxRequestTarget target, int page);	
	
	
	
	/**
	 * Called when navigating to previous page.
	 * 
	 * @param target
	 */
	void onPreviousPage(AjaxRequestTarget target);
	
	
	/**
	 * Called when navigating to next page.
	 * 
	 * @param target
	 */
	void onNextPage(AjaxRequestTarget target);
	
	/**
	 * Called when navigating to last page.
	 * 
	 * @param target
	 */
	void onLastPage(AjaxRequestTarget target);
}
