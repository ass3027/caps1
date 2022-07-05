package c.e.exper.mapper;

import c.e.exper.data.BagDAO;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;


@Mapper
public interface BagMapper {
   
   //보관주문서 등록
   @Insert("insert into orders(user_id,ord_amount,entrust_time,keep_start,withdraw_time,ord_selection,ord_request,status) " +
         "values(#{user_id},#{ord_amount},#{entrust_time},#{keep_start},#{withdraw_time},#{ord_selection},#{ord_request},#{status})")
   void storageInsert(BagDAO storageBag);
   
   //배송주문서 등록
   @Insert("insert into orders(user_id,ord_amount,entrust_time,withdraw_time,keep_start,keep_end,ord_selection,ord_request,status) " +
         "values(#{user_id},#{ord_amount},#{entrust_time},#{withdraw_time},#{keep_start},#{keep_end},#{ord_selection},#{ord_request},#{status})")
   void transportInsert(BagDAO transportBag);
   
   //물품조회(보관)
   @Select("Select ord_id,ord_amount,place.title,entrust_time,withdraw_time,status from orders,place " +
         "Where orders.user_id=#{user_id} " +
         "and orders.ORD_SELECTION='물품보관' " +
         "and orders.keep_start=pl_id " +
         "order by ord_id desc")
   List<BagDAO> selectStorage(String user_id);
   
   //일반 물품조회(운송)
   @Select("Select ord_id,ord_amount,p1.pl_id as keep_start,p2.pl_id as keep_end,entrust_time,withdraw_time,status,\n" +
         "p1.title as keep_start_title,p2.title as keep_end_title from orders,place p1, place p2\n" +
         "Where orders.user_id=#{user_id}\n" +
         "and orders.ORD_SELECTION='물품운송'\n" +
         "and orders.keep_start=p1.pl_id\n" +
         "and orders.keep_end=p2.pl_id\n" +
         "order by ord_id desc")
   List<BagDAO> selectTransport(String user_id);
   
   
   //물품상세보기
   @Select("Select * from orders where ord_id=#{ord_id}")
   BagDAO trackingDetail(String user_id);
   
   //출발키퍼 운송 승인완료로 변경
   @Update("update orders set start_status='승인완료' where ord_id=#{ord_id}")
   void deliveryStartCall(String ord_id);
   
   //도착키퍼 운송 승인완료로 변경
   @Update("update orders set end_status='승인완료' where ord_id=#{ord_id}")
   void deliveryEndCall(String ord_id);
   
   //보관중 업데이트
   @Update("update orders set status='보관중' where ord_id=#{ord_id}")
   void storageCall(String ord_id);
   
   //찾아감으로 업데이트
   @Update("update orders set status='찾아감' where ord_id=#{ord_id}")
   void visitCall(String ord_id);
   
   //키퍼회원 운송조회
   @Select("""
         Select ord_id,orders.user_id,ord_amount,p1.pl_id as keep_start,p2.pl_id as keep_end,entrust_time,withdraw_time,status,start_status,end_status,
         p1.title as keep_start_title,p2.title as keep_end_title from orders,place p1, place p2
         Where p1.user_id=#{user_id}
         and orders.keep_start=p1.pl_id
         and orders.keep_end=p2.pl_id
         union
         Select ord_id,orders.user_id,ord_amount,p1.pl_id as keep_start,p2.pl_id as keep_end,entrust_time,withdraw_time,status,start_status,end_status,
                p1.title as keep_start_title,p2.title as keep_end_title from orders,place p1, place p2
         Where p2.user_id=#{user_id}
           and orders.keep_start=p1.pl_id
           and orders.keep_end=p2.pl_id
         order by ord_id desc
         """)
   List<BagDAO> keeperTracking(String user_id);
   
   
   //장소ID 조회
   @Select("""
             Select pl_id from place
             where user_id=#{user_id}
         """)
   String plId(String user_id);
   
   @Update("update orders set status='승인완료' where ord_id=#{ord_id} and start_status='승인완료' and end_status='승인완료'")
   void deliveryAllCall(String ord_id);
   
}
