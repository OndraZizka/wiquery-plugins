/**
 * 
 */
package com.wiquery.plugins.demo;

import java.util.Iterator;
import java.util.List;

import org.apache.wicket.extensions.markup.html.repeater.data.sort.ISortState;
import org.apache.wicket.extensions.markup.html.repeater.data.table.ISortableDataProvider;
import org.apache.wicket.extensions.markup.html.repeater.util.SingleSortState;
import org.apache.wicket.extensions.markup.html.repeater.util.SortParam;
import org.apache.wicket.markup.repeater.data.ListDataProvider;

import com.wiquery.plugins.demo.test.Person;
import com.wiquery.plugins.demo.util.QueryUtils;
import com.wiquery.plugins.jqgrid.model.SortInfo;
import com.wiquery.plugins.jqgrid.model.SortOrder;

public class PersonsDataProvider extends ListDataProvider<Person> implements ISortableDataProvider<Person> {

	private static final long serialVersionUID = 1L;

	private SingleSortState state = new SingleSortState();;
	
	private List<Person> persons;
	public PersonsDataProvider(List<Person> persons) {
		super(persons);
		this.persons = persons;
	}
	
	@Override
	public Iterator<? extends Person> iterator(int first, int count) {
		if(this.state == null || this.state.getSort() == null) {
			return super.iterator(first, count);
		} else {
			SortParam sort = this.state.getSort();
			SortInfo sortInfo = new SortInfo(sort.getProperty(), sort.isAscending()? SortOrder.asc: SortOrder.desc);
			if(sortInfo != null)
				QueryUtils.sortList(persons, sortInfo);
			int toIndex = first + count;
			if (toIndex > persons.size())
			{
				toIndex = persons.size();
			}
			return persons.subList(first, toIndex).listIterator();
		}
	}


	public ISortState getSortState() {
		return state;
	}


	public void setSortState(ISortState state) {
		this.state = (SingleSortState)state;
	}
	
}