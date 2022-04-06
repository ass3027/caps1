package c.e.exper.mapper;

import c.e.exper.data.AffiliatedDAO;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Optional;

@Mapper
public interface AffiliatedMapper {

    @Insert("insert into Affiliated values(#{affiliatedDAO.user_id},#{affiliatedDAO.plan_id})")
    void insert(@Param("affiliatedDAO") AffiliatedDAO affiliatedDAO);

    @Select("Select * From Affiliated Where plan_id=#{plan_id}")
    List<AffiliatedDAO> SelectByPlanId(@Param("plan_id") String plan_id);

    @Select("Select * From Affiliated Where plan_id=#{affiliatedDAO.plan_id} AND user_id=#{affiliatedDAO.user_id}")
    Optional<AffiliatedDAO> SelectById(@Param("affiliatedDAO") AffiliatedDAO affiliatedDAO);

    @Delete("Delete From Affiliated Where user_id=#{user_id}")
    void deleteByUserId(@Param("user_id") String user_id);
//
//    @Delete("Delete From Affiliated Where user_id=#{plan_id}")
//    void DeleteByPlanId(@Param("plan_id") String plan_id);
}
