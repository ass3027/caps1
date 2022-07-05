package c.e.exper.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ShareComment {

    String comment_id;
    String comment_contents;
    String share_id;
    String user_id;
    String created_time;

}
