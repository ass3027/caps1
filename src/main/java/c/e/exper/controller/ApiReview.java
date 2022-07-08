package c.e.exper.controller;


import c.e.exper.data.Review;
import c.e.exper.mapper.ReviewMapper;
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
    private final ReviewMapper reviewMapper;

    @Autowired
    public ApiReview(ReviewService reviewService, FileServiceImpl fileService, ReviewMapper reviewMapper) {
        this.reviewService = reviewService;
        this.fileService = fileService;
        this.reviewMapper = reviewMapper;
    }


    @PostMapping("/addReview/{id}")
    public boolean addReview(@PathVariable String type, @PathVariable String id, Review review, HttpServletRequest req) {



        return true;
    }

    @GetMapping("/allReview")
    public List<Review> findAllReview() {

        System.out.println("모든 리뷰 조회");

        List<Review> allReview = reviewService.모든_리뷰_조회();

        if (allReview.isEmpty()) {
            System.out.println("리뷰 없음");
        }

        return allReview;
    }


    @GetMapping("/find/answer/{id}")
    public Review findReviewAnswer(@PathVariable String id) {
        System.out.println(id);

        return null;
    }

    @GetMapping("/find/reviews/{type}/{id}")
    public List<Review> findReview(@PathVariable String type, @PathVariable String id) {
        System.out.println(type);
        System.out.println(id);

        switch (type) {
            case "all":
                return reviewMapper.findAllReview();
            case "가이드 상품":
                return reviewMapper.findGuideProductReview(id); //user_id
            case "호텔 상품":
                return reviewMapper.findHotelProductReview(id); // pd_id
            case "호텔":
                return reviewMapper.findHotelReview(id); // pl_id
        }


        return null;
    }

    @GetMapping("/productReview")
    public List<Review> findProductReview(String pd_id) {
        System.out.println(pd_id);

        List<Review> reviews = reviewMapper.findAllReviewForProduct2(pd_id);
        reviews.forEach(review -> review.setRev_img_filename(reviewMapper.findReviewPictures(review.getRev_id())));

        return reviews;
    }

    @GetMapping("/guideReview")
    public List<Review> findGuideReview(String guide_id) {
        System.out.println(guide_id);

        List<Review> reviews = reviewMapper.findGuideReview(guide_id);
        reviews.forEach(review -> review.setRev_img_filename(reviewMapper.findReviewPictures(review.getRev_id())));

        return reviews;
    }

    @GetMapping("/gitemReview")
    public List<Review> findGitemReview(String gitem_id) {
        System.out.println(gitem_id);

        List<Review> reviews = reviewMapper.findGitemReview(gitem_id);
        reviews.forEach(review -> review.setRev_img_filename(reviewMapper.findReviewPictures(review.getRev_id())));

        return reviews;
    }

    @GetMapping("/storeReview")
    public List<Review> findStoreReview(@RequestParam String store_id) {
        System.out.println("[findPlName] store_id: " + store_id);
        String pl_name = reviewMapper.findPlName(store_id);

        return reviewService.가게_모든리뷰_조회(pl_name);
    }

    @GetMapping("/keeperReview")
    public List<Review> findKeeperReview(String keep_id) {
        System.out.println("keep_id: " + keep_id);

        return reviewService.키퍼아이디_모든리뷰_조회(keep_id);
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
        System.out.println("pay_id: " + pay_id);
        return reviewService.findByPay(pay_id);
    }


}
