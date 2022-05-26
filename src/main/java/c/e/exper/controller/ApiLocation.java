package c.e.exper.controller;


import c.e.exper.data.DuserDAO;
import c.e.exper.service.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
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

        deliveryService.운송원_위치_업데이트(form.get("user_id"), Double.parseDouble(form.get("latitude")), Double.parseDouble(form.get("longitude")));
    }

    @GetMapping("/location/check")
    public Map<String, BigDecimal> checkLocation(@RequestParam("duser_id") String duser_id) {
        System.out.println("user_id: " + duser_id);
        Map<String, BigDecimal> doubleMap = deliveryService.운송원_위치_조회(duser_id);
        System.out.println("[ApiLocation]" + doubleMap);
        return doubleMap;
    }

    // 주문서 아이디로 운송원 아이디
    @GetMapping("/duserid/ordid")
    public String findDuserIdByOrdId(String ord_id) {
        System.out.println("ord_id: " + ord_id);
        return deliveryService.주문서_운송원ID_조회(ord_id);
    }

    @GetMapping("/location/getDusers")
    public List<DuserDAO> getDusers(@RequestBody Map<String, String> coord) {

    }
}
