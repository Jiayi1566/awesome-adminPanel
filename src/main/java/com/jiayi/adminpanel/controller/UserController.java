package com.jiayi.adminpanel.controller;

import com.jiayi.adminpanel.entity.User;
import com.jiayi.adminpanel.mapper.UserMapper;
import com.jiayi.adminpanel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.ClientInfoStatus;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserService userService;


    @PostMapping
    public Integer save(@RequestBody User user){
        return userService.save(user);

    }

    @GetMapping("/")
    public List<User> index(){
        List<User> all = userMapper.findAll();
        return all;
    }

    @DeleteMapping("/{id}")
    public Integer delete(@PathVariable Integer id){
        return userMapper.deleteById(id);
    }

    @GetMapping("/page")
    public Map<String, Object> findPage (@RequestParam Integer pageNum,
                                         @RequestParam Integer pageSize,
                                         @RequestParam(required = false) String username,
                                         @RequestParam(required = false) String nickname,
                                         @RequestParam(required = false) String address)
    {
        pageNum = (pageNum - 1) * pageSize;
        List<User> data = userMapper.selectPage(pageNum, pageSize, username, nickname, address);
        Integer total = userMapper.selectTotal(username, nickname, address);
        Map<String, Object> res = new HashMap<>();
        res.put("data", data);
        res.put("total", total);
        return res;
    }


}
