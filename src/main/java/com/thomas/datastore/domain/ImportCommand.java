package com.thomas.datastore.domain;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

@Parameters( commandNames = { "import" },
		commandDescription = "Import a flat file to dataStore ."
		+ "Syntax : import -f filePath -d datastoreName -o a")
public class ImportCommand {
	
	@Parameter(
			  names = { "--file", "-f" },
			  description = "absolute pathfile to import",
			  required = true
			)
	public String filePath;
	
	
	@Parameter(
			  names = { "--datastore", "-d" },
			  description = "name of datastore",
			  required = true
			)
	public String dataStoreName;
	
	
	
	@Parameter(
			  names = { "--option", "-o" },
			  description = "flag : 'n' for new database, 'a' for add ",
			  required = true
			)
	public String dataFlag;
	
}
