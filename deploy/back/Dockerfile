## Imagen de Building ##
FROM gradle:7.3.0-jdk11 AS build

## COPY ALL PROJECT FILES TO CONTAINER WORKDIR ## 
COPY --chown=gradle:gradle ./backend/ /backend

## Stablish workdir inside container ##
WORKDIR /backend

RUN gradle build -x test

#Imagen de Prueba
FROM openjdk:11
ENV ARTIFACT_NAME=StyleOverflow-0.0.1-SNAPSHOT.jar

RUN mkdir /backend
WORKDIR /backend

## Update container SO ## 
COPY --from=build /backend/build/libs/${ARTIFACT_NAME} .

## EXPOSE CONTAINER PORT TO REACH SERVICE ## 
EXPOSE 13312
EXPOSE 8080

ENTRYPOINT java -jar /backend/${ARTIFACT_NAME}