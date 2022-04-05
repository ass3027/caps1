package c.e.exper.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Share {

    String share_id;
    String share_place;
    String share_title;
    String share_contents;
    String share_created;
    String plan_id;

}
