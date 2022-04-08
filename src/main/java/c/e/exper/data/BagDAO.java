package c.e.exper.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BagDAO {
   String ord_id;
   String pay_id;
   String user_id;
   String ord_amount;
   String keep_status;
   Date entrust_time;
   Date withdraw_time;
   String keep_start;
   String keep_end;
   String delivery_id;
   String call_time;
   String delivery_status;
}
