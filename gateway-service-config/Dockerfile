FROM openjdk:11
EXPOSE 8888
ADD /target/service-gateway.jar service-gateway.jar
ENTRYPOINT ["java", "-jar", "/service-gateway.jar"]