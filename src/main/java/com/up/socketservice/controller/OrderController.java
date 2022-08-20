package com.up.socketservice.controller;

import com.up.socketservice.model.hailing.CommonPackage;
import com.up.socketservice.model.hailing.handle.FindingDriverPackage;
import com.up.socketservice.model.hailing.handle.HandlePackageContext;
import com.up.socketservice.model.server.ServerSocket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;


@Controller
public class OrderController {
    @Autowired
    private SimpMessageSendingOperations messagingTemplate;

    ServerSocket serverSocket = ServerSocket.getInstance();

    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

    @MessageMapping("/order.getOrder")
    public void getOrder(@Payload CommonPackage commonPackage) {
        logger.info(commonPackage.toString());

        serverSocket.addHandlePackageContext(commonPackage.getIdHailing());
        HandlePackageContext tmp = serverSocket.getHandlePackageContext(commonPackage.getIdHailing());
        tmp.handle(commonPackage);
        tmp.changeState(new FindingDriverPackage(tmp));
        tmp.handle(commonPackage);

        if (commonPackage.getStatus().equals("no_driver")) {
            messagingTemplate.convertAndSend("/topic/" + commonPackage.getIdClient(), commonPackage);
            return;
        }

        messagingTemplate.convertAndSend("/topic/" + commonPackage.getIdDriver(), commonPackage);
    }

}
