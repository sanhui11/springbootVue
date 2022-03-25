package com.fjb.springbootvue.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fjb.springbootvue.entity.User;
import com.fjb.springbootvue.entity.dto.UserDto;
//import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 付建波
 * @Date: 2022/02/22/16:02
 * @Description:
 */

//列表展示  id查询  登录
//困扰很久的问题。。。还是有多层  包含一个接口层   终于解决了
public interface UserService extends IService<User> {

    List<User> listUser();

    Boolean saveUser(User user);

    User getUserById(Integer id);

    Boolean deleteUserById(Integer id);

    Long selectTotal();

    UserDto login(UserDto userDto);

    UserDto register(UserDto userDto);

    User getPerson(String username);

//    List<User> selectUser(Integer pageNum, Integer pageSize);

//    Integer updateUser(User user);

//    List<User> findAllUserByPageF(int pageNum,int pageSize);

//    PageInfo<User> findAllUserByPageS(int pageNum, int pageSize);




}
