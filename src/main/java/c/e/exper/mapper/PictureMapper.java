package c.e.exper.mapper;

import c.e.exper.data.PictureDAO;
import c.e.exper.data.PictureDTO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PictureMapper {

    @Insert("Insert Into Pictures(PIC_NAME,USER_ID) Values(#{pic.pic_name},#{pic.user_id, jdbcType=VARCHAR})")
    void InsertUser(@Param("pic") PictureDAO pictureDAO);

    @Select("select * from pictures where plan_id=#{plan_id}")
    List<PictureDAO> selectAllbyPlanId(@Param("plan_id") String plan_id);

    @Select("Select pic_name From Pictures Where user_id=#{user_id}")
    String selectPicnameByUserId(String user_id);

    @Insert("Insert Into Pictures(PIC_NAME,PL_ID) Values(#{pic.pic_name},#{pic.pl_id})")
    void InsertStore(@Param("pic") PictureDAO pictureDAO);

    @Insert("Insert Into Pictures(PIC_NAME,PLAN_ID) Values(#{pic.pic_name},#{pic.plan_id})")
    void InsertPlan(@Param("pic") PictureDAO pictureDAO);



    @Insert("""
            Insert Into Pictures(PIC_NAME, ORD_ID) values(#{pic.pic_name}, #{pic.ord_id})""")
    void InsertOrderReview(@Param("pic") PictureDAO pictureDAO);

    @Insert("""
            Insert Into Pictures(PIC_NAME, BOOK_ID) values(#{pic.pic_name}, #{pic.book_id})""")
    void InsertBookReview(@Param("pic") PictureDAO pictureDAO);


}
