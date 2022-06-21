package c.e.exper.controller;

import c.e.exper.data.OrderDAO;
import c.e.exper.mapper.OrdersMapper;
import c.e.exper.service.OrdersService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
public class ApiOrders {


    final OrdersService ordersService;

    final OrdersMapper ordersMapper;


    @GetMapping("/baginfo/{ord_id}")
    public List<Map<String, Character>> getOrdersBaginfo(@PathVariable("ord_id") String ord_id) {
        System.out.println("[/api/orders/baginfo] " + ord_id);

        return ordersService.주문서_가방정보_조회(ord_id);
    }
    @GetMapping("/order")
    public OrderDAO getOrder(String ord_id) {
        System.out.println("[/api/orders/] " + ord_id);

        OrderDAO order = ordersMapper.findOrder(ord_id);
        System.out.println(ord_id + "'s bag_info: " + order);
        return order;
    }

    @PostMapping("/arrival/{ord_id}")
    public String orderArrival(@PathVariable("ord_id") String ord_id) {
        System.out.println("[/api/orders/] " + ord_id);

        if(ordersMapper.orderArrival(ord_id)){
            return "배송완료 처리";
        }else {
            return "오류 발생";
        }

    }

    @GetMapping("/match/{ord_id}/{user_id}")
    public String matchOrder(@PathVariable("ord_id") String ord_id, @PathVariable("user_id") String user_id) {
        System.out.println("[/api/match/{ord_id}] " + ord_id);

        String status = ordersMapper.checkOrderStatus(ord_id);

        if(Objects.equals(status, "1")) {
            if(ordersMapper.updateStatus(ord_id, "2")) {
                return ordersMapper.updateDeliveryId(ord_id, user_id) ? "매칭이 완료되었습니다." : "매칭 오류.";
            } else{
                return "매칭 오류.";
            }
        }else {
            return "이미 매칭된 배송건입니다.";
        }

    }

}

