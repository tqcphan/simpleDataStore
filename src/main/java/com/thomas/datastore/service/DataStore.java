package com.thomas.datastore.service;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thomas.datastore.domain.DataStoreIndex;
import com.thomas.datastore.domain.DocumentEntry;
import com.thomas.datastore.domain.DocumentEntryHead;
import com.thomas.datastore.domain.MediaRecord;
import com.thomas.datastore.exception.DataStoreException;




/**
 * @author thomasphan
 *
 *  All datastore services is here 
 */
public class DataStore<R> implements IDataStore<R> {
	
	//dataEntry is a filename (with path) 
	private String dataStoreName;
	private static ObjectMapper mapper = new ObjectMapper();
	
	private DataStoreIndex dataStoreIndex;
	
	private DocumentEntry<R> currentDocument; 

	public DataStore(String dataStoreName, boolean isCreation) {
		super();		
		if (isCreation) {
		this.dataStoreName = dataStoreName;
		DataStoreIndex storeIndex= DataStoreTool.createDataStore(dataStoreName);
		DocumentEntryHead documentEntryHead = new DocumentEntryHead(storeIndex.getCurrentDataStoreEntry());
		
		DocumentEntry<R> initDocEntry = new DocumentEntry<R>(documentEntryHead, new HashMap<String,R>());
			
		this.dataStoreIndex = storeIndex;
		this.currentDocument = initDocEntry;
		}
		else {
			this.dataStoreName = dataStoreName;
			this.dataStoreIndex= DataStoreTool.getDataStore(dataStoreName);
			this.currentDocument =getCurrentDataEntry();
		}
	}
	
	
	
	public List<R> find(Predicate<R> query) {
		
		
		List<String> dataEntries = dataStoreIndex.getListDataEntries();
		List<R> resultR = new ArrayList<>();
		TypeReference<DocumentEntry<R>> ref = new TypeReference<DocumentEntry<R>>() { };
		
		dataEntries.forEach(entry -> {
			DocumentEntry<R> documentEntry = getDataEntryByName(entry, ref);
			
			
			resultR.addAll(documentEntry.find(query));
			
			
		});
		
		return resultR; 
		
	}
	
	public List<MediaRecord> findMedia(Predicate<MediaRecord> query) {
		List<String> dataEntries = dataStoreIndex.getListDataEntries();
		List<MediaRecord> resultR = new ArrayList<>();
		TypeReference<DocumentEntry<MediaRecord>> ref = new TypeReference<DocumentEntry<MediaRecord>>() { };
		
		dataEntries.forEach(entry -> {
			DocumentEntry<MediaRecord> documentEntry = getDataEntryMediaByName(entry, ref);
			
			
			resultR.addAll(documentEntry.findMedia(query));
			
			
		});
		
		return resultR; 
	}
	
	
   public List<R> findAll() {
		
		
		List<String> dataEntries = dataStoreIndex.getListDataEntries();
		List<R> resultR = new ArrayList<>();
		TypeReference<DocumentEntry<R>> ref = new TypeReference<DocumentEntry<R>>() { };
		
		dataEntries.forEach(entry -> {
			DocumentEntry<R> documentEntry = getDataEntryByName(entry,ref);
						
			resultR.addAll(documentEntry.findAll());
			
			
		});
		
		return resultR; 
		
	}
   
   
   public List<MediaRecord> findMediaAll() {
		
		
 		List<String> dataEntries = dataStoreIndex.getListDataEntries();
 		List<MediaRecord> resultR = new ArrayList<>();
 		TypeReference<DocumentEntry<MediaRecord>> ref = new TypeReference<DocumentEntry<MediaRecord>>() { };
 		
 		dataEntries.forEach(entry -> {
 			DocumentEntry<MediaRecord> documentEntry = getDataEntryMediaByName(entry,ref);
 						
 			resultR.addAll(documentEntry.findMediaAll());
 			
 			
 		});
 		
 		return resultR; 
 		
 	}
   
   
   public boolean insertOne (String key, R r) {
	   
	   // if R exist, replace R 
	   boolean isOk = false ;
	   TypeReference<DocumentEntry<R>> ref = new TypeReference<DocumentEntry<R>>() { };
	   
	   if (dataStoreIndex.getMapIndexRecord().containsKey(key.hashCode())) {
		   
		   DocumentEntry<R> docEntry = getDataEntryByName(
				   (dataStoreIndex.getMapIndexRecord().get(key.hashCode())),ref);
		   
		   if (docEntry.addOneRecord(key, r)) {
			   updateRecordDataStore(docEntry);
			   isOk=true;
		   }
		   
				   
	   }
	   else { 
		   //if not insert R to this current Entry 
		   // update DataStoreIndex	
		   
		   if (currentDocument.getDocumentEntryHead().isInsertable()) {
			   
			   if (currentDocument.addOneRecord(key, r)) {		   
				   //update DataStoreIndex 
				   Map<String,R> mapData = new HashMap<String,R> ();
				   mapData.put(key,r);
				   dataStoreIndex.updateIndexWithNewData(currentDocument, mapData);	
				   addNewRecordDataStore();
			   }
			   
			   
		   }
		   else {
			   Map<String,R> mapData = new HashMap<String,R> ();
			   mapData.put(key,r);
			   DocumentEntry<R> docEntry = DataStoreTool.creatDataEntry(dataStoreName, mapData );
			   currentDocument = docEntry;
			   dataStoreIndex.updateIndexWithNewData(currentDocument, mapData);
			   
			   addNewRecordDataStore();

		   }
		   
		   
	   }
	   
	   return isOk; 
	
	   
   }
   
   
   private void addNewRecordDataStore () {
	   // update Data Index
	   try {
		    String dataStoreIndexPath = DataStoreTool.getAbsoluteFilePath(dataStoreName+"Index.json");
			DataStoreTool.writePojoToDataIndex(dataStoreIndexPath,dataStoreIndex);
			String documentEntryName = currentDocument.getDocumentEntryHead().getEntryName();
			DataStoreTool.writeDocumentToDataEnry(documentEntryName, currentDocument);
			
			
			
		} catch (JsonProcessingException e) {
			 throw new DataStoreException (" Error on Json processing" );
				//e.printStackTrace();
		} catch (IOException e) {
			 throw new DataStoreException (" Error on writing Json File" );
				//e.printStackTrace();
		}
	   // update CurrentDocument Entry
	   
   }
   
   private void updateRecordDataStore (DocumentEntry<R> docEntry) {

	   // update DocumentEntry related
	   // no change on DataStoreIndex
	   
	   try {
		DataStoreTool.writeDocumentToDataEnry(docEntry.getDocumentEntryHead().getEntryName(),docEntry );
	} catch (JsonProcessingException e) {
		 throw new DataStoreException (" Error on Json processing" );
		//e.printStackTrace();
	} catch (IOException e) {
	
		 throw new DataStoreException (" Error on writing Json File" );
			//e.printStackTrace();
	}
	   
	
}
   
   
   public DocumentEntry<R> getCurrentDataEntry() {
		
		
		
		DataStoreIndex dataStoreIndex= DataStoreTool.getDataStore(dataStoreName);
		String currentDataEntry = dataStoreIndex.getCurrentDataStoreEntry();
		String currentDataEntryPath= DataStoreTool.getAbsoluteFilePath(currentDataEntry);
		
		TypeReference<DocumentEntry<R>> ref = new TypeReference<DocumentEntry<R>>() { };
		
		DocumentEntry<R> documentEntry = readJsonFromFile(currentDataEntryPath,ref);
		
		return documentEntry;

			
	} 
   
   
	public  DocumentEntry<R> getDataEntryByName(String entryName, TypeReference<DocumentEntry<R>> ref) {
		
		
		String dataEntryPath= DataStoreTool.getAbsoluteFilePath(entryName);
		
		DocumentEntry<R> documentEntry = readJsonFromFile(dataEntryPath,ref);
		
		return documentEntry;

			
	}
	
	public  DocumentEntry<MediaRecord> getDataEntryMediaByName(String entryName, TypeReference<DocumentEntry<MediaRecord>> ref) {
		
		
		String dataEntryPath= DataStoreTool.getAbsoluteFilePath(entryName);
		
		DocumentEntry<MediaRecord> documentEntry = readJsonMediaFromFile(dataEntryPath,ref);
		
		return documentEntry;

			
	}
	
	
	
	
	public DocumentEntry<R> readJsonFromFile(String filePath, TypeReference<DocumentEntry<R>> typeReference) {
		
		InputStream input =null;
		DocumentEntry<R> data = null;
		try {
			input = new FileInputStream(filePath);
			
			data = mapper.readValue(input, typeReference);
		} catch (Exception e) {
			 throw new DataStoreException (" Error on Readding Json File" );
				//e.printStackTrace();
		}
		
		return data;

	}
	
  public DocumentEntry<MediaRecord> readJsonMediaFromFile(String filePath, TypeReference<DocumentEntry<MediaRecord>> typeReference) {
		
		InputStream input =null;
		DocumentEntry<MediaRecord> data = null;
		try {
			input = new FileInputStream(filePath);
			
			data = mapper.readValue(input, typeReference);
		} catch (Exception e) {
			 throw new DataStoreException (" Error on Readding Json File" );
			//e.printStackTrace();
		}
		
		return data;

	}


}
