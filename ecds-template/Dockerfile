FROM openjdk:8-jre
VOLUME /tmp
ADD target/ecds-template-1.0-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]