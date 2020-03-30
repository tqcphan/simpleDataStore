package com.thomas.datastore.service;

import java.text.SimpleDateFormat;
import java.util.List;

import com.thomas.datastore.domain.MediaRecord;



/**
 * @author thomasphan
 * 
 * Use for select of query
 *
 */
public class PrintRecords {
	
	public static void printRecord (List<String> listFields, List<MediaRecord> records) {
		
		records.forEach(record -> {
			
			listFields.forEach(field ->{
				if("STB".equals(field.toUpperCase())) {
					System.out.print(record.getStb() + "|");
				}
				
				if("TITLE".equals(field.toUpperCase())) {
					System.out.print(record.getTitle() + "|");
				}
				
				if("PROVIDER".equals(field.toUpperCase())) {
					System.out.print(record.getProvider() + "|");
				}
				
				if("DATE".equals(field.toUpperCase())) {
					
					SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");					
					System.out.print(ft.format(record.getmDate()) + "|");
				}
				
				
				if("REV".equals(field.toUpperCase())) {
					System.out.print(record.getRev() + "|");
				}
				
				
				if("VIEW_TIME".equals(field.toUpperCase())) {
					System.out.print(record.getViewTime() + "|");
				}
			}
			);
			System.out.println();
		});
	}

}
