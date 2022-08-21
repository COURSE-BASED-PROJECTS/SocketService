package com.up.socketservice.controller;

import com.up.socketservice.model.server.gps.GpsPackage;
import com.up.socketservice.model.server.gps.ServerGPS;
import org.apache.catalina.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.Map;

@Controller
public class GpsController {
    private static final Logger logger = LoggerFactory.getLogger(GpsController.class);

    public ServerGPS serverGPS = ServerGPS.getInstance();

    @MessageMapping("/gps.getGps")
    public void getGps(@Payload GpsPackage gpsPackage) {
        serverGPS.addGPS(gpsPackage);
        logger.info(gpsPackage.toString());
    }
}
