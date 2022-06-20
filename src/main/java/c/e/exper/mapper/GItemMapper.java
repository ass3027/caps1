package c.e.exper.mapper;


import c.e.exper.data.Available_TimeDAO;
import c.e.exper.data.GItemDAO;
import c.e.exper.data.GuideDAO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface GItemMapper {

    @Insert("INSERT INTO gitem VALUES(#{gitem.gitem_id},#{gitem.user_id},#{gitem.pl_id},#{gitem.introduce},#{gitem.st_date},#{gitem.require_time},#{gitem.end_date})")
    void insert(@Param("gitem") GItemDAO gitem);

    @Insert("INSERT INTO available_time values(TIME_NUM.NEXTVAL, #{available_time.gitem_id}, #{available_time.st_time}, #{available_time.end_time}, #{available_time.book_whether})")
    void insertTime(@Param("available_time") GItemDAO gitemDAO);

    @Select("SELECT * FROM GITEM, place where gitem.pl_id = place.pl_id")
    List<GItemDAO> selectAll();

    @Delete("Delete from gitem where gitem_id = #{id}")
    void deleteGitemOne( String id);

    @Select("Select * From gitem,place where gitem.pl_id = place.pl_id and gitem_id = #{id}")
    GItemDAO selectByGitemId(String id);

    @Select("Select * From available_time where gitem_id=#{id}")
    List<GItemDAO> selectTime(String id);

    @Select("Select * From gitem, place where gitem.pl_id = place.pl_id and place.title like '%'||#{keyword}||'%'")
    List<GItemDAO> selectItemBykeyword(String keyword);

    @Update("Update available_time set book_whether = 0 where time_num = #{id}")
    int updateTime(@Param("id") int id);
}
