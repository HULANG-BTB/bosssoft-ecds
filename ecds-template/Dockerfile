FROM openjdk:8-jre
VOLUME /tmp
ADD target/ecds-template-1.0-SNAPSHOT.jar app.jar
ADD src/main/resources resources/
EXPOSE 8080
ENTRYPOINT ["java","-jar","-Xbootclasspath/a:./resources","/app.jar"]