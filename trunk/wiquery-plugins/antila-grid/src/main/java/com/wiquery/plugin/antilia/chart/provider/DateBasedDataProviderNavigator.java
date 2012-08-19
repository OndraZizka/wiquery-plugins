package com.wiquery.plugin.antilia.chart.provider;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.wicket.model.IModel;

/**
 * 
 * @author Ernesto Reinaldo Barreiro
 */
public class DateBasedDataProviderNavigator<E extends Serializable> implements IDateBasedDataNavigator<E> {

	private static final long serialVersionUID = 1L;

	private IDateBasedDataProvider<E> dataProvider;
	
	private int daysStep = 10;
	
	private List<IModel<E>> rowModels;
	
	private Calendar start = Calendar.getInstance();
	private Calendar end = Calendar.getInstance();
	private Calendar currentStart = Calendar.getInstance();
	private Calendar currentEnd = Calendar.getInstance();
	
	/**
	 * 
	 */
	public DateBasedDataProviderNavigator(Date startDate, Date endDate,  IDateBasedDataProvider<E> dataProvider) {
		this.dataProvider = dataProvider;
		this.start.setTime(startDate);
		this.currentStart.setTime(startDate);
		this.currentEnd.setTime(startDate);
		this.currentEnd.add(Calendar.DAY_OF_MONTH, daysStep);
		this.end.setTime(endDate);
	}
	
	public void initialize() {
		//do nothing
	}

	public void nextPage() {
		this.currentStart.add(Calendar.DAY_OF_MONTH, daysStep);
		this.currentEnd.add(Calendar.DAY_OF_MONTH, daysStep);	
		if(this.currentEnd.after(end)) {
			this.currentEnd.setTime(end.getTime());
		}
		rowModels = null;
	}

	public void previousPage() {
		this.currentStart.add(Calendar.DAY_OF_MONTH, -daysStep);
		if(this.currentStart.before(start)) {
			this.currentStart.setTime(start.getTime());
			this.currentEnd.setTime(start.getTime());
			this.currentEnd.add(Calendar.DAY_OF_MONTH, daysStep);
		} else 
			this.currentEnd.add(Calendar.DAY_OF_MONTH, -daysStep);
		rowModels = null;
	}


	public boolean hasNextPage() {
		return this.currentEnd.before(end);
	}

	public boolean hasPreviousPage() {
		return this.currentStart.after(start);
	}


	public void firstPage() {
		this.currentStart.setTime(start.getTime());
		this.currentEnd.setTime(start.getTime());
		this.currentEnd.add(Calendar.DAY_OF_MONTH, daysStep);
		rowModels = null;
	}
	
	

	public void lastPage() {
		this.currentStart.setTime(end.getTime());
		this.currentStart.add(Calendar.DAY_OF_MONTH, -daysStep);
		this.currentEnd.setTime(end.getTime());
		rowModels = null;
	}


	public Iterable<IModel<E>> getCurrentPage() {
		if(rowModels == null || rowModels.size() == 0) {
			rowModels = new ArrayList<IModel<E>>();
			Iterator<? extends E> it = this.dataProvider.iterator(currentStart.getTime() , currentEnd.getTime());
			while(it.hasNext()) {
				rowModels.add(dataProvider.model(it.next()));
			}
		}
		return rowModels;
	}

	public boolean containsData() {
		return getCurrentPage().iterator().hasNext();
	}

	public void reset() {
		firstPage();
	}
	
	/* (non-Javadoc)
	 * @see org.apache.wicket.model.IDetachable#detach()
	 */
	public void detach() {
		if(rowModels!= null) {
			for(IModel<E> model: rowModels){
				model.detach();
			}
		}
		if(this.dataProvider != null) {
			this.dataProvider.detach();
		}
	}

	public void goToPage(int page) {
		//TODO to be implemented later.
	}

	public Date getCurrentStartDate() {
		return currentStart.getTime();
	}

	public Date getCurrentEndDate() {
		return currentEnd.getTime();
	}

	public void setStartDate(Date date) {
		this.start.setTime(date);
	}

	public void setEndDate(Date date) {
		this.end.setTime(date);
	}

	public int getDaysStep() {
		return daysStep;
	}

	public void setDaysStep(int daysStep) {
		this.daysStep = daysStep;
	}
	
	
	
}
