package c.e.exper.mapper;

import c.e.exper.data.Plan_Suplies;
import c.e.exper.data.UserDAO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Optional;

@Mapper
public interface SuplMapper {


    public List<Plan_Suplies> findAll();
    public List<Plan_Suplies> findById(String id);


}
