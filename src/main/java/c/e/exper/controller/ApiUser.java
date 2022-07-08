package c.e.exper.controller;

import c.e.exper.data.*;
import c.e.exper.mapper.PictureMapper;
import c.e.exper.mapper.SuplMapper;
import c.e.exper.mapper.UserMapper;
import c.e.exper.service.FileService;
import c.e.exper.service.ReviewService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.util.*;
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

    final ReviewService reviewService;


    public ApiUser(UserMapper userMapper, ServletContext servletContext, SuplMapper suplMapper, FileService fileService, PictureMapper pictureMapper, ReviewService reviewService) {
        this.userMapper = userMapper;
        this.servletContext = servletContext;
        this.suplMapper = suplMapper;
        this.fileService = fileService;
        this.pictureMapper = pictureMapper;
        this.reviewService = reviewService;
    }

    public static String randomHangulName() {
        List<String> 성 = Arrays.asList("김", "이", "박", "최", "정", "강", "조", "윤", "장", "임", "한", "오", "서", "신", "권", "황", "안",
                "송", "류", "전", "홍", "고", "문", "양", "손", "배", "조", "백", "허", "유", "남", "심", "노", "정", "하", "곽", "성", "차", "주",
                "우", "구", "신", "임", "나", "전", "민", "유", "진", "지", "엄", "채", "원", "천", "방", "공", "강", "현", "함", "변", "염", "양",
                "변", "여", "추", "노", "도", "소", "신", "석", "선", "설", "마", "길", "주", "연", "방", "위", "표", "명", "기", "반", "왕", "금",
                "옥", "육", "인", "맹", "제", "모", "장", "남", "탁", "국", "여", "진", "어", "은", "편", "구", "용");
        List<String> 이름 = Arrays.asList("가", "강", "건", "경", "고", "관", "광", "구", "규", "근", "기", "길", "나", "남", "노", "누", "다",
                "단", "달", "담", "대", "덕", "도", "동", "두", "라", "래", "로", "루", "리", "마", "만", "명", "무", "문", "미", "민", "바", "박",
                "백", "범", "별", "병", "보", "빛", "사", "산", "상", "새", "서", "석", "선", "설", "섭", "성", "세", "소", "솔", "수", "숙", "순",
                "숭", "슬", "승", "시", "신", "아", "안", "애", "엄", "여", "연", "영", "예", "오", "옥", "완", "요", "용", "우", "원", "월", "위",
                "유", "윤", "율", "으", "은", "의", "이", "익", "인", "일", "잎", "자", "잔", "장", "재", "전", "정", "제", "조", "종", "주", "준",
                "중", "지", "진", "찬", "창", "채", "천", "철", "초", "춘", "충", "치", "탐", "태", "택", "판", "하", "한", "해", "혁", "현", "형",
                "혜", "호", "홍", "화", "환", "회", "효", "훈", "휘", "희", "운", "모", "배", "부", "림", "봉", "혼", "황", "량", "린", "을", "비",
                "솜", "공", "면", "탁", "온", "디", "항", "후", "려", "균", "묵", "송", "욱", "휴", "언", "령", "섬", "들", "견", "추", "걸", "삼",
                "열", "웅", "분", "변", "양", "출", "타", "흥", "겸", "곤", "번", "식", "란", "더", "손", "술", "훔", "반", "빈", "실", "직", "흠",
                "흔", "악", "람", "뜸", "권", "복", "심", "헌", "엽", "학", "개", "롱", "평", "늘", "늬", "랑", "얀", "향", "울", "련");
        Collections.shuffle(성);
        Collections.shuffle(이름);
        return 성.get(0) + 이름.get(0) + 이름.get(1);
    }

    @GetMapping("/find")
    public UserDAO findUser() {
        String user_id = SecurityContextHolder.getContext().getAuthentication().getName();
        System.out.println("user_id = " + user_id);
        if(user_id.equals("anonymousUser")) {
            return UserDAO.builder()
                    .user_id("anonymousUser")
                    .role("")
                    .build();
        }
        return userMapper.selectId(user_id).get();
    }

    @GetMapping("/id")
    public String getId() {

        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    @GetMapping("/photo")
    public String getUserPicture() {
        String user_id = SecurityContextHolder.getContext().getAuthentication().getName();
        return pictureMapper.selectPicnameByUserId(user_id);
    }

    //마이페이지
    @GetMapping("/data/{id}")
    public UserDTO getUserInfoById(@PathVariable String id) {
        //반환데이터형식

        Optional<UserDTO> userInfo = userMapper.getDeliveryInfoById(id);

        return userInfo.orElseGet(UserDTO::new);
    }

    //아이디 중복 확인
    @GetMapping("/userid")
    public boolean getUserIdCheck( //반환타입,함수명
        @RequestParam("user_id") String pp){ //프론트에서 user_id 받아옴
        Optional<UserDAO> id = userMapper.selectId(pp); //
        return id.isEmpty();
//        if (id.isEmpty()){
//            return true;
//        }else {
//            return false;
//        }
    }

    //내정보수정
    @PostMapping("/dataUpdate")
    public boolean getUserdataUpdate(UserDAO userDAO) {
        userDAO.setUser_pw(
                passwordEncoder().encode(
                        userDAO.getUser_pw()
                )
        );
        return userMapper.updateUserInfo(userDAO);
    }

    //지역별 추이
    @GetMapping("/area")
    public List<AreaDTO> getUserArea5(){
        System.out.print(userMapper.selectAreaCount());
        return userMapper.selectAreaCount();
    }

    //연령별(10대)
    @GetMapping("/age")
    public UserDAO getUserAge(){return userMapper.selectAgeCount10();}

    //연령별(20대)
    @GetMapping("/age20")
    public UserDAO getUserAge20(){return userMapper.selectAgeCount20();}

    //연령별(30대)
    @GetMapping("/age30")
    public UserDAO getUserAge30(){return userMapper.selectAgeCount30();}

    //연령별(40대)
    @GetMapping("/age40")
    public UserDAO getUserAge40(){return userMapper.selectAgeCount40();}

    //연령별(50대)
    @GetMapping("/age50")
    public UserDAO getUserAge50(){return userMapper.selectAgeCount50();}

    @GetMapping("/search")
    public List<InquiryDAO> getUserSearch(@RequestParam("keyword")String keyword){
        return userMapper.selectSearch(keyword);
    }


    //회원가입
    @PostMapping("/join/{check}")
    public boolean join(UserDTO user, HttpServletRequest req, @PathVariable("check") String check) {


        System.out.println(check);
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
        if (!Pattern.matches("^010[0-9]{8}$", user.getUser_phone())) {
            user.setUser_phone(getRandomPhone());
            System.out.println("[user_phone:]" + user.getUser_phone());
        }
        // 유저 이름이 형식에 안 맞을 경우 랜덤 값(2~5글자 사이의 한글)
        if (!Pattern.matches("^[가-힣]{2,5}$", user.getUser_name())) {
            user.setUser_name(randomHangulName());
        }


        //파일 이름 저장 밑 파일 실제 저장
        //경로 이상함
        String filePath = fileService.photoSave(user.getUser_photo(), req, "userImage");

        System.out.println("[filePath]" + filePath);

        //파일 경로를 넣은 DAO 생성
        UserDAO daoUser = UserDAO.builder()
                .user_id(user.getUser_id())
                .user_pw(user.getUser_pw())
                .user_birth(user.getUser_birth())
                .user_name(user.getUser_name())
                .user_phone(user.getUser_phone())

                .role(user.getRole())
                .gender(user.getGender())
                .preference(user.getPreference())
                .guser_intro("")
                .business_num(user.getBusiness_num())
                .user_area(user.getUser_area())
                .build();

        System.out.println(daoUser);
        userMapper.insert(daoUser);


        if (check.equals("운송원")) {
            DuserDAO daoDuser = DuserDAO.builder()
                    .user_id(user.getUser_id())
                    .duser_license(user.getDuser_license())
                    .duser_trans(user.getDuser_trans())
                    .build();
            userMapper.deliveryInsert(daoDuser);
        }


        //사진 DAO 생성 및 값 설정
        PictureDAO pictureDAO = new PictureDAO();
        pictureDAO.setPic_name(filePath);
        pictureDAO.setUser_id(user.getUser_id());

        pictureMapper.InsertUser(pictureDAO);

        return true;
    }

    @GetMapping("/orders")
    public List<OrderDAO> getUserOrders(String user_id) {

        return userMapper.selectUserOrders(user_id);
    }

    @PostMapping("/apiTest")
    public void apiTest(@RequestBody List<PlaceDAO> data) {

//        data.forEach( function(it) {
//            userMapper.insertPlace(it);
//            count++;
//        });
//        int i;
//        for(i = 0; i < data.size(); i++) {
//            userMapper.insertPlace(data.get(i));
//
//        }
//        System.out.println("count: " + i);
    }



    public String getRandomPhone() {

        char[] charaters = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};

        StringBuffer sb = new StringBuffer();
        sb.append('0');
        sb.append('1');
        sb.append('0');


        Random rn = new Random();

        for (int i = 0; i < 8; i++) {

            sb.append(charaters[rn.nextInt(charaters.length)]);

        }

        return sb.toString();

    }


}

