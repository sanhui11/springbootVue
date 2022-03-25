package com.fjb.springbootvue.service.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fjb.springbootvue.entity.Files;
import com.fjb.springbootvue.mapper.FilesMapper;
import com.fjb.springbootvue.service.FilesService;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 付建波
 * @Date: 2022/03/14/9:40
 * @Description:
 */
//实现自己的service接口并且继承mybatis-plus的serviceImpl这个类，支持泛型，需要提供一个mapper接口和实体类
//1.AdminService必须要有实现类，
// 它不能像mapper一样mybatis会自动提供实现类
@Service
public class FileServiceImpl extends ServiceImpl<FilesMapper,Files> implements FilesService {
    @Override
    public Boolean saveFile(Files file) {
        return save(file);
    }
}
