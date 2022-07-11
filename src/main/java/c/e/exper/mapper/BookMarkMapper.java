package c.e.exper.mapper;

import c.e.exper.data.PlaceDAO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface BookMarkMapper {

    //즐겨찾기한 목록 모두보기
//    @Select("SELECT title, addr1, tel, user_id, FROM PLACE where user_id=#{user_id}")
//    List<PlaceDAO> selectAllByUserId(String user_id);

    @Select("SELECT title, addr1, tel, place.pl_id,place.user_id, firstImage FROM PLACE, BOOKMARK WHERE place.pl_id = bookmark.pl_id and bookmark.user_id=#{user_id}")
    List<PlaceDAO> selectAllByUserBookMark(String user_id);

    //keeper 즐겨찾기
    @Select("SELECT place.pl_id title, addr1, tel, place.user_id, firstImage FROM PLACE, BOOKMARK WHERE place.pl_id = bookmark.pl_id and place.keeper_ox='1' and bookmark.user_id=#{user_id}")
    List<PlaceDAO> selectByUserBookMarkKeeper(String user_id);

    //숙소 즐겨찾기
    @Select("SELECT place.pl_id, title, addr1, tel, place.user_id, firstImage FROM PLACE, BOOKMARK WHERE place.pl_id = bookmark.pl_id and place.TITLE like '%호텔%' and bookmark.user_id=#{user_id}")
    List<PlaceDAO> selectByUserBookMarkHotel(String user_id);

    //관광지 즐겨찾기
    @Select("SELECT place.pl_id, title, addr1, tel, place.user_id, firstImage FROM PLACE, BOOKMARK WHERE place.pl_id = bookmark.pl_id and place.user_id is null and bookmark.user_id=#{user_id}")
    List<PlaceDAO> selectByUserBookMarkTour(String user_id);

    //북마크 여부
    @Select("""
            select count(*)
            from BOOKMARK
            where USER_ID = #{user_id}
            and PL_ID = ${pl_id}
            """)
    String getBookMarkStatus(String user_id, String pl_id);


    //북마크 추가
    @Insert("insert into BOOKMARK(user_id, pl_id) values(#{user_id},#{pl_id})")
    void insertBookMark(String user_id, String pl_id);

    //북마크 삭제
    @Delete("delete from BOOKMARK where user_id=#{user_id} and PL_ID=#{pl_id}")
    void deleteBookMark(String user_id, String pl_id);
}
