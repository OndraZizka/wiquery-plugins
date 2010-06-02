/**
 * 
 */
package com.wiquery.plugins.jqgrid.model;

import java.io.Serializable;

import org.apache.wicket.model.IModel;

/**
 * Allows to configure a jqrid column.
 * 
 * @author Ernesto Reinaldo Barreiro (reirn70@gmail.com)
 *
 */
public class GridColumnModel<B extends Serializable> extends AbstractColumnModel<B> {

	private static final long serialVersionUID = 1L;


	
	/**
	 * 
	 */
	public GridColumnModel(String propertyPath, String sortName, IModel<String> titleModel, int width) {		
		super(propertyPath, sortName, titleModel, width);
	}
	
	public ICellPopulator<B> getCellPopulator() {
		return new PropertyPopulator<B>(getPropertyPath());
	}

}
