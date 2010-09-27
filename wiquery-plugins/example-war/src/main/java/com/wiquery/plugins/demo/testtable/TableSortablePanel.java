package com.wiquery.plugins.demo.testtable;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.Model;
import org.odlabs.wiquery.ui.sortable.SortableAjaxBehavior;
import org.odlabs.wiquery.ui.sortable.SortableAjaxBehavior.SortedEvent;
import org.odlabs.wiquery.ui.sortable.SortableBehavior.AxisEnum;

/**
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class TableSortablePanel extends Panel {

	private static final long serialVersionUID = 1L;

	private String message = "No sorting done yet!";

	private Component mLabel;
	
	private WebMarkupContainer sortableW;
	
	
	private SortableAjaxBehavior<WebMarkupContainer> sortable;
	
	private int i;
	
	
	/**
	 * Constructor that is invoked when page is invoked without a session.
	 * 
	 * @param parameters
	 *            Page parameters
	 */
    public TableSortablePanel(String id) {
    	super(id);
    	
     	
    	sortable = new SortableAjaxBehavior<WebMarkupContainer>(SortedEvent.UPDATE) {
    
    		 private static final long serialVersionUID = 1L;

			@Override
		 	public void onReceive(WebMarkupContainer sortedComponent, int index,
		 		Component parentSortedComponent,
		 		AjaxRequestTarget ajaxRequestTarget) {
		 	
		 	}
    		 	
		 	@Override
		 	public void onRemove(WebMarkupContainer sortedComponent, AjaxRequestTarget ajaxRequestTarget) {
		 	
		 	}
    		 	
		 	@Override
		 	public void onUpdate(WebMarkupContainer sortedComponent, int index, AjaxRequestTarget ajaxRequestTarget) {
		 		WebMarkupContainer item = (WebMarkupContainer)sortedComponent;
		 		Label label = (Label)item.get("label");
		 		TableSortablePanel.this.setMessage("Element " + label.getDefaultModelObjectAsString() + " moved to position " + index);		 		
		 		if(ajaxRequestTarget!= null) {
		 			ajaxRequestTarget.addComponent(mLabel);
		 		}
		 	}
    		 	
    	};	
    	sortable.getSortableBehavior().setAxis(AxisEnum.X);
    	sortable.getSortableBehavior().setPlaceholder("ui-state-highlight");
    	sortable.getSortableBehavior().setForcePlaceholderSize(true);
    	
    	sortableW = new WebMarkupContainer("sortable");
    	sortableW.setOutputMarkupId(true).add(sortable);
    	add(sortableW);
    
    	
    	RepeatingView items = new RepeatingView("items");
    	for(i =0; i< 6;i++) {
    		WebMarkupContainer item = new WebMarkupContainer(items.newChildId());
    		item.setOutputMarkupId(true);
    		item.add(new AttributeModifier("style", new Model<String>("width: "+(100+(i*10))+"px;")));
    		items.add(item);    		
    		item.add(new Label("label", "Item "+i).setRenderBodyOnly(true));
    	}
    	
    	sortableW.add(items);
    	
    	
    	WebMarkupContainer contents = new WebMarkupContainer("contents");
    	add(contents);
    
    	
    	items = new RepeatingView("items");
    	for(i =0; i< 6;i++) {
    		WebMarkupContainer item = new WebMarkupContainer(items.newChildId());
    		item.setOutputMarkupId(true);
    		item.add(new AttributeModifier("style", new Model<String>("width: "+(100+(i*10))+"px;")));
    		items.add(item);    		
    		item.add(new Label("label", "Item "+i).setRenderBodyOnly(true));
    	}
    	
    	contents.add(items);
    	
    	add(mLabel= new Label("message", new AbstractReadOnlyModel<String>(){
    		private static final long serialVersionUID = 1L;

			@Override
    		public String getObject() {
    			return getMessage();
    		}
    	}).setOutputMarkupId(true));
    }

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
