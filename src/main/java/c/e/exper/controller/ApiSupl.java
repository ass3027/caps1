package c.e.exper.controller;

import c.e.exper.data.Plan_Suplies;
import c.e.exper.data.Suplies;
import c.e.exper.data.UserDAO;
import c.e.exper.data.UserDTO;
import c.e.exper.mapper.SuplMapper;
import c.e.exper.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ApiSupl {

    final
    UserMapper userMapper;

    @Autowired
    SuplMapper SuplMapper;

    public ApiSupl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @GetMapping("/getMySupl")
    public List<Plan_Suplies> getMySupl() {
        System.out.println("컨트롤러안");
        System.out.println(SuplMapper.findMyAllSupl("1"));

        return SuplMapper.findMyAllSupl("1");
    }

    @GetMapping("/getSupl")
    public List<Suplies> getSupl() {
        System.out.println("컨트롤러안");
        System.out.println(SuplMapper.findAllSupl());

        return SuplMapper.findAllSupl();
    }

    @PostMapping("/inputSupl")
    public String inputSupl(@RequestBody Plan_Suplies p){
        System.out.println("post컨트롤러안");
        System.out.println(p);

        SuplMapper.insertSuplies(p);
        return "yep";
    }



}

