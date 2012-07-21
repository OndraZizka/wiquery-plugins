/**
 * 
 */
package com.wiquery.plugins.jqgrid.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

/**
 * Class to configure the jqgrid.
 * 
 * //TODO: Reshape this class using jquery's option class. That class has to be 
 *   modified to accept instances of IModels and call detach after each 
 *   request cycle. 
 * 
 * @author Ernesto Reinaldo Barreiro (reirn70@gmail.com)
 * @author Nathan Messer
 *
 */
public class GridModel<B extends Serializable> extends Model<String> implements IModel<String>  {

	private static final long serialVersionUID = 1L;
		
	/**
	 * The column models.
	 */
	private List<IColumn<B>> columnModels = new ArrayList<IColumn<B>>();	
	
	/**
	 *   viewrecords defines the view the total records from the query in the pager
	 *   bar. The related tag is: records in xml or json definitions.
	 */
	private boolean viewrecords = false;
	
 	/**
	*  Defines the type of request to make, it's values are usually "GET" or "POST".
	*/
	private String mType;
		
	/**
	 * If set to true, an additional column is added on the left side of the grid. 
	 * This adds 28px to the grid's width. When the grid is constructed the content of 
	 * this column is filled with a check box element. When we select a row the check 
	 * box's state becomes checked (unless multiboxonly has been set to true, the row can 
	 * be clicked anywhere on the row, not just in the checkbox). When we select another row the 
	 * previous row does not change its state. When we click on a row that is selected, the state 
	 * becomes unchecked and the row is unselected. (If onRightClickRow has been defined, then 
	 * right-clicking a row does not select the row). Default value false;
	 * 
	 */
	private boolean multiselect = false;
	
	/**
	 * If multiboxonly is set to true, then a row is selected only when the checkbox is clicked (Yahoo style). 
	 * Default value <code>false</code>;
	 */
	private boolean multiboxonly = false;
	
	/**
	 * When we want selection to occur only when the user holds down a specific key 
	 * (when clicking), we define that key here. The possible values are: 
	 * 'shiftKey', 'altKey', and 'ctrlKey'. For example,
	 * multikey: 'altKey' will ensure that multiselection occurs 
	 * only when the user holds down the "Alt" key. 	
	 * 
	 * Default value <code>empty</code>.
	 */
	private String multikey;
	
	/**
	 * The caption show by the grid (or the key in a resource bundle).
	 */
	private IModel<String> caption;
	
	/**
	* Show the caption or not
	*/
	private boolean captionVisible = true;

	
	/**
	* Creates dynamic scrolling grids. 
	* When enabled, the pager elements are disabled and we can use the 
	* vertical scrollbar to load data. 
	* 
	* This option currently should be used carefully on big data sets, 
	* since I have not developed an intelligent swapper, 
	* which means that all the data is loaded and a lot of memory 
	* will be used if the dataset is large. 
	* 
	* You must be sure to have a initial vertical 
	* scroll in grid, i.e. the height should not be set to auto.
	*/
	private Integer scroll;

	
	/**
	 * Set a zebra-striped grid
	 */
	private boolean alternateRows = true;
	
	/**
	 * Constant useful to say the grid will adjust its size to fit exactly the rows it shows.
	 * 
	 */
	public static final String AUTO_HIGHT = "100%";
	
	/**
	 * The height of the grid
	 */
	private String height = null;
	
	/**
	 * The width of the table (only in pixels).
	 */
	private Integer width = null;
	
	/**
	 * See http://www.secondpersonplural.ca/jqgriddocs/index.htm
	 */
	private boolean shrinkToFit = true;
	
	/**
	 * When autowidth is set to true the grid fits to the width of the parent container.
	 */
	private boolean autowidth = false;
	
	/**
	 * The option rownumbers add additional column which count the rows 
	 */
	private boolean rownumbers = false;
	
	/**
	 * You can control the width of the show row numbers columns by settings the 
	 * rownumWidth property (default is 25).
	 */
	private int rownumWidth = 25;
	
	/** 
	 * Hides the grid on start up
	 */
	private boolean hiddengrid = false;
	
	/**
	 * Enables or disables the show/hide grid button, 
	 * which appears on the right side of the Caption layer. 
	 * Takes effect only if the caption property is not an empty string. 
	 */
	private boolean hidegrid = true;
	
	/**
	 * Flag to enable column sortability.
	 */
	private boolean sortable = true;
	
	/**
	 * Property related to grid scroll loading???
	 */
	private int rowNum = -1;
	
	/**
	 * Transfer protocol used to communicate with action.
	 * 
	 * @author Ernesto Reinaldo Barreiro (reirn70@gmail.com)
	 *
	 */
	public static enum TransferProtocol {
		xml,
		json
	}
	
	/**
	 * Flag used to set the transfer protocol used to communicate with the server.
	 */
	private TransferProtocol transferProtocol = TransferProtocol.xml;
	
	/**
	 * Sets the sorting order. Default is asc. This is the sorting order used to sort the
	 * initial sorted column. 
	 */
	private SortOrder sortOrder = SortOrder.asc;
	
	/**
	 * The page sizes that are displayed on the corresponding select at the navigation bar.
	 * Setting it to <code>null</code> will disable the select.
	 */
	private int[] rowList = {10,20,30};
	
	
	/**
	 * Assigns a class to columns that are resizable so that we can show a resize handle only for ones that are resizable.
	 * 
	 */
	private String resizeclass;

	/**
	 * Used with the Navigator.
	 */
    private boolean isEditEnabled;

    /**
     * Used with the Navigator.
     */
    private boolean isAddEnabled;

    /**
     * Used with the Navigator.
     */
    private boolean isDeleteEnabled;

    /**
     * Used with the Navigator.
     */
    private boolean isSearchEnabled;

    /**
     * Used with the Navigator.
     */
    private boolean isViewEnabled;

    /**
     * Used with the Navigator.
     */
    private boolean isRefreshEnabled;

    public static enum HorizontalPosition {
		left, center, right;
	}
	
	/**
	 * Determines the position of the record information in the pager. Can be left, center, right
	 * Default right.
	 */
	private HorizontalPosition recordpos;
	
	
	/**
	 * Determines the position of the pager in the grid. By default the pager element when created is divided 
	 * in 3 parts (one part for pager, one part for navigator buttons and one part for record information)
	 * Default: center;
	 */
	private HorizontalPosition pagerpos;
	
	/**
	 * Determines if the input box, where the user can change the 
	 * number of requested page, should be available. The input box appear in the pager bar.
	 * 
	 */
	private boolean pginput = true;
	
	/**
	 * Constructor.
	 * @param beanClass
	 */
	public GridModel() {
		super();
	}
	
	/**
	 * Constructor.
	 * @deprecated
	 * @param beanClass The bean class.
	 */
	@Deprecated
	public GridModel(Class<B> beanClass) {
		super();
	}


	/**
	 * Adds a column model.
	 * @param model
	 * @return
	 */
	public GridModel<B> addColumnModel(IColumn<B> model) {
		columnModels.add(model);
		model.setGridModel(this);
		return this;
	}
	
	/**
	 * Removes a column model.
	 * @param model
	 * @return
	 */
	public GridModel<B> removeColumnModel(IColumn<B> model) {
		columnModels.remove(model);
		model.setGridModel(null);
		return this;
	}
	
	/**	  
	 * @return Returns  an Iterable over the column models.
	 */
	public Iterable<IColumn<B>> getColumnModels() {
		return this.columnModels;
	}
	
	public IColumn<B> getColumnModel(int index) {
		return this.columnModels.get(index);
	}
	
	/**
	 * 
	 * @return Returns the number of columns.
	 */
	public int getColumnCount() {
		return this.columnModels.size();
	}
	
	
	public IColumn<B> getInitialSort() {
		for(IColumn<B> columnModel: columnModels) {
			if(columnModel.isInitialSort())
				return columnModel;
		}
		if(!columnModels.isEmpty())
			return columnModels.get(0);
		return null;
	}

	/**
	 * @return the viewrecords
	 */
	public boolean isViewrecords() {
		return viewrecords;
	}

	/**
	 * @param viewrecords the viewrecords to set
	 */
	public void setViewrecords(boolean viewrecords) {
		this.viewrecords = viewrecords;
	}

	/**
	 * @return the caption
	 */
	public IModel<String> getCaption() {
		return caption;
	}

	/**
	 * @param caption the caption to set
	 */
	public void setCaption(String caption) {
		if(caption != null)
			this.caption = new Model<String>(caption);		
		else 
			this.caption = null;
	}

	/**
	 * @param caption the caption to set
	 */
	public void setCaption(IModel<String> caption) {
		this.caption = caption;
	}
	
	/**
	 * @return the transferProtocol
	 */
	public TransferProtocol getTransferProtocol() {
		return transferProtocol;
	}

	/**
	 * @param transferProtocol the transferProtocol to set
	 */
	public void setTransferProtocol(TransferProtocol transferProtocol) {
		this.transferProtocol = transferProtocol;
	}

	/**
	 * @return the sortOrder
	 */
	public SortOrder getSortOrder() {
		return sortOrder;
	}

	/**
	 * @param sortOrder the sortOrder to set
	 */
	public void setSortOrder(SortOrder sortOrder) {
		this.sortOrder = sortOrder;
	}

	/**
	 * @return the alternateRows
	 */
	public boolean isAlternateRows() {
		return alternateRows;
	}

	/**
	 * @param alternateRows the alternateRows to set
	 */
	public void setAlternateRows(boolean alternateRows) {
		this.alternateRows = alternateRows;
	}

	/**
	 * @return the height
	 */
	public String getHeight() {
		return height;
	}

	/**
	 * @param height the height to set
	 */
	public void setHeight(String height) {
		this.height = height;
	}

	/**
	 * @return the width
	 */
	public Integer getWidth() {
		return width;
	}

	/**
	 * @param width the width to set
	 */
	public void setWidth(Integer width) {
		this.width = width;
	}

	/**
	 * @return the shrinkToFit
	 */
	public boolean isShrinkToFit() {
		return shrinkToFit;
	}

	/**
	 * @param shrinkToFit the shrinkToFit to set
	 */
	public void setShrinkToFit(boolean shrinkToFit) {
		this.shrinkToFit = shrinkToFit;
	}

	/**
	 * @return the autowidth
	 */
	public boolean isAutowidth() {
		return autowidth;
	}

	/**
	 * @param autowidth the autowidth to set
	 */
	public void setAutowidth(boolean autowidth) {
		this.autowidth = autowidth;
	}

	/**
	 * @return the rownumbers
	 */
	public boolean isRownumbers() {
		return rownumbers;
	}

	/**
	 * @param rownumbers the rownumbers to set
	 */
	public void setRownumbers(boolean rownumbers) {
		this.rownumbers = rownumbers;
	}

	/**
	 * @return the hidegrid
	 */
	public boolean isHidegrid() {
		return hidegrid;
	}

	/**
	 * @param hidegrid the hidegrid to set
	 */
	public void setHidegrid(boolean hidegrid) {
		this.hidegrid = hidegrid;
	}

	/**
	 * @return the rowList
	 */
	public int[] getRowList() {
		return rowList;
	}

	/**
	 * @param rowList the rowList to set
	 */
	public void setRowList(int[] rowList) {
		this.rowList = rowList;
	}

	/**
	 * @return the resizeclass
	 */
	public String getResizeclass() {
		return resizeclass;
	}

	/**
	 * @param resizeclass the resizeclass to set
	 */
	public void setResizeclass(String resizeclass) {
		this.resizeclass = resizeclass;
	}

	/**
	 * @return the multiselect
	 */
	public boolean isMultiselect() {
		return multiselect;
	}

	/**
	 * @param multiselect the multiselect to set
	 */
	public void setMultiselect(boolean multiselect) {
		this.multiselect = multiselect;
	}

	/**
	 * @return the multikey
	 */
	public String getMultikey() {
		return multikey;
	}

	/**
	 * @param multikey the multikey to set
	 */
	public void setMultikey(String multikey) {
		this.multikey = multikey;
	}

	/**
	 * @return the multiboxonly
	 */
	public boolean isMultiboxonly() {
		return multiboxonly;
	}

	/**
	 * @param multiboxonly the multiboxonly to set
	 */
	public void setMultiboxonly(boolean multiboxonly) {
		this.multiboxonly = multiboxonly;
	}

	public boolean isSortable() {
		return sortable;
	}

	public void setSortable(boolean sortable) {
		this.sortable = sortable;
	}

	public HorizontalPosition getRecordpos() {
		return recordpos;
	}

	public void setRecordpos(HorizontalPosition recordpos) {
		this.recordpos = recordpos;
	}

	public boolean isPginput() {
		return pginput;
	}

	public void setPginput(boolean pginput) {
		this.pginput = pginput;
	}

	public HorizontalPosition getPagerpos() {
		return pagerpos;
	}

	public void setPagerpos(HorizontalPosition pagerpos) {
		this.pagerpos = pagerpos;
	}

	public int getRownumWidth() {
		return rownumWidth;
	}

	public void setRownumWidth(int rownumWidth) {
		this.rownumWidth = rownumWidth;
	}

	public int getRowNum() {
		return rowNum;
	}

	public void setRowNum(int rowNum) {
		this.rowNum = rowNum;
	}

	public String getMType() {
		return mType;
	}

	public void setMType(String type) {
		mType = type;
	}

	public boolean isCaptionVisible() {
		return captionVisible;
	}

	public void setCaptionVisible(boolean captionVisible) {
		this.captionVisible = captionVisible;
	}

	public Integer getScroll() {
		return scroll;
	}

	public void setScroll(Integer scroll) {
		this.scroll = scroll;
	}
	
	@Override
	public void detach() {
		super.detach();		
		if(caption != null) {
			caption.detach();
		}
	}

	public boolean isHiddengrid() {
		return hiddengrid;
	}

	public void setHiddengrid(boolean hiddengrid) {
		this.hiddengrid = hiddengrid;
	}

    public boolean isEditEnabled() {
        return isEditEnabled;
    }

    public void setEditEnabled( boolean isEditEnabled ) {
        this.isEditEnabled = isEditEnabled;
    }

    public boolean isAddEnabled() {
        return isAddEnabled;
    }

    public void setAddEnabled( boolean isAddEnabled ) {
        this.isAddEnabled = isAddEnabled;
    }

    public boolean isDeleteEnabled() {
        return isDeleteEnabled;
    }

    public void setDeleteEnabled( boolean isDeleteEnabled ) {
        this.isDeleteEnabled = isDeleteEnabled;
    }

    public boolean isSearchEnabled() {
        return isSearchEnabled;
    }

    public void setSearchEnabled( boolean isSearchEnabled ) {
        this.isSearchEnabled = isSearchEnabled;
    }

    public boolean isViewEnabled() {
        return isViewEnabled;
    }

    public void setViewEnabled( boolean isViewEnabled ) {
        this.isViewEnabled = isViewEnabled;
    }

    public boolean isRefreshEnabled() {
        return isRefreshEnabled;
    }

    public void setRefreshEnabled( boolean isRefreshEnabled ) {
        this.isRefreshEnabled = isRefreshEnabled;
    }
}
