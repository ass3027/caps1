package c.e.exper.mapper;

import c.e.exper.data.BagDAO;
import c.e.exper.data.PaymentDAO;
import c.e.exper.data.PaymentDTO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
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
}
