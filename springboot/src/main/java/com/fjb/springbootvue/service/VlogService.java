package com.fjb.springbootvue.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fjb.springbootvue.entity.Vlog;
import com.fjb.springbootvue.mapper.VlogMapper;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 付建波
 * @Date: 2022/03/17/11:31
 * @Description:
 */
public interface VlogService extends IService<Vlog> {


    IPage listVlog(IPage page);
}
