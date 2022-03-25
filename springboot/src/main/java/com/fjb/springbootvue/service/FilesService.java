package com.fjb.springbootvue.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fjb.springbootvue.entity.Files;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 付建波
 * @Date: 2022/03/14/9:40
 * @Description:
 */
/*
在自己的service接口中继承IService
 */
public interface FilesService extends IService<Files> {

    Boolean saveFile(Files file);
}
