package c.e.exper.mapper;

import c.e.exper.data.UserDAO;
import org.apache.ibatis.annotations.*;


import java.util.List;
import java.util.Optional;

@Mapper
public interface UserMapper { //디비접근

    @Select("SELECT * FROM users")
    List<UserDAO> selectAll();

    @Select("SELECT * FROM users WHERE user_id=#{user_id}")
    Optional<UserDAO> selectId(String user_id);
//    (@Param("user_id") 생략가능


    @Insert("INSERT INTO users VALUES(#{user.user_id},#{user.user_pw},#{user.user_phone},#{user.user_name},#{user.user_birth},#{user.role},null)")
    void insert(@Param("user") UserDAO user);

    @Update("UPDATE USERS SET user_pw=#{user.user_pw}, user_phone=#{user.user_phone}, user_name=#{user.user_name} WHERE user_id=#{user.user_id}")
    boolean updateUserInfo(@Param("user") UserDAO user);
  //반환데이터형식(반환값) //함수이름          데이터형식 //매개변수
  //반환해주는것!


}
