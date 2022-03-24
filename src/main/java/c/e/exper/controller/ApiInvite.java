package c.e.exper.controller;

import c.e.exper.data.InviteDTO;
import c.e.exper.data.UserDAO;
import c.e.exper.data.UserDTO;
import c.e.exper.mapper.InviteMapper;
import c.e.exper.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;


@RestController
@RequestMapping("/api/")
public class ApiInvite {

    final
    UserMapper userMapper;

    final
    InviteMapper inviteMapper;

    public ApiInvite(UserMapper userMapper, InviteMapper inviteMapper) {
        this.userMapper = userMapper;
        this.inviteMapper = inviteMapper;
    }

    @PostMapping("/invite")
    public boolean inviting(@RequestBody InviteDTO inviteDTO){
        System.out.println(inviteDTO.getUser_id());
        Optional<UserDAO> userdata = userMapper.selectId(inviteDTO.getUser_id());
        if(userdata.isEmpty()) return false;

        inviteMapper.insert(inviteDTO.toDAO());

        return true;
    }

    @PostMapping("/plan")
    public boolean planMaking(String planName){


        return true;
    }
}
