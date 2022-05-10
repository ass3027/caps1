package c.e.exper.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ScheduleDAO {

    String sch_number;
    String gitem_id;
    String plan_id;
    String pl_id;
    String sch_name;
    Date sch_startTime;
    Date sch_endTime;
    String expect_expenses;


}
