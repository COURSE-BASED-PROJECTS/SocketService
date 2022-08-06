package com.up.socketservice.controller;

import com.up.socketservice.model.GpsMeassage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class GpsController {
    private static final Logger logger = LoggerFactory.getLogger(ChatController.class);

    @MessageMapping("/gps.getGps")
    @SendTo("/topic/public")
    public GpsMeassage getGps(@Payload GpsMeassage gpsMeassage) {
        logger.info(gpsMeassage.toString());
        return gpsMeassage;
    }
}
