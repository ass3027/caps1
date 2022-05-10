package c.e.exper.mapper;

import c.e.exper.data.ProductDAO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ProductMapper {

    @Select("select * from product, pictures where product.pd_id = pictures.pd_id and product.store_id = #{store_id}")
    public List<ProductDAO> findStoreProduct(@Param("store_id")String store_id);

}
