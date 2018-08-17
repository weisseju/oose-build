This is a Spring BOOT Web UI using thymeleaf templating and MVC. The Rest
services yielding current location and environment are queried using 
Rest Template. Simply run as java app and see

http://localhost:8090/view



Run the built docker image with (replace Version/Tag (1.0.0-SNAPSHOT) when required)
 
docker run -p 8090:8090 oose/de.oose.environmentservice.web:1.0.0-SNAPSHOT

To link with other containers and configure urls:

docker run -p 8090:8090 --link loci:loci --link envi:envi --env locationservice.url="http://loci:8080/de.oose.locationservice.impl" --env environment.ribbon.listOfServers="http://envi:8080" oose/de.oose.environmentservice.web:1.0.0-SNAPSHOT
 