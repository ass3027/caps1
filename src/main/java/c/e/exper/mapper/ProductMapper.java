package c.e.exper.mapper;

import c.e.exper.data.BookDAO;
import c.e.exper.data.ProductTime;
import c.e.exper.data.ProductDAO;
import org.apache.ibatis.annotations.*;
import org.springframework.security.core.parameters.P;

import java.util.List;

@Mapper
public interface ProductMapper {

//  @Select("select * from product, pictures where product.pd_id = pictures.pd_id and product.store_id = #{store_id}")
//  @Select("SELECT place.*,pic_name " +
//          "FROM PRODUCT, place, (select pic_name from picture where pl_id=#{pl_id}) pic " +
//          "WHERE PRODUCT.PL_ID = PLACE.PL_ID AND PRODUCT.PD_ID = PICTURES.PD_ID AND PRODUCT.PL_ID = #{pl_id}")

    @Select("""
            SELECT PRODUCT.PD_ID, PRODUCT.PD_NAME, PRODUCT.PD_PRICE, PLACE.*,
                   PRODUCT.PD_INFO, PICTURES.PIC_NAME
            FROM PRODUCT, PICTURES, ( select * from Place where PLACE.PL_ID = #{pl_id} ) PLACE
            WHERE PRODUCT.PD_ID= PICTURES.PD_ID(+)
              AND PLACE.PL_ID= PRODUCT.PL_ID(+)
            """)
    List<ProductDAO> findStoreProduct(@Param("pl_id") String pl_id);

    @Select("""
            SELECT PICTURES.PIC_NAME, PRODUCT.PD_ID
            FROM PRODUCT, PICTURES
            WHERE PRODUCT.PD_ID = PICTURES.PD_ID AND PRODUCT.PD_ID = #{pd_id}
            """)
    List<ProductDAO> ProductImage(@Param("pd_id") String pd_id);

    @Select("""
            SELECT *
            FROM PRODUCT_TIME
            WHERE PD_ID = #{pd_id}
            """)
    List<ProductTime> ProductTime(@Param("pd_id") String pd_id);

    @Update("""
            UPDATE PRODUCT_TIME SET PD_WHETHER = '1'
            WHERE PRODUCT_TIME_NUM = #{product_time_num}
            """)
    void productSet(@Param("product_time_num") String product_time_num);

    @Insert("""
            INSERT INTO PAYMENT(PAY_PRICE, USER_ID, PRODUCT_TIME_NUM) VALUES (#{pay_price}, #{user_id}, #{product_time_num})
            """)
    void bookInsert(String pay_price, String user_id, String product_time_num);

    @Select("""
            SELECT *
            FROM BOOK
            WHERE USER_ID = #{user_id} AND PRODUCT_TIME_NUM is not null
            """)
    List<BookDAO> SelectBook(String user_id);

}
