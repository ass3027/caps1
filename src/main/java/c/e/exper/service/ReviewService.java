package c.e.exper.service;

import c.e.exper.data.PictureDAO;
import c.e.exper.data.Review;
import c.e.exper.mapper.PictureMapper;
import c.e.exper.mapper.ReviewMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ReviewService {

    final ReviewMapper reviewMapper;
    final PictureMapper pictureMapper;

    public ReviewService(ReviewMapper reviewMapper, PictureMapper pictureMapper) {
        this.reviewMapper = reviewMapper;
        this.pictureMapper = pictureMapper;
    }


    public List<Review> 모든_리뷰_조회() {

        return reviewMapper.findAllReview();
    }

    // 조건: 기등록 리뷰가 았을 시 등록 불가(결제번호)
    public int 리뷰_등록(Review review) {

        int insertColumnCount = 0;

        PictureDAO picture = new PictureDAO();

        picture.setPic_name(review.getRev_img_filename());




        if(review.getBook_id() == null && review.getOrd_id() == null) {
            System.out.println("잘못된 형식입니다."); // 에러 출력
        } else if (review.getBook_id() != null) {
            picture.setBook_id(""+review.getBook_id());
            System.out.println(picture);
            pictureMapper.InsertBookReview(picture);

            System.out.println("예약 리뷰 등록!!!!!!!!!!");

            insertColumnCount = reviewMapper.addBookReview(review);
        } else {
            picture.setBook_id(""+review.getOrd_id());
            pictureMapper.InsertOrderReview(picture);
            System.out.println("!!!!!!!!!!!!!!"+picture);


            insertColumnCount = reviewMapper.addOrderReview(review);
        }



        System.out.println("등록 컬럼 갯수: " + insertColumnCount);
        return insertColumnCount;
    }

    public Review 리뷰_조회(String rev_id){
        return reviewMapper.selectReview(rev_id);
    }


    public List<Review> 상품아이디_모든리뷰_조회(String pd_id) { return reviewMapper.findAllReviewForProduct(pd_id); }

    public List<Review> 가게아이디_모든리뷰_조회(String store_name) { return reviewMapper.findAllReviewForStore(store_name); }

    public List<Review> 키퍼아이디_모든리뷰_조회(String keep_id) { return reviewMapper.findAllReviewForKeep(keep_id); }

    public List<Review> 운송원아이디_모든리뷰_조회(String delivery_id) { return reviewMapper.findAllReviewForDelivery(delivery_id); }

    public void 리뷰_수정(Review review) {  reviewMapper.updateReview(review); }

    // 조건: user_id가 같읕 때만 삭제 가능
    public int 리뷰_삭제(Review review){
        return reviewMapper.deleteReview(review.getRev_id());
    }


    public String findByPay(String pay_id) { return reviewMapper.findByPay(pay_id);}


}