/**
 * 
 */
package com.wiquery.plugins.demo.chart;

import java.util.Calendar;
import java.util.Date;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.data.ListDataProvider;

import com.wiquery.plugin.antilia.chart.Chart;
import com.wiquery.plugin.antilia.chart.PropertyChartModel;
import com.wiquery.plugin.antilia.chart.PropertySeriesPopulator;
import com.wiquery.plugin.antilia.chart.provider.DateBasedDataProviderNavigator;
import com.wiquery.plugin.antilia.chart.provider.PropertyListDateDataProvider;
import com.wiquery.plugins.demo.test.Person;

/**
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class ChartPanel extends Panel {

	private static final long serialVersionUID = 1L;

	/**
	 * @param id
	 */
	public ChartPanel(String id) {
		super(id);
		
		Form<Person> form = new Form<Person>("form");
		add(form);
		
		PropertyChartModel<TestChartBean> chartModel = new PropertyChartModel<TestChartBean>("date", "Dates");
		
		PropertySeriesPopulator<TestChartBean> seriesPopulator = new PropertySeriesPopulator<TestChartBean>("series1", "Series1");
		chartModel.addSeriesPopulator(seriesPopulator);
		
		seriesPopulator = new PropertySeriesPopulator<TestChartBean>("series2", "Series2");
		chartModel.addSeriesPopulator(seriesPopulator);
		
		seriesPopulator = new PropertySeriesPopulator<TestChartBean>("series3", "Series3");
		chartModel.addSeriesPopulator(seriesPopulator);
		
		seriesPopulator = new PropertySeriesPopulator<TestChartBean>("series4", "Series4");		
		chartModel.addSeriesPopulator(seriesPopulator);
		
		seriesPopulator = new PropertySeriesPopulator<TestChartBean>("series5", "Series5");		
		chartModel.addSeriesPopulator(seriesPopulator);
		
		Chart<TestChartBean> chart = new Chart<TestChartBean>("chart", chartModel, new ListDataProvider<TestChartBean>(ChartPage.beans));
		form.add(chart);
		
		Calendar calendar = Calendar.getInstance();
		Date start = calendar.getTime();
		calendar.add(Calendar.DAY_OF_MONTH, 1050);
		Date end = calendar.getTime();
		
		PropertyListDateDataProvider<TestChartBean> provider = new PropertyListDateDataProvider<TestChartBean>("date", ChartPage.beans);
		DateBasedDataProviderNavigator<TestChartBean> basedDataProviderNavigator = new DateBasedDataProviderNavigator<TestChartBean>(start, end, provider);
		
		Chart<TestChartBean> chart1 = new Chart<TestChartBean>("chart1", chartModel, basedDataProviderNavigator);
		form.add(chart1);
		

	}

}
