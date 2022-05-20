package c.e.exper.mapper;

import c.e.exper.data.ImportSuppliesDTO;
import c.e.exper.data.Plan_Suplies;
import c.e.exper.data.Suplies;
import c.e.exper.data.UserDAO;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface SuplMapper {


    public List<Plan_Suplies> findMyAllSupl(String id);

    public List<Suplies> findAllSupl();

    public Suplies findSuplByName(String suplName);

    @Update("update PLAN_SUPL set status=#{status} where PLAN_SUPL_ID=#{id}")
    public void updateStatusById(@Param("id")String id,@Param("status")String status);

    @Update("update PLAN_SUPL set quantity=#{quantity} where PLAN_SUPL_ID=#{id}")
    public void updateQuantity(@Param("id")String id,@Param("quantity")int quantity);

    @Update("update PLAN_SUPL set name=#{name} where plan_supl_id=#{plan_supl_id}")
    public void updateName(String name, String plan_supl_id);

    @Insert("INSERT INTO plan_supl(plan_id,supl_id,name) VALUES(#{suplies.plan_id},#{suplies.supl_id.supl_id},#{suplies.name})")
    void insertSuplies(@Param("suplies") Plan_Suplies suplies);

    @Insert("INSERT INTO plan_supl(plan_id,name) VALUES(#{suplies.plan_id},#{suplies.name})")
    void insertSupliesDirectly(@Param("suplies") Plan_Suplies suplies);

    @Delete("delete from PLAN_SUPL where PLAN_SUPL_ID=#{id}")
    public void deleteSuplies(@Param("id") String id);

    @Select("select c.PL_ID, c.pl_name , e.supl_id , e.SUPL_NAME\n" +
            "from PLANNER a, SCHEDULE b, place c,  PL_SUPL d, SUPLIES e\n" +
            "where a.PLAN_ID=b.PLAN_ID\n" +
            "and b.PL_ID = c.pl_id\n" +
            "and c.pl_id = d.PL_ID\n" +
            "and d.SUPL_ID = e.SUPL_ID\n" +
            "and a.PLAN_ID=#{id}\n" +
            "order by to_number(c.PL_ID)")
    public List<ImportSuppliesDTO> findSuppliesSets(@Param("id")String id);
}
