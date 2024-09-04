# docker build -t product .
# docker-compose down
# docker-compose up -d --build

FROM eclipse-temurin:21-jre-alpine
COPY build/libs/product-V2.jar /app/product-V2.jar
ENTRYPOINT ["java", "-jar", "/app/product-V2.jar"]
EXPOSE 8080
RUN apk --update --no-cache add curl
HEALTHCHECK --interval=1m --timeout=30s --start-period=5s --retries=3 \
    CMD curl -f http://localhost:8080/actuator/health || exit 1
LABEL version="0.1" \
    description="Products microservice using PostgreSQL including Docker containers and health check test"