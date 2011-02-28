/**
 * 
 */
package com.wiquery.plugin.antilia.chart.provider;

import java.util.Date;
import java.util.Iterator;

import org.apache.wicket.model.IDetachable;
import org.apache.wicket.model.IModel;

/**
 * @author Ernesto Reinaldo Barreiro
 *
 */
public interface IDateBasedDataProvider<T>  extends IDetachable {

	/**
	 * Gets an iterator for the subset of total data
	 * 
	 * @param start
	 *            The start date
	 * @param end
	 *           The end date
	 * 
	 * @return iterator capable of iterating over items between start and end date
	 */
	Iterator<? extends T> iterator(Date start , Date end);
	
	
	/**
	 * Callback used by the consumer of this data provider to wrap objects retrieved from
	 * {@link #iterator(start, end)} with a model (usually a detachable one).
	 * 
	 * @param object
	 *            the object that needs to be wrapped
	 * 
	 * @return the model representation of the object
	 */
	IModel<T> model(T object);
}
