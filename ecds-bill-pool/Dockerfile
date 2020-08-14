FROM centos

RUN yum install java-1.8.0-openjdk* -y

VOLUME /tmp

EXPOSE 8083

ENV LANG C.UTF-8

COPY provider/target/provider-1.0-SNAPSHOT.jar bill.jar

RUN bash -c "touch /bill.jar"

ENTRYPOINT ["java","-jar","bill.jar"]