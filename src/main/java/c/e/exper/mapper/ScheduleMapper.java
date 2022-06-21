package c.e.exper.mapper;

import c.e.exper.data.ScheduleDAO;
import c.e.exper.data.ScheduleDTO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ScheduleMapper {

    @Insert("insert into SCHEDULE values(default,null,#{plan_id},#{pl_id, jdbcType=VARCHAR},#{sch_name},#{sch_startTime,jdbcType=TIMESTAMP},#{sch_endTime,jdbcType=TIMESTAMP},#{expect_expenses},#{place},#{mapX},#{mapY})")
    void insert(ScheduleDAO schedule);

    @Select("Select * from Schedule")
    List<ScheduleDAO> selectAll();

    @Select("""
            Select s.*,r.title as pl_name
            From Schedule s, ( Select title,PL_ID
                               From place p
                               Where p.pl_id in (Select PL_ID
                                                 From SCHEDULE
                                                WHERE PLAN_ID=#{plan_id})) r
            Where s.pl_id=r.PL_ID(+)
            And PLAN_ID= #{plan_id}
            """)
    List<ScheduleDAO> selectAllById(@Param("plan_id")String id);

//    @Select("Select * from Schedule where sch_name=#{name}")
//    List<ScheduleDAO> selectAllByName(@Param("name")String name);
//
//    @Select("Select DISTINCT sch_name from Schedule where plan_id=#{id}")
//    List<String> selectNameById(@Param("id")String id);

    @Delete("Delete From Schedule Where plan_id=#{id}")
    void deleteByPlanId(@Param("id") String id);
}
