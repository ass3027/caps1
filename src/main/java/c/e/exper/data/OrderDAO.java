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
public class OrderDAO {

    String ord_id;
    String user_id;
    String ord_amount;
    String keep_status;
    String entrust_time;
    String withdraw_time;
    String keep_start;
    String keep_end;
    String delivery_id;
    Date call_time;
    String status;
    String pay_id;
    String ord_selection;
    String ord_request;

}
