package com.thomas.datastore.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;



/**
 * @author thomasphan
 * 
 * Header of each entry with a limit record
 * 
 *
 */
public class DocumentEntryHead {
	
	//Name of entry file
	private String entryName;
	//Maximum records allowed in this entry file 
	@JsonIgnore
	private static final int maxCapacity =10000;
	
	// number of records in this file
	private int numRecords;

	public String getEntryName() {
		return entryName;
	}

	public void setEntryName(String entryName) {
		this.entryName = entryName;
	}

	

	public int getNumRecords() {
		return numRecords;
	}

	public void setNumRecords(int numRecords) {
		this.numRecords = numRecords;
	}
    
	@JsonIgnore
	public int getMaxCapacity() {
		return maxCapacity;
	}
	
	@JsonIgnore
	public boolean isInsertable() {
		return ((numRecords - maxCapacity) >= 0) ? false : true;
		
	}


	public DocumentEntryHead(String entryName) {
		super();
		this.entryName = entryName;
		this.numRecords = 0;
	}

	public DocumentEntryHead() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	

}
