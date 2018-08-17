package de.oose.environmentservice.web;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import de.oose.environmentservice.Environment;

@FeignClient(value = "environment")
@RequestMapping("de.oose.environmentservice.impl")
public interface EnvironmentServiceClient {
    
    @RequestMapping(method = RequestMethod.GET, value = "environment",
            consumes = "application/json", produces = "application/json")
    public Environment getEnvironment(@RequestParam("lat") double lat,
            @RequestParam("lon") double lon);
}
