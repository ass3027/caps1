package c.e.exper.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AffiliatedDAO {

    String user_id;
    String plan_id;

    public AffiliatedDTO toDTO(){
        return AffiliatedDTO.builder()
                .plan_id(this.plan_id)
                .user_id(this.user_id)
                .build();
    }
}
