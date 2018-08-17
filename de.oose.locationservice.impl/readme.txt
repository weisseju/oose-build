This provides a rest service with one resource, the current location 
under "location/current". Please run on apache Tomee 1.7-jaxrs or 
glassfish 4.

http://localhost:8080/de.oose.locationservice.impl/location/current/ 

Example output :

Position is coded in lon(that is "X", East/West):lat(that is "Y", North/South) in rad, double precision
Heading is in rad, double precision
Time is in DateTimeFormatter.ISO_INSTANT format.

{

    "position": "0.4:0.34",
    "heading": 0,
    "time": "2014-08-28T11:55:20.845Z"
}

Run the built docker image with (replace Version/Tag (1.0.0-SNAPSHOT) when required)
 
docker run -p 8080:8080 oose/de.oose.locationservice.impl:1.0.0-SNAPSHOT

To expose for other docker containers only with alias "loci":

docker run --rm --name loci oose/de.oose.locationservice.impl:1.0.0-SNAPSHOT