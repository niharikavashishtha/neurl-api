## NEURL API Shortening service

NEURL is a scalable URL Shortening service. It provides clean RESTFul APIs to create short URL which are 6 character long Base62 string.

It can support upto 62^6 = 58 Billion different URLs.


## How Build

* for maven build run

```
$ mvn clean install
```

* For Code coverage

```
$ mvn clean site

```


## How to Run using docker

```
$ git clone https://github.com/niharikavashishtha/neurl-api.git
$ cd neurl-api
$ docker-compose up 

```

## With In memory DB H2
```
$ mvn clean install
$ cd target/

$ java -jar -Dspring.profiles.active=h2 neurl-api-0.0.1-SNAPSHOT.jar

```
