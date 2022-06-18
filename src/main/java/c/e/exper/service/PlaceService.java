package c.e.exper.service;

import c.e.exper.data.Place;
import c.e.exper.data.PlaceDAO;
import c.e.exper.mapper.PlaceMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PlaceService {
   final
   PlaceMapper placeMapper;

   static String[] hotelCode = {"B02010100", "B02010200", "B02010300", "B02010400", "B02010500", "B02010600", "B02011500"};
   static String[] motelCode = {"B02010900"};
   
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

   public List<PlaceDAO> 카테고리별_조회(String category) {
      List<PlaceDAO> categoryPlace = new ArrayList<>();

      switch (category) {
         case "hotel" -> {
            for (String hotel : hotelCode) {
               categoryPlace.addAll(placeMapper.findByCategory(hotel));
            }

         }
         case "motel" -> {
            for (String motel : motelCode) {
               categoryPlace.addAll(placeMapper.findByCategory(motel));
            }

         }
      }

      return categoryPlace;
   }

   public List<PlaceDAO> 카테고리_지역_조회(String category, String areaCode){
      return placeMapper.CategoryAreaCode(category, areaCode);
   }
//   public List<PlaceDAO> 지역_조회(){
//      return placeMapper.selectByPlaceTo(int areaCode);
//   }
}
