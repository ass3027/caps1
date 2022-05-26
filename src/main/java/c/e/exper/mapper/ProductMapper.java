package c.e.exper.mapper;

import c.e.exper.data.ProductDAO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ProductMapper {

//    @Select("select * from product, pictures where product.pd_id = pictures.pd_id and product.store_id = #{store_id}")
    @Select("SELECT * FROM PRODUCT, PLACE, PICTURES WHERE PRODUCT.PL_ID = PLACE.PL_ID AND PRODUCT.PD_ID = PICTURES.PD_ID")
    public List<ProductDAO> findStoreProduct(@Param("pl_id")String pl_id);

}
