package c.e.exper.controller;

import c.e.exper.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public boolean join(@RequestBody Map<String,Object> map) {
        String inputId = map.get("userId").toString();
        String inputPw = map.get("passwd").toString();
        if(userMapper.selectId(inputId).isPresent()){
            return false;
        }
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        userMapper.insert(inputId,bCryptPasswordEncoder.encode(inputPw));
        return true;
    }
}

