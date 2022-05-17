package c.e.exper.controller;

import c.e.exper.data.InquiryDAO;
import c.e.exper.data.PictureDAO;
import c.e.exper.data.UserDAO;
import c.e.exper.data.UserDTO;
import c.e.exper.mapper.InquiryMapper;
import c.e.exper.mapper.PictureMapper;
import c.e.exper.mapper.SuplMapper;
import c.e.exper.mapper.UserMapper;
import c.e.exper.service.FileService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

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
    FileService fileService;

    final
    InquiryMapper inquiryMapper;

    public ApiUser(UserMapper userMapper, ServletContext servletContext, SuplMapper suplMapper, FileService fileService, PictureMapper pictureMapper,InquiryMapper inquiryMapper) {
        this.userMapper = userMapper;
        this.servletContext = servletContext;
        this.suplMapper = suplMapper;
        this.fileService = fileService;
        this.pictureMapper = pictureMapper;
        this.inquiryMapper = inquiryMapper;
    }

    @GetMapping("/exper")
    public String exper() {

        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    @GetMapping("/user")
    public String getUserPicture(){
        String user_id = SecurityContextHolder.getContext().getAuthentication().getName();
        return pictureMapper.selectByUserId(user_id);

    }

    @GetMapping("/data/{userId}")
    public UserDAO getUserInfo(@PathVariable String userId){
        return userMapper.selectId(userId).get();
        //void=>아무것도 반환하지 않을때
    }

    @PostMapping("/MyData")
    public boolean getUserUpdate(UserDAO userDAO){
        System.out.println(userDAO);
        userDAO.setUser_pw(
                passwordEncoder().encode(
                        userDAO.getUser_pw()
                )
        );
        boolean result = userMapper.updateUserInfo(userDAO);
        return result;

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
        String filePath = fileService.photoSave(user.getUser_photo(),req,"userImage");

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

