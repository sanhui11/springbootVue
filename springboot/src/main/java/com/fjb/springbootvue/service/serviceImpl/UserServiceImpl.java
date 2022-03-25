package com.fjb.springbootvue.service.serviceImpl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fjb.springbootvue.common.Constants;
import com.fjb.springbootvue.entity.User;
import com.fjb.springbootvue.entity.dto.UserDto;
import com.fjb.springbootvue.exception.ServiceException;
import com.fjb.springbootvue.mapper.UserMapper;
import com.fjb.springbootvue.service.UserService;
import com.fjb.springbootvue.util.TokenUtils;
import com.sun.xml.internal.messaging.saaj.packaging.mime.util.QEncoderStream;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 付建波
 * @Date: 2022/02/22/16:03
 * @Description:
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {


    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> listUser() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("update_time");
        return userMapper.selectList(wrapper);
    }

    //更新操作这里一直有一个误区，我们是从runapi测试接口，如果没有给相应的属性赋值的话肯定也是算修改了他的属性，只不过是赋给了null
    //但是正常的更新肯定是在前端页面上展示所有的属性，然后在选择修改，这样 BeanUtils.copyProperties的方法就能使用
    @Override
    public Boolean saveUser(User user) {
        if (user.getId() == null) {
            return save(user);
        } else {
            return updateById(user);
        }
    }

    @Override
    public User getUserById(Integer id) {
        return userMapper.selectById(id);
    }

    @Override
    public Boolean deleteUserById(Integer id) {
        return removeById(id);
    }

    @Override
    public Long selectTotal() {
        return count();
    }

    @Override
    public UserDto login(UserDto userDto) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",userDto.getUsername());
        queryWrapper.eq("password",userDto.getPassword());
        User one;
        try {
           one=getOne(queryWrapper);
        }catch (Exception e){
            throw new ServiceException(Constants.CODE_500,"系统错误");
        }
        if (one!=null){
            BeanUtils.copyProperties(one,userDto);
            //设置token
            String token= TokenUtils.genToken(one.getId().toString(),one.getPassword());
            userDto.setToken(token);
            return userDto;
        }else {
            throw new ServiceException(Constants.CODE_600,"用户名或密码错误");
        }
    }

    @Override
    public UserDto register(UserDto userDto) {
        User user = new User();
        BeanUtils.copyProperties(userDto,user);
        boolean save = save(user);
        if (save){
            return userDto;
        }else {
            throw new ServiceException(Constants.CODE_500,"系统错误");
        }
    }

    @Override
    public User getPerson(String username) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",username);
        User one;
        try {
            one=getOne(queryWrapper);
        }catch (Exception e){
            throw new ServiceException(Constants.CODE_500,"系统错误");
        }
       return one;
    }


}