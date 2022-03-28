package c.e.exper.mapper;

import c.e.exper.data.Place;
import c.e.exper.data.Store;
import c.e.exper.data.UserDAO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;


import java.util.List;

@Mapper
public interface HotelMapper {

    @Select("select * from STORE")
    List<Store> findAll();
    //Optional<Plan_Suplies> findById(String id);

    @Insert("INSERT INTO STORE VALUES(store_id, #{user.user_id}, #{pl.pl_id}, store_phone)")
    void insert(@Param("user") UserDAO user);
    void insert(@Param("pl") Place place);

    void insert(Store stores);
}