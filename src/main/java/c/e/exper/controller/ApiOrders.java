package c.e.exper.controller;

import c.e.exper.data.OrderDAO;
import c.e.exper.mapper.OrdersMapper;
import c.e.exper.service.OrdersService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
public class ApiOrders {


    final OrdersService ordersService;

    final OrdersMapper ordersMapper;


    @GetMapping("/baginfo")
    public List<Map<String, Character>> getOrdersBaginfo(String ord_id) {
        System.out.println("[/api/orders/baginfo] " + ord_id);

        return ordersService.주문서_가방정보_조회(ord_id);
    }
    @GetMapping("/order")
    public OrderDAO getOrder(String ord_id) {
        System.out.println("[/api/orders/] " + ord_id);

        return ordersMapper.findOrder(ord_id);
    }


}

