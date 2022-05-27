package c.e.exper.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class DuserDAO {
    String user_id;
    String duser_trans;
    String duser_info;
    String duser_license;
    String duser_latitude;
    String duser_longitude;


}
