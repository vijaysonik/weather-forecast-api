FROM openjdk:8
EXPOSE 8080
ADD target/weather-forecast.jar weather-forecast.jar
ENTRYPOINT ["java","-jar","/weather-forecast.jar"]