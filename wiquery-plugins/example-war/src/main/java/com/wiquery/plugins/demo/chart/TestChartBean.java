/**
 * 
 */
package com.wiquery.plugins.demo.chart;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Ernesto Reinaldo Barreiro
 *
 */
public class TestChartBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Date date;
	
	private Double series1;
	
	private Double series2;
	
	private Double series3;
	
	private Double series4;
	
	private Double series5;
	
	/**
	 * 
	 */
	public TestChartBean() {
		// TODO Auto-generated constructor stub
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Double getSeries1() {
		return series1;
	}

	public void setSeries1(Double series1) {
		this.series1 = series1;
	}

	public Double getSeries2() {
		return series2;
	}

	public void setSeries2(Double series2) {
		this.series2 = series2;
	}

	public Double getSeries3() {
		return series3;
	}

	public void setSeries3(Double series3) {
		this.series3 = series3;
	}

	public Double getSeries4() {
		return series4;
	}

	public void setSeries4(Double series4) {
		this.series4 = series4;
	}

	public Double getSeries5() {
		return series5;
	}

	public void setSeries5(Double series5) {
		this.series5 = series5;
	}

}
