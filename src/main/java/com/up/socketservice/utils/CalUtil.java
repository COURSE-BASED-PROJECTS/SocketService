package com.up.socketservice.utils;

import com.up.socketservice.model.Hailing;
import com.up.socketservice.model.JsonDistance;
import com.up.socketservice.model.hailing.HailingPackage;
import com.up.socketservice.model.hailing.handle.DriverDistance;
import com.up.socketservice.model.server.gps.GpsPackage;
import com.up.socketservice.model.server.gps.ServerGPS;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CalUtil {
    public static double distance(double lat1,
                                  double lat2, double lon1,
                                  double lon2) {

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
                * Math.pow(Math.sin(dlon / 2), 2);

        double c = 2 * Math.asin(Math.sqrt(a));

        // Radius of earth in kilometers. Use 3956
        // for miles
        double r = 6371;

        // calculate the result
        return (c * r);
    }

    public static List<DriverDistance> GoogleMapDistance(HailingPackage infoHailing) {
        List<DriverDistance> listDriverDistance = new ArrayList<>();

        String clientLocation = infoHailing.getLocationStart().latitude + ","
                + infoHailing.getLocationStart().longitude;

        Map<String, GpsPackage> mapDriver = FindUtil.findListSuitableDriver(infoHailing.getLocationStart(), ServerGPS.mapDriver);
        if (mapDriver.isEmpty()) return listDriverDistance;

        StringBuilder locationDriver = new StringBuilder();
        for (Map.Entry<String, GpsPackage> entry : mapDriver.entrySet()) {
            locationDriver.append(entry.getValue().getStringLatLong()).append("|");
        }
        String driverLocation = locationDriver.deleteCharAt(locationDriver.length() - 1).toString();

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

        JsonDistance data = response.getBody();

        if (data != null) {
            int i = 0;
            for (Map.Entry<String, GpsPackage> entry : mapDriver.entrySet()) {
                String idDriver = entry.getValue().getDriverID();
                int distance = data.getDistance(i++);
                DriverDistance dd = new DriverDistance(idDriver, distance);
                listDriverDistance.add(dd);
            }
            return listDriverDistance;
        }
        System.out.println("Không lấy được data từ api!");
        return listDriverDistance;
    }

}
