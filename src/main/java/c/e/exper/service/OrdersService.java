package c.e.exper.service;

import c.e.exper.mapper.OrdersMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class OrdersService {

    final OrdersMapper ordersMapper;

    public OrdersService(OrdersMapper ordersMapper) {
        this.ordersMapper = ordersMapper;
    }


    public List<Map<String, Character>> 주문서_가방정보_조회(String ord_id) {

        return ordersMapper.findOrdersBaginfo(ord_id);
    }

}