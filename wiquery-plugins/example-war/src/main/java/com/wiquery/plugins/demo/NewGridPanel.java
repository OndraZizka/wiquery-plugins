/**
 * 
 */
package com.wiquery.plugins.demo;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import com.wiquery.plugins.demo.test.Person;
import com.wiquery.plugins.jqgrid.component.Grid;
import com.wiquery.plugins.jqgrid.model.GridColumnModel;
import com.wiquery.plugins.jqgrid.model.GridModel;
import com.wiquery.plugins.jqgrid.model.ICellPopulator;
import com.wiquery.plugins.jqgrid.model.PropertyPopulator;
import com.wiquery.plugins.jqgrid.model.GridModel.HorizontalPosition;

/**
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class NewGridPanel extends Panel {

	private static final long serialVersionUID = 1L;

	private String text = "No cell clicked yet!";
	
	private Label message;
	
	/**
	 * @param id
	 */
	public NewGridPanel(String id) {
		super(id);		
		GridModel<Person> model = new GridModel<Person>();
		
		model.addColumnModel(new GridColumnModel<Person>("id", "id", new Model<String>("ID"), 200));
		model.addColumnModel(new GridColumnModel<Person>("name","name", new Model<String>("Name"),200) {
        	private static final long serialVersionUID = 1L;

			@Override
        	public ICellPopulator<Person> getCellPopulator() {
        		return new PropertyPopulator<Person>("name") {
        			
        			private static final long serialVersionUID = 1L;
        			
        			@Override
        			public void populateItem(
        					Item<ICellPopulator<Person>> cellItem,
        					String componentId, int row, int col,
        					IModel<Person> rowModel) {
        				cellItem.add(new ExampleCellLink(componentId, rowModel) {
        					
        					private static final long serialVersionUID = 1L;

							@Override
        					public void onClick(AjaxRequestTarget target,
        							IModel<Person> model) {
        						setText("Cell "+ model.getObject().getName() + " was clicked!");
        						target.addComponent(NewGridPanel.this.message);
        					}
        				}); 
        			}
        		};
        	}
        });
        
		//model.addColumnModel(new GridColumnModel<Person>("name", "name", new Model<String>("Name"), 200));
        model.addColumnModel(new GridColumnModel<Person>("lastName","lastName", new Model<String>("Last Name"),200));       
        model.addColumnModel(new GridColumnModel<Person>("address.street","address.street", new Model<String>("street"),200));
        
       
        model.setPagerpos(HorizontalPosition.left);
        model.setCaption("Persons");
              
        Grid<Person> grid = new Grid<Person>("grid", model, new PersonsDataProvider(WicketApplication.getWicketApplication().getPersons()));
        add(grid);
        
        message = new Label("message", new AbstractReadOnlyModel<String>() {
        	
        	private static final long serialVersionUID = 1L;

			@Override
        	public String getObject() {
        		return getText();
        	}
		});
        message.setOutputMarkupId(true);
        add(message);
        
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	

}
