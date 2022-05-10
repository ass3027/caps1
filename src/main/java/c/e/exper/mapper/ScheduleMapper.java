package c.e.exper.mapper;

import c.e.exper.data.ScheduleDAO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ScheduleMapper {

    @Insert("insert into SCHEDULE values(default,null,#{plan_id},#{pl_id, jdbcType=VARCHAR},#{sch_name},#{sch_startTime,jdbcType=TIMESTAMP},#{sch_endTime,jdbcType=TIMESTAMP},#{expect_expenses})")
    void insert(ScheduleDAO schedule);

    @Select("Select * from Schedule")
    List<ScheduleDAO> selectAll();

    @Select("Select * from Schedule where plan_id=#{id}")
    List<ScheduleDAO> selectAllById(@Param("id")String id);

    @Select("Select * from Schedule where sch_name=#{name}")
    List<ScheduleDAO> selectAllByName(@Param("name")String name);

    @Select("Select DISTINCT sch_name from Schedule where plan_id=#{id}")
    List<String> selectNameById(@Param("id")String id);

    @Delete("Delete From Schedule Where plan_id=#{id}")
    void delete(@Param("id") String id);
}
