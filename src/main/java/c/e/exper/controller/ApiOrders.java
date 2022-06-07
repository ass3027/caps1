package c.e.exper.controller;

import c.e.exper.data.OrderDAO;
import c.e.exper.service.DeliveryService;
import c.e.exper.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/orders")
public class ApiOrders {

    @Autowired
    final OrdersService ordersService;


    public ApiOrders(OrdersService ordersService) {

        this.ordersService = ordersService;
    }

    @GetMapping("/baginfo")
    public List<Map<String, Character>> getOrdersBaginfo(String ord_id) {
        System.out.println("[/api/orders/baginfo]" + ord_id);

        return ordersService.주문서_가방정보_조회(ord_id);
    }
}

