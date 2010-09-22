/**
 * 
 */
package com.jquery.jquery;

import org.apache.wicket.ResourceReference;
import org.apache.wicket.ajax.AbstractDefaultAjaxBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.behavior.AbstractAjaxBehavior;
import org.apache.wicket.markup.html.JavascriptPackageResource;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.AbstractReadOnlyModel;

/**
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class TestDragDrop extends WebPage {
	
	public static final ResourceReference JQUERY = new ResourceReference(TestDragDrop.class, "jquery-1.4.2.js");
	public static final ResourceReference JQUERY_UI_CORE = new ResourceReference(TestDragDrop.class, "jquery.ui.core.js");	
	public static final ResourceReference JQUERY_UI_WIDGET = new ResourceReference(TestDragDrop.class, "jquery.ui.widget.js");
	public static final ResourceReference JQUERY_UI_MOUSE = new ResourceReference(TestDragDrop.class, "jquery.ui.mouse.js");
	public static final ResourceReference JQUERY_UI_DRAG = new ResourceReference(TestDragDrop.class, "jquery.ui.draggable.js");
	public static final ResourceReference JQUERY_UI_DROP = new ResourceReference(TestDragDrop.class, "jquery.ui.droppable.js");
	
	
	
	WebMarkupContainer context;
	AbstractAjaxBehavior abstractAjaxBehavior;
	
	public TestDragDrop() {
		add(JavascriptPackageResource.getHeaderContribution(JQUERY));
		add(JavascriptPackageResource.getHeaderContribution(JQUERY_UI_CORE));
		add(JavascriptPackageResource.getHeaderContribution(JQUERY_UI_WIDGET));
		add(JavascriptPackageResource.getHeaderContribution(JQUERY_UI_MOUSE));
		add(JavascriptPackageResource.getHeaderContribution(JQUERY_UI_DRAG));
		add(JavascriptPackageResource.getHeaderContribution(JQUERY_UI_DROP));
		
		context = new WebMarkupContainer("context");		
		add(context);
		
		abstractAjaxBehavior = new AbstractDefaultAjaxBehavior() {
			
			private static final long serialVersionUID = 1L;

			@Override
			protected void respond(AjaxRequestTarget target) {
				AjaxRequestTarget.get().addComponent(context);
			}
		};
		context.add(abstractAjaxBehavior);
		
		Label script = new Label("script", new AbstractReadOnlyModel<String>() {
			
			private static final long serialVersionUID = 1L;

			@Override
			public String getObject() {
				String url = abstractAjaxBehavior.getCallbackUrl().toString();
				
				StringBuffer sb = new StringBuffer();
				sb.append("$(function() {\n");
				sb.append("$(\"#draggable\").draggable();\n");
				sb.append("$(\"#droppable\").droppable({\n");
				sb.append("drop: function(event, ui) {\n");
				sb.append("alert('Hi!');");
				sb.append("wicketAjaxGet('"+url+"');");
				sb.append("},\n");
				sb.append("accept:'.ui-widget-content'");
				sb.append("});\n");
				sb.append("});\n");
				return sb.toString();
			}
		});
		script.setEscapeModelStrings(false);
		context.add(script);
	}

}
