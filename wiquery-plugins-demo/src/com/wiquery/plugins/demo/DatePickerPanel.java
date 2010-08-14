/**
 * 
 */
package com.wiquery.plugins.demo;

import java.io.Serializable;
import java.util.Date;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.odlabs.wiquery.ui.datepicker.DatePicker;
import org.odlabs.wiquery.ui.datepicker.DatePickerNumberOfMonths;
import org.odlabs.wiquery.ui.datepicker.InlineDatePicker;
import org.odlabs.wiquery.ui.datepicker.DatePicker.ShowOnEnum;

/**
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class DatePickerPanel extends Panel {

	private static final long serialVersionUID = 1L;
	
	public static class TestBean implements Serializable{
	
		private static final long serialVersionUID = 1L;

		private Date startDate;
		
		private Date endDate;
		
		public TestBean() {
			startDate = new Date();
		}				
		
		public Date getStartDate() {
			return startDate;
		}

		public void setStartDate(Date startDate) {
			this.startDate = startDate;
		}

		public Date getEndDate() {
			return endDate;
		}

		public void setEndDate(Date endDate) {
			this.endDate = endDate;
		}
	}
	
	private TestBean testBean = new TestBean();
	
	/**
	 * @param id
	 */
	public DatePickerPanel(String id) {
		super(id);		
		
		Form<TestBean> form = new Form<TestBean>("form", new CompoundPropertyModel<TestBean>(testBean));
		add(form);
		
		form.add(new DatePicker<Date>("startDate", Date.class).setShowOn(ShowOnEnum.FOCUS));
		
		form.add(new DatePicker<Date>("endDate", Date.class)
				.setShowOn(ShowOnEnum.BOTH)
				.setNumberOfMonths(new DatePickerNumberOfMonths((short)2)));
		
		InlineDatePicker<Date> inlineDatePicker = new InlineDatePicker<Date>("inlineDatePicker");
		add(inlineDatePicker);
	}

	public TestBean getTestBean() {
		return testBean;
	}

	public void setTestBean(TestBean testBean) {
		this.testBean = testBean;
	}	

}
