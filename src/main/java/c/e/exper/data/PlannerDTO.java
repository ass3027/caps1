package c.e.exper.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PlannerDTO {

    String plan_id;
    Date plan_start;
    Date plan_end;
    String user_id;
    String plan_name;

    PlannerDAO toDAO(){

        return PlannerDAO.builder()
                .plan_id(this.plan_id)
                .plan_start(this.plan_start)
                .plan_end(this.plan_end)
                .user_id(this.user_id)
                .plan_name(this.plan_name)
                .build();
    }
}

