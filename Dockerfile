FROM openjdk:17
COPY target/BIOL1330JAR.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/BIOL1330JAR.jar"]
