package com.up.socketservice.utils;

import com.up.socketservice.model.CommonPackage;
import com.up.socketservice.model.DriverDistance;

import java.util.List;

public class FindUtil {
    public static DriverDistance findSuitableDriverId(List<DriverDistance> list) {
        int minDistance = list.get(0).distance;
        DriverDistance d = new DriverDistance();
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i).distance < minDistance) {
                d = list.get(i);
                minDistance = list.get(i).distance;
            }
        }
        return d;
    }
}
