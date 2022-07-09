package c.e.exper.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class
ReviewDAO {

    // 사용자에게 form으로 입력받는 값: 리뷰내용, 별점, 리뷰사진
    private String rev_id;                      // 기본키
    private String user_id;                     // 작성자
    private String rev_content;                 // 리뷰 내용*
    private Date reg_date;                      // 작성일자
    private String title;
    private int hit;
    private int like;
    private String ord_id;                         // 주문서 외래키
    private String pay_id;
    private String img_link;
}
