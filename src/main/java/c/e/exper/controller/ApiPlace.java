package c.e.exper.controller;

import c.e.exper.data.PictureDAO;
import c.e.exper.data.PlaceDAO;
import c.e.exper.data.StoreDAO;
import c.e.exper.data.StoreDTO;
import c.e.exper.mapper.PlaceMapper;
import c.e.exper.mapper.StoreMapper;
import c.e.exper.mapper.PictureMapper;
import c.e.exper.service.FileService;
import c.e.exper.data.*;
import c.e.exper.mapper.PlaceMapper;
import c.e.exper.mapper.StoreMapper;
import c.e.exper.mapper.PictureMapper;
import c.e.exper.service.FileService;
import c.e.exper.service.Place2Service;
import c.e.exper.service.PlaceService;
import org.apache.commons.io.FilenameUtils;
import org.apache.ibatis.annotations.Select;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;

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
   PlaceService placeService;
   
   final PlaceMapper placeMapper;
   
   final Place2Service place2Service;
   
   public ApiPlace(StoreMapper storeMapper, FileService fileService, PictureMapper pictureMapper, PlaceService placeService, PlaceMapper placeMapper, Place2Service place2Service) {
      this.storeMapper = storeMapper;
      this.fileService = fileService;
      this.pictureMapper = pictureMapper;
      this.placeService = placeService;
      this.placeMapper = placeMapper;
      this.place2Service = place2Service;
   }
   
   private String getUserId() {
      return SecurityContextHolder.getContext().getAuthentication().getName();
   }
   
   @GetMapping("/place1/{category}")
   public List<PlaceDAO> getPlaceByCategory(@PathVariable String category) {
      return placeService.카테고리별_조회(category);
   }
   
   @GetMapping("/place1/{category}/{areaCode}")
   public List<PlaceDAO> getPlaceByCategory(@PathVariable String category, @PathVariable String areaCode) {
      return placeService.카테고리_지역_조회(category, areaCode);
   }
   
   @GetMapping("/place1/{category}/{areaCode}/{keyword}")
   public List<PlaceDAO> getKeyword(@PathVariable String category, @PathVariable String keyword) {
      return placeService.키보드_지역_조회(category, keyword);
   }

//   @GetMapping("/place/{category}")
//   public List<PlaceDAO> getPlaceByCategory(@PathVariable String category) {
//      System.out.println("장소 카테고리");
//      System.out.println(category);
//      System.out.println(placeService.카테고리별_조회(category));
//      return placeService.카테고리별_조회(category);
//   }

//   @GetMapping("/getHotel")
//   public List<PlaceDAO> hotel() {
//      return storeMapper.findAll();
//   }
   
   @GetMapping("/place/{areaCode}/{cat1}")
   public List<PlaceDAO> placeList(@PathVariable String areaCode, @PathVariable String cat1) {
      System.out.println("장소조회1111111");
      return placeService.장소_조회(areaCode, cat1);
   }
   
   @GetMapping("/placeTour/{areaCode}/{contenttypeid}")
   public List<PlaceDAO> placeListTour(@PathVariable String areaCode, @PathVariable String contenttypeid) {
      System.out.println("관광지조회");
      return placeService.장소_조회2(areaCode, contenttypeid);
   }
   
   @GetMapping("/place/{areaCode}/{cat1}/{keyword}")
   public List<PlaceDAO> keywordList(@PathVariable String areaCode, @PathVariable String cat1, @PathVariable("keyword") String keyword) {
      System.out.println("/place/{areaCode}/{cat1}/{keyword} : " + keyword);
      return placeService.장소_검색(areaCode, cat1, keyword);
   }
   
   @GetMapping("/placeA01/{contenttypeid}")
   public List<PlaceDAO> keywordByPlaceA01(@PathVariable("contenttypeid") String contenttypeid,
                                           @RequestParam("keyword") String keyword) {
      System.out.println("/placeA01/" + contenttypeid + keyword);
      return placeMapper.keywordByPlaceA01(contenttypeid, keyword);
   }
   
   @GetMapping("/placeTour/{areaCode}/{contenttypeid}/{keyword}")
   public List<PlaceDAO> keywordTourList(@PathVariable String areaCode, @PathVariable String contenttypeid, @PathVariable("keyword") String keyword) {
      System.out.println("/place/{areaCode}/{contenttypeid}/{keyword} : " + keyword);
      return placeService.장소_검색Tour(areaCode, contenttypeid, keyword);
   }
   
   @GetMapping("/place/{keyWord}")
   public List<PlaceDAO> getListByKeyword(@PathVariable String keyWord) {
      return placeService.searchByKeyWord(keyWord);
   }
   
   @PostMapping("/placeAdd")
   public boolean placeAdd(@ModelAttribute PlaceDAO placeAdd, HttpServletRequest req) throws IOException {
      System.out.println("호텔 추가 컨트롤러임");
      System.out.println(placeAdd.getStore_id());
      String filePath = fileService.photoSave(placeAdd.getImage(), req, "placeImage");
      System.out.println("[filePath]" + filePath);
      
      //사진 경로 설정
      PlaceDAO placeDAO = PlaceDAO.builder()
            .store_id(placeAdd.getStore_id())
            .user_id(placeAdd.getUser_id())
            .addr1(placeAdd.getAddr1())
            .areacode(placeAdd.getAreacode())
            .tel(placeAdd.getTel())
            .title(placeAdd.getTitle())
            .zipcode(placeAdd.getZipcode())
            .firstimage("/api/photo/" + filePath)
            .build();
      placeMapper.placeAdd(placeDAO);
      //이미지 주소
      //이미지 주소를 만든다. webApp 파일에 넣을 수 있게 경로랑 이름을 설정한다.
//      String filePath = req.getServletContext().getRealPath("/placeImage/" + placeAdd.getImage().getOriginalFilename());
//      //이미지 파일
//      //file 클래스를 써서 파일을 생성하고 이미지 주소로 설정한 변수를 매개변수로 넣어주고
//      // file은 multipartFile로 타입을 설정하고 placeAdd에 있는 image로 설정한다.
//      File destinationFile = new File(filePath);
//      MultipartFile file = placeAdd.getImage();
//      file.transferTo(destinationFile);

//
//      System.out.println("filePath = " + filePath);
//      System.out.println(placeAdd.getImage().getOriginalFilename());
      return true;
   }
   
   
   @GetMapping("/place2/{category}/{option}")
   public List<PlaceDAO> placeOption(@PathVariable String category, @PathVariable String option) {
      return placeService.카테고리_옵션_조회(category, option);
   }
   
   //키퍼회원 자신이 등록한 장소조회
   @GetMapping("/placeCheck")
   public List<PlaceDAO> placeCheck() {
      return placeService.placeCheck(getUserId());
   }
   
   //키퍼회원 등록한 장소 상세보기
   @GetMapping("/placeDetail/{pl_id}")
   public PlaceDAO placeDetail(@PathVariable String pl_id) {
      return placeService.placeDetail(pl_id);
   }
   
}
//   @GetMapping("/place/{areaCode}/{cat1}/{pageNumber}")
//   public List<PlaceDAO> placeCount(@PathVariable String areaCode,@PathVariable String cat1,@PathVariable int pageNumber) {
//      System.out.println("갯수");
//      return placeService.장소_조회(areaCode,cat1,pageNumber);
//   }

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


//   @PostMapping("/placeAdd")
//   public boolean placeadd(StoreDTO store, HttpServletRequest req) {
//
//      System.out.println("호텔 추가 컨트롤러임");
//
//      String filePath = fileService.photoSave(store.getPic_name(), req, "storeImage");
//
//      StoreDAO storeDAO = StoreDAO.builder()
//            .store_id(store.getStore_id())
//            .user_id(store.getUser_id())
//            .pl_id(store.getPl_id())
//            .store_name(store.getStore_name())
//            .store_phone(store.getStore_phone())
//            .category(store.getCategory())
//            .pic_name(filePath)
//            .build();
//
//      System.out.println(storeDAO);
//
//      storeMapper.insert(storeDAO);
//
//      System.out.println("사진추가");
//
//      PictureDAO pictureDAO = new PictureDAO();
//      pictureDAO.setPic_name(filePath);
//      pictureDAO.setStore_id(storeDAO.getStore_id());
//
//      pictureMapper.InsertStore(pictureDAO);
//
//      return true;
//   }


   // Left 컴포넌트 빈 방
   @GetMapping("/datePic")
   public List<PlaceDAO> datePic(String settingstart, String settingend){
      return placeMapper.datePic(settingstart, settingend);
   }
}


