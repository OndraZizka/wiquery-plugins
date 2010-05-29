/**
 * 
 */
package com.wiquery.plugins.jqgrid.model;

import java.io.Serializable;

import org.apache.wicket.extensions.markup.html.repeater.data.grid.ICellPopulator;
import org.apache.wicket.extensions.markup.html.repeater.data.grid.PropertyPopulator;
import org.apache.wicket.model.IModel;
import org.apache.wicket.util.lang.PropertyResolver;

/**
 * Allows to configure a jqrid column.
 * 
 * @author Ernesto Reinaldo Barreiro (reirn70@gmail.com)
 *
 */
public class GridColumnModel<B extends Serializable> implements  IColumn<B> {

	private static final long serialVersionUID = 1L;

	/**
	 * The parent GridModel.
	 */
	private GridModel<B> gridModel;
	
	/**
	 * The width of the column (in pixels),
	 */
	private int width = 100;
	
	/**
	 * The property path.
	 */
	private String propertyPath;
	
	/**
	 * The property path used by the column to retrieve a cell value.
	 */
	private String sortPath;
	
	/**
	 *  Flag to set column sortability. True by default. 
	 */
	private boolean sortable = true;
	
	/**
	 * Flag to set column resizability. True by default.
	 */
	private boolean resizable = true;
	
	
	
	private IModel<String> titleModel;
	
	/**
	 * Cell contents alignment.
	 */
	private Alignment alignment = Alignment.LEFT;
	
	/**
	 * If this column is used to initially sort the table.
	 */
	private boolean initialSort = false;
	
	/**
	 * 
	 */
	public GridColumnModel(String propertyPath, String sortName, IModel<String> titleModel, int width) {		
		this.propertyPath = propertyPath;
		this.sortPath = sortName;
		this.titleModel = titleModel;
		this.width = width;
	}

	/**
	 * @return the gridModel
	 */
	public GridModel<B> getGridModel() {
		return gridModel;
	}

	/**
	 * @param gridModel the gridModel to set
	 */
	public void setGridModel(GridModel<B> gridModel) {
		this.gridModel = gridModel;
	}

	/* (non-Javadoc)
	 * @see com.wijqgrid.model.IColumn#getWidth()
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * @param width the width to set
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/* (non-Javadoc)
	 * @see com.wijqgrid.model.IColumn#isSortable()
	 */
	public boolean isSortable() {
		return sortable;
	}

	/**
	 * @param sortable the sortable to set
	 */
	public void setSortable(boolean sortable) {
		this.sortable = sortable;
	}

	/* (non-Javadoc)
	 * @see com.wijqgrid.model.IColumn#getAlignment()
	 */
	public Alignment getAlignment() {
		return alignment;
	}

	/* (non-Javadoc)
	 * @see com.wijqgrid.model.IColumn#setAlignment(com.wijqgrid.model.GridColumnModel.Alignment)
	 */
	public void setAlignment(Alignment alignment) {
		this.alignment = alignment;
	}

	/* (non-Javadoc)
	 * @see com.wijqgrid.model.IColumn#isInitialSort()
	 */
	public boolean isInitialSort() {
		return initialSort;
	}

	/* (non-Javadoc)
	 * @see com.wijqgrid.model.IColumn#setInitialSort(boolean)
	 */
	public void setInitialSort(boolean initialSort) {
		this.initialSort = initialSort;
	}

	public IModel<String> getTitleModel() {
		return this.titleModel;
	}

	/* (non-Javadoc)
	 * @see com.wijqgrid.model.IColumn#isResizable()
	 */
	public boolean isResizable() {
		return resizable;
	}

	/* (non-Javadoc)
	 * @see com.wijqgrid.model.IColumn#setResizable(boolean)
	 */
	public void setResizable(boolean resizable) {
		this.resizable = resizable;
	}

	public String getPropertyPath() {
		return propertyPath;
	}

	public void setPropertyPath(String propertyPath) {
		this.propertyPath = propertyPath;
	}

	public String getSortPath() {
		return sortPath;
	}

	public void setSortName(String sortName) {
		this.sortPath = sortName;
	}

	public String renderCell(int row, int column, IModel<B> rowModel) {
		Object object = PropertyResolver.getValue(propertyPath, rowModel.getObject());
		if(object != null) {
			return object.toString();
		}
		return "";
	}
	
	@Override
	public ICellPopulator<B> getCellPopulator() {
		return new PropertyPopulator<B>(propertyPath);
	}

}
