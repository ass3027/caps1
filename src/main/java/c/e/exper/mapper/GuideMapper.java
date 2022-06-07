package c.e.exper.mapper;

import c.e.exper.data.GItemDAO;
import c.e.exper.data.GuideDAO;
import c.e.exper.data.UserDAO;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Optional;

@Mapper
public interface GuideMapper {

    @Select("SELECT * FROM guide_license,users,pictures where guide_license.user_id = users.user_id and pictures.user_id = users.user_id")
    List<GuideDAO> selectAll();

    @Select("SELECT * FROM guide_license,users,pictures where guide_license.user_id = users.user_id and pictures.user_id = users.user_id and guide_license.user_id = #{id}")
    GuideDAO selectById(String id);

    @Insert("INSERT INTO guide_license VALUES(#{guide_license.user_id},#{guide_license.guide_license})")
    void insert(@Param("guide_license") GuideDAO guide);

    @Select("SELECT * FROM guide_license,users,pictures WHERE guide_license.user_id = users.user_id and pictures.user_id = users.user_id and guide_license.user_id like '%'||#{keyword}||'%'")
    List<GuideDAO> selectBykeyword(String keyword);




}
