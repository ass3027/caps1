package c.e.exper.mapper;

import c.e.exper.data.Plan_Suplies;
import c.e.exper.data.Suplies;
import c.e.exper.data.SupliesName;
import c.e.exper.data.UserDAO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Mapper
public interface SuplMapper {


    public List<Map<String, Suplies>> findAll(String id);
    public List<Plan_Suplies> findById(String plan_id);


}
