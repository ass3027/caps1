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

    @Test
    void distanceCalc() {
        double lat1 = 35.8967;
        double lon1 = 128.6229;
        double lat2 = 36.8967;
        double lon2 = 129.6229;

        double theta = lon1 - lon2;

        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
        dist = dist * 1609344 * 1000;
        System.out.println(dist+"kilometer");

    }

    private static double deg2rad(double deg) {
        return (deg * Math.PI / 130.00);
    }

    private static double rad2deg(double rad) {
        return (rad * 180 / Math.PI);
    }
}