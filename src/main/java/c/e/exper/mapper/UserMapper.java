package c.e.exper.mapper;

import c.e.exper.data.UserDAO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;


import java.util.List;
import java.util.Optional;

@Mapper
public interface UserMapper {

    @Select("SELECT * FROM users")
    List<UserDAO> selectAll();

    @Select("SELECT * FROM users WHERE user_id=#{user_id}")
    Optional<UserDAO> selectId(@Param("user_id") String user_id);

    @Insert("INSERT INTO users VALUES(#{user.user_id},#{user.user_pw},#{user.user_phone},#{user.user_name},#{user.user_birth},#{user.role})")
    void insert(@Param("user") UserDAO user);


}
