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
Review {

    // 사용자에게 form으로 입력받는 값: 리뷰내용, 별점, 리뷰사진
    private String rev_id;                      // 기본키
    private String user_id;                     // 작성자
    private String rev_content;                 // 리뷰 내용*
    private Date reg_date;                      // 작성일자
    private int rev_rating;                     // 별점*
    private Integer ord_id;                         // 주문서 외래키
    private Integer book_id;                    // 예약 외래키(가이드 상품, 상품)
    private String rev_img_filename;
    private String title;
    private String guide_id;
    private String gitem_id;
    private String pd_id;
    private String pl_id;

    private int hit;
    private int like;

    private MultipartFile rev_photo;         // 리뷰 사진(사진 테이블)*




    // 테이블은 리뷰, 답변으로 나누어져 있지만 데이터는 합쳐서 보낸다.
    private String answer;
}
