package com.up.socketservice.utils;

import com.up.socketservice.model.hailing.handle.DriverDistance;

import java.util.List;

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
}
