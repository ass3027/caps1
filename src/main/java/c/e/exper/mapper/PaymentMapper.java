package c.e.exper.mapper;

import c.e.exper.data.GItemDAO;
import c.e.exper.data.PaymentDAO;
import c.e.exper.data.PlaceDAO;
import c.e.exper.data.PaymentDTO;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;
@Mapper
public interface PaymentMapper {

    @Select("""
            SELECT pay.pay_id, start_date, end_date, pr.pd_id, pr.pd_name, room_num, pl.pl_id, title, mapX, mapY,addr1
            FROM payment pay,
                 product pr,
                 (SELECT pl_id, title, mapX, mapY, addr1
                  FROM place l
                  WHERE l.pl_id in (SELECT d.pl_id
                                    FROM payment p, product_time t, product d
                                    WHERE p.pay_id = t.pay_id
                                    AND d.pd_id = t.pd_id
                                    AND p.user_id = #{user_id})
                 
                 
                 ) pl,
                 (select PAY_ID, max("date") as end_date, min("date") as start_date, PD_ID , room_num
                  from PRODUCT_TIME
                  where PAY_ID is not null
                  group by pay_id, PD_ID,room_num ) book
                              
            WHERE pay.user_id = #{user_id}
            AND pl.pl_id = pr.pl_id
            AND book.pd_id = pr.pd_id
            AND pay.pay_id = book.pay_id
            """)
    List<Map<String, Object>> getProductBook(String user_id);

    @Select("""
            SELECT pl.*, g.GNAME, g.REQUIRE_TIME,a.ST_TIME,a.END_TIME,a.GDATE
            FROM PAYMENT p,
                 AVAILABLE_TIME a,
                 GITEM g,
                 (SELECT pl_id,title, mapX, mapY, addr1
                  FROM place l
                  WHERE l.pl_id in (SELECT pl_id
                                    FROM payment p,AVAILABLE_TIME a, GITEM g
                                    WHERE p.GTIME_NUM = a.TIME_NUM
                                      AND a.GITEM_ID = g.GITEM_ID
                                      AND p.user_id = #{user_id})
                  ) pl
            WHERE p.GTIME_NUM = a.TIME_NUM
              AND pl.pl_id = g.pl_id
              AND p.USER_ID = #{user_id}
            """)
    List<Map<String,Object>> getGuideBook(String user_id);

    @Select("""
            SELECT *
            FROM payment
            WHERE user_id = #{user_id}
              AND rev_check = 0
            """)
    List<PaymentDAO> paymentList(String user_id);

    //물품배송 결제
    @Insert("insert into payment(user_id,ord_id,pay_price) values(#{user_id},#{ord_id},#{pay_price})")
    void transportPay(PaymentDTO imp);


    //nextval -> currval 순으로
    @Select("select order_seq.currval from dual")
    String getorderId();

    @Select("""
            select *
                from PAYMENT
                where gtime_num in (select TIME_NUM
                        from AVAILABLE_TIME
                                where GITEM_ID = #{id} )
                and user_id = #{user_id}
            """)
    List<PaymentDAO> paymentCheck(@Param("id") String id, @Param("user_id") String user_id);

    @Select("""
            select *
            from GITEM
            where GITEM_ID = (select GITEM_ID
                              from AVAILABLE_TIME
                              where time_num = (select gtime_num
                                                from PAYMENT
                                                where pay_id = #{pay_id}))
            """)
    GItemDAO gitemInfoToPayId(@Param("pay_id") String pay_id);


    // 가이드 결제내역 장소 정보
    @Select("""
            select *
            from PLACE
            where PL_ID  = (select PL_ID
                            from GITEM
                            where GITEM_ID = (select GITEM_ID
                                              from AVAILABLE_TIME
                                              where time_num = (select gtime_num
                                                                from PAYMENT
                                                                where pay_id = #{pay_id})))
            """)
    PlaceDAO placeInfoToPayIdGuide(@Param("pay_id") String pay_id);

    // 호텔 결제내역 장소 정보
    @Select("""
            select *
                from PLACE
                where PL_ID in (select PL_ID
                            from PRODUCT
                                    where PD_ID in (select distinct PD_ID
                                    from PRODUCT_TIME
                                    where PAY_ID = #{pay_id}))
            """)
    PlaceDAO placeInfoToPayIdHotel(@Param("pay_id") String pay_id);


    // 사용자 호텔 결제내역
    @Select("""
            select *
            from PAYMENT
            where USER_ID = #{user_id}
              and PAY_ID in (select PAY_ID
                             from PRODUCT_TIME
                             where PD_ID in (select pd_id
                                             from PRODUCT)
                               and PAY_ID is not null
                             group by PAY_ID)
              and rev_check = 0
            """)
    List<PaymentDAO> hotelPaymentList(@Param("user_id") String user_id);



    @Select("""
            select *
            from PAYMENT
            where PAY_ID = (select PAY_ID
                            from REVIEW
                            where REV_ID = #{rev_id})
            """)
    PaymentDAO findByRev(@Param("rev_id") String rev_id);

    @Update("""
            UPDATE payment
            SET rev_check = 1
            WHERE pay_id = #{pay_id}
            """)
    boolean revCheck(@Param("pay_id") String pay_id);




}
