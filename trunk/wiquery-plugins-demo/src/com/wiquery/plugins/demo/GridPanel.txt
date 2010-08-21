/**
 * 
 */
package com.wiquery.plugins.demo;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.OnChangeAjaxBehavior;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

import com.wiquery.plugins.demo.test.Person;
import com.wiquery.plugins.jqgrid.component.Grid;
import com.wiquery.plugins.jqgrid.component.event.OnGridCompleteAjaxEvent;
import com.wiquery.plugins.jqgrid.component.event.OnHeaderClickAjaxEvent;
import com.wiquery.plugins.jqgrid.component.event.OnPagingAjaxEvent;
import com.wiquery.plugins.jqgrid.component.event.OnResizeStopAjaxEvent;
import com.wiquery.plugins.jqgrid.component.event.OnRightClickRowAjaxEvent;
import com.wiquery.plugins.jqgrid.component.event.OnSelectRowAjaxEvent;
import com.wiquery.plugins.jqgrid.component.event.OnSortColAjaxEvent;
import com.wiquery.plugins.jqgrid.component.event.OndblClickRowAjaxEvent;
import com.wiquery.plugins.jqgrid.model.GridColumnModel;
import com.wiquery.plugins.jqgrid.model.GridModel;
import com.wiquery.plugins.jqgrid.model.IColumn;
import com.wiquery.plugins.jqgrid.model.SortOrder;
import com.wiquery.plugins.jqgrid.model.GridModel.HorizontalPosition;

/**
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class GridPanel extends Panel {

	private static final long serialVersionUID = 1L;

	private boolean scoll = false;
	
	private boolean caption  = true;
	
	private GridModel<Person> model;
	WebMarkupContainer context;
	
	/**
	 * @param id
	 */
	public GridPanel(String id) {
		super(id);
		
		Form<Person> form = new Form<Person>("form");
		add(form);
		
		BooleanDropDownChoice scrollSelect = new BooleanDropDownChoice("scrollSelect", new PropertyModel<Boolean>(this, "scoll"));
		scrollSelect.add(new OnChangeAjaxBehavior() {
			
			private static final long serialVersionUID = 1L;

			@Override
			protected void onUpdate(AjaxRequestTarget target) {
				if(!scoll) {
		        	model.setRowNum(20);
		        	model.setRowList(null);
		        	model.setScroll(null);
		        } else {
		        	model.setRowNum(10);
		        	model.setScroll(10);
		        }
				target.addComponent(context);
			}
		});
		
		form.add(scrollSelect);
		
		BooleanDropDownChoice captionSelect = new BooleanDropDownChoice("captionSelect", new PropertyModel<Boolean>(this, "caption"));
		captionSelect.add(new OnChangeAjaxBehavior() {
			
			private static final long serialVersionUID = 1L;

			@Override
			protected void onUpdate(AjaxRequestTarget target) {
				if(caption)
					model.setCaption("Persons");
				else 
					model.setCaption((String)null);
				target.addComponent(context);
			}
		});
		
		form.add(captionSelect);
		
		model = new GridModel<Person>(Person.class);
		
		if(caption)
			model.setCaption("Persons");
		else 
			model.setCaption((String)null);
		
        model.addColumnModel(new GridColumnModel<Person>("id", "id", new Model<String>("ID"), 200));
        model.addColumnModel(new GridColumnModel<Person>("name","name", new Model<String>("Name"),200));
        model.addColumnModel(new GridColumnModel<Person>("lastName","lastName", new Model<String>("Last Name"),200));       
        model.addColumnModel(new GridColumnModel<Person>("address.street","address.street", new Model<String>("street"),200));
        
        if(!scoll) {
        	model.setRowNum(20);
        	model.setRowList(null);
        } else {
        	model.setScroll(20);
        }
        
        model.setPagerpos(HorizontalPosition.left);
        
        context = new WebMarkupContainer("context");
        context.setOutputMarkupId(true);
       
        add(context);
        
        Grid<Person> grid = new Grid<Person>("grid", model, new PersonsDataProvider(WicketApplication.getWicketApplication().getPersons()));
        grid.addEvent(new OnSelectRowAjaxEvent<Person>() {
        	
			private static final long serialVersionUID = 1L;

			@Override
        	protected void onSelectRow(AjaxRequestTarget target, int row, IModel<Person> rowModel, boolean status) {
        		System.out.println(rowModel.getObject().getName() + "selected="+status);
        	}
        	
        });
        
        grid.addEvent(new OnGridCompleteAjaxEvent<Person>() {
        	
			private static final long serialVersionUID = 1L;

			@Override
			protected void onGridComplete(AjaxRequestTarget target,Grid<Person> grid) {
				System.out.println("Grid was just loaded!");
			}
        	
        });
        grid.addEvent(new OndblClickRowAjaxEvent<Person>() {
        	
			private static final long serialVersionUID = 1L;

			@Override
			protected void ondblClickRow(AjaxRequestTarget target, int row, int col, IModel<Person> rowModel) {
				System.out.println(rowModel.getObject().getName() + "col="+col + " row="+row);
			}
        });
        
        grid.addEvent(new OnRightClickRowAjaxEvent<Person>() {
        	
			private static final long serialVersionUID = 1L;
			
			@Override
			protected void onRightClickRow(AjaxRequestTarget target,
					int row, int col, IModel<Person> rowModel) {
				System.out.println(rowModel.getObject().getName() + ": right click! col="+col + " row="+row);
			}

        });
        
        grid.addEvent(new OnHeaderClickAjaxEvent<Person>() {
        	
			private static final long serialVersionUID = 1L;

			@Override
			protected void onHeaderClick(
					AjaxRequestTarget target,
					Grid<Person> grid,
					com.wiquery.plugins.jqgrid.component.event.OnHeaderClickAjaxEvent.GridState gridState) {
				System.out.println("gridState=" + gridState);
			}
			
        });
        
        grid.addEvent(new OnSortColAjaxEvent<Person>() {
        	
			private static final long serialVersionUID = 1L;

			@Override
			protected void onSortCol(AjaxRequestTarget target,
					Grid<Person> grid, IColumn<Person> column, int col,
					String sortProperty, SortOrder order) {
				System.out.println("column=" + column.getPropertyPath()+",col="+col+",order="+order);
			}										
        });
        
        grid.addEvent(new OnPagingAjaxEvent<Person>() {
        	
			private static final long serialVersionUID = 1L;

			@Override
			protected void onPaging(
					AjaxRequestTarget target,
					Grid<Person> grid,
					PageButton button) {
				System.out.println(button.name() + " was clicked!");
			}
			
												
        });
        
        grid.addEvent(new OnResizeStopAjaxEvent<Person>() {
        	
			private static final long serialVersionUID = 1L;

			@Override
			protected void resizeStop(AjaxRequestTarget target,
					Grid<Person> grid, IColumn<Person> column,
					int newwidth) {
				System.out.println(column.getPropertyPath() + ","+newwidth);
			}
			
												
        });
        context.add(grid);
	}

	public boolean isScoll() {
		return scoll;
	}

	public void setScoll(boolean scoll) {
		this.scoll = scoll;
	}

}
