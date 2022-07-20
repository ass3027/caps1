package c.e.exper.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ProductItemDTO {
   String plId;
   String userId;
   String pdName;
   String pdPrice;
   String pdInfo;
   MultipartFile image;
}
