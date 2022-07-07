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

    @Autowired
    private UserMapper userMapper;



    // 주문서 리뷰 등록
    @Test
    public void addOrderReviewTest() {

        Review review = Review.builder()
                .user_id("1")
                .rev_content("넌 그냥 나가라")
                .build();

        mapper.addOrderReview(review);
    }

    @Test
    public void addBookReviewTest() {

        Review review = Review.builder()
                .user_id("이세진")
                .rev_content("넌 그냥 들어와라")
                .build();

        mapper.addBookReview(review);
    }

    @Test
    public void selectReview() {
        Review review = mapper.selectReview("107");
        System.out.println(review);
    }

    @Test
    public void findAllReviewForKeep() {
        List<Review> reviews = mapper.findAllReviewForKeep("100");
        System.out.println(reviews);
    }

    @Test
    public void findAllReviewForProduct() {
        List<Review> allReviewForProduct = mapper.findAllReviewForProduct("100");

        allReviewForProduct.forEach(review -> System.out.println(review));

    }

    @Test
    public void findAllReviewForDelivery() {
        List<Review> allReviewForDelivery = mapper.findAllReviewForDelivery("100");

        allReviewForDelivery.forEach(review -> System.out.println(review));
    }

    @Test
    public void updateReview() {
        Review review = Review.builder()
                .rev_id("100")
                .rev_content("ABCDEFGHIJKLMNOPQRSTUVWOXYZ")
                .build();

        System.out.println(review);


        System.out.println("수정 컬럼 수: " + mapper.updateReview(review));
    }

    @Test
    public void findAllReviewForStore() {


        mapper.findAllReviewForStore("편안 호텔").forEach(System.out::println);
    }

    @Test
    public void testtest() {

    }




}
