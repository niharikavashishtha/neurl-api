## NEURL API Shortening service

NEURL is a scalable URL Shortening service. It provides clean RESTFul APIs to create short URL which are 6 character long Base62 string.

It can support upto 62^6 = 58 Billion different URLs.

It can be scaled up to multiple instances running on docker container
The Docker compose also spins up the nginx proxy to load balance the instances
using round robin scheduling.

![Flow](Images/neurlApiFlow.png)

## How Build

* for maven build run

```
$ mvn clean install
```

* For Code coverage

```
$ mvn clean site

```


## How to Run using docker and scale up with 3 API instances

```
$ git clone https://github.com/niharikavashishtha/neurl-api.git
$ cd neurl-api
$ docker-compose up --build --scale neurl-api=3

```

## With In memory DB H2
```
$ mvn clean install
$ cd target/

$ java -jar -Dspring.profiles.active=h2 neurl-api-0.0.1-SNAPSHOT.jar

```

## How to test
I have added [PostMan collection](./POSTMAN.json) to test the APIs

## Things to be done

### Cleaning up old URLs
Old URL entries in the DB are not cleaned up, over the time it will increase DB size
We can use spring scheduler (@EnableScheduling) to run a job every day at mid night to check all entries
which are older than say 5 years and clean them up.

### Caching
We have used default spring cache which is in-memory ( ConcurrentHashMap)
But to optimize the performance we can use distributed cache like Redis.
  