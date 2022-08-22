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
import java.util.stream.Collectors;

public class FindingDriverPackage extends HandlePackage {
    private String name = "Finding Driver";

    public static Map<String, List<DriverDistance>> mapDistance = new HashMap<String, List<DriverDistance>>();

    public FindingDriverPackage(HandlePackageContext handlePackageContext) {
        super(handlePackageContext);
    }

    @Override
    public void handle(CommonPackage commonPackage) {

        List<DriverDistance> listDriverDistance = CalUtil.GoogleMapDistance(commonPackage.getHailing());
        if (listDriverDistance.isEmpty()) {
            commonPackage.setStatus("no_driver");
            return;
        }

        mapDistance.put(commonPackage.getIdHailing(), listDriverDistance);

//        for (Map.Entry<String, List<DriverDistance>> entry : mapDistance.entrySet()) {
//            System.out.println(entry.getKey() + ":");
//            for (int j = 0; j < entry.getValue().size(); j++) {
//                System.out.println(entry.getValue().get(j).toString());
//            }
//        }
        //System.out.println(dd);

        DriverDistance temp = FindUtil.findSuitableDriverId(listDriverDistance);
        //System.out.println(temp.getIdDriver());
        commonPackage.setIdDriver(temp.getIdDriver());
        commonPackage.setStatus("waiting");

        // remove selected driver
        List<DriverDistance> filterList =
        mapDistance.get(commonPackage.getIdHailing()).stream().filter(l -> !l.getIdDriver().equals(temp.getIdDriver())).collect(Collectors.toList());

        mapDistance.put(commonPackage.getIdHailing(), filterList);

        //System.out.println(commonPackage.toString());
    }

    @Override
    public String getState() {
        return name;
    }

}
