package c.e.exper.controller;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiLocation {

    @PostMapping("/location")
    public void printLocation(String latitude, String longitude) {
        System.out.println(latitude + " " + longitude);
    }
}
