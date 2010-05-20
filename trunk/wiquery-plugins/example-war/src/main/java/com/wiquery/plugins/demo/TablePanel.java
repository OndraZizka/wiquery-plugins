/**
 * 
 */
package com.wiquery.plugins.demo;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;

import com.wiquery.plugin.antilia.grid.Table;
import com.wiquery.plugin.antilia.grid.TableModel;
import com.wiquery.plugins.antilia.grid.model.PropertyColumn;
import com.wiquery.plugins.demo.test.Person;

/**
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class TablePanel extends Panel {

	private static final long serialVersionUID = 1L;

	/**
	 * @param id
	 */
	public TablePanel(String id) {
		super(id);
		Form<Person> form = new Form<Person>("form");
		add(form);
		TableModel<Person> tableModel = new TableModel<Person>(Person.class);     
		tableModel.addColumnModel(new PropertyColumn<Person>("id", "id", new Model<String>("ID")));
		
		tableModel.addColumnModel(new PropertyColumn<Person>("name", "name", new Model<String>("NAME")));
		tableModel.addColumnModel(new PropertyColumn<Person>("lastName", "lastName", new Model<String>("LastName")));
		Table<Person> grid = new Table<Person>( "table", tableModel, new PersonsDataProvider(WicketApplication.getWicketApplication().getPersons()));
		//grid.setHeight(500);
		grid.setWidth(700);
		form.add(grid);
	}

}
