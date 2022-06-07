package c.e.exper.mapper;

import c.e.exper.data.GItemDAO;
import c.e.exper.data.GuideDAO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface GItemMapper {

    @Insert("INSERT INTO gitem VALUES(#{gitem.gitem_id},#{gitem.user_id},#{gitem.pl_id},#{gitem.introduce},#{gitem.st_date},#{gitem.require_time},#{gitem.end_date})")
    void insert(@Param("gitem") GItemDAO gitem);

    @Select("SELECT * FROM gitem")
    List<GItemDAO> selectAll();

    @Delete("Delete from gitem where gitem_id = #{id}")
    void deleteGitemOne( String id);

}
