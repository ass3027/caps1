package c.e.exper.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PaymentMapperTest {

    @Autowired
    PaymentMapper paymentMapper;

    @Test
    void paymentList() {
        System.out.println(paymentMapper.paymentList("um"));
    }
}