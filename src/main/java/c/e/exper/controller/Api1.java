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
import java.util.Map;

@RestController
@RequestMapping("/api")
public class Api1 {

    final
    UserMapper userMapper;

    @Autowired
    SuplMapper SuplMapper;

    public Api1(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @GetMapping("/getSupl")
    public List<Plan_Suplies> getSupl() {
        System.out.println("컨트롤러안");
        System.out.println(SuplMapper.findAll("1"));
//        List<Map<String, Suplies>> ls =SuplMapper.findAll("1");
//        for(int a=0;a<ls.size();a++) {
//            for (Map.Entry<String, Suplies> entry : ls.get(a).entrySet()) {
//                System.out.println("[key]:" + entry.getKey() + ", [value]:" + entry.getValue());
//                System.out.println(entry.getValue().getSupl_name());
//            }
//        }

        return SuplMapper.findAll("1");
    }

    @PostMapping("/postSupl")
    public String postSupl(){

        return "yep";
    }

    @GetMapping("/exper")
    public String exper() {
        return "aa";
    }

    @PostMapping("/join")
    public boolean join(UserDTO user, HttpServletRequest req) {

        //id중복확인
        if (userMapper.selectId(user.getUser_id()).isPresent()) {
            return false;
        }
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        user.setUser_pw(
                bCryptPasswordEncoder.encode(
                        user.getUser_pw()
                )
        );

        //파일 이름 저장 밑 파일 실제 저장
        //경로 이상함
        String fileName = user.getUser_photo().getOriginalFilename();
        String safeFile = new File(this.getClass().getProtectionDomain().getCodeSource().getLocation().getPath()).getAbsolutePath() + "/webApp/userImage/" +  System.currentTimeMillis() + fileName;
        System.out.println(safeFile);
        try {
            user.getUser_photo().transferTo(new File(safeFile));

        } catch (Exception e) {
            e.printStackTrace();
        }

        //파일 경로를 넣은 DAO 생성
        UserDAO daoUser = UserDAO.builder()
                .user_id(user.getUser_id())
                .user_pw(user.getUser_pw())
                .user_birth(user.getUser_birth())
                .user_name(user.getUser_name())
                .user_phone(user.getUser_phone())
                .user_photo(safeFile)
                .role(user.getRole())
                .build();

        userMapper.insert(daoUser);
        return true;
    }


}

