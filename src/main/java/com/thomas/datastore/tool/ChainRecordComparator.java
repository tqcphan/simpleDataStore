package com.thomas.datastore.tool;

import java.util.Comparator;
import java.util.List;

import com.thomas.datastore.domain.MediaRecord;



/**
 * @author thomasphan
 * 
 * Customize comparator for order 
 *
 */
public class ChainRecordComparator implements Comparator<MediaRecord> {
	
	private List<Comparator<MediaRecord>> listComparators; 
	
	@Override
	public int compare(MediaRecord mRec1, MediaRecord mRec2) {
		
		int result =0;
		for (Comparator <MediaRecord> comp : listComparators) {
			result = comp.compare(mRec1, mRec2); 
			if (result!=0) {
				return result;
			}			
		}
		
		return result;
	}
		
	
	public ChainRecordComparator(List<Comparator<MediaRecord>> listComparators) {
		super();
		this.listComparators = listComparators;
	}


	// Setter and Getter
	public List<Comparator<MediaRecord>> getListComparators() {
		return listComparators;
	}
	public void setListComparators(List<Comparator<MediaRecord>> listComparators) {
		this.listComparators = listComparators;
	}
	
	
	

}
