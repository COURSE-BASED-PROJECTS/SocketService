package com.up.socketservice.controller;

import com.up.socketservice.model.CommonPackage;
import com.up.socketservice.model.DriverDistance;
import com.up.socketservice.model.GpsMeassage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class BroadcastController {

    @Autowired
    private SimpMessageSendingOperations messagingTemplate;
    @MessageMapping("/broadcast.sendRequest")
    public CommonPackage sendRequest() {
        Map<String, List<DriverDistance>> mapDistance = OrderController.mapDistance;
        Map<String, CommonPackage> mapPackage = OrderController.mapPackage;

        for (Map.Entry<String, CommonPackage> entry : mapPackage.entrySet()) {
            List<DriverDistance> dd = mapDistance.get(entry.getKey());

            int minDistance = Integer.MAX_VALUE;
            for (int i = 0; i < dd.size(); i++) {
                if (dd.get(i).distance < minDistance){
                    minDistance = dd.get(i).distance;
                    entry.getValue().idDriver = dd.get(i).idDriver;
                }
            }

            messagingTemplate.convertAndSend("/topic/" + entry.getValue().idDriver, entry.getValue());
        }



        return new CommonPackage();
    }
}
