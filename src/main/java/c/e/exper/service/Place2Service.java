package c.e.exper.service;

import c.e.exper.data.PlaceDAO;
import c.e.exper.mapper.PlaceMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class Place2Service {
   final
   PlaceMapper placeMapper;

   static String[] hotelCode = {"B02010100", "B02010200", "B02010300", "B02010400", "B02010500", "B02010600", "B02011500"};
   static String[] motelCode = {"B02010900"};

   public Place2Service(PlaceMapper placeMapper) {
      this.placeMapper = placeMapper;
   }
   
//   public List<PlaceDAO> 장소_조회() {
//      return placeMapper.selectByPlace();
//   }


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
}
