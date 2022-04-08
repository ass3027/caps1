package c.e.exper.service;

import c.e.exper.data.Review;
import c.e.exper.mapper.ReviewMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ReviewService {

    final ReviewMapper reviewMapper;

    public ReviewService(ReviewMapper reviewMapper) {
        this.reviewMapper = reviewMapper;
    }


    public List<Review> 모든_리뷰_조회() {

        return reviewMapper.findAllReview();
    }

    // 조건: 기등록 리뷰가 았을 시 등록 불가(결제번호)
    public int 리뷰_등록(Review review) {

        int insertColumnCount = 0;

        if(review.getBook_id() == null && review.getOrd_id() == null) {
            System.out.println("잘못된 형식입니다."); // 에러 출력
        } else if (review.getBook_id() != null) {
            insertColumnCount = reviewMapper.addBookReview(review);
        } else {
            insertColumnCount = reviewMapper.addOrderReview(review);
        }

        System.out.println("등록 컬럼 갯수: " + insertColumnCount);
        return insertColumnCount;
    }

    public Review 리뷰_조회(String rev_id){
        return reviewMapper.selectReview(rev_id);
    }

    public List<Review> 리뷰아이디_리뷰_조회(String rev_id) {
        return reviewMapper.findAllReviewForReview(rev_id);
    }

    public List<Review> 상품아이디_리뷰_조회(String pd_id) { return reviewMapper.findAllReviewForProduct(pd_id); }

    public void 리뷰_수정(Review review) {  reviewMapper.updateReview(review); }

    // 조건: user_id가 같읕 때만 삭제 가능
    public int 리뷰_삭제(Review review){
        return reviewMapper.deleteReview(review.getRev_id());
    }


}