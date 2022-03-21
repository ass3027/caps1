package c.e.exper.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api")
public class HotelController {

    @GetMapping("/hotel")
    public String hotel(){
        return "hotel";
    }
}
