FROM eclipse-temurin:17-alpine

ENV APP_NAME=wisedata
ENV BUILD_FOLDER=target/appassembler
ENV START_SCRIPT=/app/bin/${APP_NAME}.sh

WORKDIR /app

COPY ${BUILD_FOLDER}/ .

ENTRYPOINT ["sh", "-c", "${START_SCRIPT}"]