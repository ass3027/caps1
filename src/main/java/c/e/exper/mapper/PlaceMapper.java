package c.e.exper.mapper;

import c.e.exper.data.*;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PlaceMapper {

    @Select("select a.user_id, count(a.user_id) as count\n" +
            "from users a, PLANNER b, SCHEDULE c\n" +
            "where a.USER_ID=b.USER_ID\n" +
            "  and b.PLAN_ID=c.PLAN_ID\n" +
            "  and pl_id in (\n" +
            "    select pl_id\n" +
            "    from users a, PLANNER b, SCHEDULE c\n" +
            "    where a.USER_ID=b.USER_ID\n" +
            "      and b.PLAN_ID=c.PLAN_ID\n" +
            "      and a.user_id=#{user_id}\n" +
            ")\n" +
            "  and a.USER_ID != #{user_id}\n" +
            "group by a.user_id\n" +
            "order by 2 desc")
    public List<recommendDTO> findSimilarUser(String user_id);

    @Select("select a.PL_ID,a.pl_name,b.PIC_NAME\n" +
            "from (select d.PL_ID,d.PL_NAME\n" +
            "from users a, PLANNER b, SCHEDULE c, place d\n" +
            "where a.USER_ID=b.USER_ID\n" +
            "  and b.PLAN_ID=c.PLAN_ID\n" +
            "  and c.PL_ID=d.PL_ID\n" +
            "  and a.user_id=#{similarUser_id}\n" +
            "  and c.pl_id not in (\n" +
            "    select c.pl_id\n" +
            "    from users a, PLANNER b, SCHEDULE c\n" +
            "    where a.USER_ID=b.USER_ID\n" +
            "  and b.PLAN_ID=c.PLAN_ID\n" +
            "  and a.user_id=#{user_id}\n" +
            "    )) a\n" +
            "    left outer join PICTURES b\n" +
            "        on a.PL_ID=b.PL_ID")
    public List<Place> findRecPlace(String user_id, String similarUser_id);

    @Select("select pl_id, PL_NAME, PIC_NAME\n" +
            "from (\n" +
            "         select a.PL_ID, PL_NAME, PIC_NAME, count\n" +
            "         from (select d.PL_ID, count(d.PL_ID) as count\n" +
            "               from SCHEDULE c,\n" +
            "                    place d\n" +
            "               where c.PL_ID = d.PL_ID\n" +
            "               group by d.PL_ID) a,\n" +
            "              PICTURES b,\n" +
            "              place c\n" +
            "         where a.PL_ID = b.PL_ID\n" +
            "           and c.PL_ID = a.PL_ID\n" +
            "         order by count desc\n" +
            "     )\n" +
            "where ROWNUM <= 5")
    public List<Place> findBestPlace();

    @Select("select * from place where pl_id = #{keep_id}")
    public PlaceDAO findKeep(@Param("keep_id") String keep_id);

}
