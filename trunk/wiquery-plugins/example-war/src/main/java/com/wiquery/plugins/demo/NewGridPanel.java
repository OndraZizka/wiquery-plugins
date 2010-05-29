/**
 * 
 */
package com.wiquery.plugins.demo;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.extensions.markup.html.repeater.data.grid.ICellPopulator;
import org.apache.wicket.extensions.markup.html.repeater.data.grid.PropertyPopulator;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import com.wiquery.plugins.antilia.link.JQIcon;
import com.wiquery.plugins.antilia.link.JqAjaxLink;
import com.wiquery.plugins.demo.test.Person;
import com.wiquery.plugins.jqgrid.model.GridColumnModel;
import com.wiquery.plugins.jqgrid.model.GridModel;
import com.wiquery.plugins.jqgrid.model.GridModel.HorizontalPosition;

/**
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class NewGridPanel extends Panel {

	private static final long serialVersionUID = 1L;

	
	/**
	 * @param id
	 */
	public NewGridPanel(String id) {
		super(id);

		/**
		List<ICellPopulator<Person>> model = new ArrayList<ICellPopulator<Person>>();
		model.add(new PropertyPopulator<Person>("id"));
		model.add(new PropertyPopulator<Person>("name") {
			
			private static final long serialVersionUID = 1L;

			@Override
			public void populateItem(Item<ICellPopulator<Person>> cellItem,
					String componentId, IModel<Person> rowModel) {
				cellItem.add(new JqAjaxLink(componentId, JQIcon.ui_icon_alert, "Alert!") {
					
					private static final long serialVersionUID = 1L;

					@Override
					public void onClick(AjaxRequestTarget target) {
						System.out.println("Click!");
					}
				}); 
			}
		});
		GridDataPanel<Person> grid = new GridDataPanel<Person>("grid", model, new PersonsDataProvider(WicketApplication.getWicketApplication().getPersons()));
		add(grid);
		*/
		
		GridModel<Person> model = new GridModel<Person>(Person.class);
		
		model.addColumnModel(new GridColumnModel<Person>("id", "id", new Model<String>("ID"), 200));
        model.addColumnModel(new GridColumnModel<Person>("name","name", new Model<String>("Name"),200) {
        	private static final long serialVersionUID = 1L;

			@Override
        	public ICellPopulator<Person> getCellPopulator() {
        		return new PropertyPopulator<Person>("name") {
        			
        			private static final long serialVersionUID = 1L;

        			@Override
        			public void populateItem(Item<ICellPopulator<Person>> cellItem,
        					String componentId, IModel<Person> rowModel) {
        				cellItem.add(new JqAjaxLink(componentId, JQIcon.ui_icon_alert, "Alert!") {
        					
        					private static final long serialVersionUID = 1L;

        					@Override
        					public void onClick(AjaxRequestTarget target) {
        						System.out.println("Click!");
        					}
        				}); 
        			}
        		};
        	}
        });
        model.addColumnModel(new GridColumnModel<Person>("lastName","lastName", new Model<String>("Last Name"),200));       
        model.addColumnModel(new GridColumnModel<Person>("address.street","address.street", new Model<String>("street"),200));
        
       
        model.setPagerpos(HorizontalPosition.left);
        
              
        com.wiquery.plugins.jqgrid.experiment.Grid<Person> grid = new com.wiquery.plugins.jqgrid.experiment.Grid<Person>("grid", model, new PersonsDataProvider(WicketApplication.getWicketApplication().getPersons()));
        add(grid);
	}

	

}
