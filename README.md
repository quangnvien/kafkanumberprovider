# Kafka Provider
A simple demonstration of Kafka messaging system.

## Description
A number provider that randomly generates positive integers under 1000 and send to Kafka cluster.

## Usage
- Start Zookeeper
```bash
bin\zookeeper-server-start.sh config\zookeeper.properties
```
- Start Kafka Brokers
```bash
bin\kafka-server-start.sh config\server{i}.properties
```
- Start Kafka Consumer
```bash
java -jar kafkanumberprovider.jar 
```

## Dependencies
- Java 12
- Gradle 5.3.1
- Spring Boot 2.1.4
- Spring Kafka 2.2.5 
- Apache Kafka 2.1.0
- Eclipse IDE 2019-03

## License
[MIT](https://choosealicense.com/licenses/mit/)
