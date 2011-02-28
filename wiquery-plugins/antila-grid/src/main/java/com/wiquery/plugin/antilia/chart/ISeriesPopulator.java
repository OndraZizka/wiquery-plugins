package com.wiquery.plugin.antilia.chart;

import java.io.Serializable;

import nl.topicus.wqplot.options.PlotSeries;

import org.apache.wicket.model.IModel;

/**
 * @author Ernesto Reinaldo Barreiro
 *
 */
public interface ISeriesPopulator<E extends Serializable> extends Serializable  {

	/**
	 * @return Returns the label for the series.
	 */
	IModel<String> getSeriesLabel();
	
	/**
	 * 
	 * @param bean
	 * @return
	 */
	Double getSeriesValue(E bean); 
	
	
	/**
	 * Allows to configure series.
	 * 
	 * @param plotSeries
	 */
	public void configureSeries(PlotSeries plotSeries);
}
