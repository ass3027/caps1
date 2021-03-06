package c.e.exper.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Plan_Suplies {

    String plan_supl_id;
    String plan_id;
    Suplies supl_id;
    String status;
    String name;
    int quantity;

}
