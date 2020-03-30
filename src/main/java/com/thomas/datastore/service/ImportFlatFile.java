package com.thomas.datastore.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.thomas.datastore.domain.MAKey;
import com.thomas.datastore.domain.MediaRecord;



/**
 * @author thomasphan
 * 
 * Simple services import csv
 *
 */
public class ImportFlatFile {
	
		
	public ImportFlatFile() {
		super();
	}
	
			
	public Map<MAKey, MediaRecord> importCsvMediaActivity (String cPath, char delimiter)  throws JsonProcessingException, IOException {
				
		List<MediaRecord> mediaRecords;
		
		Map<MAKey, MediaRecord> records = new HashMap<MAKey, MediaRecord> ();
		mediaRecords = importCsvRecord(cPath, delimiter);
		
		mediaRecords.forEach(record -> {
			MAKey maKey = new MAKey(record.getStb(), record.getTitle(), record.getmDate());
			records.put(maKey, record);
		});
		
		
		return records;
		
	}
	
	public List<MediaRecord> importCsvRecord (String cPath, char delimiter ) throws JsonProcessingException, IOException  {
		List<MediaRecord> mediaRecords = new ArrayList<MediaRecord>();  		
		BufferedReader csvFile = new BufferedReader(new FileReader(cPath));
	    CsvMapper mapper = new CsvMapper();
	    CsvSchema schema = CsvSchema.emptySchema().withHeader().withColumnSeparator(delimiter);
	    MappingIterator<MediaRecord> iterator = mapper.
	    		readerFor(MediaRecord.class)
	            .with(schema)
	            .readValues(csvFile);
	    while (iterator.hasNext()) {
	        mediaRecords.add(iterator.next());
	    }
		
		return mediaRecords; 
		
	}
		
		

}
