package c.e.exper.mapper;

import c.e.exper.data.GItemDAO;
import c.e.exper.data.GuideDAO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface
GuideMapper {

    @Select("SELECT * FROM guide_license,users,pictures where guide_license.user_id = users.user_id and pictures.user_id = users.user_id")
    List<GuideDAO> selectAll();

    @Select("SELECT * FROM guide_license,users,pictures where guide_license.user_id = users.user_id and pictures.user_id = users.user_id and guide_license.user_id = #{id}")
    GuideDAO selectById(String id);

    @Insert("INSERT INTO guide_license VALUES(#{guide_license.user_id},#{guide_license.guide_license})")
    void insert(@Param("guide_license") GuideDAO guide);

    @Select("SELECT * FROM guide_license,users,pictures WHERE guide_license.user_id = users.user_id and pictures.user_id = users.user_id and users.user_name like '%'||#{keyword}||'%'")
    List<GuideDAO> selectBykeyword(String keyword);

    @Update("Update users set guser_intro = #{guide.guser_intro} where user_id = #{id}")
    void updateIntro(@Param("guide") GuideDAO guideDAO, String id);

    @Select("""
            select user_id
            from gitem
            where gitem_id = #{gitem_id}
            """)
    String selectUser(@Param("gitem_id") String gitem_id);


}
