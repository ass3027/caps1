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
    String usage_time;
    String require_time;


}