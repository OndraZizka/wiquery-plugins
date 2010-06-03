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
import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.protocol.http.WebSession;
import org.apache.wicket.protocol.http.request.WebClientInfo;
import org.odlabs.wiquery.ui.draggable.DraggableBehavior;
import org.odlabs.wiquery.ui.draggable.DraggableContainment;
import org.odlabs.wiquery.ui.draggable.DraggableRevert;
import org.odlabs.wiquery.ui.draggable.DraggableSnap;
import org.odlabs.wiquery.ui.draggable.DraggableRevert.RevertEnum;
import org.odlabs.wiquery.ui.droppable.DroppableAccept;
import org.odlabs.wiquery.ui.droppable.DroppableAjaxBehavior;

import com.wiquery.plugins.antilia.util.RequestUtils;

/**
 *	
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public abstract class DraggableTitle<E extends Serializable> extends WebMarkupContainer {

	private static final long serialVersionUID = 1L;

	private int column;
	
	/**
	 * @param id
	 */
	public DraggableTitle(String id, int column) {
		super(id);
		this.column =column;
		
		WebClientInfo clinetInfo = (WebClientInfo)WebSession.get().getClientInfo();
		if(!clinetInfo.getProperties().isBrowserInternetExplorer()) {
			DraggableBehavior draggableBehavior = new DraggableBehavior();
			draggableBehavior.setRevert(new DraggableRevert(RevertEnum.INVALID));
			draggableBehavior.setContainment(new DraggableContainment("'#"+getTable().getMarkupId() +"_tTH'"));		
			draggableBehavior.setZIndex(100);
			draggableBehavior.setSnap(new DraggableSnap(true));	
			add(draggableBehavior);
			DroppableAjaxBehavior droppableAjaxBehavior = new DroppableAjaxBehavior() {
				
				private static final long serialVersionUID = 1L;
	
				@Override
				public void onDrop(Component droppedComponent, AjaxRequestTarget ajaxRequestTarget) {
					if(droppedComponent instanceof DraggableTitle<?>) {
						DraggableTitle<?> droppedTitle = (DraggableTitle<?>)droppedComponent;
						int dropped = droppedTitle.getColumn()-1;
						int before = DraggableTitle.this.getColumn()-1;
						DraggableTitle.this.getTable().getTableModel().moveColumnBefore(dropped, before);
						ajaxRequestTarget.addComponent(DraggableTitle.this.getTable().getUpdatableComponent());
					}
				}
			};
			
			droppableAjaxBehavior.getDroppableBehavior().setAccept(new DroppableAccept("."+getTable().getMarkupId()));
			add(droppableAjaxBehavior);
		}
		add(new AttributeModifier("style", new AbstractReadOnlyModel<String>() {
			
			private static final long serialVersionUID = 1L;

			@Override
			public String getObject() {
				if(RequestUtils.isBrowserIeExplorer6())
					return "border: none; overflow: hidden;";
				return "border: 1px solid transparent; overflow: hidden;";
			}
		}));

		add(new AttributeModifier("id", new Model<String>() {
			private static final long serialVersionUID = 1L;

			@Override
			public String getObject() {
				return DraggableTitle.this.getMarkupId();
			}
		}));
		
		add(new AttributeModifier("class", new Model<String>() {
			private static final long serialVersionUID = 1L;

			@Override
			public String getObject() {
				return getTable().getMarkupId()+ " ui-state-default";
			}
		}));
		
		add(new AttributeModifier("onmouseover", new Model<String>() {
			private static final long serialVersionUID = 1L;

			@Override
			public String getObject() {
				return "if(this.className.indexOf('dragging')<0){this.className='"+getTable().getMarkupId()+" ui-state-default ui-state-hover';};";
			}
		}));
		
		add(new AttributeModifier("onmouseout", new Model<String>() {
			private static final long serialVersionUID = 1L;

			@Override
			public String getObject() {
				return "if(this.className.indexOf('dragging')<0){this.className='"+getTable().getMarkupId()+" defaultHeaderOver ui-state-default';};";
			}
		}));
	}
	
	@Override
	public String getMarkupId() {
		return getTable().getMarkupId()+"_dragger_"+ getTable().getRendringCount() + "_" +getColumn();
	}

	protected abstract Table<E> getTable();
	
	public int getColumn() {
		return column;
	}
}
