package c.e.exper.mapper;

import c.e.exper.data.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;


import java.util.List;
import java.util.Optional;

@Mapper
public interface UserMapper {

    @Select("SELECT * FROM users")
    List<User> selectAll();

    @Select("SELECT * FROM users WHERE user_id=#{user_id}")
    Optional<User> selectId(@Param("user_id") String user_id);

    @Insert("INSERT INTO users VALUES(#{userId},#{user_pw},'user')")
    void insert(@Param("userId") String userId, @Param("user_pw") String user_pw);

}
