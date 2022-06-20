package c.e.exper.mapper;

import c.e.exper.data.AnswerDAO;
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
    //
    // 매개변수(내가 정해주는거?)

    @Select("SELECT * FROM USERS WHERE user_id=#{user_id}")
    Optional<InquiryDAO> selectId (String user_id);
    //반환데이터형식(반환값) //함수이름 데이터형식 //매개변수
    //#뒤에 user_id가 쓰일때는 매개변수를 써준다.

    //게시판 리스트 목록
    @Select("SELECT inq_id, inq_title, inq_time, user_id, inq_count FROM INQUIRY ORDER BY inq_id")
    List<InquiryDAO> list();

    //게시글 상세페이지 보기

    @Select("SELECT inq_title, inq_body, user_id, inq_type, inq_time FROM INQUIRY where inq_id=#{inq_id}")
    Optional<InquiryDAO> listPage(String inq_id);

    //게시글 수정하기
    @Update("UPDATE INQUIRY SET inq_type=#{inq_type}, user_id=#{user_id}, inq_title=#{inq_title}, inq_body=#{inq_body} where inq_id=#{inq_id}")
    boolean updateListPageInfo(InquiryDAO inq);

    //게시글 삭제하기
    @Delete("DELETE FROM INQUIRY WHERE inq_id=#{inq_id}")
    void deleteListPageInfo(String inq_id);

    //게시글 관리자 답변
    @Select("SELECT ans_body, ans_title, ans_time FROM ANSWER where ans_id=#{ans_id}")
    Optional<AnswerDAO> answerListPage(String ans_id);

    //연습(이렇게 해야하는거닝?) //조회수 올리기
    @Update("UPDATE INQUIRY SET inq_count = inq_count + 1 where inq_id=#{inq_id}")
    void updateCount(String inq_id);


}
