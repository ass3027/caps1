package c.e.exper.mapper;

import c.e.exper.data.OrderDAO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OrdersMapperTest {

    @Autowired
    OrdersMapper ordersMapper;

    @Test
    void findOrdersForKeepID() {
        System.out.println(ordersMapper.findOrdersBaginfo("1108"));
    }

    @Test
    void findNearOrders() {
        double longitude = 128.620954;
        double latitude = 35.90621;
        List<OrderDAO> nearOrders = ordersMapper.findNearOrders(longitude - 0.05, longitude + 0.05,
                latitude - 0.05, latitude + 0.05);
        nearOrders.forEach(System.out::println);
    }

    @Test
    void findOrdersBagsize() {
        List<Map<String, Character>> ordersBagsize = ordersMapper.findOrdersBaginfo("1112");

        ordersBagsize.forEach(System.out::println);

        ordersBagsize.forEach(i -> {
            System.out.println(i.get("CNT"));
        });
    }

    @Test
    void checkOrderStatus() {
        System.out.println(ordersMapper.checkOrderStatus("1112"));
    }
}