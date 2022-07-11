package c.e.exper.mapper;

import c.e.exper.data.PlaceDAO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface BookMarkMapper {

    //즐겨찾기한 목록 모두보기
//    @Select("SELECT title, addr1, tel, user_id, FROM PLACE where user_id=#{user_id}")
//    List<PlaceDAO> selectAllByUserId(String user_id);

    @Select("SELECT title, addr1, tel, place.user_id, firstImage FROM PLACE, BOOKMARK WHERE place.pl_id = bookmark.pl_id and bookmark.user_id=#{user_id}")
    List<PlaceDAO> selectAllByUserBookMark(String user_id);

    //keeper 즐겨찾기
    @Select("SELECT title, addr1, tel, place.user_id, firstImage FROM PLACE, BOOKMARK WHERE place.pl_id = bookmark.pl_id and place.keeper_ox='1' and bookmark.user_id=#{user_id}")
    List<PlaceDAO> selectByUserBookMarkKeeper(String user_id);

    //숙소 즐겨찾기
    @Select("SELECT title, addr1, tel, place.user_id, firstImage FROM PLACE, BOOKMARK WHERE place.pl_id = bookmark.pl_id and place.cat1='AO2' and bookmark.user_id=#{user_id}")
    List<PlaceDAO> selectByUserBookMarkHotel(String user_id);

}
