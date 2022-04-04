package c.e.exper.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface HotelMapper {

    @Select("select * from STORE")
    List<Store> findAll();
    //Optional<Plan_Suplies> findById(String id);

//    void insert(@Param("user") UserDAO user);
//    void insert(@Param("pl") Place place);
}