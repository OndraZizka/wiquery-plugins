/**
 * 
 */
package com.wiquery.plugins.demo.scrollpane;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.OnChangeAjaxBehavior;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.PropertyModel;

import com.wiquery.plugin.scrollpane.ScrollPaneBehavior;
import com.wiquery.plugins.demo.BooleanDropDownChoice;
import com.wiquery.plugins.demo.test.Person;

/**
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class ScrollpanePanel extends Panel {

	private static final long serialVersionUID = 1L;

	private boolean arrows = false;
	
	private WebMarkupContainer scrollpane;
	
	private ScrollPaneBehavior behavior;
	/**
	 * @param id
	 */
	public ScrollpanePanel(String id) {
		super(id);
		
		Form<Person> form = new Form<Person>("form");
		add(form);
		
		BooleanDropDownChoice scrollSelect = new BooleanDropDownChoice("arrowsSelect", new PropertyModel<Boolean>(this, "arrows"));
		scrollSelect.add(new OnChangeAjaxBehavior() {
			
			private static final long serialVersionUID = 1L;

			@Override
			protected void onUpdate(AjaxRequestTarget target) {
				behavior.setShowArrows(arrows);
				target.addComponent(scrollpane);
			}
		});
		scrollSelect.setNullValid(false);
		form.add(scrollSelect);        
		
		behavior = new ScrollPaneBehavior();
		
        scrollpane = new WebMarkupContainer("scrollpane");
        scrollpane.add(behavior);
        add(scrollpane);
	}

	public boolean isArrows() {
		return arrows;
	}

	public void setArrows(boolean arrows) {
		this.arrows = arrows;
	}

}
