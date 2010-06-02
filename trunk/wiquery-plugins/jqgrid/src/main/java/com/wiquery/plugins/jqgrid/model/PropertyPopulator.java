/**
 * 
 */
package com.wiquery.plugins.jqgrid.model;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;

public class PropertyPopulator<T> implements ICellPopulator<T>
{
	private static final long serialVersionUID = 1L;
	private final String property;

	/**
	 * Constructor
	 * 
	 * @param property
	 *            property whose value will be displayed in the cell. uses wicket's
	 *            {@link PropertyModel} notation.
	 */
	public PropertyPopulator(String property)
	{
		if (property == null)
		{
			throw new IllegalArgumentException("argument [property] cannot be null");
		}
		this.property = property;
	}

	/**
	 * @see org.apache.wicket.model.IDetachable#detach()
	 */
	public void detach()
	{
	}
	
	public void populateItem(Item<ICellPopulator<T>> cellItem,
			String componentId, int row, int col, IModel<T> rowModel) {
		cellItem.add(new Label(componentId, new PropertyModel<T>(rowModel, property)));
	}
}
