package c.e.exper.controller;


import c.e.exper.service.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class ApiLocation {

    private final DeliveryService deliveryService;

    @Autowired
    public ApiLocation(DeliveryService deliveryService) {
        this.deliveryService = deliveryService;
    }

    @PostMapping("/location/update")
    public void updateLocation(String user_id, String latitude, String longitude) {
        System.out.println("latitude: " + latitude);
        System.out.println("longitude: " + longitude);

        deliveryService.운송원_위치_업데이트(user_id, latitude, longitude);
    }

    @GetMapping("/location/check")
    public Map<String, String> checkLocation(String user_id) {
        System.out.println("user_id: " + user_id);
        return deliveryService.운송원_위치_조회(user_id);
    }

    // 주문서 아이디로 운송원 아이디
    @GetMapping("/duserid/ordid")
    public String findDuserIdByOrdId(String ord_id) {
        System.out.println("ord_id: " + ord_id);
        return deliveryService.주문서_운송원ID_조회(ord_id);
    }
}
