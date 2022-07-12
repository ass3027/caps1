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
    String title;
    String startDate;
    String endDate;
    String max_room_num;
    String count;
    String pd_name;

    String st_date;
    String end_date;
    String room_num;

    public BookDTO toBookDTO(){
        return BookDTO.builder()
                .book_price(this.book_price)
                .user_id(this.user_id)
                .product_time_num(this.product_time_num)
                .pl_id(this.pl_id)
                .pd_id(this.pd_id)
                .title(this.title)
                .startDate(this.startDate)
                .endDate(this.endDate)
               .build();
    }
}
