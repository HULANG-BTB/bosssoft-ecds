FROM openjdk:8-jre
ADD target/ecds-bill-invocing-1.0-SNAPSHOT.jar invocing.jar
EXPOSE 9123
ENTRYPOINT ["java","-jar","/invocing.jar"]