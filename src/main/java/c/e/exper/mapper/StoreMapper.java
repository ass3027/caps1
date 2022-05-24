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

    @Select("SELECT PLACE.PL_ID,\n" +
            "       OPTION_ID,\n" +
            "       PLACE.ADDRESS,\n" +
            "       PLACE.ADDRESS_SI,\n" +
            "       PLACE.ADDRESS_DONG,\n" +
            "       PLACE.STORE_PHONE,\n" +
            "       PLACE.CATEGORY,\n" +
            "       PLACE.STORE_ID,\n" +
            "       PICTURES.PIC_NAME\n" +
            "FROM PLACE,\n" +
            "     PICTURES,\n" +
            "     OPTION_PLACE\n" +
            "WHERE PLACE.PL_ID = PICTURES.PL_ID\n" +
            "  AND PLACE.PL_ID = OPTION_PLACE.PL_ID\n" +
            "  AND PLACE.CATEGORY = #{category}\n" +
            "order by 1")
    List<PlaceDAO> selectByCategory(String category);

//    @Select("select a.PL_ID, pl_name, ADDRESS,  OPTION_ID\n" +
//            "from OPTION_PLACE a, PLACE b\n" +
//            "where a.PL_ID =b.PL_ID\n" +
//            "  and OPTION_ID in (#{checkOptions})")
//    List<PlaceDAO> selectByCategory2(ArrayList)
}