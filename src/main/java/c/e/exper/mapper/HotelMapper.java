package c.e.exper.mapper;

import c.e.exper.data.StoreDAO;
import c.e.exper.data.StoreDTO;
import c.e.exper.data.UserDAO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface HotelMapper {

    @Insert("INSERT INTO Store VALUES (store_id, #{store.user_id}, pl_id, store_phone, store.pic_name)")
    void insert(@Param("store")StoreDAO store);
    // 나 왔다 감

    //Optional<Plan_Suplies> findById(String id);

    @Select("select * from STORE")
    List<StoreDAO> findAll();
}