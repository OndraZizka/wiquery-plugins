package com.wiquery.plugin.antilia.grid.navigation2;

import java.io.Serializable;

/**
 * Helper bean used to generate navigation messages.
 * 
 * @author Ernesto Reinaldo Barreiro
 *
 */
public class NavigationBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private int currentPage = 0;
	
	private int numberOfRecords = 0;
	
	private int recordsPerPage = 0;
	
	
	/**
	 * Constructor.
	 */
	public NavigationBean() {
	}


	public int getCurrentPage() {
		return currentPage;
	}


	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}


	public int getNumberOfRecords() {
		return numberOfRecords;
	}


	public void setNumberOfRecords(int numberOfRecords) {
		this.numberOfRecords = numberOfRecords;
	}


	public int getRecordsPerPage() {
		return recordsPerPage;
	}


	public void setRecordsPerPage(int recordsPerPage) {
		this.recordsPerPage = recordsPerPage;
	}
	
	
	public int getFirstShownRecord() {
		return (recordsPerPage*currentPage)+1;
	}
	
	public int getLastShownRecord() {
		return Math.min((recordsPerPage*currentPage)+recordsPerPage, numberOfRecords);
	}

}
