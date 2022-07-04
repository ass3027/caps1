package c.e.exper.mapper;


import c.e.exper.data.Available_TimeDAO;
import c.e.exper.data.GItemDAO;
import c.e.exper.data.GuideDAO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface GItemMapper {

    @Insert("INSERT INTO gitem VALUES(GITEM_SEQ.NEXTVAL,#{gitem.user_id},#{gitem.pl_id},#{gitem.introduce},#{gitem.st_date},#{gitem.require_time},#{gitem.end_date}, #{gitem.gitem_price})")
    void insert(@Param("gitem") GItemDAO gitem);

    @Insert("INSERT INTO available_time(time_num,gitem_id,st_time,end_time,gdate) values(TIME_NUM.NEXTVAL, #{available_time.gitem_id}, #{available_time.st_time}, #{available_time.end_time}, #{available_time.gdate})")
    void insertTime(@Param("available_time") GItemDAO gitemDAO);

    @Select("SELECT * FROM GITEM, place where gitem.pl_id = place.pl_id")
    List<GItemDAO> selectAll();

    @Delete("Delete from gitem where gitem_id = #{id}")
    void deleteGitemOne( String id);

    @Select("Select * From gitem,place where gitem.pl_id = place.pl_id and gitem_id = #{id}")
    GItemDAO selectByGitemId(String id);

    @Select("Select * From available_time where gitem_id=#{id} and gdate = #{time}")
    List<GItemDAO> selectTime(String id, String time);

    @Select("Select * From gitem, place where gitem.pl_id = place.pl_id and place.title like '%'||#{keyword}||'%'")
    List<GItemDAO> selectItemBykeyword(String keyword);

    @Update("Update available_time set book_whether = 0 where time_num = #{id}")
    int updateTime(@Param("id") int id);

    @Insert("Insert into book( user_id, time_num) values( #{user_id}, #{time_num})")
    void insertReserve(GItemDAO gitemDAO);

    @Insert("Insert into payment(pay_price, user_id, gtime_num) values(#{pay_price}, #{user_id}, #{gtime_num})")
    void insertPay(GItemDAO gitemDAO);

    @Select("Select * From available_time a, book b, gitem g, place p where a.time_num = b.time_num and g.gitem_id = a.gitem_id and p.pl_id = g.pl_id and b.user_id = #{id}")
    List<GItemDAO> selectTimes(@Param("id") String id);
}
