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
public class Pictures {
    MultipartFile pic_name;
    String ord_id;
    String gitem_id;
    String pl_id;
    String keep_id;
    String rev_id;
    String pd_id;
    String book_id;
    String plan_id;
    String store_id;
}
