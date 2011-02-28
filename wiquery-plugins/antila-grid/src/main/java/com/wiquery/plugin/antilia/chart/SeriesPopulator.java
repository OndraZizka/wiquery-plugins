package com.wiquery.plugin.antilia.chart;

import java.io.Serializable;

import nl.topicus.wqplot.options.PlotSeries;

import org.apache.wicket.model.IModel;

/**
 * @author Ernesto Reinaldo Barreiro
 *
 */
public abstract class SeriesPopulator<E extends Serializable> implements ISeriesPopulator<E> {

	private static final long serialVersionUID = 1L;
	
	private IModel<String> title;
	
	/**
	 * 
	 */
	public SeriesPopulator(IModel<String> title) {
		this.title = title;
	}
	
	public IModel<String> getSeriesLabel() {
		return title;
	}
	
	
	public void configureSeries(PlotSeries plotSeries) {
		
	}

}
