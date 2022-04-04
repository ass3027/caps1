package c.e.exper.controller;

import c.e.exper.data.Place;
import c.e.exper.data.StoreDTO;
import c.e.exper.data.UserDTO;
import c.e.exper.data.Pictures;
import c.e.exper.mapper.HotelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.netty.http.server.HttpServerRequest;

import java.util.List;

@RestController
@RequestMapping("/api")
public class HotelController {

    @Autowired
    HotelMapper HotelMapper;


    @GetMapping("/getHotel")
    public List<Store> hotel(){
        System.out.println("컨트롤러임");
        System.out.println(HotelMapper.findAll());
        return HotelMapper.findAll();
    }

    @PostMapping("/hotelladd")
    public boolean hoteladd(StoreDTO store){

        System.out.println("호텔 추가 컨트롤러임");

        
        return true;
    }
}
