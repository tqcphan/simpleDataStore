package com.thomas.datastore.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;



/**
 * @author thomasphan
 * 
 * A file index of the datastore with a hasmap of each record and its data entry
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class DataStoreIndex implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// name of DataStoreEntry to write new record;
	private String currentDataStoreEntry;
	
	//number of entries of datastore
	private List<String> listDataEntries;
	
	// A Hashmap that allows to know if know if it exist already on database
	// if note write it to currentDataStoreEntry
	private Map<Integer, String> mapIndexRecord;

	
	
	public List<String> getListDataEntries() {
		return listDataEntries;
	}


	public DataStoreIndex(String currentDataStoreEntry, List<String> listDataEntries,
			Map<Integer, String> mapIndexRecord) {
		super();
		this.currentDataStoreEntry = currentDataStoreEntry;
		this.listDataEntries = listDataEntries;
		this.mapIndexRecord = mapIndexRecord;
	}

	public DataStoreIndex(String currentDataStoreEntry) {
		super();
		this.currentDataStoreEntry = currentDataStoreEntry;
		this.listDataEntries = new ArrayList<String>();
		this.listDataEntries.add(currentDataStoreEntry);
		this.mapIndexRecord = new HashMap<Integer, String>();
		
	}
	
	

	public DataStoreIndex() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getCurrentDataStoreEntry() {
		return currentDataStoreEntry;
	}

	public void setCurrentDataStoreEntry(String currentDataStoreEntry) {
		this.currentDataStoreEntry = currentDataStoreEntry;
	}

	public Map<Integer, String> getMapIndexRecord() {
		return mapIndexRecord;
	}

	public void setMapIndexRecord(Map<Integer, String> mapIndexRecord) {
		this.mapIndexRecord = mapIndexRecord;
	}
	
	public <R >void updateIndexWithNewDocEntry (DocumentEntry<R> docEntry) {
		String docEntryName = docEntry.getDocumentEntryHead().getEntryName();
		
		setCurrentDataStoreEntry(docEntryName);
		this.listDataEntries.add(docEntryName);
		
		
		docEntry.getData().forEach((k,v) -> {
			this.mapIndexRecord.put(k.hashCode(), docEntry.getDocumentEntryHead().getEntryName());
			
		});	
		
	}
	
	public <R> void updateIndexWithNewData (DocumentEntry<R> currentDoc, Map<String,R>  mapData) {
		mapData.forEach((k,v) -> {
			mapIndexRecord.put(k.hashCode(), currentDoc.getDocumentEntryHead().getEntryName());
		});
	}

}
