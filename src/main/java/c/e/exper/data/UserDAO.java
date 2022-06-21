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
public class UserDAO {

    String user_id;
    String user_pw;
    String user_phone;
    String user_name;
    Date   user_birth;
    String role;
    String guser_intro; //가이드유저?
    String gender; //성별
    String preference; //여행선호도

}
