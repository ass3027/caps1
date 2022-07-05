package c.e.exper.mapper;

import c.e.exper.data.BookDAO;
import c.e.exper.data.PaymentDAO;
import c.e.exper.data.ProductTime;
import c.e.exper.data.ProductDAO;
import org.apache.ibatis.annotations.*;
import org.springframework.security.core.parameters.P;

import java.awt.print.Book;
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
            select sum(PAY_PRICE) as count
            from PAYMENT, PRODUCT_TIME
            where PAYMENT.PAY_ID = PRODUCT_TIME.PAY_ID and PAYMENT.PAY_ID in (select PRODUCT_TIME.PAY_ID
                                                                              from PRODUCT_TIME
                                                                              where PD_ID in (select PRODUCT.PD_ID
                                                                                              from PRODUCT, PLACE
                                                                                              where PLACE.PL_ID = PRODUCT.PL_ID and place.USER_ID = #{id}))
            """)
    ProductDAO product_Sales(@Param("id")String id);

    @Select("""
            select sum(PAY_PRICE) as count1
            from PAYMENT, PRODUCT_TIME
            where PAYMENT.PAY_ID = PRODUCT_TIME.PAY_ID and PAYMENT.PAY_ID in (select PRODUCT_TIME.PAY_ID
                                                                              from PRODUCT_TIME
                                                                              where PD_ID in (select PRODUCT.PD_ID
                                                                                              from PRODUCT, PLACE
                                                                                              where PLACE.PL_ID = PRODUCT.PL_ID and place.USER_ID = #{id}))
            and to_char(PAY_TIME,'yyyy-mm-dd') = TO_CHAR(SYSDATE,'yyyy-mm-dd')
            """)
    ProductDAO product_Sales1(@Param("id")String id);

    @Select("""
            select sum(PAY_PRICE) as count7
            from PAYMENT, PRODUCT_TIME
            where PAYMENT.PAY_ID = PRODUCT_TIME.PAY_ID and PAYMENT.PAY_ID in (select PRODUCT_TIME.PAY_ID
                                                                              from PRODUCT_TIME
                                                                              where PD_ID in (select PRODUCT.PD_ID
                                                                                              from PRODUCT, PLACE
                                                                                              where PLACE.PL_ID = PRODUCT.PL_ID and place.USER_ID = #{id}))
            and SYSDATE-7 < PAY_TIME
            """)
    ProductDAO product_Sales7(@Param("id")String id);

    @Select("""
            select sum(PAY_PRICE) as count30
            from PAYMENT, PRODUCT_TIME
            where PAYMENT.PAY_ID = PRODUCT_TIME.PAY_ID and PAYMENT.PAY_ID in (select PRODUCT_TIME.PAY_ID
                                                                              from PRODUCT_TIME
                                                                              where PD_ID in (select PRODUCT.PD_ID
                                                                                              from PRODUCT, PLACE
                                                                                              where PLACE.PL_ID = PRODUCT.PL_ID and place.USER_ID = #{id}))
            and to_char(PAY_TIME,'yyyy-mm-dd') > TO_CHAR(SYSDATE-30,'yyyy-mm-dd')
            """)
    ProductDAO product_Sales30(@Param("id")String id);

    @Select("""
            select p.pd_id, c."date", p.MAX_ROOM_NUM, c.count, c.PAY_ID
            from product p,
                 (select count(*) count, PD_ID, "date", PAY_ID
                  from PRODUCT_TIME t
                  group by "date", PD_ID, PAY_ID
                 ) c
            where p.PD_ID = c.PD_ID
            and p.PD_ID = #{pd_id} and c.PAY_ID = #{pay_id}
            and p.MAX_ROOM_NUM > c.count
            """)
    List<BookDAO> product_book_no(@Param("pd_id")String pd_id, @Param("pay_id")String pay_id);

}
