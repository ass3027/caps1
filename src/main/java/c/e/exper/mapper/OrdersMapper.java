package c.e.exper.mapper;


import c.e.exper.data.OrderDAO;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface OrdersMapper {


    @Insert("insert into location values (#{loc_group}, #{id}, #{latitude}, #{longitude})")
    public boolean insertLocation(@Param("loc_group") String loc_group, @Param("id") String id, @Param("latitude") double latitude, @Param("longitude") double longitude );

    @Update("update location set latitude = #{latitude}, longitude = #{longitude} where loc_group = #{loc_group} and loc_index = #{loc_index}")
    public int updateLocation(@Param("latitude") double latitude, @Param("longitude") double longitude, @Param("loc_group") String loc_group, @Param("loc_index") String loc_index);

    @Select("select latitude, longitude from location where loc_group = #{loc_group} and loc_index = #{loc_index}")
    public Map<String, Double> selectLocation(@Param("loc_group") String loc_group, @Param("loc_index") String loc_index);

    @Select("select count(*) from location where loc_group = #{loc_group} and loc_index = #{loc_index}")
    public boolean locationExist(@Param("loc_group") String loc_group, @Param("loc_index") String loc_index);

    @Select("SELECT * FROM orders WHERE keep_start in #{keep_list}")
    public List<OrderDAO> findOrdersForKeepID(@Param("keep_list") List<String> keep_list);

    @Select("""
            SELECT *
            FROM ORDERS
            where KEEP_START in (SELECT pl_id
                                 FROM place
                                 WHERE mapx > #{minLongitude} and mapx < #{maxLongitude}
                                 AND   mapy > #{minLatitude}  and mapy < #{maxLatitude})
            AND STATUS = '1'
            AND KEEP_END is not null
            """)
    public List<OrderDAO> findNearOrders(@Param("minLongitude") Double minLongitude, @Param("maxLongitude") Double maxLongitude,
                                      @Param("minLatitude") Double minLatitude, @Param("maxLatitude") Double maxLatitude);


    @Select("""
            SELECT *
            FROM orders
            WHERE status = #{status}
            AND delivery_id = #{delivery_id}
            """)
    public List<OrderDAO> findDuserOrders(@Param("delivery_id") String delivery_id, @Param("status") int status);



    @Select("""
            SELECT bag_size, COUNT(*) cnt
            FROM bag
            WHERE bag_id in(SELECT bag_id
                            FROM order_bag
                            WHERE ord_id = #{ord_id}) GROUP BY bag_size
            """)
    public List<Map<String, Character>> findOrdersBaginfo(@Param("ord_id") String ord_id);


    @Select("""
            SELECT *
            FROM orders
            WHERE ord_id = #{ord_id}
            """)
    public OrderDAO findOrder(@Param("ord_id") String ord_id);

    @Select("""
            SELECT status
            FROM orders
            WHERE ord_id = #{ord_id}
            """)
    public String checkOrderStatus(@Param("ord_id") String ord_id);

    @Update("""
            UPDATE orders
            SET status = #{statusToChange}
            WHERE ord_id = #{ord_id}
            """)
    public boolean updateStatus(@Param("ord_id") String ord_id, @Param("statusToChange") String status);

    @Update("""
            UPDATE orders
            SET delivery_id = #{user_id}
            WHERE ord_id = #{ord_id}
            """)
    public boolean updateDeliveryId(@Param("ord_id") String ord_id, @Param("user_id") String user_id);

    @Update("""
            UPDATE orders
            SET status = 3
            WHERE ord_id = #{ord_id}
            """)
    public boolean orderArrival(@Param("ord_id") String ord_id);


}
