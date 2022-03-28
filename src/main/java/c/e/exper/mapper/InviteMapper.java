package c.e.exper.mapper;

import c.e.exper.data.InviteDAO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface InviteMapper {

    @Insert("insert into invite values(#{inviteDAO.user_id},#{inviteDAO.plan_id})")
    void insert(@Param("inviteDAO") InviteDAO inviteDAO);

    @Select("SELECT * FROM Invite WHERE user_id = #{user_id}")
    List<InviteDAO> selectByUserId(@Param("user_id") String user_id);

    @Select("SELECT * FROM Invite WHERE plan_id = #{plan_id}")
    List<InviteDAO> selectByPlanId(@Param("plan_id") String plan_id);
}
