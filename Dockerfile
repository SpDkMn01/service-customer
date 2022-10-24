FROM openjdk:11
VOLUME /tmp
EXPOSE 8000
ADD ./target/Customer-0.0.1-SNAPSHOT.jar Customer.jar
ENTRYPOINT ["java","-jar","/Customer.jar"]