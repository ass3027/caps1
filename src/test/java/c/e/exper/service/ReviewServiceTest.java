package c.e.exper.service;

import c.e.exper.data.Review;
import c.e.exper.mapper.ReviewMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ReviewServiceTest {

    @Autowired ReviewService reviewService;
    @Autowired ReviewMapper reviewMapper;


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


}