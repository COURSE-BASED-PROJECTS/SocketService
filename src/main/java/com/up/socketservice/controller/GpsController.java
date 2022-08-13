package com.up.socketservice.controller;

import com.up.socketservice.model.GpsMeassage;
import com.up.socketservice.utils.CalUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.Map;

@Controller
public class GpsController {
    private static final Logger logger = LoggerFactory.getLogger(GpsController.class);

    public static Map<String, GpsMeassage> mapDriver = new HashMap<String, GpsMeassage>();
    @MessageMapping("/gps.getGps")
    @SendTo("/topic/public")
    public GpsMeassage getGps(@Payload GpsMeassage gpsMeassage) {

        mapDriver.put(gpsMeassage.getDriverID(), gpsMeassage);

        logger.info(gpsMeassage.toString());

        //System.out.println(CalUtil.GoogleMapDistance(null, null).toString());
        return gpsMeassage;
    }
}
