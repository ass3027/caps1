package c.e.exper.mapper;

import c.e.exper.data.GItemDAO;
import c.e.exper.data.PaymentDAO;
import c.e.exper.data.PlaceDAO;
import c.e.exper.data.PaymentDTO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import java.util.List;
import java.util.Map;
@Mapper
public interface PaymentMapper {

    @Select("""
            SELECT pay.pay_id, book.start_date, book.end_date, pr.pd_id, pr.pd_name, book.room_num
            FROM payment pay, product pr,
                 (select PAY_ID, max("date") as end_date, min("date") as start_date, PD_ID , room_num
                  from PRODUCT_TIME
                  where PAY_ID is not null
                  group by pay_id, PD_ID,room_num) book
                              
            WHERE pay.user_id = #{user_id}
            AND book.pd_id = pr.pd_id
            AND pay.pay_id = book.pay_id
            """)
    List<Map<String, Object>> getProductBook(String user_id);

    @Select("""
            SELECT *
            FROM payment
            where user_id = #{user_id}
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
    PlaceDAO placeInfoToPayId(@Param("pay_id") String pay_id);


}
