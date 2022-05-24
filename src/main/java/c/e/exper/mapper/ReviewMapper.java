package c.e.exper.mapper;


import c.e.exper.data.Review;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ReviewMapper {


    /* 장소 번호로 장소명 찾기 */
    @Select("select store_name from place where store_id = #{store_id}")
    public String findPlName(@Param("store_id") String store_id);

    @Select("select * from review")
    public List<Review> findAllReview();

    public String addReview(Review review);

    /* 주문서 리뷰 등록 */
    @Insert("insert into review(REV_ID, USER_ID, REV_CONTENT, REV_RATING, ORD_ID) values (REVIEW_SEQ.nextval, #{review.user_id}, #{review.rev_content}, #{review.rev_rating}, #{review.ord_id})")
    public int addOrderReview(@Param("review") Review review);

    /* 예약 리뷰 등록 */
    @Insert("insert into review( USER_ID, REV_CONTENT, REV_RATING, BOOK_ID) values ( #{review.user_id}, #{review.rev_content}, #{review.rev_rating}, #{review.book_id})")
    public int addBookReview(@Param("review") Review review);

    /* 리뷰 아이디로 리뷰 조회 */
    @Select("select * from review where REV_ID = #{rev_id}")
    public Review selectReview(@Param("rev_id") String rev_id);


    /* 리뷰 아이디로 리뷰 삭제 */
    @Delete("delete from review where REV_ID = #{rev_id}")
    public int deleteReview(@Param("rev_id") String rev_id);

    @Select("select * from review where book_id in (select BOOK.BOOK_ID from BOOK where PD_ID = (select b.PD_ID from REVIEW r, BOOK b where r.REV_ID = #{rev_id} and r.BOOK_ID = b.BOOK_ID))")
    public List<Review> findAllReviewForReview(@Param("rev_id") String rev_id);

    /* 리뷰 아이디로 상품 아이디 */
    @Select("select PD_ID from REVIEW r, BOOK b where r.REV_ID = #{rev_id} and r.BOOK_ID = b.BOOK_ID")
    public String findProductForReview(@Param("rev_id") String rev_id);

    /* 상품 아이디로 모든 리뷰 조회 */
    @Select("select * from review where booK_id in (select BOOK.BOOK_ID from BOOK where PD_ID = #{pd_id})")
    public List<Review> findAllReviewForProduct(@Param("pd_id") String pd_id);

    /* 가게 아이디로 모든 리뷰 조회*/
    @Select("SELECT review.*\n" +
            "FROM   review,\n" +
            "       book\n" +
            "WHERE  review.book_id = book.book_id\n" +
            "  AND book.pd_id IN (SELECT product.pd_id\n" +
            "                     FROM   product\n" +
            "                                left outer join place\n" +
            "                                                ON product.pl_id = place.pl_id\n" +
            "                     WHERE  store_name = #{store_name})")
    public List<Review> findAllReviewForStore(@Param("store_name") String store_name);

    /* 키퍼 아이디로 모든 리뷰 조회 */
    @Select("select * from REVIEW where ORD_ID in (select ORD_ID from ORDERS where KEEP_START = #{keep_id} or KEEP_END = #{keep_id})")
    public List<Review> findAllReviewForKeep(@Param("keep_id") String keep_id);

    /* 운송원 아이디로 모든 리뷰 조회*/
    @Select("select * from REVIEW where ORD_ID in (select ORD_ID from ORDERS where DELIVERY_ID = #{delivery_id})")
    public List<Review> findAllReviewForDelivery(@Param("delivery_id") String delivery_id);

//    @Select("select * from REVIEW where BOOK_ID in ()")

    @Update("update review set rev_content = #{review.rev_content} where review_id = #{review.rev_id}")
    public int updateReview(@Param("review") Review review);

    @Select("select BOOK_ID from BOOK where PAY_ID = #{pay_id}")
    public String findByPay(@Param("pay_id") String pay_id);
}
