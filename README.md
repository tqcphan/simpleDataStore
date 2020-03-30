# Simple Data Store


## Getting Started

A simple data store json. 



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
