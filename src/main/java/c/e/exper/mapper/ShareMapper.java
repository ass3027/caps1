package c.e.exper.mapper;

import c.e.exper.data.ImportSuppliesDTO;
import c.e.exper.data.Plan_Suplies;
import c.e.exper.data.Share;
import c.e.exper.data.Suplies;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ShareMapper {

    @Select("")
    public List<Share> findAllShares();


}
