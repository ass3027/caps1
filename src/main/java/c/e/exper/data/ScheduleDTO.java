package c.e.exper.data;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ScheduleDTO {

    String sch_number;
    String gitem_id;
    String plan_id;
    String pl_id;
    String sch_name;
    String sch_startTime;
    String sch_endTime;
    String expect_expenses;
    String place;

    double mapX;

    double mapY;

    public ScheduleDAO toDAO() {
        try{
            SimpleDateFormat sDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            System.out.println(sDate.parse(this.sch_startTime));
            return ScheduleDAO.builder()
                    .sch_number(this.sch_number)
                    .gitem_id(this.gitem_id)
                    .plan_id(this.plan_id)
                    .pl_id(this.pl_id)
                    .sch_name(this.sch_name)
                    .sch_startTime(sDate.parse(this.sch_startTime))
                    .sch_endTime(sDate.parse(this.sch_endTime))
                    .expect_expenses(this.expect_expenses)
                    .place(this.place)
                    .mapX(this.mapX)
                    .mapY(this.mapY)
                    .build();
        }
        catch (ParseException e){
            e.printStackTrace();
        }
        return new ScheduleDAO();
    }
}
