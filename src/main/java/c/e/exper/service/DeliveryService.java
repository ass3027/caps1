package c.e.exper.service;

import c.e.exper.mapper.DeliveryMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class DeliveryService {

    final DeliveryMapper deliveryMapper;

    public DeliveryService(DeliveryMapper deliveryMapper) {
        this.deliveryMapper = deliveryMapper;
    }

    public void 운송원_위치_업데이트(String user_id, String x, String y) {

        deliveryMapper.updateDuserLatitude(x, user_id);
        deliveryMapper.updateDuserLongitude(y, user_id);

    }

    public Map<String, String> 운송원_위치_조회(String user_id) {
        Map<String, String> location = new HashMap<>();
        location.put("latitude", deliveryMapper.findLatitudeById(user_id));
        location.put("longitude", deliveryMapper.findLongitudeById(user_id));

        return location;
    }

    public String 주문서_운송원ID_조회(String ord_id) {
        return deliveryMapper.findDuserIdByOrdId(ord_id);
    }

}