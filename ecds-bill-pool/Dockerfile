FROM openjdk8

VOLUME /tmp

EXPOSE 8083

ENV LANG C.UTF-8

COPY target/ecds-bill-pool-1.0-SNAPSHOT.jar bill_Source.jar

RUN bash -c "touch /bill_Source.jar"

ENTRYPOINT ["java","-jar","bill_Source.jar"]