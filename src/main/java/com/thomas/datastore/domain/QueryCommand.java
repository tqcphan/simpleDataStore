package com.thomas.datastore.domain;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

@Parameters( commandNames = { "query" },
commandDescription = "query on a dataStore ."
+ "Syntax : query -d databaseName -s field1, field2 -o field1 -f field1=toto")
public class QueryCommand {
	
	@Parameter(
			  names = { "--dataStore", "-d" },
			  description = "name of datastore ",
			  required = true
			)
	public String dataBaseName;
	
	@Parameter(
			  names = { "--select", "-s" },
			  description = "-s fiel1,field2",
			  required = true
			)
	public String selectField;
	
	@Parameter(
			  names = { "--order", "-o" },
			  description = "-o fiel1,field2",
			  required = false)
	public String orderBy; 
	
	
	@Parameter(
			  names = { "--filter", "-f" },
			  description = "-f field=tota",
			  required = false)
	public String filter; 
	

}
