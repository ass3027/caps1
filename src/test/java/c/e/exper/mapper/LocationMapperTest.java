package c.e.exper.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@SpringBootTest
class LocationMapperTest {

    private final LocationMapper mapper;

    @Autowired
    LocationMapperTest(LocationMapper mapper) {
        this.mapper = mapper;
      }


    @Test
    void insertDuserLocation() {
        System.out.println(mapper.insertLocation("2", "102", 35.891289, 128.621547));
    }

    @Test
    void updateDuserLocation() {
        System.out.println(mapper.updateLocation(1, 2, "2", "102"));
    }

    @Test
    void selectDuserLocation() {

    }

    @Test
    void locationExist() {
        System.out.println(mapper.locationExist("2", "109"));
    }

    @Test
    void findNearPlace() {
        double longitude = 128.620954;
        double latitude = 35.90621;
        List<String> nearPlaces = mapper.findNearPlace(longitude-0.05, longitude+0.05,
                latitude-0.05, latitude+0.05);
        nearPlaces.forEach(System.out::println);
        System.out.println("nearPlaces Size: " + nearPlaces.size());
    }

    @Test
    void findDuserLocation() {
        System.out.println(mapper.findDuserLocation("sw"));
    }
}