package c.e.exper.mapper;

import c.e.exper.data.GItemDAO;
import c.e.exper.data.GuideDAO;
import c.e.exper.data.UserDAO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Optional;

@Mapper
public interface GuideMapper {

    @Select("SELECT * FROM guide,users where guide.user_id = users.user_id")
    List<GuideDAO> selectAll();

    @Select("SELECT * FROM guide where user_id = #{id}")
    GuideDAO selectById(String id);

    @Insert("INSERT INTO guide VALUES(#{guide.user_id},#{guide.guide_license},#{guide.guide_intro})")
    void insert(@Param("guide") GuideDAO guide);

    @Select("SELECT * FROM guide WHERE user_id like '%'||#{keyword}||'%'")
    List<GuideDAO> selectBykeyword(String keyword);






}
