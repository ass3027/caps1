package c.e.exper.mapper;

import c.e.exper.data.PlannerDAO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PlannerMapper {

    @Insert("Insert into PLANNER values(#{planner.plan_id},#{planner.plan_start},#{planner.plan_end},#{planner.user_id},#{planner.plan_name})")
    void insert(@Param("planner")PlannerDAO plannerDAO);
}
