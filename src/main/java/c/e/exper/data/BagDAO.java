package c.e.exper.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BagDAO {
   String ord_id;    //주문번호
   String pay_id;    //결제번호
   String user_id;   //회원ID
   String ord_amount;   //금액
   String keep_status;  //보관현황
   String entrust_picker; //맡길장소
   String entrust_time;   //맡길 시간
   String withdraw_picker;   //찾을 날짜
   String withdraw_time;  //찾을 시간
   String keep_start;   //출발장소
   String keep_end;     //도착장소
   String delivery_id;  //운송원ID
   String call_time;
   String delivery_status;
}
