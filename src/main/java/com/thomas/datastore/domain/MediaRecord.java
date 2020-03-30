package com.thomas.datastore.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;


/**
 * @author thomasphan
 * POJO represent a line in csv file
 * Use in Jackson ObjectMapper to process read/write jsonfile (simple datastore)  
 *
 */
public class MediaRecord {
	private String stb;
	private String title;
	
	@JsonFormat(
		      shape = JsonFormat.Shape.STRING,
		      pattern = "yyyy-MM-dd",
		      timezone = "PST")
	
	//@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd@HH:mm:ss.SSSZ")
	private Date mDate;
	
	//The price incurred by the STB to lease the asset (Price in US dollars and cents)
	private Double rev; 
	
	//The amount of time the STB played the asset.  (Time in hours:minutes)
	private String viewTime; 
	
	private String provider;
	
	public MediaRecord(String stb, String title, Date mDate, Double rev, String viewTime, String provider) {
		super();
		this.stb = stb;
		this.title = title;
		this.mDate = mDate;
		this.rev = rev;
		this.viewTime = viewTime;
		this.provider = provider;
	}
	
	

	public MediaRecord() {
		super();
		// TODO Auto-generated constructor stub
	}


	@JsonProperty("STB")
	public String getStb() {
		return stb;
	}
	@JsonProperty("STB")
	public void setStb(String stb) {
		this.stb = stb;
	}


	@JsonProperty("TITLE")
	public String getTitle() {
		return title;
	}
	@JsonProperty("TITLE")
	public void setTitle(String title) {
		this.title = title;
	}
	@JsonProperty("DATE")
	public Date getmDate() {
		return mDate;
	}

	@JsonProperty("DATE")
	public void setmDate(Date mDate) {
		this.mDate = mDate;
	}
	@JsonProperty("REV")
	public Double getRev() {
		return rev;
	}
	@JsonProperty("REV")
	public void setRev(Double rev) {
		this.rev = rev;
	}
	@JsonProperty("VIEW_TIME")
	public String getViewTime() {
		return viewTime;
	}
	@JsonProperty("VIEW_TIME")
	public void setViewTime(String viewTime) {
		this.viewTime = viewTime;
	}
	@JsonProperty("PROVIDER")
	public String getProvider() {
		return provider;
	}
	@JsonProperty("PROVIDER")
	public void setProvider(String provider) {
		this.provider = provider;
	}
	
	
	
	

}
