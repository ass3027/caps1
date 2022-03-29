package c.e.exper.controller;

import c.e.exper.data.UserDAO;
import c.e.exper.data.UserDTO;
import c.e.exper.mapper.SuplMapper;
import c.e.exper.mapper.UserMapper;
import c.e.exper.service.FileSaveService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

import static c.e.exper.service.SecurityConfig.passwordEncoder;

@RestController
@RequestMapping("/api")
public class ApiUser {

    final
    SuplMapper suplMapper;

    final
    ServletContext servletContext;

    final
    UserMapper userMapper;

    final
    FileSaveService fileSaveService;

    public ApiUser(UserMapper userMapper, ServletContext servletContext, SuplMapper suplMapper, FileSaveService fileSaveService) {
        this.userMapper = userMapper;
        this.servletContext = servletContext;
        this.suplMapper = suplMapper;
        this.fileSaveService = fileSaveService;
    }

//    @GetMapping("/exper")
//    public String exper(HttpServletRequest request) {
//        List<Map<String, Object>> ls = suplMapper.findAll();
//        for (Map<String, Object> l : ls) {
//            for (Map.Entry<String, Object> entry : l.entrySet()) {
//                System.out.println("[key]:" + entry.getKey() + ", [value]:" + entry.getValue());
//            }
//        }
//        return "aa";
//    }
    

    @PostMapping("/join")
    public boolean join(UserDTO user,HttpServletRequest req) {

        // id 중복확인
        if (userMapper.selectId(user.getUser_id()).isPresent()) {
            return false;
        }

        //passwordEncoder는 sercurity Config에서 임포트함
        user.setUser_pw(
                passwordEncoder().encode(
                        user.getUser_pw()
                )
        );

        //파일 이름 저장 밑 파일 실제 저장
        //경로 이상함
        String filePath = fileSaveService.photoSave(user.getUser_photo(),req);

        //파일 경로를 넣은 DAO 생성
        UserDAO daoUser = UserDAO.builder()
                .user_id(user.getUser_id())
                .user_pw(user.getUser_pw())
                .user_birth(user.getUser_birth())
                .user_name(user.getUser_name())
                .user_phone(user.getUser_phone())
                .user_photo(filePath)
                .role(user.getRole())
                .build();

        userMapper.insert(daoUser);
        return true;
    }
}

