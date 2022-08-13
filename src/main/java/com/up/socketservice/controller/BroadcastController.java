package com.up.socketservice.controller;

import com.up.socketservice.model.CommonPackage;
import com.up.socketservice.model.GpsMeassage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class BroadcastController {
//    @MessageMapping("/order.findingDriver")
//    @SendTo("/topic/public")
//    public List<String> findingDriver(@Payload OrderMessage orderMessage) {
//        // Add username in web socket session
//        Map<String, GpsMeassage> mapDriver = GpsController.mapDriver;
//        List<String> idDriver = new ArrayList<>();
//
//        for (Map.Entry<String, GpsMeassage> entry : mapDriver.entrySet()) {
//            //System.out.println(entry.getKey() + ": " + entry.getValue().toString());
//            GpsMeassage temp = entry.getValue();
//            double distance = CalUtil.distance(orderMessage.getLatArrivingAddr(), Double.parseDouble(temp.latitude),
//                    orderMessage.getLngArrivingAddr(), Double.parseDouble(temp.longitude));
//
//            if (distance <= 2.0) {
//                idDriver.add(temp.driverID);
//            }
//        }
//
//        return idDriver;
//    }

    @MessageMapping("/order.getDriver")
    @SendTo("/topic/public")
    public CommonPackage getDriver() {

        Map<String, GpsMeassage> mapDriver = GpsController.mapDriver;
        Map<String, CommonPackage> mapPackage = OrderController.mapPackage;
        List<String> idDriver = new ArrayList<>();


        for (Map.Entry<String, GpsMeassage> entry : mapDriver.entrySet()) {
            //System.out.println(entry.getKey() + ": " + entry.getValue().toString());
            GpsMeassage temp = entry.getValue();

        }

        return new CommonPackage();
    }
}
