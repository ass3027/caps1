package c.e.exper.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PictureMapperTest {

    @Autowired
    private PictureMapper pictureMapper;

    @Test
    void insertUser() {
    }

    @Test
    void selectAllbyPlanId() {
    }

    @Test
    void selectPicnameByUserId() {
    }

    @Test
    void selectPicnameByRevId() {
        System.out.println(pictureMapper.selectPicnameByRevId("226"));
    }

    @Test
    void insertStore() {
    }

    @Test
    void insertPlan() {
    }

    @Test
    void insertOrderReview() {
    }

    @Test
    void insertBookReview() {
    }
}