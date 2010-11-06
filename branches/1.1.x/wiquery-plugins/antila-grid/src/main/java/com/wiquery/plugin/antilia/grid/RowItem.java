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

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.IComponentInheritedModel;
import org.apache.wicket.model.Model;

/**
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public class RowItem<E extends Serializable> extends Item<E> {

	private static final long serialVersionUID = 1L;

	private Table<E> table; 
	
	
	/**
	 * @param id
	 * @param index
	 * @param model
	 */
	public RowItem(String id, int index, final IComponentInheritedModel<E> model, Table<E> table) {
		super(id, index, model);
		this.table = table;
		String tableId = table.getMarkupId(); 
		add(new AttributeModifier("id", new Model<String>(tableId+"_r_"+index)));
		String clazz = "tbodyrow"+(index%2) + " ui-widget-content " + (index%2==1?" ui-state-active" : "");
		add(new AttributeModifier("class", new Model<String>(clazz)));
		add(new AttributeModifier("onmouseover",new Model<String>("this.className = 'highlightedRow ui-widget-content ui-state-hover';")));		
		add(new AttributeModifier("onmouseout",new Model<String>("this.className = '"+clazz+"';")));
		
		
		
		/*
		add(new AjaxEventBehavior("ondblclick") {

			private static final long serialVersionUID = 1L;

			@Override
			protected void onEvent(AjaxRequestTarget target) {
				getTable().onRowClickedEvent(target, getIndex());
			}
					
			@Override
			protected IAjaxCallDecorator getAjaxCallDecorator() {
				return new IAjaxCallDecorator() 
				{
					private static final long serialVersionUID = 1L;

					public CharSequence decorateOnFailureScript(CharSequence script) {
						return script;
					}

					public CharSequence decorateOnSuccessScript(CharSequence script) {
						return getTable().getMarkupId()+".toggleSelected("+getIndex()+");"+script;
					}

					public CharSequence decorateScript(CharSequence script) {
						return  script;
					}
					
				};
			}
		});
		*/
	}
	public Table<E> getTable() {
		return table;
	}
	public void setTable(Table<E> table) {
		this.table = table;
	}
}
