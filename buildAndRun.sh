#!/bin/sh
mvn clean package && docker build -t com.mycompany/LISTA4 .
docker rm -f LISTA4 || true && docker run -d -p 9080:9080 -p 9443:9443 --name LISTA4 com.mycompany/LISTA4