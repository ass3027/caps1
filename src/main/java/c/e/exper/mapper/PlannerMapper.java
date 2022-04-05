package c.e.exper.mapper;

import c.e.exper.data.PlannerDAO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PlannerMapper {

    @Insert("Insert into PLANNER(plan_start,plan_end,user_id,plan_name) values(#{planner.plan_start},#{planner.plan_end},#{planner.user_id},#{planner.plan_name})")
    void insert(@Param("planner")PlannerDAO plannerDAO);

    @Select("Select * from Planner")
    List<PlannerDAO> selectAll();

    @Select("Select * from Planner where user_id=#{id}")
    List<PlannerDAO> selectAllById(@Param("id")String id);

    @Delete("Delete From Planner Where plan_id=#{id}")
    void delete(@Param("id") String id);
}
