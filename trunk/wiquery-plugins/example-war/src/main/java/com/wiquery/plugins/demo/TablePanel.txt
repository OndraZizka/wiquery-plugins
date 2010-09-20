/**
 * 
 */
package com.wiquery.plugins.demo;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import com.wiquery.plugin.antilia.grid.FirstBodyCell;
import com.wiquery.plugin.antilia.grid.RowItem;
import com.wiquery.plugin.antilia.grid.Table;
import com.wiquery.plugin.antilia.grid.TableModel;
import com.wiquery.plugin.antilia.grid.model.PropertyColumn;
import com.wiquery.plugin.antilia.link.JQIcon;
import com.wiquery.plugin.antilia.link.JqAjaxLink;
import com.wiquery.plugin.antilia.menu.IMenu;
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
		
		tableModel.addColumnModel(new PropertyColumn<Person>("name", "name", new Model<String>("NAME"))
				.addBodyAddionalCssClass(PropertyColumn.ALIGN_RIGHT_CLASS));
		tableModel.addColumnModel(new PropertyColumn<Person>("lastName", "lastName", new Model<String>("LastName")));
		Table<Person> grid = new Table<Person>( "table", tableModel, new PersonsDataProvider(WicketApplication.getWicketApplication().getPersons()));
		//grid.setHeight(500);
		grid.setWidth(700);
		form.add(grid);
		
		Table<Person> grid1 = new Table<Person>(
				"table1", 
				tableModel, 
				new PersonsDataProvider(WicketApplication.getWicketApplication().getPersons())) {
			
			private static final long serialVersionUID = 1L;

			@Override
			protected WebMarkupContainer newFirstBodyCell(String id,
					int row, 
					Table<Person> table, 
					RowItem<Person> item) {
				return new FirstBodyCell<Person>(id, row, table, item) {
					
					private static final long serialVersionUID = 1L;
					
					@Override
					protected void populateMenu(IMenu menu, int row, IModel<Person> rowModel) {
						menu.addMenuItem(new JqAjaxLink(menu.newItemId(), JQIcon.ui_icon_alert, "alert!") {
							
							private static final long serialVersionUID = 1L;

							@Override
							public void onClick(AjaxRequestTarget target) {
								System.out.println("Here!");
							}
						});
					}
					
					
				};
			}
		};
		//grid.setHeight(500);
		//grid.setWidth(700);
		form.add(grid1);
	}

}
