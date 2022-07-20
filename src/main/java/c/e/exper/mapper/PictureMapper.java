package c.e.exper.mapper;

import c.e.exper.data.PictureDAO;
import c.e.exper.data.PictureDTO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
@Repository
public interface PictureMapper {
   
   @Insert("Insert Into Pictures(PIC_NAME,USER_ID) Values(#{pic.pic_name},#{pic.user_id, jdbcType=VARCHAR})")
   void InsertUser(@Param("pic") PictureDAO pictureDAO);
   
   @Select("select * from pictures where plan_id=#{plan_id}")
   List<PictureDAO> selectAllbyPlanId(@Param("plan_id") String plan_id);
   
   @Select("Select pic_name From Pictures Where user_id=#{user_id} and plan_id is null")
   String selectPicnameByUserId(String user_id);
   
   @Select("SELECT pic_name FROM pictures WHERE rev_id=#{rev_id}")
   String selectPicnameByRevId(String rev_id);
   
   @Insert("Insert Into Pictures(PIC_NAME,PL_ID) Values(#{pic.pic_name},#{pic.pl_id})")
   void InsertStore(@Param("pic") PictureDAO pictureDAO);
   
   @Insert("Insert Into Pictures(PIC_NAME,PLAN_ID,USER_ID,category) Values(#{pic.pic_name},#{pic.plan_id},#{pic.user_id},#{pic.category})")
   void InsertPlan(@Param("pic") PictureDAO pictureDAO);
   
   
   @Delete("delete from PICTURES where PIC_NAME = #{pic_name}")
   void deletePicture(String pic_name);
   
   @Insert("""
            insert into pictures (pic_name,pl_id) values (#{pic_name},#{pl_id})
         """)
   void productImage(String pic_name, String pl_id);
}
