package c.e.exper.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BookDTO {
    String book_price;
    String user_id;
    String product_time_num;
    String pl_id;
    String pd_id;
    String title;
    String startDate;
    String endDate;
}
