package c.e.exper.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaymentDAO {
    String pay_id;
    String pay_method;
    LocalDateTime pay_time;
    String pay_price;
    String pay_context;
    String plan_id;
    String user_id;
    String ord_id;
    String gtime_num;
    boolean rev_check;

    String buy_status;

    int price1;
    int price7;
    int pirce30;
    int price_sum;
}
