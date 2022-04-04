package c.e.exper.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class Planner {

    String plan_id;
    String plan_start;
    String plan_end;
    String user_id;
    String plan_name;

}
