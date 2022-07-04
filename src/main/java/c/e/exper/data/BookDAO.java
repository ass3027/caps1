package c.e.exper.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BookDAO {
    String book_id;
    String book_time;
    String book_price;
    String user_id;
    String pay_id;
    String plan_id;
    String time_num;
    String product_time_num;
    String date;
    String pl_id;
    String pd_id;
}
