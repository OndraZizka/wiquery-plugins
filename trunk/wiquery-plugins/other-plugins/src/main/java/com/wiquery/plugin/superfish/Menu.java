package com.wiquery.plugin.superfish;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.ResourceReference;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.panel.Panel;
import org.odlabs.wiquery.core.commons.CoreJavaScriptResourceReference;
import org.odlabs.wiquery.core.commons.IWiQueryPlugin;
import org.odlabs.wiquery.core.commons.WiQueryResourceManager;
import org.odlabs.wiquery.core.javascript.JsQuery;
import org.odlabs.wiquery.core.javascript.JsStatement;
import org.odlabs.wiquery.core.options.Options;
import org.odlabs.wiquery.ui.commons.WiQueryUIPlugin;

import com.wiquery.plugin.menu.IMenu;
import com.wiquery.plugin.menu.IMenuItem;
import com.wiquery.plugin.superfish.js.HoverIntentJavaScriptResourceReference;
import com.wiquery.plugin.superfish.js.SuperfishJavaScriptResourceReference;

/**
 * This component is based on this Menu
 * 
 * http://www.dynamicdrive.com/style/csslibrary/item/jquery_multi_level_css_menu_2/
 * 
 *
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
@WiQueryUIPlugin
public class Menu extends Panel implements IMenu, IWiQueryPlugin {

	List<IMenuItem> items = new ArrayList<IMenuItem>();
	
	
	protected static final ResourceReference CSS = new ResourceReference(Menu.class, "css/superfish.css");	
	protected static final ResourceReference NAVIGATIONBAR_CSS = new ResourceReference(Menu.class, "css/superfish-navbar.css");
	protected static final ResourceReference VERTICAL_CSS = new ResourceReference(Menu.class, "css/superfish-vertical.css");
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private WebMarkupContainer menu;
	
	private Options options;
	
	//private RepeatingView itemsView;
	
	/**
	 * @param id
	 */
	public Menu(String id) {
		super(id);
		
		options = new Options();
		
		setRenderBodyOnly(true);
		
		menu = new WebMarkupContainer("menu");				
		menu.setOutputMarkupId(true);
		
		add(menu);		
	}
	
	public void contribute(WiQueryResourceManager wiQueryResourceManager) {
		wiQueryResourceManager.addJavaScriptResource(CoreJavaScriptResourceReference.get());
		wiQueryResourceManager.addJavaScriptResource(HoverIntentJavaScriptResourceReference.get());
		wiQueryResourceManager.addJavaScriptResource(SuperfishJavaScriptResourceReference.get());
		wiQueryResourceManager.addCssResource(newMenuCss());
	}
	
	public JsStatement statement() {
		return new JsQuery(menu).$().chain("superfish", options.getJavaScriptOptions());
	}
	
	/**
	 *  Flag to enable/disable drop shadows 
	 * 
	 * @param dropShadows
	 *            true to drop shadow, false otherwise
	 * @return instance of the current component
	 */
	public Menu setDropShadows(boolean dropShadows) {
		options.put("dropShadows", dropShadows);
		return this;
	}
	
	/**
	 *  If set to true, arrow mark-up generated automatically = cleaner source 
	 *  code at expense of initialization performance  
	 * 
	 * @param autoArrows
	 *            true to auto-generate arrows, false otherwise
	 * @return instance of the current component
	 */
	public Menu setAutoArrows(boolean autoArrows) {
		options.put("autoArrows", autoArrows);
		return this;
	}
	
	
	
	/**
	 * 
	 * @return
	 */
	protected ResourceReference newMenuCss() {
		return CSS;
	}
	
	
	public IMenu addItem(IMenuItem item) {
		items.add(item);
		return this;
	}
	
	public IMenu removeItem(IMenuItem item) {
		items.add(item);
		return this;
	}
	
	public boolean isOnTop() {
		return true;
	}
	
}
