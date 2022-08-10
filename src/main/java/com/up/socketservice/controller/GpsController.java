package com.up.socketservice.controller;

import com.up.socketservice.model.GpsMeassage;
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
    private static final Logger logger = LoggerFactory.getLogger(ChatController.class);

    Map<String, GpsMeassage> map = new HashMap<String, GpsMeassage>();
    @MessageMapping("/gps.getGps")
    @SendTo("/topic/public")
    public GpsMeassage getGps(@Payload GpsMeassage gpsMeassage) {
        // Test chổ này xem có lưu lại được runtime tọa độ tài xế vô ko?
        map.put(gpsMeassage.getDriverID(), gpsMeassage);

        for (Map.Entry<String, GpsMeassage> entry : map.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue().toString());
        }
        logger.info(gpsMeassage.toString());
        return gpsMeassage;
    }
}
