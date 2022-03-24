package c.e.exper.mapper;

import c.e.exper.data.AffiliatedDAO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AffiliatedMapper {

    @Insert("insert into invite values(#{affiliatedDAO.user_id},#{affiliatedDAO.plan_id})")
    void insert(@Param("invite") AffiliatedDAO affiliatedDAO);
}
