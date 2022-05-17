package c.e.exper.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PlaceDAO {
    String pl_id;
    String pl_name;
    String address;
    String address_si;
    String address_gu;
    String address_dong;
    String pl_info;
    String store_phone;
    String store_name;
    String category;
    String store_id;
    String store_info;
    String user_id;
    String pic_name;
}
