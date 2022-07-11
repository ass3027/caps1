package c.e.exper.mapper;

import c.e.exper.data.ReviewDTO;
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

        ReviewDTO review = ReviewDTO.builder()
                .user_id("1")
                .rev_content("넌 그냥 나가라")
                .build();

        mapper.addOrderReview(review);
    }

    @Test
    public void addBookReviewTest() {

        ReviewDTO review = ReviewDTO.builder()
                .user_id("이세진")
                .rev_content("넌 그냥 들어와라")
                .build();

        mapper.addBookReview(review);
    }

    @Test
    public void selectReview() {
        ReviewDTO review = mapper.selectReview("107");
        System.out.println(review);
    }

    @Test
    public void findAllReviewForKeep() {
        List<ReviewDTO> reviews = mapper.findAllReviewForKeep("100");
        System.out.println(reviews);
    }

    @Test
    public void findAllReviewForProduct() {
        List<ReviewDTO> allReviewForProduct = mapper.findAllReviewForProduct("100");

        allReviewForProduct.forEach(review -> System.out.println(review));

    }

    @Test
    public void findAllReviewForDelivery() {
        List<ReviewDTO> allReviewForDelivery = mapper.findAllReviewForDelivery("100");

        allReviewForDelivery.forEach(review -> System.out.println(review));
    }

    @Test
    public void updateReview() {
        ReviewDTO review = ReviewDTO.builder()
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


    @Test
    void findHotelReview() {
        List<ReviewDTO> hotelReview = mapper.findHotelReview("1");

        hotelReview.forEach(System.out::println);
    }

    @Test
    void hitCount() {

        System.out.println(mapper.hitCount("173"));
    }

    @Test
    void paymentReviewCheck() {
        System.out.println(mapper.paymentReviewCheck("173"));
    }

    @Test
    void findProductInfoHotel() {
        System.out.println(mapper.findProductInfoHotel("238"));
    }
}
