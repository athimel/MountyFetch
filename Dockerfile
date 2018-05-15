# fetch basic image
FROM java:8-jre

# install JAR file
COPY rest/target/rest-*-jar-with-dependencies.jar mounty-monsters-rest.jar

# local application port
EXPOSE 8080

# execute it
CMD ["java", "-jar", "mounty-monsters-rest.jar"]
