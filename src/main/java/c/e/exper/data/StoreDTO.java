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
public class StoreDTO {
    String store_id;
    String user_id;
    String pl_id;
    String store_name;
    String store_phone;
    String category;
    MultipartFile pic_name;
}
