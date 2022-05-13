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
    @Insert("INSERT INTO inquiry(inq_title, inq_body, user_id, inq_type)VALUES(#{inq.inq_title},#{inq.inq_body},#{inq.user_id},#{inq.inq_type})")
    //시퀀스를 쓴 쿼리
    boolean insert(@Param("inq") InquiryDAO inq);
    //void

    @Select("SELECT * FROM  WHERE user_id=#{user_id}")
    Optional<InquiryDAO> selectId (String user_id);
    //반환데이터형식(반환값) //함수이름               데이터형식 //매개변수

    //게시판목록 보이게 할때
    @Insert("INSERT INTO inquiry VALUES(#{user.user_id},#{user.user_pw},#{user.user_phone},#{user.user_name},#{user.user_birth},#{user.role},null)")
    void list(@Param("inq") InquiryDAO inq);

}
