package c.e.exper.mapper;

import c.e.exper.data.PictureDAO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PictureMapper {

    @Insert("Insert Into Pictures Values(#{pic.pic_name},#{pic.ord_id, jdbcType=VARCHAR},#{pic.gitem_id, jdbcType=VARCHAR},#{pic.pl_id, jdbcType=VARCHAR},#{pic.keep_id, jdbcType=VARCHAR},#{pic.rev_id, jdbcType=VARCHAR},#{pic.pd_id, jdbcType=VARCHAR},#{pic.book_id, jdbcType=VARCHAR},#{pic.plan_id, jdbcType=VARCHAR},#{pic.store_id, jdbcType=VARCHAR},#{pic.user_id, jdbcType=VARCHAR})")
    void InsertUser(@Param("pic") PictureDAO pictureDAO);

    @Select("select * from pictures where plan_id=#{plan_id}")
    List<PictureDAO> selectAllbyPlanId(@Param("plan_id") String plan_id);
}
