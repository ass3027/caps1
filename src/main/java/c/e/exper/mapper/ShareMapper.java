package c.e.exper.mapper;

import c.e.exper.data.Share;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ShareMapper {

    @Select("select * from shares")
    public List<Share> findAllShares();


}
