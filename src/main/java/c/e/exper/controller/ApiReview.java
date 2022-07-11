package c.e.exper.controller;


import c.e.exper.data.GItemDAO;
import c.e.exper.data.PaymentDAO;
import c.e.exper.data.ReviewDTO;
import c.e.exper.mapper.GuideMapper;
import c.e.exper.mapper.PaymentMapper;
import c.e.exper.mapper.ReviewMapper;
import c.e.exper.service.FileServiceImpl;
import c.e.exper.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ApiReview {

    private final ReviewService reviewService;
    private final FileServiceImpl fileService;
    private final ReviewMapper reviewMapper;
    private final PaymentMapper paymentMapper;
    private final GuideMapper guideMapper;

    @Autowired
    public ApiReview(ReviewService reviewService, FileServiceImpl fileService, ReviewMapper reviewMapper, PaymentMapper paymentMapper, GuideMapper guideMapper) {
        this.reviewService = reviewService;
        this.fileService = fileService;
        this.reviewMapper = reviewMapper;
        this.paymentMapper = paymentMapper;
        this.guideMapper = guideMapper;
    }


    @PostMapping("/addReview")
    public boolean addReview(ReviewDTO reviewDTO, HttpServletRequest req) {

        reviewDTO.setImg_link(fileService.photoSave(reviewDTO.getImageFile(), req, "reviewImage"));
        reviewDTO.setUser_id(SecurityContextHolder.getContext().getAuthentication().getName());
        System.out.println("reviewDTO = " + reviewDTO);

//        paymentMapper.revCheck(reviewDTO.getPay_id());


        return reviewMapper.addReview(reviewDTO);
    }

    @GetMapping("/allReview")
    public List<ReviewDTO> findAllReview() {

        System.out.println("모든 리뷰 조회");

        List<ReviewDTO> allReview = reviewService.모든_리뷰_조회();

        if (allReview.isEmpty()) {
            System.out.println("리뷰 없음");
        }

        return allReview;
    }


    @GetMapping("/find/answer/{id}")
    public ReviewDTO findReviewAnswer(@PathVariable String id) {
        System.out.println(id);

        return null;
    }

    @GetMapping("/find/reviews/{type}/{id}")
    public List<ReviewDTO> findReview(@PathVariable String type, @PathVariable String id) {
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
    public List<ReviewDTO> findProductReview(String pd_id) {
        System.out.println(pd_id);

        List<ReviewDTO> reviews = reviewMapper.findAllReviewForProduct2(pd_id);
        reviews.forEach(review -> review.setImg_link(reviewMapper.findReviewPictures(review.getRev_id())));

        return reviews;
    }

    @GetMapping("/guideReview")
    public List<ReviewDTO> findGuideReview(String guide_id) {
        System.out.println(guide_id);

        List<ReviewDTO> reviews = reviewMapper.findGuideReview(guide_id);
        reviews.forEach(review -> review.setImg_link(reviewMapper.findReviewPictures(review.getRev_id())));

        return reviews;
    }

    @GetMapping("/gitemReview")
    public List<ReviewDTO> findGitemReview(String gitem_id) {
        System.out.println(gitem_id);

        List<ReviewDTO> reviews = reviewMapper.findGitemReview(gitem_id);
        reviews.forEach(review -> review.setImg_link(reviewMapper.findReviewPictures(review.getRev_id())));

        return reviews;
    }

    @GetMapping("/storeReview")
    public List<ReviewDTO> findStoreReview(@RequestParam String store_id) {
        System.out.println("[findPlName] store_id: " + store_id);
        String pl_name = reviewMapper.findPlName(store_id);

        return reviewService.가게_모든리뷰_조회(pl_name);
    }

    @GetMapping("/keeperReview")
    public List<ReviewDTO> findKeeperReview(String keep_id) {
        System.out.println("keep_id: " + keep_id);

        return reviewService.키퍼아이디_모든리뷰_조회(keep_id);
    }


    @PutMapping("/updateReview")
    public void updateReview(ReviewDTO review) {

        System.out.println(review);
        reviewService.리뷰_수정(review);
    }

    @PutMapping("/test")
    public void test(ReviewDTO review) {
        System.out.println(review);
    }

    @PostMapping("/deleteReview")
    public void deleteReview(ReviewDTO review) {
        int deleteColumnCount = reviewService.리뷰_삭제(review);

        System.out.println("삭제 열 갯수: " + deleteColumnCount);
    }


    @PostMapping("/hit/{id}")
    public boolean hitCount(@PathVariable String id) {
        System.out.println("Hit Update: " + id);
        return reviewMapper.hitCount(id);
    }

    @PostMapping("/like/{id}")
    public boolean likeCount(@PathVariable String id) {
        System.out.println("Like Update: " + id);
        return reviewMapper.likeCount(id);
    }

    @GetMapping("/review/productInfo/{rev_id}")
    public GItemDAO getProductInfo(@PathVariable String rev_id) {

        return reviewMapper.findProductInfo(rev_id);

    }

    // 리뷰아이디로 판매자아이디
    @GetMapping("/review/seller/{rev_id}")
    public String getSeller(@PathVariable String rev_id) {

        PaymentDAO payment = paymentMapper.findByRev(rev_id);

        // 가이드
        if (payment.getGtime_num() != null) {
            return reviewMapper.sellerId(payment.getGtime_num());
        }

        // 호텔


        return "a";
    }

    @GetMapping("/review/type/{rev_id}")
    public String getType(@PathVariable String rev_id) {

        PaymentDAO payment = paymentMapper.findByRev(rev_id);

        // 가이드
        if (payment.getGtime_num() != null) {
            return "guide";
        } else {
            return "hotel";
        }

        // 호텔


    }

    @GetMapping("/review/answer/{rev_id}")
    public String getAnswer(@PathVariable String rev_id) {

        System.out.println(reviewMapper.getAnswer(rev_id));
        return reviewMapper.getAnswer(rev_id);
    }

    @PostMapping("/review/answer/{rev_id}")
    public boolean setAnswer(@PathVariable String rev_id, String content) {
        System.out.println("rev_id = " + rev_id);
        System.out.println("content = " + content);

        return reviewMapper.setAnswer(rev_id, content);
    }

    @GetMapping("/review/paymentCheck/{pay_id}")
    public boolean paymentReviewCheck(@PathVariable String pay_id) {
        return reviewMapper.paymentReviewCheck(pay_id) != null;
    }
}
