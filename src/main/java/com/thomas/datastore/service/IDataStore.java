package com.thomas.datastore.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.function.Predicate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.thomas.datastore.domain.MediaRecord;

public interface IDataStore <R> {
	
	
	
	
	public List<R> find(Predicate<R> query);
	
	public List<MediaRecord> findMedia(Predicate<MediaRecord> query);
	
	
	 public List<R> findAll();
	 
	 
	 public List<MediaRecord> findMediaAll();
	 
	 public boolean insertOne (String key, R r);
	 
	
	
	
	

}
