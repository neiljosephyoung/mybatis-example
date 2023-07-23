package com.example.batis.demo.Controller;

import com.example.batis.demo.User;
import com.example.batis.demo.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/demo/")
public class DemoController {

    @Autowired
    private UserMapper userMapper;

    @GetMapping("all")
    public List<User> getAll() {
        return userMapper.getAll();
    }

    @GetMapping("/user/{uid}")
    public User getSpecificUser(@PathVariable Long uid) {
        return userMapper.getById(uid);
    }

    @PostMapping("update")
    public void updateUser(@Valid @RequestBody User user) {
        userMapper.update(user);
    }
}
