package com.fjb.springbootvue.service.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fjb.springbootvue.entity.Vlog;
import com.fjb.springbootvue.mapper.VlogMapper;
import com.fjb.springbootvue.service.UserService;
import com.fjb.springbootvue.service.VlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 付建波
 * @Date: 2022/03/17/11:32
 * @Description:
 */
@Service
public class VlogServiceImpl extends ServiceImpl<VlogMapper, Vlog> implements VlogService {

    @Autowired
    private UserService userService;


    @Override
    public IPage listVlog(IPage page) {
        QueryWrapper<Vlog> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("update_time");
        return page(page,queryWrapper);
    }

}
