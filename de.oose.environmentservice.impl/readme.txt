This provides a webservice to retrieve the environment at a specific location. 
Some environment properties are read using a database. The Application is
 configured using spring.

Since Tomee does not support Spring 4 yet, please run on Tomcat 7. This 
is no "standard" rest app but Spring MVC with dispatcher servlet.

Url is 

http://localhost:8079/de.oose.environmentservice.impl/environment?lat=1.2&lon=0.6

where xx and yy are the latitude and longitude in rad. 

Example Respose

{

    "country": "Deutschland",
    "language": "deutsch",
    "weather": "superwetter at lat 3.5 lon 7.7"

}


Run the built docker image with (replace Version/Tag (1.0.0-SNAPSHOT) when required)
 
docker run -p 8079:8080 oose/de.oose.environmentservice.impl:1.0.0-SNAPSHOT

To expose to other containers only with alias "envi"

docker run --rm --name envi oose/de.oose.environmentservice.impl:1.0.0-SNAPSHOT