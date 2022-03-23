package c.e.exper.mapper;

import c.e.exper.data.Plan_Suplies;
import c.e.exper.data.Suplies;
import c.e.exper.data.UserDAO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface SuplMapper {


    public List<Plan_Suplies> findMyAllSupl(String id);

    public List<Suplies> findAllSupl();

    public Suplies findSuplByName(String suplName);

    @Update("update PLAN_SUPL set status=#{status} where PLAN_SUPL_ID=#{id}")
    public void updateStatusById(@Param("id")String id,@Param("status")String status);

    @Update("update PLAN_SUPL set quantity=#{quantity} where PLAN_SUPL_ID=#{id}")
    public void updateQuantity(@Param("id")String id,@Param("quantity")int quantity);

    @Insert("INSERT INTO plan_supl(plan_id,supl_id,name) VALUES(#{suplies.plan_id},#{suplies.supl_id.supl_id},#{suplies.name})")
    void insertSuplies(@Param("suplies") Plan_Suplies suplies);

    @Insert("INSERT INTO plan_supl(plan_id,name) VALUES(#{suplies.plan_id},#{suplies.name})")
    void insertSupliesDirectly(@Param("suplies") Plan_Suplies suplies);

    @Delete("delete from PLAN_SUPL where PLAN_SUPL_ID=#{id}")
    public void deleteSuplies(@Param("id") String id);
}
