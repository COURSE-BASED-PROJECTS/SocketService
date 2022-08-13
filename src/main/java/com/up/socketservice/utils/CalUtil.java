package com.up.socketservice.utils;

import com.up.socketservice.model.JsonDistance;
import com.up.socketservice.model.Location;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CalUtil {
    public static double distance(double lat1,
                                  double lat2, double lon1,
                                  double lon2)
    {

        // The math module contains a function
        // named toRadians which converts from
        // degrees to radians.
        lon1 = Math.toRadians(lon1);
        lon2 = Math.toRadians(lon2);
        lat1 = Math.toRadians(lat1);
        lat2 = Math.toRadians(lat2);

        // Haversine formula
        double dlon = lon2 - lon1;
        double dlat = lat2 - lat1;
        double a = Math.pow(Math.sin(dlat / 2), 2)
                + Math.cos(lat1) * Math.cos(lat2)
                * Math.pow(Math.sin(dlon / 2),2);

        double c = 2 * Math.asin(Math.sqrt(a));

        // Radius of earth in kilometers. Use 3956
        // for miles
        double r = 6371;

        // calculate the result
        return(c * r);
    }

    public static JsonDistance GoogleMapDistance(String clientLocation, String driverLocation)
    {
        String url = "https://rsapi.goong.io/DistanceMatrix?origins={origins}&destinations={destinations}&vehicle={vehicle}&api_key={api_key}";
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);

        Map<String, String> params = new HashMap<String, String>();
        params.put("origins", clientLocation);
        params.put("destinations", driverLocation);
        params.put("vehicle", "car");
        params.put("api_key", "bjcbz6ObuZeCfwHhJu4lpkzLFXDsMDBPGZXPOHav");


        HttpEntity entity = new HttpEntity(headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<JsonDistance> response = restTemplate.exchange(url, HttpMethod.GET, entity, JsonDistance.class, params);
        return response.getBody();
    }

}
