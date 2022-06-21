package c.e.exper.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class GuideDAO {

    String user_id;
    String guide_license;
    String guide_intro;
    String user_name;
    String pic_name;
    String guser_intro;



}
