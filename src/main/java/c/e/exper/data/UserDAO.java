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
    String business_num; //사업자번호
    String user_area;

    int user_birth10;
    int user_birth20;
    int user_birth30;
    int user_birth40;
    int user_birth50;


}
