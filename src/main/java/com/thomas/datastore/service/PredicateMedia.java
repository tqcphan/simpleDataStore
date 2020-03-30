package com.thomas.datastore.service;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

import com.thomas.datastore.domain.MediaRecord;



/**
 * @author thomasphan
 * 
 * Customize filter for query
 *
 */
public class PredicateMedia {
	
	private Map<String, Predicate<MediaRecord>> mapPredicate;

	private String value; 
	
	private String fieldFilter;
	public static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
	
	public PredicateMedia(String fieldFilter, String valueFilter) {
		super();
		this.mapPredicate = new HashMap<String, Predicate<MediaRecord>>();
		this.fieldFilter= fieldFilter;
		this.value = valueFilter;
		
		Predicate<MediaRecord> filterByStb = r -> r.getStb().equals(value);
		
		Predicate<MediaRecord> filterByTitle = r -> r.getTitle().equals(value);
		Predicate<MediaRecord> filterByDate = r -> simpleDateFormat.format(r.getmDate()).equals(value);
		Predicate<MediaRecord> filterByRev = r -> r.getRev().toString().equals(value);
		Predicate<MediaRecord> filterByViewTime = r -> r.getViewTime().equals(value);
		Predicate<MediaRecord> filterByProvider = r -> r.getProvider().equals(value);
		
		//// Initialize map of comparators 
		
		this.mapPredicate.put("STB", filterByStb);
		
		this.mapPredicate.put("TITLE", filterByTitle);
		this.mapPredicate.put("DATE", filterByDate);
		this.mapPredicate.put("REV", filterByRev);
		this.mapPredicate.put("VIEW_TIME", filterByViewTime);
		
		this.mapPredicate.put("PROVIDER", filterByProvider);
	
	}
		
		public Predicate<MediaRecord> getPredicate() {
			return this.mapPredicate.get(fieldFilter.toUpperCase());
		}
		
	} 

