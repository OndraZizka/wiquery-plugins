package com.wiquery.plugins.demo;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.PropertyModel;
import org.odlabs.wiquery.ui.sortable.SortableAjaxBehavior;
import org.odlabs.wiquery.ui.sortable.SortableAjaxBehavior.SortedEvent;
import org.odlabs.wiquery.ui.sortable.SortableBehavior.AxisEnum;

/**
 * 
 * @author Ernesto Reinaldo Barreiro
 *
 */
public class SortablePanel extends Panel {

	private static final long serialVersionUID = 1L;

	private String message = "No sorting done yet!";

	private Component mLabel;
	
	private WebMarkupContainer sortableW;
	
	private boolean showPlaceholder = false;
	
	private boolean restrictToYAxis = false;
	
	private SortableAjaxBehavior<WebMarkupContainer> sortable;
	
	
	/**
	 * Constructor that is invoked when page is invoked without a session.
	 * 
	 * @param parameters
	 *            Page parameters
	 */
    public SortablePanel(String id) {
    	super(id);
    	
    	Form<Void> form = new Form<Void>("form");
    	BooleanDropDownChoice showPlaceholder = new BooleanDropDownChoice("placeholder", new PropertyModel<Boolean>(this,"showPlaceholder"));
    	showPlaceholder.setNullValid(false);
    	form.add(showPlaceholder);
    	
    	
    	BooleanDropDownChoice restrictToYAxis = new BooleanDropDownChoice("restrictToYAxis", new PropertyModel<Boolean>(this,"restrictToYAxis"));
    	restrictToYAxis.setNullValid(false);
    	form.add(restrictToYAxis);
    	
    	AjaxButton ajaxButton = new AjaxButton("apply") {
    		
    		private static final long serialVersionUID = 1L;

			@Override
    		protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				if(SortablePanel.this.showPlaceholder == true) {
					sortable.getSortableBehavior().setPlaceholder("ui-state-highlight");					
				} else {
					sortable.getSortableBehavior().setPlaceholder(null);
				}
				
				if(SortablePanel.this.restrictToYAxis == true) {
					sortable.getSortableBehavior().setAxis(AxisEnum.Y);					
				} else {
					sortable.getSortableBehavior().setAxis(null);
				}
				target.addComponent(sortableW);				
    		}
    	};
    	form.add(ajaxButton);
    	
    	add(form);
    	
    	
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
		 		Label label = (Label)sortedComponent.get("label");
		 		SortablePanel.this.setMessage("Element " + label.getDefaultModelObjectAsString() + " moved to position " + index);		 		
		 		if(ajaxRequestTarget!= null) {
		 			ajaxRequestTarget.addComponent(mLabel);
		 		}
		 	}
    		 	
    	};
    	sortableW = new WebMarkupContainer("sortable");
    	sortableW.setOutputMarkupId(true).add(sortable);
    	add(sortableW);
    
    	
    	RepeatingView items = new RepeatingView("items");
    	for(int i =0; i< 8;i++) {
    		WebMarkupContainer item = new WebMarkupContainer(items.newChildId());
    		item.setOutputMarkupId(true);
    		items.add(item);    		
    		item.add(new Label("label", "Item "+i).setRenderBodyOnly(true));
    	}
    	
    	sortableW.add(items);
    	
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

	public boolean isShowPlaceholder() {
		return showPlaceholder;
	}

	public void setShowPlaceholder(boolean showPlaceholder) {
		this.showPlaceholder = showPlaceholder;
	}

	public boolean isRestrictToYAxis() {
		return restrictToYAxis;
	}

	public void setRestrictToYAxis(boolean restrictToYAxis) {
		this.restrictToYAxis = restrictToYAxis;
	}
}
