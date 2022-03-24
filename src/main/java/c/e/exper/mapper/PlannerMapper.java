package c.e.exper.mapper;

import c.e.exper.data.PlannerDAO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PlannerMapper {

    @Insert("Insert into PLANNER(plan_start,plan_end,user_id,plan_name) values(#{planner.plan_start},#{planner.plan_end},#{planner.user_id},#{planner.plan_name})")
    void insert(@Param("planner")PlannerDAO plannerDAO);
}
