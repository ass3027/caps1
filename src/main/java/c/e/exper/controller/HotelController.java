package c.e.exper.controller;

import c.e.exper.data.StoreDAO;
import c.e.exper.data.StoreDTO;
import c.e.exper.mapper.HotelMapper;
import c.e.exper.service.FileSaveService;
import org.apache.catalina.Store;
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
    private FileSaveService fileSaveService;


    @GetMapping("/getHotel")
    public List<StoreDAO> hotel(){
        System.out.println("컨트롤러임");
        System.out.println(HotelMapper.findAll());
        return HotelMapper.findAll();
    }

    @PostMapping("/hotelladd")
    public boolean hoteladd(StoreDTO store, HttpServletRequest req){

        System.out.println("호텔 추가 컨트롤러임");

        String filePath = fileSaveService.photoSave(store.getPic_name(), req);

        StoreDAO storeDAO = StoreDAO.builder()
                .store_id(store.getStore_id())
                .user_id(store.getUser_id())
                .pl_id(store.getPl_id())
                .store_phone(store.getStore_phone())
                .pic_name(filePath)
                .build();

        HotelMapper.insert(storeDAO);
        return true;
    }
}
