package c.e.exper.service;

import c.e.exper.data.Review;
import c.e.exper.mapper.ReviewMapper;
import c.e.exper.mapper.UserMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ReviewServiceTest {

    @Autowired ReviewService reviewService;
    @Autowired ReviewMapper reviewMapper;

    @Autowired UserMapper userMapper;


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


    @Test
    void test() {

        List<String> users_id = userMapper.selectAllId();
        users_id.forEach(i -> userMapper.updateUserPhone(getRandomPhone(), i));
    }

    @Test
    void 모든_리뷰_조회() {

        // given

        // when
        List<Review> reviewList = reviewService.모든_리뷰_조회();

        // then
        reviewList.forEach(review -> System.out.println(review));


    }

    @Test
    void 리뷰_등록() {

        // given
        Review review = Review.builder()
                .user_id("um")
                .rev_content("엄...")
                .rev_rating(3)
                .ord_id(2)
                .build();

        // when
        if(review.getBook_id() == null && review.getOrd_id() == null) {
            System.out.println("잘못된 형식입니다.");
        } else if (review.getBook_id() != null) {
            reviewMapper.addBookReview(review);
        } else {
            reviewMapper.addOrderReview(review);
        }

        // then
    }

    @Test
    void 리뷰_조회() {
        System.out.println(reviewMapper.selectReview("109"));
    }

    @Test
    void 리뷰_삭제() {
        System.out.println(reviewMapper.deleteReview("113"));
    }

    @Test
    void 리뷰아이디_리뷰_조회() {
//        System.out.println(reviewMapper.findAllReviewForReview("102"));
    }

    @Test
    void 정규식_테스트() {

        String val = "가나다라마1";

        if(!Pattern.matches("^[가-힣]{2,5}$", val)) {
            System.out.println(randomHangulName());
        } else{
            System.out.println(val);
        }

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





}