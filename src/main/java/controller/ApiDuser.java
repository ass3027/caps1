package controller;

import c.e.exper.data.*;
import c.e.exper.mapper.OrdersMapper;
import c.e.exper.service.DeliveryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/duser")
public class ApiDuser {


    final DeliveryService deliveryService;
    final OrdersMapper ordersMapper;

    public ApiDuser(DeliveryService deliveryService, OrdersMapper ordersMapper) {

        this.deliveryService = deliveryService;
        this.ordersMapper = ordersMapper;
    }

    @GetMapping("/orders/{user_id}/{status}")
    public List<OrderDAO> getDuserOrders(@PathVariable("user_id") String user_id, @PathVariable("status")String status) {
        System.out.println("[/api/duser/orders]" + user_id  + ", " + status);
        // 근처 주문서

        return switch (status) {
            case "운송요청" -> deliveryService.근처_주문서_조회(user_id);
            case "운송중", "운송완료" -> ordersMapper.findDuserOrders(user_id, status);
            default -> null;
        };

    }


}

