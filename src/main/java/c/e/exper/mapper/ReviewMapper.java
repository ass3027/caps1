package c.e.exper.mapper;


import c.e.exper.data.Review;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ReviewMapper {


    /* 장소 번호로 장소명 찾기 */
    @Select("""
            SELECT pl_name
            FROM place
            where store_id = #{store_id}""")
    String findPlName(@Param("store_id") String store_id);


    @Select("""
            SELECT *
            FROM review""")
    List<Review> findAllReview();

    @Select("""
            SELECT *
            FROM review
            WHERE user_id = #{user_id}""")
    List<Review> findUserReviews(@Param("user_id") String user_id);


    @Select("""
            select *
            from review
            where pay_id in (select PAY_ID
                             from PAYMENT
                             where gtime_num in (select TIME_NUM
                                                 from AVAILABLE_TIME
                                                 where GITEM_ID = #{gitem_id}));""")
    List<Review> findGuideProductReview(@Param("gitem_id") String gitem_id);


    @Select("""
            select *
            from review
            where pay_id in (select PAY_ID
                             from PAYMENT
                             where gtime_num in (select TIME_NUM
                                                 from AVAILABLE_TIME
                                                 where GITEM_ID in (select GITEM_ID
                                                                    from gitem
                                                                    where user_id = #{guide_id})))
            """)
    List<Review> findGuideReview(@Param("guide_id") String guide_id);

    @Insert("""
            INSERT INTO review(rev_id,
                               user_id,
                               rev_content,
                               title,
                               pay_id)
            VALUES (REVIEW_SEQ.nextval,
                    #{review.user_id},
                    #{review.rev_content},
                    #{review.title},
                    #{review.pay_id})""")
    boolean addReview(Review review);

    @Insert("""
            INSERT INTO review(rev_id,
                               user_id,
                               rev_content,
                               title,
                               pay_id)
                        VALUES (REVIEW_SEQ.nextval,
                               'um',
                               'aaa',
                               'aaa',
                               '100')""")
    boolean addReviewA(Review review);

    @Insert("""
            INSERT INTO review(rev_id,
                               user_id,
                               rev_content,
                               title,
                               pd_id)
            VALUES (REVIEW_SEQ.nextval,
                    #{review.user_id},
                    #{review.rev_content},
                    #{review.title})
                    #{review.pd_id})""")
    int addReviewYame(Review review);

    /* 주문서 리뷰 등록 */
    @Insert("""
            INSERT INTO review(rev_id,
                               user_id,
                               rev_content,
                               rev_rating,
                               ord_id)
            VALUES (REVIEW_SEQ.nextval,
                    #{review.user_id},
                    #{review.rev_content},
                    #{review.rev_rating},
                    #{review.ord_id})""")
    int addOrderReview(@Param("review") Review review);


    /* 예약 리뷰 등록 */
    @Insert("""
            INSERT INTO review(user_id,
                               rev_content,
                               rev_rating,
                               book_id)
            VALUES ( #{review.user_id},
                     #{review.rev_content},
                     #{review.rev_rating},
                     #{review.book_id})""")
    int addBookReview(@Param("review") Review review);

    /* 리뷰 아이디로 리뷰 조회 */
    @Select("""
            SELECT *
            FROM review
            WHERE rev_id = #{rev_id}""")
    Review selectReview(@Param("rev_id") String rev_id);


    /* 리뷰 아이디로 리뷰 삭제 */
    @Delete("""
            DELETE FROM review
            WHERE rev_id = #{rev_id}""")
    int deleteReview(@Param("rev_id") String rev_id);

    @Select("""
            SELECT *
            FROM review
            WHERE book_id IN (SELECT book.book_id
                              FROM book
                              WHERE pd_id = (SELECT b.pd_id
                                             FROM review r, book b
                                             WHERE r.rev_id = #{rev_id}
                                             AND r.book_id = b.book_id))""")
    List<Review> findAllReviewForReview(@Param("rev_id") String rev_id);

    /* 리뷰 아이디로 상품 아이디 */
    @Select("""
            select *
            from review
            where PAY_ID in (select PAY_ID
                             from PRODUCT_TIME
                             where PD_ID = #{pd_id}
                               and PAY_ID is not null
                             group by PAY_ID)""")
    List<Review> findHotelProductReview(@Param("pd_id") String pd_id);

    @Select("""
            select *
            from review
            where PAY_ID in (select PAY_ID
                             from PRODUCT_TIME
                             where PD_ID in (select pd_id
                                             from PRODUCT
                                             where PL_ID = #{pl_id})
                               and PAY_ID is not null
                             group by PAY_ID)
            """)
    List<Review> findHotelReview(@Param("pl_id") String pl_id);



    /* 상품 아이디로 모든 리뷰 조1회 */
    @Select("""
            SELECT *
            FROM   review
            WHERE  booK_id IN (SELECT book.book_id
                               FROM book
                               WHERE pd_id = #{pd_id})
                               """)
    List<Review> findAllReviewForProduct(@Param("pd_id") String pd_id);

    @Select("""
            SELECT *
            FROM   review
            WHERE  pd_id = #{pd_id}""")
    List<Review> findAllReviewForProduct2(@Param("pd_id") String pd_id);


    @Select("""
            SELECT *
            FROM   review
            WHERE  gitem_id = #{gitem_id}
                               """)
    List<Review> findGitemReview(@Param("gitem_id") String gitem_id);

    @Select("""
            SELECT pic_name
            FROM   pictures
            WHERE  rev_id = #{rev_id}""")
    String findReviewPictures(@Param("rev_id") String rev_id);


    /* 가게 이름으로 모든 리뷰 조회*/
    @Select("""
            SELECT review.*
            FROM   review,
                   book
            WHERE  review.book_id = book.book_id
              AND  book.pd_id IN (SELECT product.pd_id
                                  FROM   product, place
                                  WHERE  pl_name = #{pl_name}
                                  AND    product.pl_id = place.pl_id)""")
    List<Review> findAllReviewForStore(@Param("pl_name") String pl_name);

    /* 키퍼 아이디로 모든 리뷰 조회 */
    @Select("""
            SELECT *
            FROM review
            WHERE ord_id IN (SELECT ord_id
                             FROM   orders
                             WHERE keep_start = #{keep_id}
                                OR keep_end   = #{keep_id})""")
    List<Review> findAllReviewForKeep(@Param("keep_id") String keep_id);

    /* 운송원 아이디로 모든 리뷰 조회*/
    @Select("""
            SELECT *
            FROM review
            WHERE ord_id IN (SELECT ord_id
                             FROM orders
                             WHERE delivery_id = #{delivery_id})""")
    List<Review> findAllReviewForDelivery(@Param("delivery_id") String delivery_id);

//    @Select("select * from REVIEW where BOOK_ID in ()")

    @Update("""
            UPDATE review
            SET rev_content = #{review.rev_content}
            WHERE review_id = #{review.rev_id}""")
    int updateReview(@Param("review") Review review);

    @Select("""
            SELECT book_id
            FROM book
            WHERE pay_id = #{pay_id}""")
    String findByPay(@Param("pay_id") String pay_id);
}
