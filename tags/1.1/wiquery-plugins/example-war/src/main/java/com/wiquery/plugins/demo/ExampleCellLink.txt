/**
 * 
 */
package com.wiquery.plugins.demo;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

import com.wiquery.plugins.demo.test.Person;

/**
 * @author Ernesto Reinaldo Barreiro
 *
 */
public abstract class ExampleCellLink extends Panel {

	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public ExampleCellLink(String id, final IModel<Person> model) {
		super(id);
		
		AjaxLink<Void> link = new AjaxLink<Void>("link") {
			
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick(AjaxRequestTarget target) {
				ExampleCellLink.this.onClick(target, model);
			}
		};
		
		add(link);
		link.add(new Label("text", model.getObject().getName()).setRenderBodyOnly(true));
		
	}
	
	public abstract void onClick(AjaxRequestTarget target, IModel<Person> model);

}
