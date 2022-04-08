package c.e.exper.mapper;

import c.e.exper.data.PlannerDAO;
import c.e.exper.data.Schedule;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ScheduleMapper {

    @Insert("insert into SCHEDULE values(default,null,#{plan_id},#{pl_id},#{sch_name},#{sch_starttime},#{sch_endtime},#{expect_expenses})")
    void insert(Schedule schedule);

    @Select("Select * from Schedule")
    List<Schedule> selectAll();

    @Select("Select * from Schedule where plan_id=#{id}")
    List<Schedule> selectAllById(@Param("id")String id);

    @Delete("Delete From Schedule Where plan_id=#{id}")
    void delete(@Param("id") String id);
}
