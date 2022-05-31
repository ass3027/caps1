package c.e.exper.service;

import c.e.exper.data.PlaceDAO;
import c.e.exper.mapper.PlaceMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Place2Service {
   final
   PlaceMapper placeMapper;
   
   public Place2Service(PlaceMapper placeMapper) {
      this.placeMapper = placeMapper;
   }
   
   public List<PlaceDAO> 장소_조회(String areacode,String cat1) {
      return placeMapper.selectByPlace(areacode, cat1);
   }
//   public List<PlaceDAO> 지역_조회(){
//      return placeMapper.selectByPlaceTo(int areacode);
//   }
}
