package c.e.exper.mapper;

import c.e.exper.data.PaymentDAO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserMapperTest {

    @Autowired
    UserMapper userMapper;

    @Test
    void selectAll() {
    }

    @Test
    void selectId() {
    }

    @Test
    void insert() {
    }

    @Test
    void updateUserInfo() {
    }

    @Test
    void checkExist() {
    }

    @Test
    void updateUserPhone() {
    }

    @Test
    void selectAllId() {
    }

    @Test
    void selectUserPayments() {
        List<PaymentDAO> sw = userMapper.selectUserPayments("sw");
        System.out.println(sw);
    }
}