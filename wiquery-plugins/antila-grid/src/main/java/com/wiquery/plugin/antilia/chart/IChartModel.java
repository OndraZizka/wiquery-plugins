package com.wiquery.plugin.antilia.chart;

import java.io.Serializable;
import java.util.Date;

import org.apache.wicket.model.IModel;

/**
 * 
 * @author Ernesto Reinaldo Barreiro
 *
 */
public interface IChartModel<E extends Serializable> extends Serializable {

		Iterable<ISeriesPopulator<E>> getSeries();
	
		IModel<String> getTitle();
		
		Date getDate(E bean);
				
}
