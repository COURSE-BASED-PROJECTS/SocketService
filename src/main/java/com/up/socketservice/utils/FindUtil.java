package com.up.socketservice.utils;

import com.up.socketservice.model.hailing.Location;
import com.up.socketservice.model.hailing.handle.DriverDistance;
import com.up.socketservice.model.server.gps.GpsPackage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindUtil {
    public static DriverDistance findSuitableDriverId(List<DriverDistance> list) {
        int minDistance = list.get(0).getDistance();
        DriverDistance d = list.get(0);

        for (int i = 1; i < list.size(); i++) {
            if (list.get(i).getDistance() < minDistance) {
                d = list.get(i);
                minDistance = list.get(i).getDistance();
            }
        }

        return d;
    }

    public static Map<String, GpsPackage> findListSuitableDriver(Location clientLocation, Map<String, GpsPackage> mapDriver) {
        Map<String, GpsPackage> result = new HashMap<>();
        double MIN_DISTANCE = 5.0;

        for (Map.Entry<String, GpsPackage> entry : mapDriver.entrySet()) {
            double distance = CalUtil.distance(clientLocation.latitude, Double.parseDouble(entry.getValue().latitude)
                    , clientLocation.longitude, Double.parseDouble(entry.getValue().longitude));

            if (distance <= MIN_DISTANCE) {
                result.put(entry.getKey(), entry.getValue());
            }
        }


        return result;
    }
}
