package c.e.exper.mapper;

import c.e.exper.data.PlannerDAO;
import c.e.exper.data.Share;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PlannerMapper {

//    @Insert("Insert into PLANNER(plan_start,plan_end,user_id,plan_name) values(#{planner.plan_start},#{planner.plan_end},#{planner.user_id},#{planner.plan_name})")
//    void insert(@Param("planner")PlannerDAO plannerDAO);

    @Insert("Insert into PLANNER(plan_id,plan_start,plan_end,user_id,plan_name) values(#{plan_id},#{plan_start},#{plan_end},#{user_id},#{plan_name})")
    @SelectKey(statement="select PLAN_SEQUENCE.nextval FROM DUAL", keyProperty="plan_id", before=true, resultType=String.class)
    int insert(PlannerDAO plannerDAO);


    @Select("Select * from Planner")
    List<PlannerDAO> selectAll();

    @Select("Select * from Planner where user_id=#{id}")
    List<PlannerDAO> selectAllById(@Param("id")String id);

    @Select("Select * from Planner where plan_id=#{plan_id}")
    PlannerDAO selectById(String plan_id);

    @Delete("Delete From Planner Where plan_id=#{id}")
    void delete(@Param("id") String id);
}
