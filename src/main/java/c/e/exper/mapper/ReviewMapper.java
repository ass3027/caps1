package c.e.exper.mapper;


import c.e.exper.data.Review;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ReviewMapper {



    @Select("select * from review")
    public List<Review> findAllReview();

    public String addReview(Review review);

    /* 주문서 리뷰 등록 */
    @Insert("insert into review(REV_ID, USER_ID, REV_CONTENT, REV_RATING, ORD_ID) values (REVIEW_SEQ.nextval, #{review.user_id}, #{review.rev_content}, #{review.rev_rating}, #{review.ord_id})")
    public int addOrderReview(@Param("review") Review review);

    /* 예약 리뷰 등록 */
    @Insert("insert into review(REV_ID, USER_ID, REV_CONTENT, REV_RATING, BOOK_ID) values (REVIEW_SEQ.nextval, #{review.user_id}, #{review.rev_content}, #{review.rev_rating}, #{review.book_id})")
    public int addBookReview(@Param("review") Review review);

    /* 리뷰 조회 */
    @Select("select * from review where REV_ID = #{rev_id}")
    public Review selectReview(@Param("rev_id") String rev_id);

    @Delete("delete from review where REV_ID = #{rev_id}")
    public int deleteReview(@Param("rev_id") String rev_id);

    @Select("select * from review where book_id in (select BOOK.BOOK_ID from BOOK where PD_ID = (select b.PD_ID from REVIEW r, BOOK b where r.REV_ID = #{rev_id} and r.BOOK_ID = b.BOOK_ID))")
    public List<Review> findAllReviewForReview(@Param("rev_id") String rev_id);

    @Select("select * from review where booK_id in (select BOOK.BOOK_ID from BOOK where PD_ID = #{pd_id})")
    public List<Review> findAllReviewForProduct(@Param("pd_id") String pd_id);

    @Update("update review set rev_content = #{review.rev_content} where review_id = #{review.rev_id}")
    public int updateReview(@Param("review") Review review);
}