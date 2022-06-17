package c.e.exper.mapper;

import c.e.exper.data.PlaceDAO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface BookMarkMapper {
    @Select("SELECT pl_name, address, store_phone, user_id FROM PLACE where user_id=#{user_id}")
    List<PlaceDAO> selectAllByUserId(String user_id);
}
