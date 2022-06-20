package c.e.exper.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class InquiryDAO {
    
    String inq_id;
    String inq_title;
    String inq_body;
    Date inq_time;
    String user_id;
    String inq_type;
    Integer inq_count;
}
