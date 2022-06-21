package c.e.exper.mapper;

import c.e.exper.data.BagDAO;
import c.e.exper.data.BagDTO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

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
   
   
   //물품조회(운송)
   @Select("Select ord_id,ord_amount,p1.pl_id,p2.PL_ID,entrust_time,withdraw_time,status," +
         "p1.title as keep_start,p2.title as keep_end from orders,place p1, place p2\n" +
         "Where orders.user_id=#{user_id}\n" +
         "and orders.ORD_SELECTION='물품운송'\n" +
         "and orders.keep_start=p1.pl_id\n" +
         "and orders.KEEP_END=p2.PL_ID\n" +
         "order by ord_id desc")
   List<BagDAO> selectTransport(String user_id);
}