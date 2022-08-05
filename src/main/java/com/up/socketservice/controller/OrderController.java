package com.up.socketservice.controller;

import com.up.socketservice.model.ChatMessage;
import com.up.socketservice.model.OrderMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;

public class OrderController {
    private static final Logger logger = LoggerFactory.getLogger(ChatController.class);

    @MessageMapping("/order.sendOrder")
    @SendTo("/topic/public")
    public OrderMessage sendOrder(@Payload OrderMessage orderMessage) {
        logger.info(orderMessage.toString());

        return orderMessage;
    }

    @MessageMapping("/order.findingDriver")
    @SendTo("/topic/public")
    public OrderMessage findingDriver(@Payload OrderMessage orderMessage,
                               SimpMessageHeaderAccessor headerAccessor) {
        // Add username in web socket session
        headerAccessor.getSessionAttributes().put("username", orderMessage.getCusName());
        logger.info(orderMessage.toString());
        return orderMessage;
    }

}
