package c.e.exper.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface PaymentMapper {

    @Select("""
            SELECT pay.pay_id, t."date", pr.pd_id, pr.pd_name
            FROM payment pay,product_time t,product pr
            WHERE pay.user_id = #{user_id}
            AND t.pd_id = pr.pd_id
            AND pay.product_time_num = t.product_time_num
            AND pay.product_time_num is not null
            """)
    List<Map<String,Object>> getProductBook(String user_id);
}
