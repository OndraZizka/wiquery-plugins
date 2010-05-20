package org.wiquery.plugins.hzaccordion;

import org.apache.wicket.ResourceReference;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.panel.Panel;
import org.odlabs.wiquery.core.commons.IWiQueryPlugin;
import org.odlabs.wiquery.core.commons.WiQueryResourceManager;
import org.odlabs.wiquery.core.javascript.JsQuery;
import org.odlabs.wiquery.core.javascript.JsStatement;
import org.odlabs.wiquery.core.options.Options;
import org.odlabs.wiquery.ui.commons.WiQueryUIPlugin;

/**
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
@WiQueryUIPlugin
public class HorizontalAccordion extends Panel implements IWiQueryPlugin{

	private static final long serialVersionUID = 1L;

	private ResourceReference CSS = new ResourceReference(HorizontalAccordion.class,"jquery.hrzAccordion.defaults.css");

	/**
	 * The options set for this component.
	 */
	private Options options;
	
	WebMarkupContainer hscroll;
	
	public HorizontalAccordion(String id) {
		super(id);
		options = new Options();
		hscroll = new WebMarkupContainer("hscroll");
		hscroll.setOutputMarkupId(true);
		add(hscroll);
	}
	
	protected ResourceReference newCss() {
		return CSS;
	}
	public void contribute(WiQueryResourceManager wiQueryResourceManager) {		
		wiQueryResourceManager.addCssResource(newCss());
		wiQueryResourceManager.addJavaScriptResource(HorizontaAccordionJavaScriptReference.get());
	}
	
	public JsStatement statement() {
		return new JsQuery(hscroll).$().chain("hrzAccordion",
				options.getJavaScriptOptions());
	}
}
