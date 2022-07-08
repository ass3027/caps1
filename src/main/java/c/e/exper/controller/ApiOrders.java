package c.e.exper.controller;

import c.e.exper.data.OrderDAO;
import c.e.exper.mapper.OrdersMapper;
import c.e.exper.service.OrdersService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
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
//        System.out.println("[/api/orders/baginfo] " + ord_id);

        return ordersService.주문서_가방정보_조회(ord_id);
    }

    @GetMapping("/order")
    public OrderDAO getOrder(String ord_id) {
        System.out.println("[/api/orders/] " + ord_id);

        OrderDAO order = ordersMapper.findOrder(ord_id);
        System.out.println(ord_id + "'s bag_info: " + order);
        return order;
    }

    @PostMapping("/status/change/{change_to}/{ord_id}")
    public String statusChange(@PathVariable String change_to, @PathVariable String ord_id) {
        System.out.println(change_to);
        System.out.println(ord_id);

        String status = ordersMapper.checkOrderStatus(ord_id);


        // 승인완료 -> 픽업 전    pickup_before
        // 픽업 전 -> 운송 중     pickup_after
        // 운송 중 -> 운송 완료   arrival

        if (Objects.equals(change_to, "픽업전")) {
            if (!Objects.equals(status, "승인완료")) return "이미 매칭된 배송건입니다";
            if (ordersMapper.statusChange(change_to, ord_id)) {
                return ordersMapper.updateDeliveryId(ord_id, SecurityContextHolder.getContext().getAuthentication().getName()) ? "매칭이 완료되었습니다." : "매칭 오류.";
            }
        }

        return ordersMapper.statusChange(change_to, ord_id) ? "성공" : "실패";
    }


    @GetMapping("/match/{ord_id}/{user_id}")
    public String matchOrder(@PathVariable("ord_id") String ord_id, @PathVariable("user_id") String user_id) {
        System.out.println("[/api/match/{ord_id}] " + ord_id);

        String status = ordersMapper.checkOrderStatus(ord_id);

        if (Objects.equals(status, "운송요청")) {
            if (ordersMapper.updateStatus(ord_id, "운송중")) {
                return ordersMapper.updateDeliveryId(ord_id, user_id) ? "매칭이 완료되었습니다." : "매칭 오류.";
            } else {
                return "매칭 오류.";
            }
        } else {
            return "이미 매칭된 배송건입니다.";
        }


    }

    @GetMapping("/myTransOrders")
    public List<OrderDAO> myTransOrders(String user_id){
        System.out.println(user_id);
        return ordersMapper.selectMyTransOrders(user_id);
    }

    @GetMapping("/myKeepOrders")
    public List<OrderDAO> myKeepOrders(String user_id){
        System.out.println(user_id);
        return ordersMapper.selectMyKeepOrders(user_id);
    }

}

