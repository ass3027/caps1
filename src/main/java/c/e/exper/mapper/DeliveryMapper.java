package c.e.exper.mapper;


import org.apache.ibatis.annotations.*;

@Mapper
public interface DeliveryMapper {


    // 운송원 위도 변경
    @Update("update delivery_user set duser_latitude = #{latitude} where user_id = #{user_id}")
    public void updateDuserLatitude(@Param("latitude") String latitude, @Param("user_id") String user_id);

    // 운송원 경도 변경
    @Update("update delivery_user set duser_longitude = #{longitude} where user_id = #{user_id}")
    public void updateDuserLongitude(@Param("longitude") String longitude, @Param("user_id") String user_id);

    // 운송원 위도 조회
    @Select("select duser_latitude from delivery_user where user_id = #{user_id}")
    public String findLatitudeById(@Param("user_id") String user_id);

    // 운송원 경도 조회
    @Select("select duser_longitude from delivery_user where user_id = #{user_id}")
    public String findLongitudeById(@Param("user_id") String user_id);

    @Select("select delivery_id from orders where ord_id = #{ord_id}")
    public String findDuserIdByOrdId(@Param("ord_id") String ord_id);
}
