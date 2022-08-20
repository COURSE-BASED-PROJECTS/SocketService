package com.up.socketservice.controller;

import com.up.socketservice.dto.HailingDto;
import com.up.socketservice.dto.LocationDto;
import com.up.socketservice.model.hailing.CommonPackage;
import com.up.socketservice.model.hailing.HailingPackage;
import com.up.socketservice.model.hailing.handle.DriverDistance;
import com.up.socketservice.model.hailing.handle.FindingDriverPackage;
import com.up.socketservice.model.hailing.handle.HandlePackageContext;
import com.up.socketservice.model.hailing.handle.ReFindingDriverPackage;
import com.up.socketservice.model.server.ServerSocket;
import com.up.socketservice.utils.FindUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;


@Controller
public class BroadcastController {

    @Autowired
    private SimpMessageSendingOperations messagingTemplate;

    @MessageMapping("/broadcast.handleRequest")
    public void handleRequest(@Payload CommonPackage commonPackage) {

        if (commonPackage.getStatus().equals("accept")) {
            //Gọi service lưu thông tin chuyến đi
//            HailingPackage h = commonPackage.getHailing();
//            Integer locationStartId = FindUtil.findLocationIdByLatLong(h.getLocationStart().latitude, h.getLocationStart().longitude);
//            Integer locationEndId = FindUtil.findLocationIdByLatLong(h.getLocationEnd().latitude, h.getLocationEnd().longitude);
//            HailingDto hailingDtoStart = new HailingDto();
//            HailingDto hailingDtoEnd = new HailingDto();
//            if (locationStartId == -1) {
//
//            }
//
//            if (locationEndId == -1){
//
//            }

            // Gửi thông báo cho khách hàng
            commonPackage.setStatus("have_driver");
            messagingTemplate.convertAndSend("/topic/" + commonPackage.getIdClient(), commonPackage);
        } else if (commonPackage.getStatus().equals("decline")) {
            HandlePackageContext tmp = ServerSocket.getInstance().getHandlePackageContext(commonPackage.getIdHailing());
            tmp.changeState(new ReFindingDriverPackage(tmp));
            tmp.handle(commonPackage);

            messagingTemplate.convertAndSend("/topic/" + commonPackage.getIdDriver(), commonPackage);
        } else if (commonPackage.getStatus().equals("end")){

            messagingTemplate.convertAndSend("/topic/" + commonPackage.getIdClient(), commonPackage);
        }
    }
}
