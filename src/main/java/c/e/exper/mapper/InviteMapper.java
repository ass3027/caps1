package c.e.exper.mapper;

import c.e.exper.data.InviteDAO;
import org.apache.ibatis.annotations.*;
import org.springframework.security.core.parameters.P;

import java.util.List;
import java.util.Optional;

@Mapper
public interface InviteMapper {

    @Insert("insert into invite values(#{inviteDAO.user_id},#{inviteDAO.plan_id})")
    void insert(@Param("inviteDAO") InviteDAO inviteDAO);

    @Select("SELECT * FROM Invite WHERE user_id = #{user_id}")
    List<InviteDAO> selectByUserId(@Param("user_id") String user_id);

    @Select("SELECT * FROM Invite WHERE plan_id = #{plan_id}")
    List<InviteDAO> selectByPlanId(@Param("plan_id") String plan_id);

    @Select("SELECT * FROM Invite WHERE plan_id = #{inviteDAO.plan_id} AND user_id=#{inviteDAO.user_id}")
    Optional<InviteDAO> selectById(@Param("inviteDAO") InviteDAO inviteDAO);

    @Delete("Delete From Invite Where user_id=#{user_id} AND plan_id=#{plan_id}")
    void deleteById(@Param("user_id") String user_id, @Param("plan_id") String plan_id);
}
