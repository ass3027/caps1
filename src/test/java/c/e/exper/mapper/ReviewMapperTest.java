package c.e.exper.mapper;

import c.e.exper.data.Review;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class ReviewMapperTest {

    @Autowired
    private ReviewMapper mapper;


    // 주문서 리뷰 등록
    @Test
    public void addOrderReviewTest() {

        Review review = Review.builder()
                .user_id("1")
                .rev_content("넌 그냥 나가라")
                .rev_rating(1)
                .ord_id(2)
                .build();

        mapper.addOrderReview(review);
    }

    @Test
    public void addBookReviewTest() {

        Review review = Review.builder()
                .user_id("이세진")
                .rev_content("넌 그냥 들어와라")
                .rev_rating(1)
                .book_id(1)
                .build();

        mapper.addBookReview(review);
    }

    @Test
    public void selectReview() {
        Review review = mapper.selectReview("107");
        System.out.println(review);
    }

    @Test
    public void findAllReviewForReview() {
        List<Review> allReviewForReview = mapper.findAllReviewForReview("102");

        allReviewForReview.forEach(review -> System.out.println(review));
    }

    @Test
    public void findAllReviewForProduct() {
        List<Review> allReviewForProduct = mapper.findAllReviewForProduct("100");

        allReviewForProduct.forEach(review -> System.out.println(review));

    }



}
