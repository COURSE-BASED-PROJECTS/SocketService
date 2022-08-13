package com.up.socketservice.controller;

import com.up.socketservice.model.CommonPackage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Controller;



@Controller
public class OrderController {
    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

    @MessageMapping("/order")
    //@SendTo("/topic/public")
    public void getOrder(@Payload CommonPackage commonPackage) {
        logger.info(commonPackage.toString());

    }



}
