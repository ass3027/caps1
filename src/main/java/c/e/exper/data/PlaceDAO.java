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
public class PlaceDAO {
    String pl_id;
    String user_id;
    //String pl_name;
    String address;
    String pl_info;
    String address_si;
    String address_gu;
    String address_dong;
    String store_phone;
    String category;
    String store_id;
    String keeper_ox;
    String addr1;
    String addr2;
    String areaCode;
    String cat1;
    String cat2;
    String cat3;
<<<<<<< Updated upstream
    String contentId;
    String contentTypeId;
    String firstimage;
    String firstImage2;
=======
    String contentid;
    String contenttypeid;
    String firstimage;
    String firstimage2;
>>>>>>> Stashed changes
    Double mapx;
    Double mapy;
    String tel;
    String sigungucode;
    String title;
    String zipcode;
    String content;
    MultipartFile image;
    int seq;


    String pic_name;
    String option_id;
}
