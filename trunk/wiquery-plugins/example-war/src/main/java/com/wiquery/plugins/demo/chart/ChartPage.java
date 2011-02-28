package com.wiquery.plugins.demo.chart;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.wicket.markup.html.panel.Panel;

import com.wiquery.plugins.demo.BasePage;
import com.wiquery.plugins.demo.code.SourceInfo;
import com.wiquery.plugins.demo.code.SourceInfo.FORMAT;

/**
 * Homepage
 */
public class ChartPage extends BasePage {

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
	
	
	/**
	 * Constructor that is invoked when page is invoked without a session.
	 * 
	 * @param parameters
	 *            Page parameters
	 */
    public ChartPage() {
    	super();
    }
    
    @Override
    protected Panel getLayoutCenterComponent(String wicketId) {
    	return new ChartPanel(wicketId);
    }
    
    @Override
    protected void addSourceCode(List<SourceInfo> codeInfos) {
    	codeInfos.add(new SourceInfo(ChartPage.class));
    	codeInfos.add(new SourceInfo(ChartPanel.class));
		codeInfos.add(new SourceInfo(ChartPanel.class, FORMAT.HTML, "ChartPanel.html", "ChartPanel.html"));
    }
}
