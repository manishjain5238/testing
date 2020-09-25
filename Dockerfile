FROM adoptopenjdk/openjdk11:alpine-jre

ARG SPRING_PROFILES_ACTIVE=dev
ENV SPRING_PROFILES_ACTIVE=${SPRING_PROFILES_ACTIVE}

ADD ./target/weather-forecast-1.0.0.jar /app/

EXPOSE 8080

CMD ["java", "--illegal-access=deny", "-jar", "/app/weather-forecast-1.0.0.jar"]