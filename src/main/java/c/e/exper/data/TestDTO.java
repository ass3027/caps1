package c.e.exper.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Date;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class TestDTO {

    String user_id;
    String user_pw;
    String user_phone;
    String user_name;
    Date   user_birth;
    String role;
    MultipartFile user_photo;
}
