/**
 * 
 */
package com.wiquery.plugin.antilia.chart.provider;

import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;

import org.apache.wicket.model.IModel;

import junit.framework.TestCase;
import com.wiquery.plugin.antilia.chart.*;

/**
 * @author Ernesto Reinaldo Barreiro
 *
 */
public class DateBasedDataProviderNavigatorTestCase extends TestCase {

	
	public void testIterator() {
		
		PropertyListDateDataProvider<TestChartBean> dataProvider = new PropertyListDateDataProvider<TestChartBean>("date", PropertyListDateDataProviderTestCase.beans);
		PropertyListDateDataProviderTestCase.CALENDAR.setTime(new Date());
		Date start = PropertyListDateDataProviderTestCase.CALENDAR.getTime();
		PropertyListDateDataProviderTestCase.CALENDAR.add(Calendar.DAY_OF_MONTH, 1000);
		Date end = PropertyListDateDataProviderTestCase.CALENDAR.getTime();
		DateBasedDataProviderNavigator<TestChartBean> basedDataProviderNavigator = new DateBasedDataProviderNavigator<TestChartBean>(start, end, dataProvider);		
		printPage(basedDataProviderNavigator);		
		basedDataProviderNavigator.nextPage();
		System.out.println("Next page");
		printPage(basedDataProviderNavigator);
	}
	
	private void printPage(DateBasedDataProviderNavigator<TestChartBean> basedDataProviderNavigator) {
		Iterator<IModel<TestChartBean>> it = basedDataProviderNavigator.getCurrentPage().iterator();
		
		for(int i=0; i<10;i++) {
			assertTrue(it.hasNext());
			TestChartBean test = it.next().getObject();
			assertNotNull(test);
			System.out.println(test.getDate());
		}
		assertFalse(it.hasNext());
	}
	
}
