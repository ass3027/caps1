package c.e.exper.mapper;

import c.e.exper.data.BagDAO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface BagMapper {
   
   //보관주문서 등록
   @Insert("insert into orders(user_id,ord_amount,entrust_time,withdraw_time,keep_start,ord_selection) " +
         "values(#{user_id},#{ord_amount},#{entrust_time},#{withdraw_time},#{keep_start},#{ord_selection})")
   void storageInsert(BagDAO storageBag);
   
   //배송주문서 등록
   @Insert("insert into orders(user_id,ord_amount,entrust_time,keep_start,withdraw_time,keep_end,ord_selection) " +
         "values(#{user_id},#{ord_amount},#{entrust_time},#{keep_start},#{withdraw_time},#{keep_end},#{ord_selection})")
   void transportInsert(BagDAO transportBag);
   
   
   //배송조회
   @Select("Select * from Orders Where user_id=#{user_id}")
   List<BagDAO> selectByUserId(String user_id);
   
   
}