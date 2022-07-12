package c.e.exper.service;

import c.e.exper.data.PictureDAO;
import c.e.exper.data.ReviewDTO;
import c.e.exper.mapper.PictureMapper;
import c.e.exper.mapper.ReviewMapper;
import c.e.exper.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ReviewService {

    final ReviewMapper reviewMapper;
    final PictureMapper pictureMapper;
    final UserMapper userMapper;

    public ReviewService(ReviewMapper reviewMapper, PictureMapper pictureMapper, UserMapper userMapper) {
        this.reviewMapper = reviewMapper;
        this.pictureMapper = pictureMapper;
        this.userMapper = userMapper;
    }


    public List<ReviewDTO> 모든_리뷰_조회() {

        return reviewMapper.findAllReview();
    }

    public List<ReviewDTO> 가게_모든리뷰_조회(String pl_name) {
        List<ReviewDTO> allReviewForStore = reviewMapper.findAllReviewForStore(pl_name);

        allReviewForStore.forEach(i -> i.setImg_link(pictureMapper.selectPicnameByRevId(i.getRev_id())));

        return allReviewForStore;
    }


    // 조건: 기등록 리뷰가 았을 시 등록 불가(결제번호)
    public int 리뷰_등록(ReviewDTO review) {

        int insertColumnCount = 0;

        PictureDAO picture = new PictureDAO();

        picture.setPic_name(review.getImg_link());




        insertColumnCount = reviewMapper.addReviewYame(review);




        System.out.println("등록 컬럼 갯수: " + insertColumnCount);
        return insertColumnCount;
    }

    public ReviewDTO 리뷰_조회(String rev_id){
        return reviewMapper.selectReview(rev_id);
    }


    public List<ReviewDTO> 상품아이디_모든리뷰_조회(String pd_id) { return reviewMapper.findAllReviewForProduct(pd_id); }

    public List<ReviewDTO> 가게이름_모든리뷰_조회(String pl_name) { return reviewMapper.findAllReviewForStore(pl_name); }

    public List<ReviewDTO> 키퍼아이디_모든리뷰_조회(String keep_id) { return reviewMapper.findAllReviewForKeep(keep_id); }

    public List<ReviewDTO> 운송원아이디_모든리뷰_조회(String delivery_id) { return reviewMapper.findAllReviewForDelivery(delivery_id); }

    public void 리뷰_수정(ReviewDTO review) {
        reviewMapper.updateReview(review); }

    // 조건: user_id가 같읕 때만 삭제 가능
    public int 리뷰_삭제(ReviewDTO review){
        return reviewMapper.deleteReview(review.getRev_id());
    }




}