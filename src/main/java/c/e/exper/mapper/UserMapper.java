package c.e.exper.mapper;

import c.e.exper.data.*;
import org.apache.ibatis.annotations.*;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReferenceArray;

@Mapper
public interface UserMapper { //디비접근

    @Select("SELECT * FROM users")
    List<UserDAO> selectAll();

    @Select("SELECT * FROM users WHERE user_id=#{user_id}")
    Optional<UserDAO> selectId(String user_id);

    //회원정보
    @Select("""
            SELECT * 
            FROM users LEFT JOIN delivery_user 
            ON users.user_id = delivery_user.user_id 
            WHERE USERS.user_id = #{user_id}""")
    Optional<UserDTO> getDeliveryInfoById(String user_id);
//    (@Param("user_id") 생략가능


    //회원가입(일반회원)
    @Insert("INSERT INTO users VALUES(#{user.user_id},#{user.user_pw},#{user.user_phone},#{user.user_name},#{user.user_birth},#{user.role},null,#{user.gender, jdbcType=VARCHAR},#{user.preference, jdbcType=VARCHAR}, #{user.business_num, jdbcType=VARCHAR},#{user.user_area,jdbcType=VARCHAR})")
    void insert(@Param("user") UserDAO user);

    //회원가입(운송원)
    @Insert("INSERT INTO delivery_user(user_id, duser_trans, duser_license) VALUES(#{duser.user_id},#{duser.duser_trans},#{duser.duser_license})")
    void deliveryInsert(@Param("duser") DuserDAO duser);

    //키퍼(키퍼회원) --사업자번호 상호명 사업자이름?
    @Insert("INSERT INTO place(user_id) VALUES(#{user.user_id})")
    void keeperInsert(@Param("user") PlaceDAO user);

    //지역별 연습
    @Select("SELECT USER_AREA,COUNT(*) FROM users where user_area is not null group by USER_AREA order by count(*) desc")
    List<UserDAO> areaCount();

    //지역별
    @Select("select USER_AREA, count(USER_AREA) as count\n" +
            "    from USERS\n" +
            "    WHERE USER_AREA IS NOT NULL\n" +
            "    group by USER_AREA order by count(*) desc")
    List<AreaDTO> selectAreaCount();

    //연령별(10대)
    @Select("SELECT COUNT(USER_BIRTH) AS USER_BIRTH10\n" +
            "FROM USERS\n" +
            "WHERE USER_BIRTH BETWEEN '2004-01-01' AND '2013-12-31'")
    UserDAO selectAgeCount10();

    //연령별(20대)
    @Select("SELECT COUNT(USER_BIRTH) AS USER_BIRTH20\n" +
            "FROM USERS\n" +
            "WHERE USER_BIRTH BETWEEN '1994-01-01' AND '2003-12-31'")
    UserDAO selectAgeCount20();

    //연령별(30대)
    @Select("SELECT COUNT(USER_BIRTH) AS USER_BIRTH30\n" +
            "FROM USERS\n" +
            "WHERE USER_BIRTH BETWEEN '1984-01-01' AND '1993-12-31'")
    UserDAO selectAgeCount30();

    //연령별(40대)
    @Select("SELECT COUNT(USER_BIRTH) AS USER_BIRTH40\n" +
            "FROM USERS\n" +
            "WHERE USER_BIRTH BETWEEN '1974-01-01' AND '1983-12-31'")
    UserDAO selectAgeCount40();

    //연령별(50대이상)
    @Select("SELECT COUNT(USER_BIRTH) AS USER_BIRTH50\n" +
            "FROM USERS\n" +
            "WHERE USER_BIRTH BETWEEN '1940-01-01' AND '1973-12-31'")
    UserDAO selectAgeCount50();

    @Select("select sum(pay_price)as pay_price;")







    @Select("select INQ_ID,INQ_TITLE,INQ_TIME,USER_ID,INQ_COUNT\n" +
            "from INQUIRY\n" +
            "where user_id = #{user_id.jdbcType=VARCHAR}")
    List<InquiryDAO> selectSearch(String user_id);




    //서울지역 가입수만 받아오기
    @Select("select count(USER_NAME),USER_AREA\n" +
            "from USERS\n" +
            "where USER_AREA = '서울특별시' and user_area is not null\n" +
            "group by USER_AREA order by count(*)")
    ArrayList<Integer> selectArea1();


    //회원정보수정
    @Update("UPDATE USERS SET user_pw=#{user.user_pw}, user_phone=#{user.user_phone}, user_name=#{user.user_name}, preference=#{user.preference} WHERE user_id=#{user.user_id}")
    boolean updateUserInfo(@Param("user") UserDAO user);
    //반환데이터형식(반환값) //함수이름          데이터형식 //매개변수
    //반환해주는것!

//    @Insert("""
//            insert into
//            place(pl_id,pl_name,address,pl_info,address_si,address_gu,
//            address_dong,store_phone,category,store_id,user_id,keeper_ox,
//            addr1,addr2,areaCode,cat1,cat2,cat3,contentid,contentypeid,
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
//                #{place.areaCode,jdbcType = VARCHAR},
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
//            from dual where not exists (select 1 from place where pl_id = #{place.contentid})""")
//    void insertPlace(@Param("place") PlaceDAO place);

    @Select("SELECT pl_id FROM place WHERE pl_id=#{pl_id}")
    boolean checkExist(@Param("pl_id") String pl_id);


    /* user 전화번호 올바른 형식으로 업데이트 */
    @Update("update users set user_phone=#{phone_num} where user_id = #{user_id}")
    void updateUserPhone(@Param("phone_num") String phone_num, @Param("user_id") String user_id);

    @Select("SELECT user_id FROM users")
    List<String> selectAllId();
    /* user 전화번호 올바른 형식으로 업데이트 */

    @Select("SELECT * FROM PAYMENT WHERE CARD_NUM IN (SELECT CARD_NUM FROM CARD WHERE USER_ID = #{user_id})")
    List<PaymentDAO> selectUserPayments(@Param("user_id") String user_id);

    @Select("SELECT * FROM ORDERS WHERE USER_ID IN #{user_id}")
    List<OrderDAO> selectUserOrders(@Param("user_id") String user_id);


    @Select("""
            SELECT count(*)
            FROM guide_license
            WHERE USER_ID = #{user_id}
            """)
    boolean checkGuide(@Param("user_id") String user_id);


    @Select("""
            SELECT count(*)
            FROM delivery_user
            WHERE USER_ID = #{user_id}
            """)
    boolean checkDeliveryUser(@Param("user_id") String user_id);


}
