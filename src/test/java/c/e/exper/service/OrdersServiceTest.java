package c.e.exper.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OrdersServiceTest {

    @Autowired
    OrdersService ordersService;

    @Test
    void 주문서_가방정보_조회() {
        ordersService.주문서_가방정보_조회("1112");
    }
}