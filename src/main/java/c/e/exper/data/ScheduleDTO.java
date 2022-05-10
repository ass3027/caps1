package c.e.exper.data;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ScheduleDTO {

    String sch_number;
    String gitem_id;
    String plan_id;
    String pl_id;
    String sch_name;
    String sch_startTime;
    String sch_endTime;
    String expect_expenses;

    public ScheduleDAO toDAO() {
        System.out.println(Date.valueOf(this.sch_startTime));
        return ScheduleDAO.builder()
                .sch_number(this.sch_number)
                .gitem_id(this.gitem_id)
                .plan_id(this.plan_id)
                .pl_id(this.pl_id)
                .sch_name(this.sch_name)
                .sch_startTime(Date.valueOf(this.sch_startTime))
                .sch_endTime(Date.valueOf(this.sch_endTime))
                .expect_expenses(this.expect_expenses)
                .build();
    }
}
