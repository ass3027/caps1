package c.e.exper.controller;

import c.e.exper.data.PictureDAO;
import c.e.exper.data.StoreDAO;
import c.e.exper.data.StoreDTO;
import c.e.exper.mapper.HotelMapper;
import c.e.exper.mapper.PictureMapper;
import c.e.exper.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api")
public class HotelController {

    @Autowired
    HotelMapper HotelMapper;

    @Autowired
    FileService fileService;

    @Autowired
    PictureMapper pictureMapper;


    @GetMapping("/getHotel")
    public List<StoreDAO> hotel(){
        System.out.println("컨트롤러임");
        System.out.println(HotelMapper.findAll());
        return HotelMapper.findAll();
    }

    @PostMapping("/hotelladd")
    public boolean hoteladd(StoreDTO store, HttpServletRequest req){

        System.out.println("호텔 추가 컨트롤러임");

        String filePath = fileService.photoSave(store.getPic_name(), req,"storeImage");

        StoreDAO storeDAO = StoreDAO.builder()
                .store_id(store.getStore_id())
                .user_id(store.getUser_id())
                .pl_id(store.getPl_id())
                .store_phone(store.getStore_phone())
                .pic_name(filePath)
                .build();

        System.out.println(storeDAO);

        HotelMapper.insert(storeDAO);

        System.out.println("사진추가");

        PictureDAO pictureDAO = new PictureDAO();
        pictureDAO.setPic_name(filePath);
        pictureDAO.setStore_id(storeDAO.getStore_id());

        pictureMapper.InsertStore(pictureDAO);

        return true;
    }
}
