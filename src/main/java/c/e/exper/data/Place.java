package c.e.exper.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Place {
    String pl_id;
    String pl_name;
    String pl_location;
    String pl_info;
    String pic_name;

}
