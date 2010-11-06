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
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import com.wiquery.plugin.antilia.menu.IMenuItem;

/**
 *	
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public abstract class JqAjaxSubmitLink extends Panel implements IMenuItem {

	private static final long serialVersionUID = 1L;

	public JqAjaxSubmitLink(String id, final JQIcon icon, String title) {
		this(id,icon,new Model<String>(title));
	}
	
	/**
	 * @param id
	 */
	public JqAjaxSubmitLink(String id, final JQIcon icon, IModel<String> title) {
		super(id);
		AjaxSubmitLink link = new AjaxSubmitLink("link") {
			private static final long serialVersionUID = 1L;

		
			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				JqAjaxSubmitLink.this.onSubmit(target, form);
			}
			
			@Override
			public boolean isEnabled() {
				return JqAjaxSubmitLink.this.isEnabled();
			}
		};
		
		
		
		link.add(new AttributeModifier("title", title));
		
		link.add(new AttributeModifier("class", new AbstractReadOnlyModel<String>() {
			
			private static final long serialVersionUID = 1L;

			@Override
			public String getObject() {
				return "ui-icon " + icon.getCssName();
			}
		}));
		
		link.add(new AttributeModifier("onmouseover", new AbstractReadOnlyModel<String>() {
			
			private static final long serialVersionUID = 1L;

			@Override
			public String getObject() {
				return "this.parentNode.className = 'ui-state-default ui-corner-all ui-state-hover';";
			}
		}));

		link.add(new AttributeModifier("onmouseout", new AbstractReadOnlyModel<String>() {
			
			private static final long serialVersionUID = 1L;

			@Override
			public String getObject() {
				return "this.parentNode.className = 'ui-state-default ui-corner-all';";
			}
		}));

		add(link);
	}
	
	/**
	 * On click.
	 * @param target
	 */
	protected abstract void onSubmit(AjaxRequestTarget target, Form<?> form);

}
