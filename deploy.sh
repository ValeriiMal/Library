#!/bin/bash

sudo service tomcat7 stop
mvn package
sudo rm -rf /var/lib/tomcat7/webapps/valmal-library-1.0-SNAPSHOT
sudo cp target/valmal-library-1.0-SNAPSHOT.war /var/lib/tomcat7/webapps/valmal-library-1.0-SNAPSHOT.war
sudo service tomcat7 start
