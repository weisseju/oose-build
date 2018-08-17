This uses a web service to get the current location and renders a html
page with the information. 
Please run on Tomee 1.7 with cxf. 


Configure to use JaxRsServiceClient  
to run on glassfish 4 and use jax-rs 2.0 Rest Client API.

Url is 


http://localhost:8080/de.oose.locationservice.web/view/


Run the built docker image with (replace Version/Tag (1.0.0-SNAPSHOT) when required)
 
docker run -p 8080:8080 oose/de.oose.locationservice.web:1.0.0-SNAPSHOT

To specify other location service:
docker run -p 8080:8080 --link loci:loci --env CATALINA_OPTS="-DlocationServiceUrl=http://loci:8080/de.oose.locationservice.impl" oose/de.oose.locationservice.web:1.0.0-SNAPSHOT
 
 