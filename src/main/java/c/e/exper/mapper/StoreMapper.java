package c.e.exper.mapper;

import c.e.exper.data.PictureDAO;
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
public interface StoreMapper {

    @Insert("INSERT INTO Store VALUES (#{store.store_id}, #{store.user_id}, #{store.pl_id}, #{store.store_phone}, #{store.category})")
    void insert(@Param("store")StoreDAO store);

    @Select("select * from STORE")
    List<StoreDAO> findAll();

    @Select("select pic_name, store_id from PICTURES where store_id is not null")
    List<PictureDAO> selectStorePic();

    @Select("Select s.*,p.pic_name From Store s,Pictures p Where s.category=#{category} and s.store_id=p.store_id")
    List<StoreDAO> selectByCategory(String category);
}