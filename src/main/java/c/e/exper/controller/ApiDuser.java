package c.e.exper.controller;

import c.e.exper.data.*;
import c.e.exper.service.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/duser")
public class ApiDuser {

    @Autowired
    final DeliveryService deliveryService;

    public ApiDuser(DeliveryService deliveryService) {

        this.deliveryService = deliveryService;
    }

    @GetMapping("/orders")
    public List<OrderDAO> getDuserOrders(String user_id) {
        System.out.println("[/api/duser/orders]" + user_id);
        // 근처 주문서


        return deliveryService.근처_주문서_조회(user_id);
    }
}

