package c.e.exper.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PlaceMapperTest {

    @Autowired
    PlaceMapper placeMapper;

    @Test
    void selectByPlace() {
    }

    @Test
    void selectByPlaceTo() {
    }

    @Test
    void findSimilarUser() {
    }

    @Test
    void findRecPlace() {
    }

    @Test
    void findBestPlace() {
    }

    @Test
    void findByCategory() {
        System.out.println(placeMapper.findByCategory("B02011500"));

    }
}