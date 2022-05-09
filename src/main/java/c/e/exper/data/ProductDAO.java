package c.e.exper.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductDAO {
    String pd_id;
    String pd_name;
    String pd_price;
    String store_id;
    String pic_name;
}
