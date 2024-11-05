package com.jiayi.adminpanel.mapper;
import com.jiayi.adminpanel.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {
    @Select("select * from user")
    List<User> findAll();

    @Insert("INSERT into user(username, password, nickname, email, phone, address) VALUES (#{username},#{password}," +
            "#{nickname},#{email},#{phone},#{address})")
    int insert(User user);

    int update(User user);

    @Delete("delete from user where id = #{id}")
    Integer deleteById(@Param("id") Integer id);

    @Select("<script>" +
            "SELECT * FROM user " +
            "WHERE 1=1 " +
            "<if test='username != null and username != \"\"'> " +
            "AND username LIKE CONCAT('%', #{username}, '%') " +
            "</if> " +
            "<if test='nickname != null and nickname != \"\"'> " +
            "AND nickname LIKE CONCAT('%', #{nickname}, '%') " +
            "</if> " +
            "<if test='address != null and address != \"\"'> " +
            "AND address LIKE CONCAT('%', #{address}, '%') " +
            "</if> " +
            "ORDER BY id ASC " +
            "LIMIT #{pageNum}, #{pageSize} " +
            "</script>")
    List<User> selectPage(@Param("pageNum") Integer pageNum,
                          @Param("pageSize") Integer pageSize,
                          @Param("username") String username,
                          @Param("nickname") String nickname,
                          @Param("address") String address);

    @Select("<script>" +
            "SELECT COUNT(*) FROM user " +
            "WHERE 1=1 " +
            "<if test='username != null and username != \"\"'> " +
            "AND username LIKE CONCAT('%', #{username}, '%') " +
            "</if> " +
            "<if test='nickname != null and nickname != \"\"'> " +
            "AND nickname LIKE CONCAT('%', #{nickname}, '%') " +
            "</if> " +
            "<if test='address != null and address != \"\"'> " +
            "AND address LIKE CONCAT('%', #{address}, '%') " +
            "</if> " +
            "</script>")
    Integer selectTotal(@Param("username") String username,
                        @Param("nickname") String nickname,
                        @Param("address") String address);

}
