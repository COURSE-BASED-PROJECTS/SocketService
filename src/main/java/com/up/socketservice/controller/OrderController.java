package com.up.socketservice.controller;

import com.up.socketservice.model.CommonPackage;
import com.up.socketservice.model.GpsMeassage;
import com.up.socketservice.utils.CalUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.Map;


@Controller
public class OrderController {
    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);
    public static Map<String, CommonPackage> mapPackage = new HashMap<String, CommonPackage>();
    @MessageMapping("/order.getOrder")
    //@SendTo("/topic/public")
    public void getOrder(@Payload CommonPackage commonPackage) {
        logger.info(commonPackage.toString());
        mapPackage.put(commonPackage.idClient, commonPackage);

        Map<String, GpsMeassage> mapDriver = GpsController.mapDriver;
        String locationDriver = "";
        for (Map.Entry<String, GpsMeassage> entry : mapDriver.entrySet()) {
            locationDriver += entry.getValue().toString() + "|";
        }
        String locationClient = commonPackage.hailing.locationStart.latitude + "," + commonPackage.hailing.locationStart.longitude;

        System.out.println(locationDriver);
        System.out.println(locationClient);

        System.out.println(CalUtil.GoogleMapDistance(locationClient,locationDriver));

    }



}
