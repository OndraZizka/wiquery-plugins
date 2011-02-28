package com.wiquery.plugin.antilia.chart.provider;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Ernesto Reinaldo Barreiro
 *
 */
public interface IDateBasedDataNavigator<E extends Serializable> extends IDataNavigator<E> {
	
	/**
	 * Allows to set the current start date
	 * 
	 * @param date
	 */
	void setStartDate(Date date);
	
	/**
	 * @return Returns the current start date.
	 */
	Date getCurrentStartDate();
			
	
	/**
	 * Allows to set the current end date
	 * 
	 * @param date
	 */
	void setEndDate(Date date);
	
	/**
	 * @return Returns the current start date.
	 */	
	Date getCurrentEndDate();
	
	
	/**
	 * @return The number of day used to step on navigation.
	 */
	int getDaysStep();
	
	/**
	 * Allows to set the number of day used to step on navigation.
	 * 
	 * @param daysStep
	 */
	void setDaysStep(int daysStep);
}
