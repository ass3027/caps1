package c.e.exper.service;

import c.e.exper.data.DuserDAO;
import c.e.exper.data.OrderDAO;
import c.e.exper.mapper.DeliveryMapper;
import c.e.exper.mapper.LocationMapper;
import c.e.exper.mapper.OrdersMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class DeliveryService {

    final DeliveryMapper deliveryMapper;
    final LocationMapper locationMapper;

    final OrdersMapper ordersMapper;

    public DeliveryService(DeliveryMapper deliveryMapper, LocationMapper locationMapper, OrdersMapper ordersMapper) {
        this.deliveryMapper = deliveryMapper;
        this.locationMapper = locationMapper;
        this.ordersMapper = ordersMapper;
    }

    public void 운송원_위치_업데이트(String user_id, double x, double y) {

        if(locationMapper.locationExist("2", user_id))
            locationMapper.updateLocation(x, y, "2", user_id);
        else
            locationMapper.insertLocation("2", user_id, x, y);

    }

    public Map<String, BigDecimal> 운송원_위치_조회(String user_id) {

        Map<String, BigDecimal> stringDoubleMap = locationMapper.findDuserLocation(user_id);

        System.out.println("[DeliveryService]" + stringDoubleMap);
        return stringDoubleMap;
    }

    public String 주문서_운송원ID_조회(String ord_id) {
        return deliveryMapper.findDuserIdByOrdId(ord_id);
    }


    public List<DuserDAO> 근처_운송원_조회(double x, double y) {
        return null;
    }


    public List<OrderDAO> 근처_주문서_조회(String user_id) {
        // 위치 얻고
        // 근처의 키퍼 얻고
        // 주문서에서 where 키퍼, 주문상태
        Map<String, BigDecimal> user_location = locationMapper.findDuserLocation(user_id);
        System.out.println(user_location);

        double longitude = Double.parseDouble(String.valueOf(user_location.get("LONGITUDE"))); // +- 경도 10분(1분 = 1.48km)
        double latitude = Double.parseDouble(String.valueOf(user_location.get("LATITUDE"))); // +- 위도 10분(1분 = 1.85km)


        return ordersMapper.findNearOrders(longitude - 0.05, longitude + 0.05, latitude - 0.05, latitude + 0.05);
    }
}