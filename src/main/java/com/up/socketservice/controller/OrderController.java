package com.up.socketservice.controller;

import com.up.socketservice.model.ChatMessage;
import com.up.socketservice.model.GpsMeassage;
import com.up.socketservice.model.OrderMessage;
import com.up.socketservice.utils.CalUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class OrderController {
    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

    @MessageMapping("/order.sendOrder")
    @SendTo("/topic/public")
    public OrderMessage sendOrder(@Payload OrderMessage orderMessage) {
        logger.info(orderMessage.toString());

        return orderMessage;
    }



}
