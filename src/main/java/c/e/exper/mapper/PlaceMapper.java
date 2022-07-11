package c.e.exper.mapper;

import c.e.exper.data.*;
import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Mapper
public interface PlaceMapper {
   //   @Select("select title,addr1,zipcode,addr2,firstImage2,areaCode,cat1,firstImage2,MAPX,MAPY " +
//         "from place " +
//         "where areaCode=#{areaCode} " +
//         "and cat1=#{cat1} " +
//         "and rownum<=9 " +
//         "and zipcode>=6")
//   public List<PlaceDAO> selectByPlace(String areaCode, String cat1);
   
   
   @Select("""
         select pl_id,title,addr1,addr2,zipcode,firstImage2,areaCode,cat1,MAPX,MAPY,tel
         from place
         where CONTENTTYPEID=#{contenttypeid}
         and title like '%'||#{keyword}||'%'""")
   List<PlaceDAO> keywordByPlaceA01(String contenttypeid, String keyword);
   
   @Select("""
         select pl_id,user_id,title,addr1,addr2,zipcode,firstImage2,areaCode,cat1,MAPX,MAPY,contenttypeid
         from place
         where areaCode=#{areaCode}
         and cat1=#{cat1}""")
   List<PlaceDAO> selectByPlace(String areaCode, String cat1);
   
   @Select("""
         select pl_id,user_id,title,addr1,addr2,zipcode,firstImage2,areaCode,cat1,MAPX,MAPY
         from place
         where areaCode=#{areaCode}
         and cat1=#{cat1}
         and title like '%'||#{keyword}||'%'""")
   List<PlaceDAO> keywordByPlace(String areaCode, String cat1, String keyword);
   
   @Select("""
         select pl_id,user_id,title,addr1,addr2,zipcode,firstImage2,areaCode,cat1,MAPX,MAPY
         from place
         where title like '%'||#{keyword}||'%'""")
   List<PlaceDAO> SearchPlaceByKeyword(String keyword);
   
   @Select("""
         SELECT *
         FROM PLACE
         WHERE cat3 = #{category} and areaCode = #{areaCode}
         and firstimage is not null and tel is not null
         """)
   List<PlaceDAO> CategoryAreaCode(String category, String areaCode);
   
   @Select("""
         select pl_id,title,addr1,addr2,zipcode,firstImage2,areaCode,cat1,MAPX,MAPY
         from place
         where areaCode=#{areaCode}
         and contenttypeid=#{contenttypeid}""")
   List<PlaceDAO> selectByPlace2(String areaCode, String contenttypeid);
   
   
   @Select("""
         select pl_id,title,addr1,addr2,zipcode,firstImage2,areaCode,cat1,MAPX,MAPY
         from place
         where areaCode=#{areaCode}
         and contenttypeid=#{contenttypeid}
         and title like '%'||#{keyword}||'%'""")
   List<PlaceDAO> keywordByPlaceTour(String areaCode, String contenttypeid, String keyword);
   
/*
   @Select("select title,\n" +
         "       addr1,\n" +
         "       zipcode,\n" +
         "       addr2,\n" +
         "       firstImage2,\n" +
         "       areaCode,\n" +
         "       cat1,\n" +
         "       MAPX,\n" +
         "       MAPY\n" +
         "from (\n" +
         "         select seq,\n" +
         "                title,\n" +
         "                addr1,\n" +
         "                zipcode,\n" +
         "                addr2,\n" +
         "                firstImage2,\n" +
         "                areaCode,\n" +
         "                cat1,\n" +
         "                MAPX,\n" +
         "                MAPY\n" +
         "         from (\n" +
         "                  select rownum as seq,\n" +
         "                         title,\n" +
         "                         addr1,\n" +
         "                         zipcode,\n" +
         "                         addr2,\n" +
         "                         firstImage2,\n" +
         "                         areaCode,\n" +
         "                         cat1,\n" +
         "                         MAPX,\n" +
         "                         MAPY\n" +
         "                  from (\n" +
         "                           select *\n" +
         "                           from place\n" +
         "                           where cat1 = #{cat1}\n" +
         "                             and areaCode = #{areaCode}\n" +
         "                           order by pl_id desc\n" +
         "                       )\n" +
         "              )\n" +
         "         where seq >= 6\n" +
         "     )\n" +
         "where rownum <= 9")
   public List<PlaceDAO> selectByPlace(String areaCode, String cat1);
*/
   
   @Select("select * from place where pl_id = #{keep_id}")
   public PlaceDAO findKeep(@Param("keep_id") String keep_id);
   
   @Select("""
         select a.user_id, count(a.user_id) as count
         from users a,
              PLANNER b,
              SCHEDULE c
         where a.USER_ID = b.USER_ID
           and b.PLAN_ID = c.PLAN_ID
           and pl_id in (
             select distinct c.PL_ID
             from SCHEDULE c
             where c.PLAN_ID in (select PLAN_ID
                                 from PLANNER
                                 where user_id=#{user_id})
             and c.PL_ID is not null
         )
           and a.USER_ID != #{user_id}
         group by a.user_id
         order by 2 desc""")
   List<recommendDTO> findSimilarUser(String user_id);
   
   @Select("""
         select distinct a.PL_ID, a.title, FIRSTIMAGE, b.PIC_NAME,mapx,mapy
         from (
                  select d.PL_ID, d.title, d.FIRSTIMAGE,d.mapx,d.mapy
                  from users a,
                       PLANNER b,
                       SCHEDULE c,
                       place d
                  where a.USER_ID = b.USER_ID
                    and b.PLAN_ID = c.PLAN_ID
                    and c.PL_ID = d.PL_ID
                    and a.user_id = #{similarUser_id}
                    and c.pl_id not in (
                      select c.pl_id
                      from users a,
                           PLANNER b,
                           SCHEDULE c
                      where a.USER_ID = b.USER_ID
                        and b.PLAN_ID = c.PLAN_ID
                        and a.user_id = #{user_id}
                        and c.PL_ID is not null
                  )) a
                  left outer join PICTURES b
                                  on a.PL_ID = b.PL_ID
         where rownum<=6
         """)
   List<PlaceDAO> findRecPlace(String user_id, String similarUser_id);
   
   @Select("""
         select pl_id, title, FIRSTIMAGE,mapx,mapy
         from (
                  select a.PL_ID, title,FIRSTIMAGE,count, mapx,mapy
                  from (select d.PL_ID, count(d.PL_ID) as count
                        from SCHEDULE c,
                             place d
                        where c.PL_ID = d.PL_ID
                        group by d.PL_ID) a,
                       place c
                  where c.PL_ID = a.PL_ID
                  order by count desc
              )
         where ROWNUM <= 6
         """)
   List<PlaceDAO> findBestPlace();
   
   @Select("""
         SELECT *
         FROM PLACE
         WHERE cat3 = #{category}
         and firstimage is not null and tel is not null""")
   List<PlaceDAO> findByCategory(@Param("category") String category);
   
   @Select("""
         select *
         from place, OPTION_PLACE
         where place.PL_ID = OPTION_PLACE.PL_ID and OPTION_PLACE.OPTION_ID = #{option} AND PLACE.CAT3 = #{category}
         """)
   List<PlaceDAO> findByOption(@Param("category") String category, @Param("option") String option);
   
   @Select("""
         select b.*
         from (select PL_ID, count(PL_ID)
               from (
                        select distinct a.PAY_ID, pl_id
                        from PRODUCT_TIME a,
                             product b
                        where a.PD_ID = b.PD_ID
                    )
               group by PL_ID
               order by 2 desc
              )a, place b
         where rownum <= 6
         and a.PL_ID=b.PL_ID
         """)
   List<PlaceDAO> findBestHotel();
   
   @Select("select firstimage from place where pl_id=#{contentid}")
   String getFirstImage(String contentid);
   
   //장소등록
   @Insert("""
         insert into place(store_id,user_id,addr1,areacode,cat1,contentTypeId,firstImage,tel,title,zipcode)
         values(#{store_id},#{user_id},#{addr1},#{areacode},'B02','32',#{firstImage},#{tel},#{title},#{zipcode})
         """)
   void placeAdd(PlaceDAO placeAdd);
}
