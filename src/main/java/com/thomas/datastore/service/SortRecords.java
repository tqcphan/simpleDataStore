package com.thomas.datastore.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.thomas.datastore.domain.MediaRecord;
import com.thomas.datastore.tool.ChainRecordComparator;
import com.thomas.datastore.tool.RecordComparatorByDuration;

/**
 * @author thomasphan
 *
 */

public class SortRecords {
	
	//
	private Map<String, Comparator<MediaRecord>> mapComparators;

	public SortRecords() {
		super();
		this.mapComparators = new HashMap<String, Comparator<MediaRecord>>();
		//// Initialize map of comparators 
		
		this.mapComparators.put("STB", Comparator.comparing(MediaRecord::getStb));
		
		this.mapComparators.put("TITLE", Comparator.comparing(MediaRecord::getTitle));
		this.mapComparators.put("DATE", Comparator.comparing(MediaRecord::getmDate));
		this.mapComparators.put("REV", Comparator.comparing(MediaRecord::getRev));
		this.mapComparators.put("VIEW_TIME", new RecordComparatorByDuration());
		
		this.mapComparators.put("PROVIDER", Comparator.comparing(MediaRecord::getProvider));
		
	} 
	
	
	
	public List<MediaRecord> sortRecordMultipleFields (List<String> listFields, List<MediaRecord> data) {
		List<Comparator<MediaRecord>> listComp  = new ArrayList<Comparator<MediaRecord>>();
		listFields.forEach(field -> {
			if(mapComparators.containsKey(field.toUpperCase())) {
				listComp.add(mapComparators.get(field.toUpperCase()));
			}
				
		});
		
		Collections.sort(data, new ChainRecordComparator(listComp));
		
		return data;
		
	}
	

}
