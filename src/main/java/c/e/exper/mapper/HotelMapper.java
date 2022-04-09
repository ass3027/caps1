package c.e.exper.mapper;

import c.e.exper.data.StoreDAO;
import c.e.exper.data.StoreDTO;
import c.e.exper.data.UserDAO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Optional;

@Mapper
public interface HotelMapper {

    @Insert("INSERT INTO Store VALUES (#{store.store_id}, #{store.user_id}, #{store.pl_id}, #{store.store_phone})")
    void insert(@Param("store")StoreDAO store);

    @Select("select * from STORE")
    List<StoreDAO> findAll();

    @Select("select PIC_NAME from PICTURES where store_id is not null")
    List<String> selectStorePic();
}