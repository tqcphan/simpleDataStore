package com.thomas.datastore.tool;

import java.time.Duration;

public class TimeFormatting {
	
	
	//this function to convert Duration string format (HH:MM) to Duration Object 
	// 1/ concert to standard format ISO 8601
	// 2/ parse to Duration object
	public static Duration durationToStandard (String iDuration) {
		String stdDuration = "PT" + iDuration.replace(':', 'H')+"M";
		Duration recordDuration = Duration.parse(stdDuration);
		
		return recordDuration; 
		
	}

}
