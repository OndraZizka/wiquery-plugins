/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.wiquery.plugin.antilia.menu;

/**
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public interface IMenu {
	
	IMenu addMenuItem(IMenuItem menuItem);
	
	String newItemId();

}
