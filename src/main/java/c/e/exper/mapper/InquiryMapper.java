package c.e.exper.mapper;

import c.e.exper.data.InquiryDAO;
import c.e.exper.data.UserDAO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Optional;

@Mapper
public interface InquiryMapper {//디비접근
    //글쓸때
    @Insert("INSERT INTO inquiry VALUES(#{inq.inq_id},#{inq.inq_type},#{inq.inq_title},#{inq.inq_body},#{inq.inq_time},#{inq.count},)")
    boolean insert(@Param("inq") InquiryDAO inq);
    //void

    @Select("SELECT * FROM  WHERE user_id=#{user_id}")
    Optional<InquiryDAO> selectId (String user_id);
    //반환데이터형식(반환값) //함수이름               데이터형식 //매개변수


}
