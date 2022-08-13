package com.up.socketservice.controller;

import com.up.socketservice.model.CommonPackage;
import com.up.socketservice.model.DriverDistance;
import com.up.socketservice.model.GpsMeassage;
import com.up.socketservice.model.JsonDistance;
import com.up.socketservice.utils.CalUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
public class OrderController {
    @Autowired
    private SimpMessageSendingOperations messagingTemplate;
    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);
    public static Map<String, CommonPackage> mapPackage = new HashMap<String, CommonPackage>();
    public static Map<String, List<DriverDistance>> mapDistance = new HashMap<String, List<DriverDistance>>();

    @MessageMapping("/order.getOrder")
//    @SendTo("/topic/public")
    public void getOrder(@Payload CommonPackage commonPackage) {
        logger.info(commonPackage.toString());
        mapPackage.put(commonPackage.idClient, commonPackage);

        Map<String, GpsMeassage> mapDriver = GpsController.mapDriver;
        String locationDriver = "";
        for (Map.Entry<String, GpsMeassage> entry : mapDriver.entrySet()) {
            locationDriver += entry.getValue().getStringLatLong() + "|";
        }
        String locationClient = commonPackage.hailing.locationStart.latitude + "," + commonPackage.hailing.locationStart.longitude;
        StringBuilder sb = new StringBuilder(locationDriver);
        locationDriver = sb.deleteCharAt(sb.length() - 1).toString();

        System.out.println("");
        System.out.println(locationDriver);
        System.out.println(locationClient);

        JsonDistance data = CalUtil.GoogleMapDistance(locationClient, locationDriver);
        System.out.println(data);
        int i = 0;

        List<DriverDistance> listDriverDistance = new ArrayList<>();
        for (Map.Entry<String, GpsMeassage> entry : mapDriver.entrySet()) {
            String idDriver = entry.getValue().getDriverID();
            int distance = data.getDistance(i++);
            DriverDistance dd = new DriverDistance(idDriver, distance);
            listDriverDistance.add(dd);
        }

        mapDistance.put(commonPackage.idHailing, listDriverDistance);
        for (Map.Entry<String, List<DriverDistance>> entry : mapDistance.entrySet()) {
            System.out.println(entry.getKey() + ":");
            for (int j = 0; j < entry.getValue().size(); j++) {
                System.out.println(entry.getValue().get(j).toString());
            }
        }

        List<DriverDistance> dd = mapDistance.get(commonPackage.idHailing);

        int minDistance = Integer.MAX_VALUE;
        for (int j = 0; j < dd.size(); j++) {
            if (dd.get(j).distance < minDistance) {
                minDistance = dd.get(j).distance;
                commonPackage.idDriver = dd.get(j).idDriver;
            }
        }
        System.out.println(commonPackage.toString());
        commonPackage.status = "waiting";
        // remove selected driver
        messagingTemplate.convertAndSend("/topic/"+commonPackage.idDriver, commonPackage);
//        return commonPackage;
    }


}
