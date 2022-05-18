package c.e.exper.controller;


import c.e.exper.service.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class ApiLocation {

    private static int count = 0;
    private final DeliveryService deliveryService;

    @Autowired
    public ApiLocation(DeliveryService deliveryService) {
        this.deliveryService = deliveryService;
    }

    @PostMapping("/location/update")
    public void updateLocation(@RequestBody Map<String, String> form) {
        int temp = count++%10;
        System.out.println(temp);

        System.out.println("[updateLocation] " + form);

        deliveryService.운송원_위치_업데이트(form.get("user_id"), form.get("latitude")+temp, form.get("longitude")+temp);
    }

    @GetMapping("/location/check")
    public Map<String, String> checkLocation(@RequestParam("duser_id") String duser_id) {
        System.out.println("user_id: " + duser_id);
        return deliveryService.운송원_위치_조회(duser_id);
    }

    // 주문서 아이디로 운송원 아이디
    @GetMapping("/duserid/ordid")
    public String findDuserIdByOrdId(String ord_id) {
        System.out.println("ord_id: " + ord_id);
        return deliveryService.주문서_운송원ID_조회(ord_id);
    }
}
