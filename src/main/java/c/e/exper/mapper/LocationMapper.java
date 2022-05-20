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

    @Select("select latitude, longitude from location where loc_group = #{loc_group} and loc_index = #{loc_index}")
    public Map<String, BigDecimal> selectLocation(@Param("loc_group") String loc_group, @Param("loc_index") String loc_index);

    @Select("select count(*) from location where loc_group = #{loc_group} and loc_index = #{loc_index}")
    public boolean locationExist(@Param("loc_group") String loc_group, @Param("loc_index") String loc_index);
}
