package com.thomas.datastore.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.Map;
import java.util.UUID;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thomas.datastore.domain.DocumentEntryHead;
import com.thomas.datastore.domain.DataStoreIndex;
import com.thomas.datastore.domain.DocumentEntry;
import com.thomas.datastore.domain.MediaRecord;
import com.thomas.datastore.exception.DataStoreException;


/**
 * @author thomasphan
 * 
 * DataStore tool to use
 *
 */
public class DataStoreTool {
	private static ObjectMapper mapper = new ObjectMapper();
	
	
	public static DataStoreIndex createDataStore (String dataStoreName) {
				
		//Generate first entry data 
		int entry = 0;
		String dataStoreEntryName = dataStoreName + "_" + entry + ".json";
		
		// Init firt dataStoreEntry
		DocumentEntryHead dataStoreEntry = new DocumentEntryHead(dataStoreEntryName);
		
		// Init DataStore Index
		DataStoreIndex  dataStoreIndex = new DataStoreIndex(dataStoreEntryName);
		
		String dataStoreNameIndex = dataStoreName+"Index.json";
		String dataStoreAbsIndex = getAbsoluteFilePath(dataStoreNameIndex);
		
		// Create file Index
		
		try {
			writePojoToDataIndex(dataStoreAbsIndex, dataStoreIndex);
		} catch (JsonProcessingException e1) {
			 throw new DataStoreException (" Error on Json processing" );
				//e.printStackTrace();
		} catch (IOException e1) {
			 throw new DataStoreException (" Error on writing Json" );
				//e.printStackTrace();
		}
		
		return dataStoreIndex;
		
	}
	
	public static DataStoreIndex getDataStore(String dataStoreName) {
		
		//
		String dataStoreNameIndex = dataStoreName+"Index.json";
		String dataStoreAbsIndex = getAbsoluteFilePath(dataStoreNameIndex);
		
		TypeReference<DataStoreIndex> ref = new TypeReference<DataStoreIndex>() { };
		
		DataStoreIndex dataStoreIndex= readJsonFromFile(dataStoreAbsIndex,ref);
		
		return dataStoreIndex;
			
	}
	
	

	
	public  static <R> DocumentEntry<R> creatDataEntry(String dataStoreName, Map<String, R> data ) {
		

		String entry = UUID.randomUUID().toString();
		String dataStoreEntryName = dataStoreName + "_" + entry + ".json";

		DocumentEntryHead documentEntryHead = new DocumentEntryHead(dataStoreEntryName);
		
		DocumentEntry<R> docEntry = new DocumentEntry<R>();
		docEntry.setDocumentEntryHead(documentEntryHead);
		
		data.forEach((k,v)-> {
			docEntry.addOneRecord(k, v);}
		);
		
		return docEntry;

			
	}
	


	// Return absolute file path from a file name, using working directory as user.dir
	
   public static String getAbsoluteFilePath (String fileName) {
	   String absoluteFilePath ="";
	   
	   String workingDirectory = System.getProperty("user.dir");
	   
	   absoluteFilePath = workingDirectory + File.separator + fileName;
	   
	   return absoluteFilePath;
	   
   }


  
	public static <V> void writePojoToDataIndex (String filePath, V records) throws JsonProcessingException, IOException {
	    // convert map to JSON file
	    mapper.writerWithDefaultPrettyPrinter().writeValue(Paths.get(filePath).toFile(), records);
	}
	
	
	public static <K> void writeDocumentToDataEnry (String entryName, K documentEntry) throws JsonProcessingException, IOException {
	    // convert map to JSON file
		String entryPath = getAbsoluteFilePath (entryName);
	    mapper.writerWithDefaultPrettyPrinter().writeValue(Paths.get(entryPath).toFile(), documentEntry);
	}
	

	
	public static <T> T readJsonFromFile(String filePath, TypeReference<T> typeReference) {
		
		InputStream input =null;
		T data = null;
		try {
			input = new FileInputStream(filePath);
			
			data = mapper.readValue(input, typeReference);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return data;

	}
   
 
}
