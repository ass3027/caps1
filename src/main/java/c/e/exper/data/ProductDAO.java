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
    String pl_id;
    String pd_info;
    String pic_name;
    String addr1;
    String title;
    String tel;
    String content;
    String firstimage;
    String firstimage2;

}
