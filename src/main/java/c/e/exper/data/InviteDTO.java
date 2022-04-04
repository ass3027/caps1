package c.e.exper.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class InviteDTO {

    String user_id;
    String plan_id;

    public InviteDAO toDAO(){

        return InviteDAO.builder()
                .user_id(this.user_id)
                .plan_id(this.plan_id)
                .build();

    }
}
