FROM openjdk:11
#EXPOSE 8000
#RUN mkdir /usr/app/

#COPY build/lib/* /usr/app
COPY target/jma-trailers-*.jar /trailers.jar
#WORKDIR /usr/app

CMD ["java", "-jar", "-Dspring.profiles.active=release", "/trailers.jar"]
