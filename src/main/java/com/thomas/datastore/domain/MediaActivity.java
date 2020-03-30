package com.thomas.datastore.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;




/**
 * @author thomasphan
 * POJO use for processing Jackson datastore (jsonfile)
 * This POJO is despricated, please see DocuemntEntry
 * 
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class MediaActivity implements Serializable{
	
	
	private static final long serialVersionUID = 1L;

	// Type of Media activity : leasing
	private DocumentEntryHead dataStoreEntry; 
	
	/**
	 * A map of mediaRecord, MAKey is composed by 3 fields : STB, TITLE and DATE.
	 *  MediaRecord : Pojo record of csv-line
	 */
	private Map <MAKey,MediaRecord> mediaRecords;

	

	

	public MediaActivity(DocumentEntryHead dataStoreEntry, Map<MAKey, MediaRecord> mediaRecords) {
		super();
		this.dataStoreEntry = dataStoreEntry;
		this.mediaRecords = mediaRecords;
	}



	public MediaActivity() {
		super();
	}



	public Map<MAKey, MediaRecord> getMediaRecords() {
		return mediaRecords;
	}

	public void setMediaRecords(Map<MAKey, MediaRecord> mediaRecords) {
		this.mediaRecords = mediaRecords;
	}

   
	
	
	
	public DocumentEntryHead getDataStoreEntry() {
		return dataStoreEntry;
	}



	public void setDataStoreEntry(DocumentEntryHead dataStoreEntry) {
		this.dataStoreEntry = dataStoreEntry;
	}



	// 
	/**
	 * @return list of MediaRecods from the map <MAKey, MediaRecord>
	 * 
	 */
	@JsonIgnore
	public List<MediaRecord> getListMediaRecod () {
		List<MediaRecord> listRecords = new ArrayList<MediaRecord>();
		mediaRecords.forEach((K,V) -> {
			listRecords.add(V);
		});
		return listRecords;
	}
	

}
