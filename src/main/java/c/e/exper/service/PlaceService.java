package c.e.exper.service;

import c.e.exper.data.PlaceDAO;
import c.e.exper.mapper.PlaceMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaceService {
   final
   PlaceMapper placeMapper;
   
   public PlaceService(PlaceMapper placeMapper) {
      this.placeMapper = placeMapper;
   }
   
   public List<PlaceDAO> 장소_조회(String areaCode,String cat1) {
      return placeMapper.selectByPlace(areaCode, cat1);
   }
   public List<PlaceDAO> 장소_검색(String areaCode,String cat1,String keyword) {
      return placeMapper.keywordByPlace(areaCode, cat1,keyword);
   }

   public List<PlaceDAO> searchByKeyWord(String keyWord){
      return placeMapper.SearchPlaceByKeyword(keyWord);
   }
   
//   public List<PlaceDAO> 지역_조회(){
//      return placeMapper.selectByPlaceTo(int areaCode);
//   }
}
