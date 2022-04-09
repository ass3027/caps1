package c.e.exper.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class Schedule {

    String sch_number;
    String gitem_id;
    String plan_id;
    String pl_id;
    String sch_name;
    Date sch_starttime;
    Date sch_endtime;
    String expect_expenses;

}
