#!/bin/bash
set -e
mvn clean package
docker cp target/examen-backend.war payara-examen:/tmp/examen-backend.war
docker exec payara-examen /opt/payara/appserver/bin/asadmin --user admin --passwordfile /tmp/pwdfile undeploy examen-backend
docker exec payara-examen /opt/payara/appserver/bin/asadmin --user admin --passwordfile /tmp/pwdfile deploy --contextroot examen-backend /tmp/examen-backend.war
echo "Redeploy completo."