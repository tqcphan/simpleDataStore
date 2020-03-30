package com.thomas.datastore.domain;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;


/**
 * @author thomasphan
 * a wrapper 3 fields stb,title to form a unique key
 * when processing to json data, that becomes a String key = stb|title|date
 * Jackson use  
 *   - toString() to Serialize MAKey ==> json =  stb|title|date
 *   - Constructor (strKey) to Deserialize json to MAKey Object. 
 */
public class MAKey implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final  String stb;
	private final  String title;
	private final  Date mDate;
	
	
	
	public MAKey(String stb, String title, Date date) {
		
		super();
		this.stb = stb;
		this.title = title;
		this.mDate = date;
	}
	
	/**
	 *  Constructor for Deserialization json key to this MAKey
	 */
	@JsonCreator
	public MAKey(String strKey)
	   {
		String[] arrayKey = strKey.split("\\|"); 
		Date mDate = new Date();
		
		try {
		   mDate = new SimpleDateFormat("yyyy-MM-dd").parse(arrayKey[2]);
			} 
	       	catch (ParseException e) {
			
			e.printStackTrace();
	       	}
		
	       this.stb = arrayKey[0];
	       this.title = arrayKey[1];
	      
			this.mDate = mDate;
	       
	       
	   }

	public String getStb() {
		return stb;
	}

	public String getTitle() {
		return title;
	}

		

	public Date getmDate() {
		return mDate;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((mDate == null) ? 0 : mDate.hashCode());
		result = prime * result + ((stb == null) ? 0 : stb.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MAKey other = (MAKey) obj;
		if (mDate == null) {
			if (other.mDate != null)
				return false;
		} else if (!mDate.equals(other.mDate))
			return false;
		if (stb == null) {
			if (other.stb != null)
				return false;
		} else if (!stb.equals(other.stb))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}
	
	
	@Override
	@JsonValue
	public String toString() {		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String strMDate = dateFormat.format(this.mDate);
		return this.stb + "|" + this.title + "|" + strMDate; 
	}
	

}
