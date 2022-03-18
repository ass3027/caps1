package c.e.exper.mapper;

import c.e.exper.data.Plan_Suplies;
import c.e.exper.data.Suplies;
import c.e.exper.data.UserDAO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SuplMapper {


    public List<Plan_Suplies> findMyAllSupl(String id);

    public List<Suplies> findAllSupl();


//    public List<Plan_Suplies> findById(String plan_id);

    @Insert("INSERT INTO plan_supl(plan_id,supl_id) VALUES(#{suplies.plan_id},#{suplies.supl_id.supl_id})")
    void insertSuplies(@Param("suplies") Plan_Suplies suplies);

    @Insert("INSERT INTO plan_supl(plan_id,name) VALUES(#{suplies.plan_id},#{suplies.name})")
    void insertSupliesDirectly(@Param("suplies") Plan_Suplies suplies);


}
