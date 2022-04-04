package c.e.exper.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AffiliatedDTO {

    String user_id;
    String plan_id;

    public AffiliatedDAO toDAO(){
        return AffiliatedDAO.builder()
                .plan_id(this.plan_id)
                .user_id(this.user_id)
                .build();
    }
}
