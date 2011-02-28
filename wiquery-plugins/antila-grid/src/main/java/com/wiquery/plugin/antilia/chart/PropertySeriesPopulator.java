package com.wiquery.plugin.antilia.chart;

import java.io.Serializable;

import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.lang.PropertyResolver;

/**
 * @author Ernesto Reinaldo Barreiro
 *
 */
public class PropertySeriesPopulator<E extends Serializable> extends SeriesPopulator<E> {

	private static final long serialVersionUID = 1L;
	
	private String beanProperty;
	
	public PropertySeriesPopulator(String beanProperty, String title) {
		this(beanProperty, new Model<String>(title));
	}
	
	public PropertySeriesPopulator(String beanProperty, IModel<String> title) {
		super(title);
		this.beanProperty = beanProperty;
	}
	
	public Double getSeriesValue(E bean) {
		Object object = PropertyResolver.getValue(this.beanProperty, bean);
		if(object instanceof Double) {
			return (Double)object;
		} else if(object instanceof String) {
			try {
				return Double.parseDouble((String)object);
			} catch (NumberFormatException e) {
				return null;
			}
		}
		return null;
	};
}
