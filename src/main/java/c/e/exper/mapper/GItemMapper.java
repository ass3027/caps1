package c.e.exper.mapper;


import c.e.exper.data.GItemDAO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface GItemMapper {

    @Insert("INSERT INTO gitem VALUES(GITEM_SEQ.NEXTVAL,#{gitem.user_id},#{gitem.pl_id},#{gitem.introduce},#{gitem.st_date},#{gitem.require_time},#{gitem.end_date}, #{gitem.gitem_price} ,#{gitem.gname})")
    void insert(@Param("gitem") GItemDAO gitemDAO);

    @Insert("INSERT INTO available_time(time_num,gitem_id,st_time,end_time,gdate) values(TIME_NUM.NEXTVAL, #{available_time.gitem_id}, #{available_time.st_time}, #{available_time.end_time}, #{available_time.gdate})")
    void insertTime(@Param("available_time") GItemDAO gitemDAO);

    @Select("SELECT * FROM GITEM, place where gitem.pl_id = place.pl_id")
    List<GItemDAO> selectAll();

    @Delete("Delete from gitem where gitem_id = #{id}")
    void deleteGitemOne(String id);

    @Delete("Delete from payment where pay_id = #{id}")
    void deletePayment(String id);

    @Select("Select * From gitem,place where gitem.pl_id = place.pl_id and gitem_id = #{id}")
    GItemDAO selectByGitemId(String id);

    @Select("Select * From available_time where gitem_id=#{id} and gdate = #{time}")
    List<GItemDAO> selectTime(String id, String time);

    @Select("Select * From gitem, place where gitem.pl_id = place.pl_id and place.title like '%'||#{keyword}||'%'")
    List<GItemDAO> selectItemBykeyword(String keyword);

    @Update("Update available_time set book_whether = 0 where time_num = #{id}")
    int updateTime(@Param("id") int id);

    @Update("Update payment set buy_status = 0 where pay_id = #{id}")
    int updategTime(@Param("id") int id);

    @Insert("Insert into book(user_id, time_num) values( #{user_id}, #{time_num})")
    void insertReserve(GItemDAO gitemDAO);

    @Insert("Insert into payment(pay_price, user_id, gtime_num) values(#{pay_price}, #{user_id}, #{gtime_num})")
    void insertPay(GItemDAO gitemDAO);

    @Select("Select * From available_time a, gitem g, place p, payment " +
            "where  g.gitem_id = a.gitem_id and p.pl_id = g.pl_id and payment.gtime_num = a.time_num and payment.user_id = #{id} and  payment.buy_status = 0")
    List<GItemDAO> selectTimes(@Param("id") String id);

    @Select("Select * From available_time a, gitem g, place p, payment " +
            "where  g.gitem_id = a.gitem_id and p.pl_id = g.pl_id and payment.gtime_num = a.time_num and payment.user_id = #{id} and  payment.buy_status = 1")
    List<GItemDAO> selectTimes2(@Param("id") String id);

    @Select("select sum(PAY_PRICE)as count\n" +
            "from PAYMENT\n" +
            "where buy_status = 0 and gtime_num in (select TIME_NUM\n" +
            "                    from AVAILABLE_TIME\n" +
            "                    where GITEM_ID in (select GITEM_ID\n" +
            "                                       from GITEM\n" +
            "                                       where USER_ID = #{id}))")
    GItemDAO selectCount(@Param("id") String id);

    @Select("select b.*,FIRSTIMAGE\n" +
            "from (\n" +
            "         select b.GITEM_ID, count(b.GITEM_ID)\n" +
            "         from PAYMENT a,\n" +
            "              AVAILABLE_TIME b\n" +
            "         where a.GTIME_NUM = b.TIME_NUM\n" +
            "         group by b.GITEM_ID\n" +
            "         order by 2 desc\n" +
            "     )a, GITEM b, place c\n" +
            "where ROWNUM <= 6\n" +
            "and a.GITEM_ID=b.GITEM_ID\n" +
            "and b.PL_ID=c.PL_ID")
    List<GItemDAO> findBestGItem();

    @Select("select PAY_TIME, PAY_PRICE, GNAME\n" +
            "from PAYMENT p,GITEM g, AVAILABLE_TIME a\n" +
            "where p.GTIME_NUM = a.TIME_NUM\n" +
            "AND a.GITEM_ID = g.GITEM_ID\n" +
            "AND p.buy_status = 0\n" +
            "AND gtime_num in (select TIME_NUM\n" +
            "                    from AVAILABLE_TIME\n" +
            "                    where GITEM_ID in (select GITEM_ID\n" +
            "                                  from GITEM\n" +
            "                                  where USER_ID = #{id})\n" +
            "\n" +
            "                    )")
    List<GItemDAO> selectCountList(@Param("id") String id);


    @Select("select sum(PAY_PRICE)as count7\n" +
            "from PAYMENT\n" +
            "where buy_status = 0 and gtime_num in (select TIME_NUM\n" +
            "                    from AVAILABLE_TIME\n" +
            "                    where GITEM_ID in (select GITEM_ID\n" +
            "                                       from GITEM\n" +
            "                                       where USER_ID = #{id}))" +
            "and SYSDATE-7 < PAY_TIME")
    GItemDAO selectCount7(@Param("id") String id);

    @Select("select sum(PAY_PRICE)as count1\n" +
            "from PAYMENT\n" +
            "where buy_status = 0 and gtime_num in (select TIME_NUM\n" +
            "                    from AVAILABLE_TIME\n" +
            "                    where GITEM_ID in (select GITEM_ID\n" +
            "                                       from GITEM\n" +
            "                                       where USER_ID = #{id}))\n" +
            "and to_char(PAY_TIME,'yyyy-mm-dd') = TO_CHAR(SYSDATE,'yyyy-mm-dd')")
    GItemDAO selectCount1(@Param("id") String id);

    @Select("select sum(PAY_PRICE)as count30\n" +
            "from PAYMENT\n" +
            "where buy_status = 0 and gtime_num in (select TIME_NUM\n" +
            "                    from AVAILABLE_TIME\n" +
            "                    where GITEM_ID in (select GITEM_ID\n" +
            "                                       from GITEM\n" +
            "                                       where USER_ID = #{id}))\n" +
            "and to_char(PAY_TIME,'yyyy-mm-dd') > TO_CHAR(SYSDATE-30,'yyyy-mm-dd')")
    GItemDAO selectCount30(@Param("id") String id);



}
