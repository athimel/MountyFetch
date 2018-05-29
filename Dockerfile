# fetch basic image
FROM maven:3-jdk-8

# application placed into /opt/app
RUN mkdir -p /opt/app

# install project
COPY pom.xml /opt/app/
COPY parser /opt/app/parser
COPY rest /opt/app/rest

# build
WORKDIR /opt/app
RUN mvn package && rm -rf ~/.m2

# local application port
EXPOSE 8080

# execute it
CMD ["java", "-jar", "rest/target/mounty-fetch-rest-jar-with-dependencies.jar"]
