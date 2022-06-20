package c.e.exper.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Available_TimeDAO {
    int time_num;
    String gitem_id;
    String st_time;
    String end_time;
    String book_whether;
}
