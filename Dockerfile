FROM openjdk:8u242-slim
ADD target/flight-management-1.0.jar .
EXPOSE 7070
CMD java -jar flight-management-1.0.jar