package c.e.exper.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Date;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
public class UserDTO {

    String user_id;
    String user_pw;
    String user_phone;
    String user_name;
    Date   user_birth;
    String role;
    MultipartFile user_photo;
}
