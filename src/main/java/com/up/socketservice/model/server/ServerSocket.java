package com.up.socketservice.model.server;

import com.up.socketservice.model.hailing.CommonPackage;
import com.up.socketservice.model.hailing.handle.HandlePackageContext;

import java.util.HashMap;
import java.util.Map;

public final class ServerSocket {
    private static ServerSocket INSTANCE;

    public static Map<String, HandlePackageContext> mapHandlePackageContext;

    // Private constructor to avoid client applications to use constructor
    private ServerSocket() {
        mapHandlePackageContext = new HashMap<String, HandlePackageContext>();
    }

    public static ServerSocket getInstance() {
        if(INSTANCE == null){
            INSTANCE = new ServerSocket();
        }
        return INSTANCE;
    }

    public void addHandlePackageContext(String idHailing){
        mapHandlePackageContext.put(idHailing, new HandlePackageContext());
    }

    public HandlePackageContext getHandlePackageContext(String idHailing){
        return mapHandlePackageContext.get(idHailing);
    }

}
