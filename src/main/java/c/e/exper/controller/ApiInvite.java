package c.e.exper.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/invite")
@RestController
public class ApiInvite {

    @PostMapping("/")
    public boolean inviting(@RequestBody String id){

        return true;
    }
}
