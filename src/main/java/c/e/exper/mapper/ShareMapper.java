package c.e.exper.mapper;

import c.e.exper.data.Share;
import c.e.exper.data.SharePictureDAO;
import org.apache.ibatis.annotations.*;
import org.springframework.security.core.parameters.P;

import java.util.List;

@Mapper
public interface ShareMapper {

    @Insert("INSERT INTO shares(share_id,user_id, PLAN_ID) VALUES  (#{share_id}, #{user_id}, #{plan_id})")
    @SelectKey(statement="select SHARES_SEQUENCE.nextval FROM DUAL", keyProperty="share_id", before=true, resultType=String.class)
    int insert(Share share);


    public List<Share> getTest();

    public long insertTest(String param1, String param2);

    @Select("select * from shares")
    public List<Share> findAllShares();

    @Insert("insert into shares(user_id,SHARE_PLACE,SHARE_TITLE,SHARE_CONTENTS,PLAN_ID)\n" +
            "values(#{share.user_id},#{share.share_place},#{share.share_title},#{share.share_contents},#{share.plan_id})")
    public void insertShares(@Param("share")Share share);

    @Insert("insert into SHARES_PICTURES(share_id,pic_name)\n" +
            "values(#{sharePic.share_id},#{sharePic.pic_name})")
    public void insertSharesPictures(@Param("sharePic") SharePictureDAO sharePic);


}
