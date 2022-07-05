package c.e.exper.mapper;


import c.e.exper.data.OrderDAO;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface OrdersMapper {


    @Insert("insert into location values (#{loc_group}, #{id}, #{latitude}, #{longitude})")
    boolean insertLocation(@Param("loc_group") String loc_group, @Param("id") String id, @Param("latitude") double latitude, @Param("longitude") double longitude);

    @Update("update location set latitude = #{latitude}, longitude = #{longitude} where loc_group = #{loc_group} and loc_index = #{loc_index}")
    int updateLocation(@Param("latitude") double latitude, @Param("longitude") double longitude, @Param("loc_group") String loc_group, @Param("loc_index") String loc_index);

    @Select("select latitude, longitude from location where loc_group = #{loc_group} and loc_index = #{loc_index}")
    Map<String, Double> selectLocation(@Param("loc_group") String loc_group, @Param("loc_index") String loc_index);

    @Select("select count(*) from location where loc_group = #{loc_group} and loc_index = #{loc_index}")
    boolean locationExist(@Param("loc_group") String loc_group, @Param("loc_index") String loc_index);

    @Select("SELECT * FROM orders WHERE keep_start in #{keep_list}")
    List<OrderDAO> findOrdersForKeepID(@Param("keep_list") List<String> keep_list);

    @Select("""
            SELECT *
            FROM ORDERS
            where KEEP_START in (SELECT pl_id
                                 FROM place
                                 WHERE mapx > #{minLongitude} and mapx < #{maxLongitude}
                                 AND   mapy > #{minLatitude}  and mapy < #{maxLatitude})
            AND STATUS = '승인완료'
            AND KEEP_END is not null
            """)
    List<OrderDAO> findNearOrders(@Param("minLongitude") Double minLongitude, @Param("maxLongitude") Double maxLongitude,
                                  @Param("minLatitude") Double minLatitude, @Param("maxLatitude") Double maxLatitude);


    @Select("""
            SELECT *
            FROM orders
            WHERE status = #{status}
            AND delivery_id = #{delivery_id}
            """)
    List<OrderDAO> findDuserOrders(@Param("delivery_id") String delivery_id, @Param("status") String status);

    @Select("""
            SELECT *
            FROM orders
            WHERE delivery_id = #{delivery_id}
            """)
    List<OrderDAO> findDuserOrdersAll(@Param("delivery_id") String delivery_id);


    @Select("""
            SELECT bag_size, COUNT(*) cnt
            FROM bag
            WHERE bag_id in(SELECT bag_id
                            FROM order_bag
                            WHERE ord_id = #{ord_id}) GROUP BY bag_size
            """)
    List<Map<String, Character>> findOrdersBaginfo(@Param("ord_id") String ord_id);


    @Select("""
            SELECT *
            FROM orders
            WHERE ord_id = #{ord_id}
            """)
    OrderDAO findOrder(@Param("ord_id") String ord_id);

    @Select("""
            SELECT status
            FROM orders
            WHERE ord_id = #{ord_id}
            """)
    String checkOrderStatus(@Param("ord_id") String ord_id);

    @Update("""
            UPDATE orders
            SET status = #{statusToChange}
            WHERE ord_id = #{ord_id}
            """)
    boolean updateStatus(@Param("ord_id") String ord_id, @Param("statusToChange") String status);

    @Update("""
            UPDATE orders
            SET delivery_id = #{user_id}
            WHERE ord_id = #{ord_id}
            """)
    boolean updateDeliveryId(@Param("ord_id") String ord_id, @Param("user_id") String user_id);

    @Update("""
            UPDATE orders
            SET status = '운송완료'
            WHERE ord_id = #{ord_id}
            """)
    boolean orderArrival(@Param("ord_id") String ord_id);


    @Update("""
            UPDATE orders
            SET status = '운송중'
            WHERE ord_id = #{ord_id}
            """)
    boolean orderPickup(@Param("ord_id") String ord_id);

    @Update("""
            UPDATE orders
            SET status = #{status}
            WHERE ord_id = #{ord_id}
            """)
    boolean statusChange(@Param("status") String type, @Param("ord_id") String ord_id);


}
