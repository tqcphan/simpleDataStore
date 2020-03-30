package com.thomas.datastore;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.thomas.datastore.domain.ImportCommand;
import com.thomas.datastore.domain.MAKey;
import com.thomas.datastore.domain.MediaRecord;
import com.thomas.datastore.domain.QueryCommand;
import com.thomas.datastore.exception.DataStoreException;
import com.thomas.datastore.service.DataStore;
import com.thomas.datastore.service.DataStoreTool;
import com.thomas.datastore.service.ImportFlatFile;
import com.thomas.datastore.service.PredicateMedia;
import com.thomas.datastore.service.PrintRecords;
import com.thomas.datastore.service.SortRecords;

@SpringBootApplication
public class DatastoreApplication {

	public static void main(String[] args) {
		
		ImportCommand importCommand = new ImportCommand();
	     QueryCommand queryCommand = new QueryCommand();
		JCommander importCommander =  JCommander.newBuilder()
				.addCommand(importCommand)
				.addCommand(queryCommand).build();		
		
		try {
			
				importCommander.parse(args);	
				
				String dataCmd = importCommander.getParsedCommand();
				
					switch (dataCmd) {
					
				    case "import":
				        {

				        	
				        	importFlatFileDataStore (importCommand.filePath, importCommand.dataStoreName,importCommand.dataFlag );
				        	
				        	System.out.println("Import terminated ");
				        
				        break;
				        }
				        	 
				    case "query":
				    	
				    {   
				    	String orderBy = "UnSort";
				    	String filter = "NoFilter";
				    	if (queryCommand.orderBy!=null) orderBy = queryCommand.orderBy;
				    	if (queryCommand.filter!=null) filter = queryCommand.filter;
				    	queryDataStore (queryCommand.dataBaseName,queryCommand.selectField
				    			,orderBy, filter);
				    						    	
				    	System.out.println("---- End Query ----- ");
				    }
		
				    	
				    	
				        break;
				 
				    default:
				        System.err.println("Invalid command: " + dataCmd);
				        importCommander.usage();
				
					}
					
				
				}
		
		
		catch (ParameterException e) {
			  System.err.println(e.getLocalizedMessage());
			  importCommander.usage();
		}
	}
	
	
	
	public static void importFlatFileDataStore (String filePath, String dataStoreName, String option ) {
		
		// Get the file 
        File f = new File(filePath); 
        String dataIndex = DataStoreTool.getAbsoluteFilePath(dataStoreName+"Index.json");  
        File database = new File (dataIndex) ;
        
        boolean isNewDataStore = false ;
        
        System.out.println(database.exists());
        
        if (!f.exists())  { 
        	System.out.println("flat file doesn't exist"); 
        	System.exit(-1);
        }
        
        if (!database.exists())  { 
        	System.out.println("Database doesn't exist, a new database will be created "); 
        	isNewDataStore=true;     
        }
		
		char delimiter = '|';
		ImportFlatFile importCsv = new ImportFlatFile();
		
		if ("n".equals(option)) {
			isNewDataStore = true; 
		}
		
		try {
			Map<MAKey, MediaRecord> mapRecord = importCsv.importCsvMediaActivity(filePath, delimiter);
			
			
			DataStore<MediaRecord> dataStore = new DataStore<MediaRecord>(dataStoreName,isNewDataStore);
			
			mapRecord.forEach((k,v) -> {
				dataStore.insertOne(k.toString(), v);
				});
			
		} catch (JsonProcessingException e) {
		   throw new DataStoreException (" Error on Json Processing" );
			//e.printStackTrace();
		} catch (IOException e) {
			 throw new DataStoreException (" Error on IO file" );
		//	e.printStackTrace();
		}
		
	
		
	}
	
	
	
	public static void queryDataStore (String dataStoreName, String selection, String order, String filter ) {
		
		
		 String dataIndex = DataStoreTool.getAbsoluteFilePath(dataStoreName+"Index.json");  
	     File database = new File (dataIndex) ;
	     
	     if (!database.exists())  { 
	        	System.out.println("Database doesn't exist, please create it or check its name"); 
	        	System.exit(-1);    
	        }
	     
	   //Connecto to database 
		
		DataStore<MediaRecord> dataStore = new DataStore<MediaRecord>(dataStoreName,false);
		SortRecords sortRecords = new SortRecords();
		
		List<String> listPrintOrder = Arrays.asList(selection.split(","));
		List<String> listOrders = Arrays.asList(order.split(","));
			
		
		List<MediaRecord> recordList = dataStore.findMediaAll();
		
		if (!order.equals("UnSorted")) {
			recordList= sortRecords.sortRecordMultipleFields(listOrders, recordList);
		}
		if (!filter.equals("NoFilter")) {
			
			
			List<String> filterList = Arrays.asList(filter.split("="));
			PredicateMedia mediapredicate = new PredicateMedia(filterList.get(0), filterList.get(1));
			Predicate<MediaRecord> filterMedia = mediapredicate.getPredicate();
			
			recordList = recordList.stream().filter(filterMedia).collect(Collectors.toList());
			
			
		}
		
	
		PrintRecords.printRecord(listPrintOrder, recordList);
		
	}
	
	
	

}


