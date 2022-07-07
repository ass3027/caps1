package c.e.exper.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaymentDAO {
    String user_id;
    String pay_place;
    String ord_id;
    Date pay_time;
    String pay_price;
    String pay_context;
    String plan_id;
    String gtime_num;
}
