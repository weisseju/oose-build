package de.oose.environmentservice.fx.model;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.client.RestTemplate;

import com.vividsolutions.jts.geom.Coordinate;

import de.oose.environmentservice.Environment;

public class EnvironmentServiceClient {

    private String url;
    private RestTemplate template;

    public void setUrl(String url) {
        this.url = url;
    }

    public void setTemplate(RestTemplate template) {
        this.template = template;
    }

    public Environment geEnvironmentFromService(Coordinate position) {

        Map<String, Object> queryParameters = new HashMap<String, Object>();
        queryParameters.put("lon", position.x);
        queryParameters.put("lat", position.y);

        try {
            Environment environmentFromJson =
                    template.getForObject(url
                            + "/environment?lat={lat}&lon={lon}",
                            Environment.class, queryParameters);

            return environmentFromJson;
        } catch (RuntimeException e) {
            return null;
        }
    }
}
