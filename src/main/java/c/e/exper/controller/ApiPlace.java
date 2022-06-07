package c.e.exper.controller;

import c.e.exper.data.*;
import c.e.exper.mapper.PlaceMapper;
import c.e.exper.mapper.StoreMapper;
import c.e.exper.mapper.PictureMapper;
import c.e.exper.service.FileService;
import c.e.exper.service.Place2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ApiPlace {
   
   final
   StoreMapper storeMapper;
   
   final
   FileService fileService;
   
   final
   PictureMapper pictureMapper;
   
   final
   Place2Service place2Service;
   
   public ApiPlace(StoreMapper storeMapper, FileService fileService, PictureMapper pictureMapper, Place2Service place2Service) {
      this.storeMapper = storeMapper;
      this.fileService = fileService;
      this.pictureMapper = pictureMapper;
      this.place2Service = place2Service;
   }
   
   @GetMapping("/place/{category}")
   public List<PlaceDAO> getPlaceByCategory(@PathVariable String category) {
      System.out.println("장소 카테고리");
      System.out.println(category);
      System.out.println(place2Service.카테고리별_조회(category));
      return place2Service.카테고리별_조회(category);
   }
   
   @GetMapping("/getHotel")
   public List<PlaceDAO> hotel() {
      return storeMapper.findAll();
   }
   
   @GetMapping("/place2")
   public List<PlaceDAO> placeList() {
      System.out.println("장소조회1111111");
      return place2Service.장소_조회();
   }

//    @GetMapping("/getHotelPic")
//    public List<PictureDAO> hotelPic(){
//        System.out.println("호텔 사진 컨트롤러임");
//        List<PictureDAO> data = storeMapper.selectStorePic();
//        System.out.println(data.size());
//        for(int a=0;a<data.size();a++){
//            System.out.println(data.get(a));
//        }
//
//        return data;
//    }
   
   @PostMapping("/placeadd")
   public boolean placeadd(StoreDTO store, HttpServletRequest req) {
      
      System.out.println("호텔 추가 컨트롤러임");
      
      String filePath = fileService.photoSave(store.getPic_name(), req, "storeImage");
      
      StoreDAO storeDAO = StoreDAO.builder()
            .store_id(store.getStore_id())
            .user_id(store.getUser_id())
            .pl_id(store.getPl_id())
            .store_name(store.getStore_name())
            .store_phone(store.getStore_phone())
            .category(store.getCategory())
            .pic_name(filePath)
            .build();
      
      System.out.println(storeDAO);
      
      storeMapper.insert(storeDAO);
      
      System.out.println("사진추가");
      
      PictureDAO pictureDAO = new PictureDAO();
      pictureDAO.setPic_name(filePath);
      pictureDAO.setStore_id(storeDAO.getStore_id());
      
      pictureMapper.InsertStore(pictureDAO);
      
      return true;
   }
}
