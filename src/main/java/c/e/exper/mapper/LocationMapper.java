package c.e.exper.mapper;


import org.apache.ibatis.annotations.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Mapper
public interface LocationMapper {


    @Insert("insert into location values (#{loc_group}, #{id}, #{latitude}, #{longitude})")
    public boolean insertLocation(@Param("loc_group") String loc_group, @Param("id") String id, @Param("latitude") double latitude, @Param("longitude") double longitude );

    @Update("update location set latitude = #{latitude}, longitude = #{longitude} where loc_group = #{loc_group} and loc_index = #{loc_index}")
    public int updateLocation(@Param("latitude") double latitude, @Param("longitude") double longitude, @Param("loc_group") String loc_group, @Param("loc_index") String loc_index);

    @Select("""
            SELECT latitude, longitude
            FROM delivery_user
            WHERE user_id = #{duser_id}
            """)
    public Map<String, BigDecimal> findDuserLocation(@Param("duser_id") String duser_id);

    @Select("select count(*) from location where loc_group = #{loc_group} and loc_index = #{loc_index}")
    public boolean locationExist(@Param("loc_group") String loc_group, @Param("loc_index") String loc_index);

    @Select("""
            SELECT pl_id
            FROM place2
            WHERE mapx > #{minLongitude} and mapx < #{maxLongitude}
            AND   mapy > #{minLatitude}  and mapy < #{maxLatitude}""")
    public List<String> findNearPlace(@Param("minLongitude") Double minLongitude, @Param("maxLongitude") Double maxLongitude,
                                        @Param("minLatitude") Double minLatitude, @Param("maxLatitude") Double maxLatitude);

}
