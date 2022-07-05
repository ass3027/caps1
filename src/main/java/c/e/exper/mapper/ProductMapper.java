package c.e.exper.mapper;

import c.e.exper.data.BookDAO;
import c.e.exper.data.PaymentDAO;
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
            INSERT INTO PRODUCT_TIME(PD_ID, "date", ROOM_NUM) VALUES (#{pd_id}, #{date}, #{room_num})
            """)
    void product_Time_Insert(ProductTime productTime);

    @Select("""
            select *
            from PRODUCT_TIME a, PAYMENT b
            where b.PAY_ID = a.PAY_ID and USER_ID = #{id}
            """)
    List<BookDAO> SelectBook(@Param("id") String id);


    @Select("""
            select sum(PAY_PRICE) as 일주일
            from PAYMENT, PRODUCT_TIME
            where PAYMENT.PAY_ID = PRODUCT_TIME.PAY_ID and PAYMENT.PAY_ID in (select PRODUCT_TIME.PAY_ID
                                                                              from PRODUCT_TIME
                                                                              where PD_ID in (select PRODUCT.PD_ID
                                                                                              from PRODUCT, PLACE
                                                                                              where PLACE.PL_ID = PRODUCT.PL_ID and place.USER_ID = #{id}))
            and SYSDATE-7 < PAY_TIME
            """)
    ProductDAO product_Sales7(@Param("id")String id);

//    @Select("select sum(PAY_PRICE)as count\n" +
//            "from PAYMENT\n" +
//            "where gtime_num in (select TIME_NUM\n" +
//            "                    from AVAILABLE_TIME\n" +
//            "                    where GITEM_ID in (select GITEM_ID\n" +
//            "                                       from GITEM\n" +
//            "                                       where USER_ID = #{id}))")
//    GItemDAO selectCount(@Param("id") String id);
//
//    @Select("select sum(PAY_PRICE)as count7\n" +
//            "from PAYMENT\n" +
//            "where gtime_num in (select TIME_NUM\n" +
//            "                    from AVAILABLE_TIME\n" +
//            "                    where GITEM_ID in (select GITEM_ID\n" +
//            "                                       from GITEM\n" +
//            "                                       where USER_ID = #{id}))" +
//            "and SYSDATE-7 < PAY_TIME")
//    GItemDAO selectCount7(@Param("id") String id);
//
//    @Select("select sum(PAY_PRICE)as count1\n" +
//            "from PAYMENT\n" +
//            "where gtime_num in (select TIME_NUM\n" +
//            "                    from AVAILABLE_TIME\n" +
//            "                    where GITEM_ID in (select GITEM_ID\n" +
//            "                                       from GITEM\n" +
//            "                                       where USER_ID = #{id}))\n" +
//            "and to_char(PAY_TIME,'yyyy-mm-dd') = TO_CHAR(SYSDATE,'yyyy-mm-dd')")
//    GItemDAO selectCount1(@Param("id") String id);
//
//    @Select("select sum(PAY_PRICE)as count30\n" +
//            "from PAYMENT\n" +
//            "where gtime_num in (select TIME_NUM\n" +
//            "                    from AVAILABLE_TIME\n" +
//            "                    where GITEM_ID in (select GITEM_ID\n" +
//            "                                       from GITEM\n" +
//            "                                       where USER_ID = #{id}))\n" +
//            "and to_char(PAY_TIME,'yyyy-mm-dd') > TO_CHAR(SYSDATE-30,'yyyy-mm-dd')")
//    GItemDAO selectCount30(@Param("id") String id);
}
