package com.wiquery.plugins.demo;

import java.util.Arrays;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.OnChangeAjaxBehavior;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.PropertyModel;
import org.odlabs.wiquery.core.effects.EffectSpeed;

import com.wiquery.plugins.antilia.menu.IMenuItem;

/**
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class EffectSpeedPanel extends Panel implements IMenuItem {

	private static final long serialVersionUID = 1L;

	private EffectSpeed speed;	
	 
	/**
	 * 
	 * @param id
	 * @param text
	 */
    public EffectSpeedPanel(String id, EffectSpeed speed) {
    	super(id);
    	
    	this.speed = speed;
    	
    	DropDownChoice<EffectSpeed> cspeed  = new  DropDownChoice<EffectSpeed>("speed", Arrays.asList(new EffectSpeed[]{EffectSpeed.SLOW, EffectSpeed.FAST}));
    	cspeed.setModel(new PropertyModel<EffectSpeed>(this,"speed"));
    	cspeed.setNullValid(false);
    	cspeed.add(new OnChangeAjaxBehavior() {
    		
			private static final long serialVersionUID = 1L;

			@Override
    		protected void onUpdate(AjaxRequestTarget target) {
    			
    		}
    	});
    	add(cspeed);
    }

	public EffectSpeed getSpeed() {
		return speed;
	}

	public void setSpeed(EffectSpeed speed) {
		this.speed = speed;
	}
        
}
