package com.up.socketservice.model.server.gps;

import java.util.HashMap;
import java.util.Map;

public final class ServerGPS {
    private static ServerGPS INSTANCE;
    public static Map<String, GpsPackage> mapDriver = new HashMap<String, GpsPackage>();

    // Private constructor to avoid client applications to use constructor
    private ServerGPS() {

    }

    public static ServerGPS getInstance() {
        if(INSTANCE == null){
            INSTANCE = new ServerGPS();
        }
        return INSTANCE;
    }

    public void addGPS(GpsPackage gps){
        mapDriver.put(gps.getDriverID(), gps);
    }
}
