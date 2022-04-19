FROM openjdk:11

ADD ./build/libs/ip-blocker-0.0.1.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/ip-blocker-0.0.1.jar"]

EXPOSE 8080