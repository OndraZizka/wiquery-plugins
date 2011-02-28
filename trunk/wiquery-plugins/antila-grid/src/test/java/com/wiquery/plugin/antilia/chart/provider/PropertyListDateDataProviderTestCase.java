package com.wiquery.plugin.antilia.chart.provider;

import java.util.ArrayList;

import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import com.wiquery.plugin.antilia.chart.*;

import junit.framework.TestCase;

public class PropertyListDateDataProviderTestCase extends TestCase {

	
	public static List<TestChartBean> beans = new ArrayList<TestChartBean>();
	
	public static int DAY = 1000*60*60*24;
	
	public static long currentDate = System.currentTimeMillis();
	
	public static final Calendar CALENDAR = Calendar.getInstance();
		
	static  {		
		CALENDAR.setTime(new Date());
		for (int i=0; i < 1000; i++) {						
			Date date = CALENDAR.getTime();
			TestChartBean chartBean = new TestChartBean();
			System.out.println("date="+date);
			chartBean.setDate(date);			
			chartBean.setSeries1(Math.random()*1000+10);
			chartBean.setSeries2(Math.random()*1000+10);
			chartBean.setSeries3(Math.random()*1000+10);
			chartBean.setSeries4(Math.random()*1000+10);
			chartBean.setSeries5(Math.random()*1000+10);
			beans.add(chartBean);
			CALENDAR.add(Calendar.DAY_OF_MONTH, 1);
		}
	}
	
	public void testIterator() {
		PropertyListDateDataProvider<TestChartBean> dataProvider = new PropertyListDateDataProvider<TestChartBean>("date", beans);
		CALENDAR.setTime(new Date());
		Date start = CALENDAR.getTime();
		CALENDAR.add(Calendar.DAY_OF_MONTH, 10);
		Date end = CALENDAR.getTime();
		Iterator<? extends TestChartBean> it = dataProvider.iterator(start, end);
		for(int i=0; i<10;i++) {
			assertTrue(it.hasNext());
			TestChartBean test = it.next();			
			assertNotNull(test);
			System.out.println(test.getDate());
		}
		assertFalse(it.hasNext());
	}
	
	
}
