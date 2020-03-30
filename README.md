# Simple Data Store


## Getting Started

A simple data store json. 

```
The structure of datastore : 
 - Data format Json 

A datastore is composed by :
 1/ A json file index = NameDataStoreIndex.json. 
 2/ N files dataEntry, each dataEntry has a limit capacity of storage of record.

 DataStore index file : this file contains the information related to data entry and record
   - name of latest/active data entry
   - a map with key is a hascode of each record and value is the name of data entry where the record is store
 
 DataStore entry :  
   - Header contains name of entry and number of records in this entry
   - Records - data 

DataStore operations: 
  - When connected only 2 files :
      - Index file and 
      - latest entry 
    are loaded in memory. This helps  to avoid loading whole datastore in memory.

  - Insert a new record
     - look up this record in data index first use hashcode of this record
     - key exist ==> locate data entry then replace this record
     - key non exist ==> insert this record in current/latest data entry
     - when data entry is full a new one is created.

  - Select by criteria
     - load each data entry and select the records that meet the criteria

  - Order records by field
     - use comparator on each field of record
     - use chain comparators on multi feilds
 -  Filter 
     - use stream function to acheive the operation with predicate

 -  Group and agregate functions 
    - use stream java function to archive these function

    
```

### Prerequisites

- Java 8+

- Maven for installation

```

```

### Build + Installing
```
- git clone https://github.com/tqcphan/simpleDataStore.git

- mvn install

A "datastore-0.0.1-SNAPSHOT.jar" will be created after " mvn install " in ./target and .m2 maven repository


```

#### Create mock data
```
Create a  a mock data "flat file" csv with '|' delimiter

Example  :

STB|TITLE|PROVIDER|DATE|REV|VIEW_TIME
stb1|the matrix|warner bros|2014-04-01|4.00|1:30
stb1|unbreakable|buena vista|2014-04-03|6.00|2:05
stb2|the hobbit|warner bros|2014-04-02|8.00|2:45
stb3|the matrix|warner bros|2014-04-02|4.00|1:05


```

## Running the tests


```
 -> java -jar datastore-0.0.1-SNAPSHOT.jar import  <- enter for help 


import new file : 

-> java -jar datastore-0.0.1-SNAPSHOT.jar import -f /pathFile/MocksData.csv -d ComscoreTestDataSto -o n

Query :

-> java -jar datastore-0.0.1-SNAPSHOT.jar query -d ComscoreTestDataStore -s title,date,stb -o date,stb -f stb=stb1
the matrix|2014-04-01|stb1|
unbreakable|2014-04-03|stb1|

```




## Deployment

```
NA

## Built With

* [Maven](https://maven.apache.org/) - Dependency Management
 

## Contributing


## Versioning



## Authors

* **Thomas Phan** - *Initial work*


## License

NA

## Acknowledgments

* 
* 
* 
