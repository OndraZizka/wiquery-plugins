/**
 * 
 */
package com.wiquery.plugin.antilia.chart.provider;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;


/**
 * 
 * @author Ernesto Reinaldo Barreiro
 */
public abstract class AbstractListDateBasedDataProvider<T extends Serializable> implements IDateBasedDataProvider<T> {

	private static final long serialVersionUID = 1L;
	
	private List<T> list;
	
	/**
	 * 
	 */
	public AbstractListDateBasedDataProvider(List<T> list) {
		if(list == null)
			throw new IllegalArgumentException("List cannot be null");
		this.list  = list;
	}

	/* (non-Javadoc)
	 * @see org.apache.wicket.model.IDetachable#detach()
	 */
	@Override
	public void detach() {
		//do nothing.
	}

	/* (non-Javadoc)
	 * @see com.wiquery.plugin.antilia.chart.IDateBasedDataProvider#iterator(java.util.Date, java.util.Date)
	 */
	@Override
	public Iterator<? extends T> iterator(Date start, Date end) {		
		List<T> temp = new ArrayList<T>();
		for(T bean: list) {
			Date date = getDate(bean);
			if(date.after(start) && date.before(end))
				temp.add(bean);
		}
		return temp.iterator();
	}

	/* (non-Javadoc)
	 * @see com.wiquery.plugin.antilia.chart.IDateBasedDataProvider#model(java.lang.Object)
	 */
	@Override
	public IModel<T> model(T object) {
		return new Model<T>(object);
	}
	
	protected abstract Date getDate(T bean); 

}
