package c.e.exper.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Date;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class Suplies {

    String supl_id;
    String supl_name;

}