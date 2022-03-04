package c.e.exper.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Web1 {

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/join")
    public String join() {
        return "join";
    }


}