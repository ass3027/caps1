package c.e.exper.mapper;

import c.e.exper.data.Share;
import c.e.exper.data.ShareComment;
import c.e.exper.data.ShareDTO;
import c.e.exper.data.SharePictureDAO;
import org.apache.ibatis.annotations.*;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Mapper
public interface ShareMapper {

    @Insert("INSERT INTO shares(share_id,user_id, plan_id,share_place,share_title,share_contents) VALUES  (#{share_id}, #{user_id}, #{plan_id}, #{share_place}, #{share_title},#{share_contents})")
    @SelectKey(statement="select SHARES_SEQUENCE.nextval FROM DUAL", keyProperty="share_id", before=true, resultType=String.class)
    int insert(Share share);


    public List<Share> getTest();

    public long insertTest(String param1, String param2);

    @Select("select * from shares order by TO_NUMBER(SHARE_ID)desc")
    public List<ShareDTO> findAllShares();

    @Select("select * from shares where SHARE_ID=#{share_id}")
    public Share findShareById(String share_id);

    @Select("select * from SHARES_PICTURES where share_id=#{share_id}")
    public List<SharePictureDAO> findPicturesById(String share_id);


    @Insert("insert into shares(user_id,SHARE_PLACE,SHARE_TITLE,SHARE_CONTENTS,PLAN_ID)\n" +
            "values(#{share.user_id},#{share.share_place},#{share.share_title},#{share.share_contents},#{share.plan_id})")
    public void insertShares(@Param("share")Share share);

    @Insert("insert into SHARES_PICTURES(share_id,pic_name)\n" +
            "values(#{sharePic.share_id},#{sharePic.pic_name})")
    public void insertSharesPictures(@Param("sharePic") SharePictureDAO sharePic);

    @Update("update SHARES set SHARE_COUNT=SHARE_COUNT+1 where SHARE_ID=#{share_id}")
    public void updateShareCount(String share_id);

    @Delete("delete from shares where share_id=#{share_id}")
    public void deleteSharePlan(String share_id);

    @Update("update shares\n" +
            "set SHARE_PLACE=#{share_place},SHARE_TITLE=#{share_title},SHARE_CONTENTS=#{share_contents}\n" +
            "where SHARE_ID=${share_id}")
    public void updateShare(Share share);

    @Delete("delete from SHARES_PICTURES where SHARE_ID=#{share_id}")
    public void deleteSharePic(String share_id);

    @Select("select PREFERENCE from USERS where USER_ID=#{user_id}")
    public String selectPreference(String user_id);

    @Insert("insert into SHARES_REC(share_id, user_id) values(#{share_id},#{user_id})")
    public void insertRecommend(String share_id,String user_id);

    @Select("select count(share_id) from SHARES_REC where SHARE_ID = #{share_id}")
    public String selectShareRec(String share_id);

    @Insert("insert into SHARES_COMMENT(comment_contents, user_id, share_id) values(#{comment_contents},#{user_id},#{share_id})")
    public void postComment(ShareComment comment);

    @Select("select * from SHARES_COMMENT where SHARE_ID = #{share_id}")
    public List<ShareComment> getComments(String share_id);

}
