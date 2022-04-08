package c.e.exper.controller;

import c.e.exper.data.PictureDAO;
import c.e.exper.data.UserDAO;
import c.e.exper.data.UserDTO;
import c.e.exper.mapper.PictureMapper;
import c.e.exper.mapper.SuplMapper;
import c.e.exper.mapper.UserMapper;
import c.e.exper.service.FileSaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.security.Security;
import java.util.List;
import java.util.Map;

import static c.e.exper.service.SecurityConfig.passwordEncoder;

@RestController
@RequestMapping("/api/")
public class ApiUser {

    final
    PictureMapper pictureMapper;
    final
    SuplMapper suplMapper;

    final
    ServletContext servletContext;

    final
    UserMapper userMapper;

    final
    FileSaveService fileSaveService;

    public ApiUser(UserMapper userMapper, ServletContext servletContext, SuplMapper suplMapper, FileSaveService fileSaveService, PictureMapper pictureMapper) {
        this.userMapper = userMapper;
        this.servletContext = servletContext;
        this.suplMapper = suplMapper;
        this.fileSaveService = fileSaveService;
        this.pictureMapper = pictureMapper;
    }

    @GetMapping("/exper")
    public String exper() {

        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
    

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
                .role(user.getRole())
                .build();

        userMapper.insert(daoUser);

        //사진 DAO 생성 및 값 설정
        PictureDAO pictureDAO = new PictureDAO();
        pictureDAO.setPic_name(filePath);
        pictureDAO.setUser_id(user.getUser_id());

        pictureMapper.InsertUser(pictureDAO);

        return true;
    }




}

