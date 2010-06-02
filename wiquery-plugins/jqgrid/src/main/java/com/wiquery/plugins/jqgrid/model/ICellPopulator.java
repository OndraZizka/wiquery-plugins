package com.wiquery.plugins.jqgrid.model;


import org.apache.wicket.IClusterable;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.IDetachable;
import org.apache.wicket.model.IModel;

import com.wiquery.plugins.jqgrid.component.Grid;

/**
 * 
 * @author Ernesto Reinaldo Barreiro
 * 
 * @param <T>
 */
public interface ICellPopulator<T> extends IClusterable, IDetachable
{
	/**
	 * Method used to populate a cell in the {@link Grid}
	 * 
	 * @param cellItem
	 * @param componentId
	 * @param row
	 * @param column
	 * @param rowModel
	 */
	void populateItem(final Item<ICellPopulator<T>> cellItem, final String componentId, int row, int col, final IModel<T> rowModel);
}
