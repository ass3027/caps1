package c.e.exper.mapper;

import c.e.exper.data.BagDAO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface BagMapper {

    @Insert("insert into orders(user_id,keep_start,keep_end) values(#{user_id},#{keep_start},#{keep_end})")
    void insert(BagDAO bag);

    @Select("Select * from Orders Where user_id=#{user_id}")
    List<BagDAO> selectByUserId(String user_id);


}