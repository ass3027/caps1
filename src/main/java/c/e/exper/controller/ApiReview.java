package c.e.exper.controller;


import c.e.exper.data.Review;
import c.e.exper.service.FileServiceImpl;
import c.e.exper.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ApiReview {

    private final ReviewService reviewService;
    private final FileServiceImpl fileService;

    @Autowired
    public ApiReview(ReviewService reviewService, FileServiceImpl fileService) {
        this.reviewService = reviewService;
        this.fileService = fileService;
    }



    @PostMapping("/addReview")
    public int addReview(Review review, HttpServletRequest req) {


        review.setUser_id("100");

        System.out.println("등록할 리뷰: "+ review);


        review.setRev_img_filename(fileService.photoSave(review.getRev_photo(), req, "reviewImage"));

        int insertColumnCount = reviewService.리뷰_등록(review);
        System.out.println("등록 컬럼 갯수: " + insertColumnCount);

        return insertColumnCount;
    }

    @GetMapping("/allReview")
    public List<Review> findAllReview() {

        System.out.println("모든 리뷰 조회");

        List<Review> allReview = reviewService.모든_리뷰_조회();

        if(allReview.isEmpty()){
            System.out.println("리뷰 없음");
        }

        return allReview;
    }

    @GetMapping("/productReview")
    public List<Review> findProductReview(String pd_id) {
        System.out.println(pd_id);

        return reviewService.상품아이디_리뷰_조회(pd_id);
    }

    @PutMapping("/updateReview")
    public void updateReview(Review review) {

        System.out.println(review);
        reviewService.리뷰_수정(review);
    }

    @PutMapping("/test")
    public void test(Review review) {
        System.out.println(review);
    }

    @PostMapping("/deleteReview")
    public void deleteReview(Review review) {
        int deleteColumnCount = reviewService.리뷰_삭제(review);

        System.out.println("삭제 열 갯수: " + deleteColumnCount);
    }

    @GetMapping("/findByPay")
    public String findByPay(String pay_id) {
        System.out.println("pay_id: " +pay_id);
        return reviewService.findByPay(pay_id);
    }

}
