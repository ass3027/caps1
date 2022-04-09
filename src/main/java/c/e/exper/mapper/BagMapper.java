package c.e.exper.mapper;

import c.e.exper.data.BagDAO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface BagMapper {

    @Insert("insert into orders(ord_id,user_id,keep_start,keep_end) values(#{ord_id},#{user_id},#{keep_start},#{keep_end})")
    public void insert(BagDAO bag);


}