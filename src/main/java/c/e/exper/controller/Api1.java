package c.e.exper.controller;

import c.e.exper.data.User;
import c.e.exper.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class Api1{

    final
    UserMapper userMapper;

    public Api1(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @PostMapping("/join")
    public boolean join(@ModelAttribute User user) {
        if(userMapper.selectId(user.getUser_id()).isPresent()){
            return false;
        }
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        user.setUser_pw(
                bCryptPasswordEncoder.encode(
                        user.getUser_pw()
                )
        );
        userMapper.insert(user);
        return true;
    }
}

