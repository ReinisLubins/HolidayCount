# Holiday Count Application

### This is spring web application is for counting holidays in given year or year interval

### How to run project

* Application works on - http://localhost:8080/
* To start the project: Open CMD, select folder of this project and type -  .\mvnw spring-boot:run

#### /myperfectapp/holidaycount/{countryCode}/{yearInterval} - get endpoint which accepts two parameters (countryCode, yearInterval) and returns holidays count. Example:
```
Endpoint - http://localhost:8080/myperfectapp/holidaycount/LV/1990-1995
Return value (Holidays count) - 84
```
### Additional features

#### Swagger console - http://localhost:8080/swagger-ui/index.html#/
```
<dependency>
    <groupId>org.springdoc</groupId>
    <artifactId>springdoc-openapi-ui</artifactId>
    <version>1.6.8</version>
</dependency>
```

### Possible improvements

* Creating additional unit tests

