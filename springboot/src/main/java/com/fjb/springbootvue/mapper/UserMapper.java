package com.fjb.springbootvue.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fjb.springbootvue.entity.User;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 付建波
 * @Date: 2022/02/22/14:54
 * @Description:
 */
//mapper层 mybatis-plus会自动提供实现类
@Repository
public interface UserMapper extends BaseMapper<User> {

    User selById(int id);

}
