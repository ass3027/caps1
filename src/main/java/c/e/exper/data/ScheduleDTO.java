package c.e.exper.data;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ScheduleDTO {

    String sch_number;
    String gitem_id;
    String plan_id;
    String pl_id;

    String pl_name;
    String sch_name;
    String sch_startTime;
    String sch_endTime;
    String expect_expenses;
    String place;

    double mapX;

    double mapY;

    public ScheduleDAO toDAO() {
        try{
            SimpleDateFormat sDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date convertedStart = sDate.parse(this.sch_startTime);
            Date convertedEnd = sDate.parse(this.sch_endTime);

            return ScheduleDAO.builder()
                    .sch_number(this.sch_number)
                    .gitem_id(this.gitem_id)
                    .plan_id(this.plan_id)
                    .pl_id(this.pl_id)
                    .pl_name(this.pl_name)
                    .sch_name(this.sch_name)
                    .sch_startTime(convertedStart)
                    .sch_endTime(convertedEnd)
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
