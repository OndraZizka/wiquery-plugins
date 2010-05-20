/**
 * 
 */
package com.wiquery.plugins.jqgrid.provider;

import java.io.PrintWriter;
import java.io.Serializable;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.wicket.extensions.markup.html.repeater.data.sort.ISortState;
import org.apache.wicket.extensions.markup.html.repeater.data.sort.ISortStateLocator;
import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.model.IModel;

import com.wiquery.plugins.jqgrid.model.GridModel;
import com.wiquery.plugins.jqgrid.model.IColumn;
import com.wiquery.plugins.jqgrid.model.SortInfo;
import com.wiquery.plugins.jqgrid.model.SortOrder;
import com.wiquery.plugins.jqgrid.util.ReflectionUtils;

/**
 * @author Ernesto Reinaldo Barreiro (reirn70@gmail.com)
 *
 */
public  class ProviderNavigator<B extends Serializable> implements Serializable {

	private static final long serialVersionUID = 1L;

	private IDataProvider<B> dataProvider;
	
	private GridModel<B> gridModel;
	
	//private IQuery<B> query;
	
	private B searchBean;
	
	private String[] searchFields;
	
	private SortInfo sortInfo;
	
	/**
	 * 
	 * @param dataProvider
	 * @param columnModel
	 */
	public ProviderNavigator(IDataProvider<B> dataProvider, GridModel<B> gridModel, B searchBean, String... searchFields) {
		this.dataProvider = dataProvider;
		this.gridModel = gridModel;
		this.searchBean = searchBean;		
		this.searchFields = searchFields;
	}
	
	
	/**
	 * 
	 * @param request
	 * @return
	 */
	public final void renderData(HttpServletRequest request, HttpServletResponse response) throws Exception {
		configureSort(request);
		if(this.gridModel.getTransferProtocol().equals(GridModel.TransferProtocol.xml))
			renderXML(request, response);
		else {
			renderJson(request, response);
		}
	}
	
	/**
	 * Give a chance to configure the provider.
	 * 
	 * @param dataProvider
	 */
	protected void configureSort(HttpServletRequest request) {
		SortOrder sortOrder = getSortOrder(request);
		String propertyPath = getSortColumn(request);
		if(propertyPath != null) {
			this.sortInfo = new SortInfo(propertyPath, sortOrder);
		} else {
			this.sortInfo = null;
		}
		/*
		getQuery().clearOrders();
		IOrder<B> order = Order.des(propertyPath);
		if(sortOrder.equals(SortOrder.asc)) {
			order = Order.asc(propertyPath);			
		}
		getQuery().addOrder(order);
		int rows = getNumberOfRows(ServletActionContext.getRequest());
		int page = getCurrentPage(ServletActionContext.getRequest());
		getQuery().setFirstResult(rows*(page-1));
		getQuery().setMaxResults(rows);
		*/
	}
	
	protected String renderJson(HttpServletRequest request, HttpServletResponse response) throws Exception {    	   
		response.setContentType("text/json-comment-filtered"); 
		PrintWriter writer = response.getWriter(); 
		int rows = getNumberOfRows(request);
		int page = getCurrentPage(request);
		long records = totalRecords(getSearchBean(), this.sortInfo, getSearchFields());
		int start = rows*(page-1);
		long totalPages = (records/rows)+1;
		writer.print("{\"page\":\"");
		writer.print(page);		
		writer.print("\",\"total\":\"");
		writer.print(totalPages);
		writer.print("\",\"records\":\"");
		writer.print(records);
		writer.print("\",");
		writer.print("\"rows\":[");
		int row = 1;
		Iterator<? extends B> it = getRows(start, rows, getSearchBean(), sortInfo, getSearchFields());
		while(it.hasNext()) {
			B bean = it.next();
			writer.print("{");
			writer.print("\"id\":\""+row);
			writer.print("\", \"cell\":[");
		    int column = 1;
		    Iterator<IColumn<B>> it1 = this.gridModel.getColumnModels().iterator();		    
		    while(it1.hasNext()) {
		    	IColumn<B> columnModel = it1.next();
		    	writer.print("\"");	
		    	//TODO: do escaping for JSON format.
		    	//if(columnModel.getCellRenderer() != null)
		    	//	writer.print(columnModel.getCellRenderer().renderCell(bean, columnModel.getPropertyPath(), column, row));		    		
		    	///else
		    	writer.print(createCellContent(row, column, columnModel.getPropertyPath(), bean));		    	
		    		
		    	writer.print("\"");
		    	if(it1.hasNext())
		    		writer.print(",");
		    }
		    writer.print("]}");
		    if(it.hasNext())
		    	writer.print(",");
		    row++;
		}						
		writer.print("]}"); 		
		writer.flush();		
		return null;
    }
	
	/**
	 * 
	 * @param request
	 * @return
	 */
	protected void renderXML(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/xml"); 
		PrintWriter writer = response.getWriter();
		int rows = getNumberOfRows(request);
		int page = getCurrentPage(request);
		long records = totalRecords(getSearchBean(),this.sortInfo, getSearchFields());
		int start = rows*(page-1);
		long totalPages = (records/rows)+1;
		writer.println("<?xml version='1.0' encoding='utf-8'?>");
		writer.println("<rows>");
		writer.print("<page>");
		writer.print(page);
		writer.println("</page>");
		writer.print("<total>");
		writer.print(totalPages);
		writer.println("</total>");
		writer.print("<records>");
		writer.print(records);;		
		writer.println("</records>");		
		int row = 1;
		Iterator<? extends B> it = getRows(start, rows, getSearchBean(), sortInfo, getSearchFields());
		while(it.hasNext()) {			
			B bean = it.next();
			IModel<B> model = dataProvider.model(bean);
			writer.print("<row id=\"");
			writer.print("row"+row);
			writer.println("\">");
		    int column = 1;
		    for(IColumn<B> columnModel: this.gridModel.getColumnModels()) {
		    	// warp contents in a CDATA.
		    	writer.append("<cell><![CDATA[");	
		    	writer.append(columnModel.renderCell(row,column,  model));
		    		
		    	writer.append("]]></cell>");			     
		    }
		    writer.println("</row>");
		    row++;
		}				
		writer.println("</rows>");
		this.dataProvider.detach(); 
		writer.flush();
	}
	
	protected String createCellContent(int row, int column, String propertyPath, B bean) throws NoSuchFieldException {
		//return propertyPath+"_"+column;
		Object object = ReflectionUtils.getPropertyValue(bean, propertyPath);
		if(object != null) {
			return object.toString();
		}
		return "";
	}	
	
	private Iterator<? extends B> getRows(int start, int rows, B searchBean, SortInfo sortInfo, String[] searchFields) {
		if(this.dataProvider instanceof ISortStateLocator) {
			ISortStateLocator locator = (ISortStateLocator)dataProvider;
			ISortState sortState = locator.getSortState();
			if(sortState != null) {
				int sortOrder = sortState.getPropertySortOrder(sortInfo.getProperty());
				if(sortOrder == ISortState.DESCENDING)
					sortState.setPropertySortOrder(sortInfo.getProperty(), ISortState.ASCENDING);
				else if(sortOrder == ISortState.ASCENDING)
					sortState.setPropertySortOrder(sortInfo.getProperty(), ISortState.DESCENDING);
				else 
					sortState.setPropertySortOrder(sortInfo.getProperty(), ISortState.ASCENDING);
			}
		}
		return this.dataProvider.iterator(start, rows);
	}
	
	private int totalRecords(B searchBean, SortInfo sortInfo, String[] searchFields) {
		if(this.dataProvider instanceof ISortStateLocator) {
			ISortStateLocator locator = (ISortStateLocator)dataProvider;
			ISortState sortState = locator.getSortState();
			if(sortState != null) {
				int sortOrder = sortState.getPropertySortOrder(sortInfo.getProperty());
				if(sortOrder == ISortState.DESCENDING)
					sortState.setPropertySortOrder(sortInfo.getProperty(), ISortState.ASCENDING);
				else if(sortOrder == ISortState.ASCENDING)
					sortState.setPropertySortOrder(sortInfo.getProperty(), ISortState.DESCENDING);
				else 
					sortState.setPropertySortOrder(sortInfo.getProperty(), ISortState.ASCENDING);
			}
		}
		return this.dataProvider.size();
	}
	
	private int getCurrentPage(HttpServletRequest request) {
		try {
			return Integer.parseInt(request.getParameter("page"));
		} catch (Exception e) {
			return 10;
		}
	}
	
	private int getNumberOfRows(HttpServletRequest request) {
		try {
			return Integer.parseInt(request.getParameter("rows"));
		} catch (Exception e) {
			return 10;
		}
	}
	
	
	private SortOrder getSortOrder(HttpServletRequest request) {
		try {
			return SortOrder.valueOf(request.getParameter("sord"));
		} catch (Exception e) {
			return SortOrder.asc;
		}
	}
	
	private String getSortColumn(HttpServletRequest request) {
		try {
			return request.getParameter("sidx");
		} catch (Exception e) {
			return null;
		}
	}


	public B getSearchBean() {
		return searchBean;
	}


	public void setSearchBean(B searchBean) {
		this.searchBean = searchBean;
	}


	public String[] getSearchFields() {
		return searchFields;
	}


	public void setSearchFields(String[] searchFields) {
		this.searchFields = searchFields;
	}		
}
