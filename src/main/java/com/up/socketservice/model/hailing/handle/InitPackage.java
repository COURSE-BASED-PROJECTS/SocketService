package com.up.socketservice.model.hailing.handle;

import com.up.socketservice.model.hailing.CommonPackage;

import java.util.HashMap;
import java.util.Map;

public class InitPackage extends HandlePackage {
    private String name = "Init Package";
    public static Map<String, CommonPackage> mapPackage = new HashMap<String, CommonPackage>();

    InitPackage(HandlePackageContext handlePackageContext) {
        super(handlePackageContext);
    }


    @Override
    public void handle(CommonPackage commonPackage) {
        mapPackage.put(commonPackage.getIdHailing(), commonPackage);
    }

    @Override
    public String getState() {
        return name;
    }
}
