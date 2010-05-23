package com.wiquery.plugins.demo.watermark;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;

import com.wiquery.pluglin.watermark.TextFieldWatermarkBehaviour;

/**
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class WatermarkPanel extends Panel {

	private static final long serialVersionUID = 1L;
	   
    /**
	 * Constructor that is invoked when page is invoked without a session.
	 * 
	 * @param parameters
	 *            Page parameters
	 */
    public WatermarkPanel(String id) {
    	super(id);
    	
    	Form<WatermarkPanel> demo1 = new Form<WatermarkPanel>("demo1");
    	add(demo1);
    	
    	demo1.add(new TextField<String>("country").add(
    			new TextFieldWatermarkBehaviour("Leave blank for USA")
    			.setUseNative(false)));
     	
    }
     
}
