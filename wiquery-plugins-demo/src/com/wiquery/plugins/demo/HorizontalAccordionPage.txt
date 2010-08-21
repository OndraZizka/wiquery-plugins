package com.wiquery.plugins.demo;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.wiquery.plugins.hzaccordion.HorizontalAccordion;
import org.wiquery.plugins.hzaccordion.IAccordionPane;


/**
 * Homepage
 */
public class HorizontalAccordionPage extends BasePage {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructor that is invoked when page is invoked without a session.
	 * 
	 * @param parameters
	 *            Page parameters
	 */
    public HorizontalAccordionPage() {
    	super();
    }
    
    @Override
    protected Panel getLayoutCenterComponent(String wicketId) {
    	List<IAccordionPane> accordionPanes = new ArrayList<IAccordionPane>();
    	accordionPanes.add(new IAccordionPane() {
			
			public IModel<String> getPaneTitle() {				
				return new Model<String>("1");
			}
			
			public Component getPaneContents(String id) {
				return new SliderPanel(id);
			}
		});
    	accordionPanes.add(new IAccordionPane() {
			
			public IModel<String> getPaneTitle() {				
				return new Model<String>("2");
			}
			
			public Component getPaneContents(String id) {
				return new GridPanel(id);
			}
		});
    	return new HorizontalAccordion(wicketId, accordionPanes);
    }
   
}
