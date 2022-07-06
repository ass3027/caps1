package c.e.exper.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

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
            AND book.pd_id = pr.pd_id
            AND pay.pay_id = book.pay_id
            """)
    List<Map<String,Object>> getProductBook(String user_id);
}
