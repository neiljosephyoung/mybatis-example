package com.example.batis.demo;

import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {
    @Select("SELECT * FROM Users")
    List<User> getAll();

    @Select("SELECT * FROM Users WHERE uid = #{uid}")
    User getById(Long uid);

    @Insert("INSERT INTO Users (username, password) VALUES (#{username}, #{password})")
    @Options(useGeneratedKeys = true, keyProperty = "uid")
    void insert(User user);

    @Update("UPDATE Users SET username = #{username}, password = #{password} WHERE uid = #{uid}")
    void update(User user);

    @Delete("DELETE FROM Users WHERE uid = #{uid}")
    void delete(Long uid);
}
