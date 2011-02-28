package com.wiquery.plugin.antilia.chart;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.model.IModel;

/**
 * @author Ernesto Reinaldo Barreiro
 *
 */
public abstract class ChartModel<E extends Serializable> implements IChartModel<E> {

	private static final long serialVersionUID = 1L;
	
	private IModel<String> title;
	
	private List<ISeriesPopulator<E>> series;
	
	/**
	 * 
	 */
	public ChartModel(IModel<String> title) {
		this.title = title;
		series = new ArrayList<ISeriesPopulator<E>>();
	}

	public IModel<String> getTitle() {
		return title;
	}
	
	public Iterable<ISeriesPopulator<E>> getSeries() {
		return series;
	}
	
	public ChartModel<E> addSeriesPopulator(ISeriesPopulator<E> seriesPopulator) {
		series.add(seriesPopulator);
		return this;
	}
}
