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
package com.wiquery.plugin.antilia.link;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import com.wiquery.plugin.antilia.menu.IMenuItem;

/**
 *	A panel containing an AJAX link styled as a jquery icon UI image.
 *
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public abstract class JqAjaxLink extends Panel implements IMenuItem {

	private static final long serialVersionUID = 1L;

	private JQIcon icon;
	
	/**
	 * Constructor.
	 * 
	 * @param id The id of the link.
	 * @param icon The icon to be shown by the link.
	 * @param title The text to be shown  as a title for the link (to be shown as the user hovers over the link).
	 */
	public JqAjaxLink(String id, JQIcon icon, String title) {
		this(id,icon,new Model<String>(title));
	}
	
	/**
	 * Constructor.
	 * 
	 * @param id The id of the link.
	 * @param icon The icon to be shown by the link.
	 * @param title The model to be used as a title for the link (to be shown as the user hovers over the link).
	 */
	public JqAjaxLink(String id, JQIcon icon, IModel<String> title) {
		super(id);
		
		this.icon = icon;
		
		WebMarkupContainer parent = new WebMarkupContainer("parent");
		parent.add(new AttributeModifier("class", new AbstractReadOnlyModel<String>() {
			
			private static final long serialVersionUID = 1L;

			@Override
			public String getObject() {
				if(JqAjaxLink.this.isEnabled())
					return "ui-state-default ui-corner-all";
				else 
					return "ui-state-default ui-state-disabled ui-corner-all";
			}
		}));
		
		add(parent);
		
		// we create the AJAX link.		
		AjaxLink<Void> link = newAjaxLink("link");		
		parent.add(link);
		
		link.add(new AttributeModifier("title", title));
		
		link.add(new AttributeModifier("class", new AbstractReadOnlyModel<String>() {
			
			private static final long serialVersionUID = 1L;

			@Override
			public String getObject() {
				return "ui-icon " + getIcon().getCssName();
			}
		}));
		
		link.add(new AttributeModifier("onmouseover", new AbstractReadOnlyModel<String>() {
			
			private static final long serialVersionUID = 1L;

			@Override
			public String getObject() {
				if(JqAjaxLink.this.isEnabled())
					return "this.parentNode.className = 'ui-state-default ui-corner-all ui-state-hover';";
				else
					return "this.parentNode.className = 'ui-state-default ui-state-disabled ui-corner-all';";
			}
		}));

		link.add(new AttributeModifier("onmouseout", new AbstractReadOnlyModel<String>() {
			
			private static final long serialVersionUID = 1L;

			@Override
			public String getObject() {
				if(JqAjaxLink.this.isEnabled())
					return "this.parentNode.className = 'ui-state-default ui-corner-all';";
				else 
					return "this.parentNode.className = 'ui-state-default ui-state-disabled ui-corner-all';";
			}
		}));
	}
	
	/**
	 * Override this method to tune AjaxLink creation.
	 * 
	 * @param id The id of the link.
	 * @return The AjaxLink<Void>
	 */
	protected AjaxLink<Void> newAjaxLink(String id) {
		return new AjaxLink<Void>(id) {
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick(AjaxRequestTarget target) {
				JqAjaxLink.this.onClick(target);
			}
			
			@Override
			public boolean isEnabled() {
				return JqAjaxLink.this.isEnabled();
			}
		};
	}
	
	/**
	 * On click event handler. 
	 * 
	 * @param target The AjaxRequestTarget associated to the AJAX round-trip.
	 */
	public abstract void onClick(AjaxRequestTarget target);

	/**
	 * Gets the icon.
	 * @return
	 */
	public JQIcon getIcon() {
		return icon;
	}

	/**
	 * Sets the icon.
	 * 
	 * @param icon The JQIcon to set.
	 */
	public void setIcon(JQIcon icon) {
		this.icon = icon;
	}

}
