FROM openjdk:8u242-slim
ADD target/flight-management-1.0.jar .
EXPOSE 8080
CMD java -jar flight-management-1.0.jar