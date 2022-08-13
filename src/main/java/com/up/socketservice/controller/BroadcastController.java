package com.up.socketservice.controller;

import com.up.socketservice.model.CommonPackage;
import com.up.socketservice.model.DriverDistance;
import com.up.socketservice.model.GpsMeassage;
import com.up.socketservice.utils.FindUtil;
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
public class BroadcastController {

    @Autowired
    private SimpMessageSendingOperations messagingTemplate;

    @MessageMapping("/broadcast.handleRequest")
    public void handleRequest(@Payload CommonPackage commonPackage) {
        if (commonPackage.status.equals("accept")) {
            //Gọi service lưu thông tin chuyến đi
            // ...

            // Gửi thông báo cho khách hàng
            messagingTemplate.convertAndSend("/topic/public", commonPackage);
        } else if (commonPackage.status.equals("decline")) {
            Map<String, List<DriverDistance>> mapDistance = OrderController.mapDistance;
            List<DriverDistance> dd = mapDistance.get(commonPackage.idHailing);

            DriverDistance temp = FindUtil.findSuitableDriverId(dd);
            commonPackage.idDriver = temp.idDriver;
            commonPackage.status = "waiting";
            System.out.println(commonPackage.toString());

            // remove selected driver
            mapDistance.get(commonPackage.idHailing).remove(temp);
            messagingTemplate.convertAndSend("/topic/" + commonPackage.idDriver, commonPackage);
        }
    }
}
