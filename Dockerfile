FROM openjdk:8
EXPOSE 8080
ADD target/weather-forecast-0.0.1-SNAPSHOT.jar weather-forecast.jar
ENTRYPOINT ["java","-jar","/weather-forecast.jar"]