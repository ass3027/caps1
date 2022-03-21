package c.e.exper.mapper;

import c.e.exper.data.Store;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;


import java.util.List;

@Mapper
public interface HotelMapper {

    @Select("select * from STORE")
    List<Store> findAll();
    //Optional<Plan_Suplies> findById(String id);


}