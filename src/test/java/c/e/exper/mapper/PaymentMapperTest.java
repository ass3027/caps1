package c.e.exper.mapper;

import c.e.exper.data.GItemDAO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PaymentMapperTest {

    public static final String PAY_ID = "100";
    @Autowired
    PaymentMapper paymentMapper;

    @Test
    void paymentList() {
        System.out.println(paymentMapper.paymentList("um"));
    }

    @Test
    void paymentCheck() {
        System.out.println(paymentMapper.paymentCheck("13", "um"));
    }

    @Test
    void gitemInfoToPayId() {
        GItemDAO gItemDAOS = paymentMapper.gitemInfoToPayId(PAY_ID);
        System.out.println("gItemDAOS = " + gItemDAOS);
    }
}