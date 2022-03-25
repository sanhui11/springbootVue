package com.fjb.springbootvue;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fjb.springbootvue.entity.User;
import com.fjb.springbootvue.mapper.UserMapper;
import com.fjb.springbootvue.service.UserService;
//import com.github.pagehelper.PageHelper;
//import com.github.pagehelper.PageInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class SpringbootvueApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserService userService;

    @Test
    void contextLoads() {
    }

    @Test
    void logicTest(){
        User user = new User("管理员", "fu19980918.", "付建波", "2736326100@qq.com", "11111", "18707040585", "江西抚州");
        int flag = userMapper.insert(user);
        if(flag>0){
            System.out.println("插入成功");
        }else {
            System.out.println("错了喔");
        }
        System.out.println(userService.selectTotal());
    }

    @Test
    void selById(){
        User user = userMapper.selById(6);
        if (user!=null){
            System.out.println(user.toString());
        }else {
            System.out.println("查询失败");
        }
    }


}
