package c.e.exper.mapper;

import c.e.exper.data.InviteDAO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface InviteMapper {

    @Insert("insert into invite values(#{inviteDAO.user_id},#{inviteDAO.plan_id})")
    void insert(@Param("inviteDAO") InviteDAO inviteDAO);
}
