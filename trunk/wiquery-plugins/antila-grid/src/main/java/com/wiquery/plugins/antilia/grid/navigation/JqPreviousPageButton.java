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
package com.wiquery.plugins.antilia.grid.navigation;

import java.io.Serializable;

import org.apache.wicket.ajax.AjaxRequestTarget;

import com.wiquery.plugin.antilia.grid.IPageableComponent;
import com.wiquery.plugin.antilia.grid.IPageableNavigationListener;
import com.wiquery.plugins.antilia.link.JQIcon;

/**
 *	
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public class JqPreviousPageButton<E extends Serializable> extends JqPageableButton<E> {

	private static final long serialVersionUID = 1L;

	public JqPreviousPageButton(String id) {
		super(id, JQIcon.ui_icon_seek_prev, "Previous");
	}
	
	@Override
	public void onClick(AjaxRequestTarget target) {
		IPageableComponent<E> component = findPageableComponent();
		component.previousPage();
		/*
		if(!component.isKeepSelectionOnNavigation() && component.getSourceSelector() != null) {
			component.getSourceSelector().clear();
		}
		*/
		target.addComponent(component.getUpdatableComponent());
		if(component instanceof IPageableNavigationListener) {
			IPageableNavigationListener listener = (IPageableNavigationListener)component;
			listener.onNextPage(target);
		}
	}
	
	@Override
	public boolean isEnabled() {
		IPageableComponent<E> component = findPageableComponent();
		if(component != null)
			return component.hasPreviousPage();
		return false;
	}
}
