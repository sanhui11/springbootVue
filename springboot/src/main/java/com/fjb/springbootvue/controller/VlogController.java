package com.fjb.springbootvue.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fjb.springbootvue.common.Result;
import com.fjb.springbootvue.entity.Vlog;
import com.fjb.springbootvue.service.VlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 付建波
 * @Date: 2022/03/17/11:33
 * @Description:
 */
@RestController
@RequestMapping("/vlog")
public class VlogController {

    @Autowired
    private VlogService vlogService;

    //有一个连表查的过程  通过author_id来查询作者的信息
    @GetMapping
    public Result listVlog(@RequestParam Integer pageNum,
                           @RequestParam Integer pageSize){
        IPage page=new Page(pageNum,pageSize);
        IPage pageInfo = vlogService.listVlog(page);
        return Result.success(pageInfo);
    }
}
