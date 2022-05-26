package c.e.exper.service;

import c.e.exper.data.DuserDAO;
import c.e.exper.mapper.DeliveryMapper;
import c.e.exper.mapper.LocationMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class DeliveryService {

    final DeliveryMapper deliveryMapper;
    final LocationMapper locationMapper;

    public DeliveryService(DeliveryMapper deliveryMapper, LocationMapper locationMapper) {
        this.deliveryMapper = deliveryMapper;
        this.locationMapper = locationMapper;
    }

    public void 운송원_위치_업데이트(String user_id, double x, double y) {

        if(locationMapper.locationExist("2", user_id))
            locationMapper.updateLocation(x, y, "2", user_id);
        else
            locationMapper.insertLocation("2", user_id, x, y);

    }

    public Map<String, BigDecimal> 운송원_위치_조회(String user_id) {

        Map<String, BigDecimal> stringDoubleMap = locationMapper.selectLocation("2", user_id);

        System.out.println("[DeliveryService]" + stringDoubleMap);
        return stringDoubleMap;
    }

    public String 주문서_운송원ID_조회(String ord_id) {
        return deliveryMapper.findDuserIdByOrdId(ord_id);
    }


    public List<DuserDAO> 근처_운송원_조회(double x, double y) {
        locationMapper
    }
}