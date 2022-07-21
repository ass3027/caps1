package c.e.exper.mapper;

import c.e.exper.data.*;
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
                   PRODUCT.PD_INFO, PICTURES.PIC_NAME, PRODUCT.ROOM_HUMAN, PRODUCT.BED_INFO
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
            INSERT INTO PRODUCT_TIME(PD_ID, "date", ROOM_NUM, PAY_ID) VALUES (#{pd_id}, #{date}, #{room_num}, #{pay_id})
            """)
    void product_Time_Insert(ProductTime productTime);

    @Insert("""
            INSERT INTO PAYMENT(PAY_PRICE, USER_ID) VALUES (#{paymentResult.pay_price}, #{paymentResult.user_id})
            """)
    void product_payment_Insert(@Param("paymentResult")PaymentResult paymentResult);

    @Select("""
            select PAY_SEQUENCES.CURRVAL
            from dual
            """)
    String pay_id();

    @Select("""
            select min("date") st_date, max("date")+1 end_date, c.title, b.USER_ID
            from PRODUCT_TIME a, PAYMENT b, PLACE c, PRODUCT d
            where b.PAY_ID = a.PAY_ID and c.PL_ID = d.PL_ID and b.USER_ID = #{id}
            group by a.PAY_ID, c.title, b.USER_ID
            """)
    List<BookDAO> SelectBook(@Param("id") String id);

    @Select("""
            select min("date") st_date, max("date")+1 end_date, c.title, b.USER_ID, d.pd_name, u.USER_NAME, u.USER_PHONE
            from PRODUCT_TIME a, PAYMENT b, PLACE c, PRODUCT d, USERS u
            where b.PAY_ID = a.PAY_ID and c.PL_ID = d.PL_ID and c.USER_ID = #{id} and b.USER_ID = u.USER_ID
            group by a.PAY_ID, c.title, b.USER_ID, d.pd_name, u.USER_NAME, u.USER_PHONE
            """)
    List<BookDAO> SelectUserBook(@Param("id") String id);

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
            select c."date", c.ROOM_NUM
            from product p,
                 (select count(*) count, PD_ID, "date", ROOM_NUM
                  from PRODUCT_TIME t
                  where "date" between #{st_date} AND #{end_date}
                  group by "date", PD_ID, ROOM_NUM
                 ) c
            where p.PD_ID = c.PD_ID
            and p.PD_ID = #{pd_id}
            and p.MAX_ROOM_NUM <= c.count
            """)
    List<BookDAO> product_book_no(@Param("pd_id")String pd_id, @Param("st_date")String st_date, @Param("end_date")String end_date);

    @Select("""
            select *
            from PLACE, PRODUCT
            where PLACE.PL_ID = PRODUCT.PL_ID and PLACE.USER_ID = #{user_id}
            """)
    List<ProductDAO> product_chart(@Param("user_id")String user_id);

    @Select("""
            select sum(PAY_PRICE)
            from PAYMENT, PRODUCT_TIME
            where PAYMENT.PAY_ID = PRODUCT_TIME.PAY_ID and PAYMENT.PAY_ID in (select PRODUCT_TIME.PAY_ID
                                                                              from PRODUCT_TIME
                                                                              where PD_ID in (select PRODUCT.PD_ID
                                                                                              from PRODUCT, PLACE
                                                                                              where PLACE.PL_ID = PRODUCT.PL_ID and place.USER_ID = #{user_id} and product.PD_ID = #{value}))
            """)
    ProductDAO product_chartss(@Param("user_id")String user_id, @Param("value")String value);

    @Select("""
            select *
            from place, product
            where place.pl_id = product.pl_id and product.pd_id = #{pd_id}
            """)
    ProductDAO product_book_pd_id(@Param("pd_id")String pd_id);
    
    @Insert("""
         insert into product(pd_name,pd_price,pl_id,pd_info) values(#{pd_name},#{pd_price},#{pl_id},#{pd_info})
         """)
    void productItem(String pd_name, String pd_price, String pl_id, String pd_info);

    @Select("""
            select *
            from product, pictures
            where product.pl_id = pictures.pl_id and product.pl_id = #{pl_id}
            """)
    List<ProductDAO> productItemGet(String pl_id);

    @Delete("delete from product where PD_ID=#{pd_id}")
    void productDelete(String pd_id);
}
