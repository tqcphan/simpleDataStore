# Simple Data Store


## Getting Started


### Prerequisites

Java 8+
Maven for installation

```

```

### Installing

git clone https://github.com/tqcphan/simpleDataStore.git
mvn install


```
Create a  a mock data "flat file" csv with '|' delimiter


## Running the tests

import new file : 

java -jar datastore-0.0.1-SNAPSHOT.jar import -f /pathFile/MocksData.csv -d ComscoreTestDataSto -o n

Query :

java -jar datastore-0.0.1-SNAPSHOT.jar query -d ComscoreTestDataStore -s title,date,stb -o date,stb -f stb=stb1
the matrix|2014-04-01|stb1|
unbreakable|2014-04-03|stb1|


### Break down into end to end tests

Explain what these tests test and why

```
Give an example
```

### And coding style tests

Explain what these tests test and why

```
Give an example
```

## Deployment

Add additional notes about how to deploy this on a live system

## Built With

* 
* [Maven](https://maven.apache.org/) - Dependency Management
* 

## Contributing


## Versioning



## Authors

* **Thomas Phan** - *Initial work*

See also the list of [contributors](https://github.com/your/project/contributors) who participated in this project.

## License

None

## Acknowledgments

* 
* 
* 
