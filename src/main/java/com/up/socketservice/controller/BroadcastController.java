package com.up.socketservice.controller;

import com.up.socketservice.dto.HailingDto;
import com.up.socketservice.model.hailing.CommonPackage;
import com.up.socketservice.model.hailing.HailingPackage;
import com.up.socketservice.model.hailing.handle.HandlePackageContext;
import com.up.socketservice.model.hailing.handle.ReFindingDriverPackage;
import com.up.socketservice.model.server.ServerSocket;
import com.up.socketservice.utils.FindUtil;
import com.up.socketservice.utils.SaveDbUtil;
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
            HailingPackage h = commonPackage.getHailing();
            Integer locationStartId = 0;
            Integer locationEndId = 0;
            try {
                locationStartId = FindUtil.findLocationId(h.getLocationStart());
                locationEndId = FindUtil.findLocationId(h.getLocationEnd());
            } catch (Exception e) {
                System.out.println("Lỗi hàm lưu location");
                throw new RuntimeException(e);
            }

            HailingDto hailingDto = new HailingDto(null, commonPackage.getIdClient(),
                    Integer.parseInt(commonPackage.getIdDriver()), h.getDistance(),
                    h.getTimeDuring(), commonPackage.getHailing().getCost(),
                    h.getTimeStart(), commonPackage.getScope(), commonPackage.getStatus(),
                    locationStartId, locationEndId, h.getCarType());

            try {
                SaveDbUtil.saveHailing(hailingDto);
            } catch (Exception e) {
                System.out.println("Lưu chuyến đi không thành công");
                throw new RuntimeException(e);
            }

            // Update balance tài xế
            try {
                SaveDbUtil.updateDriver(commonPackage.getIdDriver(), h.getCost());
            } catch (Exception e) {
                System.out.println("Update tài xế không thành công");
                throw new RuntimeException(e);
            }

            // Gửi thông báo cho khách hàng
            commonPackage.setStatus("have_driver");
            messagingTemplate.convertAndSend("/topic/" + commonPackage.getIdClient(), commonPackage);
        } else if (commonPackage.getStatus().equals("decline")) {
            HandlePackageContext tmp = ServerSocket.getInstance().getHandlePackageContext(commonPackage.getIdHailing());
            tmp.changeState(new ReFindingDriverPackage(tmp));
            tmp.handle(commonPackage);

            messagingTemplate.convertAndSend("/topic/" + commonPackage.getIdDriver(), commonPackage);
        } else if (commonPackage.getStatus().equals("end")) {

            messagingTemplate.convertAndSend("/topic/" + commonPackage.getIdClient(), commonPackage);
        }
    }
}
