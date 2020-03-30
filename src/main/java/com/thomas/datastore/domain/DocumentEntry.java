package com.thomas.datastore.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import com.fasterxml.jackson.annotation.JsonProperty;




/**
 * @author thomasphan
 *
 * @param Document is an entry Json file of database
 */
public class DocumentEntry <R> implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private DocumentEntryHead documentEntryHead;
	
	@JsonProperty("data")
	private HashMap<String, R> data;

	public DocumentEntry() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DocumentEntryHead getDocumentEntryHead() {
		return documentEntryHead;
	}

	public void setDocumentEntryHead(DocumentEntryHead dataStoreEntry) {
		this.documentEntryHead = dataStoreEntry;
	}
	@JsonProperty("data")
	public HashMap<String, R> getData() {
		return data;
	}
	@JsonProperty("data")
	public void setData(HashMap<String, R> data) {
		this.data = data;
	}

	public DocumentEntry(DocumentEntryHead dataStoreEntry, HashMap<String, R> data) {
		super();
		this.documentEntryHead = dataStoreEntry;
		this.data = data;
	}
	
	public boolean addOneRecord (String key, R r) {
		boolean isOk = false ; 
		
		if (documentEntryHead.isInsertable()) {
			data.put(key, r);
			int numRecord = data.size();
			documentEntryHead.setNumRecords(numRecord);
			isOk = true; 
		}
		
		return isOk;
		
		
	}
	
		
	public List<R> find(Predicate<R> query) {				
		List<R> result = new ArrayList<R>(); 
        data.forEach((K,V) -> {
        	if (query.test(V))             
                result.add(V);});      
            
        return result; 
	}
	
	public List<MediaRecord> findMedia(Predicate<MediaRecord> query) {				
		List<MediaRecord> result = new ArrayList<MediaRecord>(); 
        data.forEach((K,V) -> {
        	if (query.test((MediaRecord) V))             
                result.add((MediaRecord) V);});      
            
        return result; 
	}
	
	
	public List<R> findAll() {				
		List<R> result = new ArrayList<R>(); 
        data.forEach((K,V) -> {        	          
                result.add(V);});                 
        return result; 
	}
	
	public List<MediaRecord> findMediaAll() {				
		List<MediaRecord> result = new ArrayList<MediaRecord>(); 
        data.forEach((K,V) -> {        	          
                result.add((MediaRecord) V);});                 
        return result; 
	}

}
