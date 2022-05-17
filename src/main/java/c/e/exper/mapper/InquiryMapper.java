package c.e.exper.mapper;

import c.e.exper.data.InquiryDAO;
import c.e.exper.data.UserDAO;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Optional;

@Mapper
public interface InquiryMapper {//디비접근
    //글쓸때
    @Insert("INSERT INTO inquiry(inq_title, inq_body, user_id, inq_type)VALUES(#{inq.inq_title},#{inq.inq_body},#{inq.user_id},#{inq.inq_type})")
    //시퀀스를 쓴 쿼리
    boolean insert(@Param("inq") InquiryDAO inq);
    //void

    @Select("SELECT * FROM USERS WHERE user_id=#{user_id}")
    Optional<InquiryDAO> selectId (String user_id);
    //반환데이터형식(반환값) //함수이름 데이터형식 //매개변수
    //#뒤에 user_id가 쓰일때는 매개변수를 써준다.

    //게시판 리스트 목록
    @Select("SELECT inq_id, inq_title, inq_time, user_id, inq_count FROM INQUIRY ORDER BY inq_id")
    List<InquiryDAO> list();

//    @Insert("INSERT INTO inquiry VALUES(#{inq.inq_id},#{inq.inq_title},#{inq.inq_time},#{inq.user_id},#{inq.inq_count})")
//    void list(@Param("inq") InquiryDAO inq);

    //게시글 찾을때?
    @Select("SELECT inq_title, inq_body, user_id, inq_type FROM INQUIRY where inq_id=#{inq_id}")
    Optional<InquiryDAO> listPage(String inq_id);

    //게시글 수정하기
    @Update("UPDATE INQUIRY SET inq_type=#{inq_type}, user_id=#{user_id}, inq_title=#{inq_title}, inq_body=#{inq_body} where inq_id=#{inq_id}")
    boolean updateListPageInfo(InquiryDAO inq);



}
