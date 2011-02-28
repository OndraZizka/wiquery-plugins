package com.wiquery.plugin.antilia.chart;

import java.io.Serializable;

import org.apache.wicket.markup.html.panel.Panel;

import com.wiquery.plugin.antilia.chart.navigation.DateFromToItem;
import com.wiquery.plugin.antilia.chart.navigation.JqFirstPageButton;
import com.wiquery.plugin.antilia.chart.navigation.JqLastPageButton;
import com.wiquery.plugin.antilia.chart.navigation.JqNextPageButton;
import com.wiquery.plugin.antilia.chart.navigation.JqPreviousPageButton;
import com.wiquery.plugin.antilia.chart.navigation.JqRefreshLink;
import com.wiquery.plugin.antilia.chart.navigation.NumberOfDaysButton;
import com.wiquery.plugin.antilia.chart.navigation.PageNumberItem;
import com.wiquery.plugin.antilia.chart.navigation.PageSizeButton;
import com.wiquery.plugin.antilia.chart.navigation.SeparatorButton;
import com.wiquery.plugin.antilia.menu.IMenu;
import com.wiquery.plugin.antilia.menu.Menu;

/**
 * @author Ernesto Reinaldo Barreiro
 *
 */
public class ChartHeaderPanel<E extends Serializable> extends Panel {

	private static final long serialVersionUID = 1L;

	/**
	 * @param id
	 */
	public ChartHeaderPanel(String id) {
		super(id);
		
		add(newTableMenu("menu"));
	}

	protected Menu newTableMenu(String id) {
		Menu menu =  new Menu(id); 
		
		//add before navigation.		
		addBeforeNavigation(menu);
		
		// add navigation.		
		addNavigation(menu);
			
		// add after navigation.		
		addAfterNavigation(menu);
		return menu;
	}
	
	protected void addNavigation(IMenu menu) {
		menu.addMenuItem(new JqFirstPageButton<E>(menu.newItemId()));
		menu.addMenuItem(new JqPreviousPageButton<E>(menu.newItemId()));
		menu.addMenuItem(new PageNumberItem<E>(menu.newItemId()));
		menu.addMenuItem(new DateFromToItem<E>(menu.newItemId()));
		menu.addMenuItem(new JqNextPageButton<E>(menu.newItemId()));
		menu.addMenuItem(new JqLastPageButton<E>(menu.newItemId()));
		

		menu.addMenuItem(new SeparatorButton(menu.newItemId()));		
		menu.addMenuItem(new PageSizeButton<E>(menu.newItemId()));
		menu.addMenuItem(new NumberOfDaysButton<E>(menu.newItemId()));
		menu.addMenuItem(new JqRefreshLink<E>(menu.newItemId()));	
	}
	
	/**
	 * Add items before navigation panel.
	 * 
	 * @param menu
	 */
	protected void addBeforeNavigation(IMenu menu) {
		
	}
	
	/**
	 * Add it
	 * @param menu
	 */
	protected void addAfterNavigation(IMenu menu) {
		
	}
}
