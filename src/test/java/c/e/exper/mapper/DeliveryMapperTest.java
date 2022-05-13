package c.e.exper.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DeliveryMapperTest {

    @Autowired
    private DeliveryMapper mapper;


    @Test
    void updateDuserLatitude() {
        mapper.updateDuserLatitude("1321313131", "100");
    }

    @Test
    void updateDuserLongitude() {
        mapper.updateDuserLongitude("128.6215081", "100");
    }

    @Test
    void findLatitudeById() {
        System.out.println(mapper.findLatitudeById("100"));
    }

    @Test
    void findLongitudeById() {
        System.out.println(mapper.findLongitudeById("100"));
    }

    @Test
    void findDuserIdByordId() {
        System.out.println(mapper.findDuserIdByOrdId("100"));
    }
}