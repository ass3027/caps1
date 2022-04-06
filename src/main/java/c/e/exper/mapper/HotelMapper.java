package c.e.exper.mapper;

import c.e.exper.data.StoreDAO;
import c.e.exper.data.StoreDTO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface HotelMapper {

    @Select("select * from STORE")
    List<StoreDAO> findAll();

    //@Insert("INSERT INTO store VALUES()")

    //Optional<Plan_Suplies> findById(String id);

}