package c.e.exper.controller;

import c.e.exper.data.*;
import c.e.exper.mapper.OrdersMapper;
import c.e.exper.service.DeliveryService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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

    @GetMapping("/orders/{user_id}")
    public List<OrderDAO> getDuserOrders(@PathVariable("user_id") String user_id) {
        System.out.println("[/api/duser/orders]" + user_id);
        // 근처 주문서

        List<OrderDAO> orders = new ArrayList<>();

        orders.addAll(deliveryService.근처_주문서_조회(user_id));
        orders.addAll(ordersMapper.findDuserOrdersAll(user_id));

        return orders;
    }


}

