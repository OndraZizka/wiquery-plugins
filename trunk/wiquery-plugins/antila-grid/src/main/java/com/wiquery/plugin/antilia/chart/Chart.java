package com.wiquery.plugin.antilia.chart;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import nl.topicus.wqplot.components.JQPlot;
import nl.topicus.wqplot.components.plugins.JQPlotDateAxisRenderer;
import nl.topicus.wqplot.data.DateNumberSeries;
import nl.topicus.wqplot.options.PlotOptions;
import nl.topicus.wqplot.options.PlotSeries;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.Component;
import org.apache.wicket.ResourceReference;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.util.ListModel;
import org.odlabs.wiquery.core.commons.IWiQueryPlugin;
import org.odlabs.wiquery.core.commons.WiQueryResourceManager;
import org.odlabs.wiquery.core.javascript.JsStatement;
import org.odlabs.wiquery.ui.commons.WiQueryUIPlugin;

import com.wiquery.plugin.antilia.chart.provider.DataProviderNavigator;
import com.wiquery.plugin.antilia.chart.provider.IDataNavigator;
import com.wiquery.plugin.antilia.grid.resources.DefaultStyle;

/**
 * @author Ernesto Reinaldo Barreiro
 *
 */
@WiQueryUIPlugin
public class Chart<E extends Serializable> extends Panel implements IPageableComponent<E>, IWiQueryPlugin {

	private static final long serialVersionUID = 1L;

	private IChartModel<E> chartModel;
	
	private IDataNavigator<E> navigator;	

	private WebMarkupContainer tBody;
	
	public static final int AUTO = -1;
	
	/**
	 * Table height.
	 */
	private int height = AUTO;
	
	/**
	 * Table width
	 */
	private int width = AUTO;
		
	
	private WebMarkupContainer tRoot;
	
	private WebMarkupContainer tHead;
	
	public Chart(String id, IChartModel<E> chartModel, IDataProvider<E> provider) {
		this(id, chartModel, new DataProviderNavigator<E>(provider));
	}
	
	/**
	 * @param id
	 */
	public Chart(String id, IChartModel<E> chartModel, IDataNavigator<E> navigator) {
		super(id);
		setOutputMarkupId(true);
		this.chartModel = chartModel;
		this.navigator = navigator;		
		
		
		tRoot = new WebMarkupContainer("tRoot");
		addOrReplace(tRoot);
		tRoot.add(new AttributeModifier("style",new AbstractReadOnlyModel<String>(){
			
			private static final long serialVersionUID = 1L;

			@Override
			public String getObject() {
				String height = "auto";
				if(Chart.this.height >0)
					height = Chart.this.height+"px;";
				String width = "auto;";
				if(Chart.this.width >0)
					width = Chart.this.width+"px;";
				
				return new StringBuilder()
						.append("position: relative; width: ").append(width)
						.append(";height:")
						.append(height)
						.toString();				
			}
		}));
		
		
		
		
		tHead = new WebMarkupContainer("tHead");
		tRoot.addOrReplace(tHead);
		tHead.add(new AttributeModifier("style",new AbstractReadOnlyModel<String>(){
			
			private static final long serialVersionUID = 1L;

			@Override
			public String getObject() {
				String width = "auto;";
				if(Chart.this.width >0)
					width = Chart.this.width+2+"px;";
				
				return new StringBuilder()
						.append("width:").append(width)
						.toString();				
			}
		}));
		

		tHead.add(newTableHeader("header"));
		
		tBody = new WebMarkupContainer("tBody");
		tRoot.add(tBody);
		
		WebMarkupContainer tFoot = new WebMarkupContainer("tFoot");
		tFoot.add(new AttributeModifier("style",new AbstractReadOnlyModel<String>(){
			
			private static final long serialVersionUID = 1L;

			@Override
			public String getObject() {
				String width = "auto;";
				if(Chart.this.width >0)
					width = "100%;";
				
				return new StringBuilder()
						.append("width:").append(width)
						.toString();				
			}
		}));
		tRoot.addOrReplace(tFoot);
	}
	
	@Override
	public void detachModels() {
		super.detachModels();
		if(navigator != null) {
			navigator.detach();
		}
	}
	
	@Override
	protected void onBeforeRender() {
		navigator.initialize();
		List<DateNumberSeries<Double>> series = new ArrayList<DateNumberSeries<Double>>();
		Iterable<IModel<E>> values = navigator.getCurrentPage();
		for(ISeriesPopulator<E> col: chartModel.getSeries()) {
			DateNumberSeries<Double> serie = new DateNumberSeries<Double>();
			for(IModel<E> value: values) {
				E bean = value.getObject();
				Double double1 = col.getSeriesValue(bean);
				if(double1 != null)
					serie.addEntry(chartModel.getDate(bean), double1);
			}
			series.add(serie);
		}
		JQPlot chart =
			new JQPlot("chart1", new ListModel<DateNumberSeries<Double>>(series));
		tBody.addOrReplace(chart);		
		
		PlotOptions plotOptions = chart.getOptions();
		plotOptions.getLegend().setShow(true);
		for(ISeriesPopulator<E> col: chartModel.getSeries()) {
			PlotSeries plotSeries = plotOptions.addNewSeries();
			plotSeries.setLabel(col.getSeriesLabel().getObject());
			col.configureSeries(plotSeries);
		}
		plotOptions.getAxes().getXaxis().setRenderer(JQPlotDateAxisRenderer.get().getName());		
		configurePlotOptions(plotOptions);
		super.onBeforeRender();	
	}
	
	/**
	 * Use this method to configure plot options.
	 * @param plotOptions
	 */
	protected void configurePlotOptions(PlotOptions plotOptions) {
		plotOptions.getCursor().setShowTooltip(true);
		plotOptions.getCursor().setShow(true);
		plotOptions.getCursor().setShowTooltipUnitPosition(true);
		plotOptions.getCursor().setFollowMouse(true);
	}
	
	public void contribute(WiQueryResourceManager wiQueryResourceManager) {
		wiQueryResourceManager.addCssResource(getChartCSS());
	}
	
	protected ResourceReference getChartCSS() {		
		return DefaultStyle.CSS_TABLE;
	}
	
	public JsStatement statement() {
		return null;
	}
	/**
	 * Creates the table header. Override if you want to provide 
	 * a different header.
	 * 
	 * @param id
	 * @return
	 */
	protected Panel newTableHeader(String id) {
		return new ChartHeaderPanel<E>(id);
	}


	public Component getUpdatableComponent() {
		return this;
	}

	public IDataNavigator<E> getNavigator() {
		return navigator;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

}
