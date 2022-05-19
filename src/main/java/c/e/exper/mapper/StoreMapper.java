package c.e.exper.mapper;

import c.e.exper.data.*;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Optional;

@Mapper
public interface StoreMapper {

    @Insert("INSERT INTO Store VALUES (#{store.store_id}, #{store.user_id}, #{store.pl_id}, #{store.store_phone}, #{store.category}, #{store.store_name})")
    void insert(@Param("store")StoreDAO store);

    @Select("select * from STORE")
    List<PlaceDAO> findAll();

//    @Select("select pic_name, store_id from PICTURES where store_id is not null")
//    List<PictureDAO> selectStorePic();

    @Select("SELECT PLACE.PL_ID, PLACE.ADDRESS, PLACE.ADDRESS_SI, PLACE.ADDRESS_DONG, PLACE.STORE_PHONE, PLACE.CATEGORY, PLACE.STORE_ID, PLACE.STORE_INFO, PLACE.STORE_NAME, PICTURES.PIC_NAME" +
            " FROM PLACE, PICTURES WHERE PLACE.PL_ID = PICTURES.PL_ID AND PLACE.CATEGORY=#{CATEGORY}")
    List<PlaceDAO> selectByCategory(String category, String options);

}