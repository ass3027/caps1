package c.e.exper.controller;

import c.e.exper.data.PictureDAO;
import c.e.exper.data.UserDAO;
import c.e.exper.data.UserDTO;
import c.e.exper.mapper.PictureMapper;
import c.e.exper.mapper.SuplMapper;
import c.e.exper.mapper.UserMapper;
import c.e.exper.service.FileService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;


import java.util.Random;
import java.util.regex.Pattern;

import static c.e.exper.service.SecurityConfig.passwordEncoder;

@RestController
@RequestMapping("/api/user")
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

    public ApiUser(UserMapper userMapper, ServletContext servletContext, SuplMapper suplMapper, FileService fileService, PictureMapper pictureMapper) {
        this.userMapper = userMapper;
        this.servletContext = servletContext;
        this.suplMapper = suplMapper;
        this.fileService = fileService;
        this.pictureMapper = pictureMapper;
    }

    @GetMapping("/find")
    public UserDAO findUser(String user_id) {
        return userMapper.selectId(user_id).get();
    }

    @GetMapping("/exper")
    public String exper() {

        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    @GetMapping("/photo")
    public String getUserPicture(){
        String user_id = SecurityContextHolder.getContext().getAuthentication().getName();
        System.out.println(user_id);
        return pictureMapper.selectPicnameByUserId(user_id);
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

        // 폰 번호가 형식에 안 맞을 경우 랜덤 값
        if ( !Pattern.matches("^010[0-9]{8}$", user.getUser_phone())) {
            user.setUser_phone(getRandomPhone());
            System.out.println("[user_phone:]" + user.getUser_phone());
        }

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


    public String getRandomPhone(){

        char[] charaters = {'0','1','2','3','4','5','6','7','8','9'};

        StringBuffer sb = new StringBuffer();
        sb.append('0');
        sb.append('1');
        sb.append('0');


        Random rn = new Random();

        for( int i = 0 ; i < 8 ; i++ ){

            sb.append( charaters[ rn.nextInt( charaters.length ) ] );

        }

        return sb.toString();

    }

}

