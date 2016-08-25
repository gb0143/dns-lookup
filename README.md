To run this applicaiton:

```
mvn clean install && java -jar target/*.jar
```

In order to connect to a remote database, uncomment the following lines with the correct information:
```
spring.data.mongodb.uri=mongodb://<HOSTNAME>:<PORT>/<databasename>
spring.data.mongodb.username=<USERNAME>
spring.data.mongodb.password=<PASSWORD>
```
and set the correct parameters.

This line can be found in the `application.properties` file.

Pre-requsites:
* Mongodb must be installed and running on the local machine
* Maven must be installed
* Java must be installed