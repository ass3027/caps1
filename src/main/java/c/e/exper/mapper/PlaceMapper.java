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
           select pl_id,title,addr1,addr2,zipcode,firstImage2,areaCode,cat1,MAPX,MAPY
           from place
           where cat1='A01'
           and title like '%'||#{keyword}||'%'""")
   List<PlaceDAO> keywordByPlaceA01(String keyword);

    @Select("""
            select pl_id,title,addr1,addr2,zipcode,firstImage2,areaCode,cat1,MAPX,MAPY
            from place
            where areaCode=#{areaCode}
            and cat1=#{cat1}""")
    List<PlaceDAO> selectByPlace(String areaCode, String cat1);

    @Select("""
            select pl_id,title,addr1,addr2,zipcode,firstImage2,areaCode,cat1,MAPX,MAPY
            from place
            where areaCode=#{areaCode}
            and cat1=#{cat1}
            and title like '%'||#{keyword}||'%'""")
    List<PlaceDAO> keywordByPlace(String areaCode, String cat1, String keyword);

    @Select("""
            select pl_id,title,addr1,addr2,zipcode,firstImage2,areaCode,cat1,MAPX,MAPY
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
           select a.PL_ID, a.title, FIRSTIMAGE, b.PIC_NAME,mapx,mapy
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
                                    on a.PL_ID = b.PL_ID""")
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
           where ROWNUM <= 5""")
   List<PlaceDAO> findBestPlace();

   @Select("""
           SELECT *
           FROM PLACE
           WHERE cat3 = #{category} 
           and firstimage is not null and tel is not null
           """)
   List<PlaceDAO> findByCategory(@Param("category") String category);

   @Select("""
           SELECT *
           FROM PLACE P, OPTION_PLACE O
           WHERE P.PL_ID = O.PL_ID AND P.cat3 = #{category} AND O.OPTION_ID = #{option}
           """)
   List<PlaceDAO> findByOption(@Param("category")String category, @Param("option")String option);

}
