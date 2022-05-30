package c.e.exper.mapper;

import c.e.exper.data.OrderDAO;
import c.e.exper.data.PaymentDAO;
import c.e.exper.data.PlaceDAO;
import c.e.exper.data.UserDAO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.annotations.*;


import java.util.List;
import java.util.Optional;

@Mapper
public interface UserMapper { //디비접근

    @Select("SELECT * FROM users")
    List<UserDAO> selectAll();

    @Select("SELECT * FROM users WHERE user_id=#{user_id}")
    Optional<UserDAO> selectId(String user_id);
//    (@Param("user_id") 생략가능

    @Insert("INSERT INTO users VALUES(#{user.user_id},#{user.user_pw},#{user.user_phone},#{user.user_name},#{user.user_birth},#{user.role},null)")
    void insert(@Param("user") UserDAO user);

    @Update("UPDATE USERS SET user_pw=#{user.user_pw}, user_phone=#{user.user_phone}, user_name=#{user.user_name} WHERE user_id=#{user.user_id}")
    boolean updateUserInfo(@Param("user") UserDAO user);
  //반환데이터형식(반환값) //함수이름          데이터형식 //매개변수
  //반환해주는것!

//    @Insert("""
//            insert into
//            place2(pl_id,pl_name,address,pl_info,address_si,address_gu,
//            address_dong,store_phone,category,store_id,user_id,keeper_ox,
//            addr1,addr2,areacode,cat1,cat2,cat3,contentid,contentypeid,
//            firstimage,firstimage2,mapx,mapy,tel,sigungucode,title,zipcode
//            )
//            select
//                #{place.contentid,jdbcType = VARCHAR},
//                #{place.pl_name,jdbcType = VARCHAR},
//                #{place.address,jdbcType = VARCHAR},
//                #{place.pl_info,jdbcType = VARCHAR},
//                #{place.address_si,jdbcType = VARCHAR},
//                #{place.address_gu,jdbcType = VARCHAR},
//                #{place.address_dong,jdbcType = VARCHAR},
//                #{place.store_phone,jdbcType = VARCHAR},
//                #{place.category,jdbcType = VARCHAR},
//                #{place.store_id,jdbcType = VARCHAR},
//                #{place.user_id,jdbcType = VARCHAR},
//                #{place.keeper_ox,jdbcType = VARCHAR},
//                #{place.addr1,jdbcType = VARCHAR},
//                #{place.addr2,jdbcType = VARCHAR},
//                #{place.areacode,jdbcType = VARCHAR},
//                #{place.cat1,jdbcType = VARCHAR},
//                #{place.cat2,jdbcType = VARCHAR},
//                #{place.cat3,jdbcType = VARCHAR},
//                #{place.contentid,jdbcType = VARCHAR},
//                #{place.contentypeid,jdbcType = VARCHAR},
//                #{place.firstimage,jdbcType = VARCHAR},
//                #{place.firstimage2,jdbcType = VARCHAR},
//                #{place.mapx,jdbcType = VARCHAR},
//                #{place.mapy,jdbcType = VARCHAR},
//                #{place.tel,jdbcType = VARCHAR},
//                #{place.sigungucode,jdbcType = VARCHAR},
//                #{place.title,jdbcType = VARCHAR},
//                #{place.zipcode,jdbcType = VARCHAR}
//            from dual where not exists (select 1 from place2 where pl_id = #{place.contentid})""")
//    void insertPlace(@Param("place") PlaceDAO place);

    @Select("SELECT pl_id FROM place2 WHERE pl_id=#{pl_id}")
    boolean checkExist(@Param("pl_id") String pl_id);


    /* user 전화번호 올바른 형식으로 업데이트 */
    @Update("update users set user_phone=#{phone_num} where user_id = #{user_id}")
    void updateUserPhone(@Param("phone_num")String phone_num, @Param("user_id") String user_id);

    @Select("SELECT user_id FROM users")
    List<String> selectAllId();
    /* user 전화번호 올바른 형식으로 업데이트 */

    @Select("SELECT * FROM PAYMENT WHERE CARD_NUM IN (SELECT CARD_NUM FROM CARD WHERE USER_ID = #{user_id})")
    List<PaymentDAO> selectUserPayments(@Param("user_id") String user_id);

    @Select("SELECT * FROM ORDERS WHERE USER_ID IN #{user_id}")
    List<OrderDAO> selectUserOrders(@Param("user_id") String user_id);



}
