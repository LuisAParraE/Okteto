## Download Base Image ##
FROM node:12.13.0

## Update container SO ##
RUN apt-get update

## Stablish workdir inside container ##
WORKDIR /frontend

## COPY ALL PROJECT FILES TO CONTAINER WORKDIR ## 
COPY ./frontend/. /frontend/

RUN rm -fr /frontend/node_modules
RUN npm install

## EXPOSE CONTAINER PORT TO REACH SERVICE ## 
EXPOSE 13300

ENTRYPOINT ["npm","start"]
