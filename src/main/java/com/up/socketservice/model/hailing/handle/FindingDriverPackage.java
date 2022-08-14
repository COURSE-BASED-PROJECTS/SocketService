package com.up.socketservice.model.hailing.handle;

import com.up.socketservice.model.JsonDistance;
import com.up.socketservice.model.hailing.CommonPackage;
import com.up.socketservice.model.server.gps.GpsPackage;
import com.up.socketservice.model.server.gps.ServerGPS;
import com.up.socketservice.utils.CalUtil;
import com.up.socketservice.utils.FindUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindingDriverPackage extends HandlePackage {
    private String name = "Finding Driver";

    public static Map<String, List<DriverDistance>> mapDistance = new HashMap<String, List<DriverDistance>>();

    public FindingDriverPackage(HandlePackageContext handlePackageContext) {
        super(handlePackageContext);
    }

    @Override
    public void handle(CommonPackage commonPackage) {
        Map<String, GpsPackage> mapDriver = ServerGPS.mapDriver;

        String locationDriver = "";
        for (Map.Entry<String, GpsPackage> entry : mapDriver.entrySet()) {
            locationDriver += entry.getValue().getStringLatLong() + "|";
        }
        StringBuilder sb = new StringBuilder(locationDriver);
        locationDriver = sb.deleteCharAt(sb.length() - 1).toString();

        String locationClient = commonPackage.getHailing().getLocationStart().latitude + ","
                + commonPackage.getHailing().getLocationEnd().longitude;

        JsonDistance data = CalUtil.GoogleMapDistance(locationClient, locationDriver);
        int i = 0;

        List<DriverDistance> listDriverDistance = new ArrayList<>();
        for (Map.Entry<String, GpsPackage> entry : mapDriver.entrySet()) {
            String idDriver = entry.getValue().getDriverID();
            int distance = data.getDistance(i++);
            DriverDistance dd = new DriverDistance(idDriver, distance);
            listDriverDistance.add(dd);
        }

        mapDistance.put(commonPackage.getIdHailing(), listDriverDistance);
        for (Map.Entry<String, List<DriverDistance>> entry : mapDistance.entrySet()) {
            System.out.println(entry.getKey() + ":");
            for (int j = 0; j < entry.getValue().size(); j++) {
                System.out.println(entry.getValue().get(j).toString());
            }
        }

        List<DriverDistance> dd = mapDistance.get(commonPackage.getIdHailing());
        System.out.println(dd);


        DriverDistance temp = FindUtil.findSuitableDriverId(dd);
        System.out.println(temp.getIdDriver());
        commonPackage.setIdDriver(temp.getIdDriver());
        commonPackage.setStatus("waiting");

        // remove selected driver
        mapDistance.get(commonPackage.getIdHailing()).remove(temp);

        System.out.println(commonPackage.toString());
    }

    @Override
    public String getState() {
        return name;
    }

}
