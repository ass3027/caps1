package c.e.exper.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class GItemDAO {
    String gitem_id;
    String user_id;
    String pl_id;
    String introduce;
    String st_date;
    String require_time;
    String end_date;
    int time_num;
    String st_time;
    String end_time;
    String book_whether;
    String title;
    String book_time;
    String detail;
    String gitem_price;
    String pay_price;
    String gtime_num;
    String gdate;
    int count;
    int count7;
    int count1;
    int count30;
    String firstimage2;
    String pay_time;
    String firstimage;
    String gname;

}
