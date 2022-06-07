package c.e.exper.controller;

import c.e.exper.data.PictureDAO;
import c.e.exper.data.PlaceDAO;
import c.e.exper.data.StoreDAO;
import c.e.exper.data.StoreDTO;
import c.e.exper.mapper.PlaceMapper;
import c.e.exper.mapper.StoreMapper;
import c.e.exper.mapper.PictureMapper;
import c.e.exper.service.FileService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
    PlaceMapper placeMapper;

    public ApiPlace(StoreMapper storeMapper, FileService fileService, PictureMapper pictureMapper, PlaceMapper placeMapper) {
        this.storeMapper = storeMapper;
        this.fileService = fileService;
        this.pictureMapper = pictureMapper;
        this.placeMapper = placeMapper;
    }

    @GetMapping("/place/{category}")
    public List<PlaceDAO> getStoreByCategory(@PathVariable String category){
        return storeMapper.selectByCategory(category);
    }

    @GetMapping("/getHotel")
    public List<PlaceDAO> hotel(){
        System.out.println("컨트롤러임");

        return storeMapper.findAll();
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

    @PostMapping("/hoteladd")
    public boolean hoteladd(StoreDTO store, HttpServletRequest req){

        System.out.println("호텔 추가 컨트롤러임");

        String filePath = fileService.photoSave(store.getPic_name(), req,"storeImage");

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

    @GetMapping("/keep/find")
    public PlaceDAO findKeep(String keep_id){

        System.out.println("[/api/keep/find] "+keep_id);
        return placeMapper.findKeep(keep_id);
    }
}
