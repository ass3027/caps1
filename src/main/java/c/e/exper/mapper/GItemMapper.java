package c.e.exper.mapper;


import c.e.exper.data.GItemDTO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface GItemMapper {

    @Insert("INSERT INTO gitem VALUES(GITEM_SEQ.NEXTVAL,#{gitem.user_id},#{gitem.pl_id},#{gitem.introduce},#{gitem.st_date},#{gitem.require_time},#{gitem.end_date}, #{gitem.gitem_price} ,#{gitem.gname})")
    void insert(@Param("gitem") GItemDTO gitem);

    @Insert("INSERT INTO available_time(time_num,gitem_id,st_time,end_time,gdate) values(TIME_NUM.NEXTVAL, #{available_time.gitem_id}, #{available_time.st_time}, #{available_time.end_time}, #{available_time.gdate})")
    void insertTime(@Param("available_time") GItemDTO gitemDTO);

    @Select("SELECT * FROM GITEM, place where gitem.pl_id = place.pl_id")
    List<GItemDTO> selectAll();

    @Delete("Delete from gitem where gitem_id = #{id}")
    void deleteGitemOne(String id);

    @Select("Select * From gitem,place where gitem.pl_id = place.pl_id and gitem_id = #{id}")
    GItemDTO selectByGitemId(String id);

    @Select("Select * From available_time where gitem_id=#{id} and gdate = #{time}")
    List<GItemDTO> selectTime(String id, String time);

    @Select("Select * From gitem, place where gitem.pl_id = place.pl_id and place.title like '%'||#{keyword}||'%'")
    List<GItemDTO> selectItemBykeyword(String keyword);

    @Update("Update available_time set book_whether = 0 where time_num = #{id}")
    int updateTime(@Param("id") int id);

    @Insert("Insert into book( user_id, time_num) values( #{user_id}, #{time_num})")
    void insertReserve(GItemDTO gitemDTO);

    @Insert("Insert into payment(pay_price, user_id, gtime_num) values(#{pay_price}, #{user_id}, #{gtime_num})")
    void insertPay(GItemDTO gitemDTO);

    @Select("Select * From available_time a, gitem g, place p, payment " +
            "where  g.gitem_id = a.gitem_id and p.pl_id = g.pl_id and payment.gtime_num = a.time_num and payment.user_id = #{id}")
    List<GItemDTO> selectTimes(@Param("id") String id);

    @Select("select sum(PAY_PRICE)as count\n" +
            "from PAYMENT\n" +
            "where gtime_num in (select TIME_NUM\n" +
            "                    from AVAILABLE_TIME\n" +
            "                    where GITEM_ID in (select GITEM_ID\n" +
            "                                       from GITEM\n" +
            "                                       where USER_ID = #{id}))")
    GItemDTO selectCount(@Param("id") String id);

    @Select("select b.*,FIRSTIMAGE\n" +
            "from (\n" +
            "         select b.GITEM_ID, count(b.GITEM_ID)\n" +
            "         from PAYMENT a,\n" +
            "              AVAILABLE_TIME b\n" +
            "         where a.GTIME_NUM = b.TIME_NUM\n" +
            "         group by b.GITEM_ID\n" +
            "         order by 2 desc\n" +
            "     )a, GITEM b, place c\n" +
            "where ROWNUM <= 3\n" +
            "and a.GITEM_ID=b.GITEM_ID\n" +
            "and b.PL_ID=c.PL_ID")
    List<GItemDTO> findBestGItem();

    @Select("select sum(PAY_PRICE)as count7\n" +
            "from PAYMENT\n" +
            "where gtime_num in (select TIME_NUM\n" +
            "                    from AVAILABLE_TIME\n" +
            "                    where GITEM_ID in (select GITEM_ID\n" +
            "                                       from GITEM\n" +
            "                                       where USER_ID = #{id}))" +
            "and SYSDATE-7 < PAY_TIME")
    GItemDTO selectCount7(@Param("id") String id);

    @Select("select sum(PAY_PRICE)as count1\n" +
            "from PAYMENT\n" +
            "where gtime_num in (select TIME_NUM\n" +
            "                    from AVAILABLE_TIME\n" +
            "                    where GITEM_ID in (select GITEM_ID\n" +
            "                                       from GITEM\n" +
            "                                       where USER_ID = #{id}))\n" +
            "and to_char(PAY_TIME,'yyyy-mm-dd') = TO_CHAR(SYSDATE,'yyyy-mm-dd')")
    GItemDTO selectCount1(@Param("id") String id);

    @Select("select sum(PAY_PRICE)as count30\n" +
            "from PAYMENT\n" +
            "where gtime_num in (select TIME_NUM\n" +
            "                    from AVAILABLE_TIME\n" +
            "                    where GITEM_ID in (select GITEM_ID\n" +
            "                                       from GITEM\n" +
            "                                       where USER_ID = #{id}))\n" +
            "and to_char(PAY_TIME,'yyyy-mm-dd') > TO_CHAR(SYSDATE-30,'yyyy-mm-dd')")
    GItemDTO selectCount30(@Param("id") String id);

}
