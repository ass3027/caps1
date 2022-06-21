package c.e.exper.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;
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
    String pl_name;
    String sch_name;
    Date sch_startTime;
    Date sch_endTime;
    String expect_expenses;
    String place;

    double mapX;

    double mapY;


    public ScheduleDTO toDTO() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //System.out.println(formatter.format(this.sch_startTime));

        return ScheduleDTO.builder()
                .sch_number(this.sch_number)
                .gitem_id(this.gitem_id)
                .plan_id(this.plan_id)
                .pl_id(this.pl_id)
                .pl_name(this.pl_name)
                .sch_name(this.sch_name)
                .sch_startTime(formatter.format(this.sch_startTime))
                .sch_endTime(formatter.format(this.sch_endTime))
                .expect_expenses(this.expect_expenses)
                .place(this.place)
                .mapX(this.mapX)
                .mapY(this.mapY)
                .build();


    }

}
