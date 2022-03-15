package c.e.exper.mapper;

import c.e.exper.data.Plan_Suplies;
import org.apache.ibatis.annotations.Mapper;


import java.util.List;
import java.util.Map;
import java.util.Optional;

@Mapper
public interface SuplMapper {


    List<Map<String, Object>> findAll();
    //Optional<Plan_Suplies> findById(String id);


}