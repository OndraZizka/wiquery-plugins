/**
 * 
 */
package com.wiquery.plugins.demo;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.odlabs.wiquery.ui.dialog.Dialog;

/**
 * @author Ernesto Reinaldo Barreiro 
 *
 */
public class DialogPanel extends Panel {

	private static final long serialVersionUID = 1L;

	private int counter = 0;
	
	private Dialog dialog;
	private Label content;
	/**
	 * @param id
	 */
	public DialogPanel(String id) {
		super(id);
		AjaxLink<Void> openDialog = new AjaxLink<Void>("openDialog") {
			
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick(AjaxRequestTarget target) {
				counter++;		
				target.addComponent(content);
				dialog.open(target);
			}
		};
		
		add(openDialog);
		
		dialog = new Dialog("dialog");
		dialog.setModal(true);
		dialog.setAutoOpen(false);
		add(dialog);
		
		add(dialog);
		
		content = new Label("content", new AbstractReadOnlyModel<String>() {
			
			private static final long serialVersionUID = 1L;

			@Override
			public String getObject() {
				return "I have been openned " + counter + " times via AJAX!";
			}
		});
		content.setOutputMarkupId(true);
		dialog.add(content);
	}

}
