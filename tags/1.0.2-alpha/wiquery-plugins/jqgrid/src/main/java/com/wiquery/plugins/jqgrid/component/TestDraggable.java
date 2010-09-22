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
package com.wiquery.plugins.jqgrid.component;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.panel.Panel;
import org.odlabs.wiquery.ui.draggable.DraggableBehavior;
import org.odlabs.wiquery.ui.droppable.DroppableAccept;
import org.odlabs.wiquery.ui.droppable.DroppableAjaxBehavior;

/**
 *	
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public class TestDraggable extends Panel {

	private static final long serialVersionUID = 1L;

	private WebMarkupContainer context;
	
	/**
	 * @param id
	 */
	public TestDraggable(String id) {
		super(id);
		context = new WebMarkupContainer("context");
		context.setOutputMarkupId(true);
		add(context);
		
		WebMarkupContainer draggable = new WebMarkupContainer("draggable");
		draggable.add(new DraggableBehavior());
		context.add(draggable);
		WebMarkupContainer droppable = new WebMarkupContainer("droppable");
		DroppableAjaxBehavior ajaxBehavior = new DroppableAjaxBehavior() {
			
			private static final long serialVersionUID = 1L;

			@Override
			public void onDrop(Component droppedComponent, AjaxRequestTarget ajaxRequestTarget) {
				if(ajaxRequestTarget != null) {
					System.out.println("Here!");
					ajaxRequestTarget.addComponent(context);
				}
			}
		};		
		droppable.add(ajaxBehavior);
		ajaxBehavior.getDroppableBehavior().setAccept(new DroppableAccept(".test"));
		context.add(droppable);
		
		
		
	}
}
