@echo off
call mvn clean package
call docker build -t com.mycompany/LISTA4 .
call docker rm -f LISTA4
call docker run -d -p 9080:9080 -p 9443:9443 --name LISTA4 com.mycompany/LISTA4