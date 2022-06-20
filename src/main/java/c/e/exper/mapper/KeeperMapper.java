package c.e.exper.mapper;


import c.e.exper.data.OrderDAO;
import c.e.exper.data.PlaceDAO;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface KeeperMapper {


    @Select("""
            SELECT *
            FROM place
            WHERE pl_id = #{keep_id}
            """)
    PlaceDAO findKeep(@Param("keep_id")String keep_id);


}
