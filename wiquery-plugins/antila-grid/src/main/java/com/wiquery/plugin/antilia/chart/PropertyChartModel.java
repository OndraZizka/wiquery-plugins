package com.wiquery.plugin.antilia.chart;

import java.io.Serializable;
import java.util.Date;

import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.lang.PropertyResolver;

/**
 * @author Ernesto Reinaldo Barreiro
 *
 */
public class PropertyChartModel<E extends Serializable> extends ChartModel<E> {

	private static final long serialVersionUID = 1L;
	
	private String dateProperty;
	
	public PropertyChartModel(String dateProperty, String title) {
		this(dateProperty, new Model<String>(title));
	}
	
	public PropertyChartModel(String dateProperty, IModel<String> title) {
		super(title);
		this.dateProperty = dateProperty;
	}

	public java.util.Date getDate(E bean) {
		Object object = PropertyResolver.getValue(this.dateProperty, bean);
		if(object instanceof Date) {
			return (Date)object;
		}
		throw new IllegalArgumentException("Property "+this.dateProperty+" of bean "+bean+"is not of type date");
	};
}
