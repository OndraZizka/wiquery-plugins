/**
 * 
 */
package com.wiquery.plugin.antilia.chart.provider;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.apache.wicket.util.lang.PropertyResolver;

/**
 * @author Ernesto Reinaldo Barreiro
 *
 */
public class PropertyListDateDataProvider<T extends Serializable> extends
		AbstractListDateBasedDataProvider<T> {
	
	private static final long serialVersionUID = 1L;
	
	private String propertyName;
	
	public PropertyListDateDataProvider(String propertyName, List<T> list) {
		super(list);
		this.propertyName = propertyName;
	}
	
	protected java.util.Date getDate(T bean) {
		Object object = PropertyResolver.getValue(propertyName, bean);
		return (Date)object;
	};

}
