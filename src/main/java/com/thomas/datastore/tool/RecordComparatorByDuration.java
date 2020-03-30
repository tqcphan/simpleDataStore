package com.thomas.datastore.tool;

import java.time.Duration;
import java.util.Comparator;

import com.thomas.datastore.domain.MediaRecord;


/**
 * @author thomasphan
 * Customize duration as it is not stardard
 *
 */
public class RecordComparatorByDuration implements Comparator<MediaRecord> {
	
	@Override
	public int compare(MediaRecord medRec1, MediaRecord medRec2) {
		Duration dRec1 = TimeFormatting.durationToStandard(medRec1.getViewTime());
		Duration dRec2 = TimeFormatting.durationToStandard(medRec2.getViewTime());
		
		return dRec1.compareTo(dRec2);
	}
}
