package com.up.socketservice.model.hailing.handle;

import com.up.socketservice.model.hailing.CommonPackage;
import com.up.socketservice.utils.FindUtil;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ReFindingDriverPackage extends HandlePackage {
    private String name = "Refinding Driver";

    public ReFindingDriverPackage(HandlePackageContext handlePackageContext) {
        super(handlePackageContext);
    }

    @Override
    public void handle(CommonPackage commonPackage) {
        Map<String, List<DriverDistance>> mapDistance = FindingDriverPackage.mapDistance;
        List<DriverDistance> dd = mapDistance.get(commonPackage.getIdHailing());

        if(dd.isEmpty()) {
            commonPackage.setStatus("no_driver");
            return;
        }
        DriverDistance temp = FindUtil.findSuitableDriverId(dd);
        commonPackage.setIdDriver(temp.getIdDriver());
        commonPackage.setStatus("waiting");
        System.out.println(commonPackage.toString());

        // remove selected driver
        List<DriverDistance> filterList =
                mapDistance.get(commonPackage.getIdHailing()).stream().filter(l -> !l.getIdDriver().equals(temp.getIdDriver())).collect(Collectors.toList());

        mapDistance.put(commonPackage.getIdHailing(), filterList);

    }

    @Override
    public String getState() {
        return name;
    }
}
