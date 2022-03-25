package com.fjb.springbootvue.controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fjb.springbootvue.common.Result;
import com.fjb.springbootvue.entity.Files;
import com.fjb.springbootvue.mapper.FilesMapper;
import com.fjb.springbootvue.service.FilesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 付建波
 * @Date: 2022/03/11/17:43
 * @Description: 文件上传相关接口
 */
@RestController
@RequestMapping("/file")
public class FileController {

    @Value("${files.upload.path}")
    private String fileUploadPath;

    @Autowired
    private FilesService filesService;

    @Autowired
    private FilesMapper filesMapper;

    /**
     * @Description: 文件上传接口
     * @Param:
     * @return:
     * @Author: 付建波
     * @Date: 2022/3/14
     */

    //数据库里的bigInt对应的是java中的long  图片有一个属性md5 确定是否为同一张图片
    @PostMapping("/upload")
    public String upload(@RequestParam MultipartFile file) throws IOException {
        //最好不使用最原始的文件名包含后缀的那种，会有重复，特殊字符
        String originalFilename = file.getOriginalFilename();
        String type = FileUtil.extName(originalFilename);
        long size = file.getSize();

        //定义一个文件唯一的标识码
        String uuid = IdUtil.fastSimpleUUID();
        String fileUUid = uuid + StrUtil.DOT + type;

        File uploadFile = new File(fileUploadPath + fileUUid);
        //判断配置的文件目录是否存在，不存在则创建一个新的文件目录
        if (!uploadFile.getParentFile().exists()) {
            uploadFile.getParentFile().mkdirs();
        }

        String md5;
        String url;
        file.transferTo(uploadFile);
        //获取文件的md5
        md5 = SecureUtil.md5(uploadFile);
        //从数据库中查询是否有相同的纪录
        Files dbFiles = getFileMd5(md5);
        if (dbFiles != null) {
            url = dbFiles.getUrl();
            //文件已经存在，所以删除
            uploadFile.delete();
        } else {
            url = "http://localhost:9090/file/" + fileUUid;
        }

        //存储到数据库，有些时候可以对传来的文件进行判断，
        // 当是同一个文件时 数据库保存纪录而本地磁盘不进存储
        Files saveFile = new Files();
        saveFile.setName(originalFilename);
        saveFile.setType(type);
        saveFile.setSize(size / 1024);
        saveFile.setUrl(url);
        saveFile.setMd5(md5);
        filesService.saveFile(saveFile);
        return url;
    }

    /**
     * 文件下载接口  http://localhost:9090/file/{fileUUID}"
     *
     * @param fileUUID
     * @param response
     * @throws IOException
     */

    //下载就是从磁盘去找，需要完整的磁盘路径,通过流的方式去下载 传进来的是路径
    @GetMapping("/{fileUUID}")
    public void download(@PathVariable String fileUUID, HttpServletResponse response) throws IOException {
        //根据文件的唯一标识码读取指定的文件
        File uploadFile = new File(fileUploadPath + fileUUID);
        ServletOutputStream os = response.getOutputStream();
        //设置输出流的格式
        response.addHeader("content-Disposition", "attachment;filename=" + URLEncoder.encode(fileUUID, "UTF-8"));
        response.setContentType("application/octet-stream");
        //读取文件的字节流
        os.write(FileUtil.readBytes(uploadFile));
        os.flush();
        os.close();
    }


    /**
     * 分页查询接口
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/page")
    public Result page(@RequestParam Integer pageNum,
                       @RequestParam Integer pageSize,
                       @RequestParam("") String type) {
        //没有用逻辑删除，需要自己加条件设置查询的deleted=‘0’
        IPage<Files> page = new Page<>(pageNum, pageSize);
        QueryWrapper<Files> queryWrapper = new QueryWrapper<>();
        if (StrUtil.isNotBlank(type)){
            queryWrapper.eq("type",type);
        }
        queryWrapper.orderByDesc("update_time");
        //mp提供的分页方法 传入一些分页参数和条件自动返回page
        return Result.success(filesService.page(page, queryWrapper));
    }

    /**
     * 删除接口
     *
     * @param id
     * @return
     */
    @DeleteMapping("{id}")
    public Result deleteFileById(@PathVariable Integer id) {
        return Result.success(filesService.removeById(id));
    }

    /**
     * 批量删除接口
     *
     * @param ids
     * @return
     */
    @PostMapping("/del/batch")
    public Result deleteFileBatch(@RequestBody List<Integer> ids) {
        return Result.success(filesService.removeBatchByIds(ids));
    }

    @PostMapping("/update")
    public Result save(@RequestBody Files files){
        return  Result.success(filesMapper.updateById(files));
    }

    //原始方法逻辑删除
//    @PostMapping("/del/batch")
//    public Result deleteBatch(@RequestBody List<Integer> ids) {
//        QueryWrapper<Files> queryWrapper = new QueryWrapper<>();
//        //select * from sye_files where id in (id,id,id)
//        queryWrapper.in("id", ids);
//        List<Files> files = filesMapper.selectList(queryWrapper);
//        for (Files file : files) {
//            //数据库的字段类型应该为bigint 对应java的boolean
//            // file.setDeleted(true);
//            filesMapper.updateById(file);
//
//        }
//        return Result.success();
//    }


    private Files getFileMd5(String md5) {
        QueryWrapper<Files> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("md5", md5);
        List<Files> list = filesService.list(queryWrapper);
        return list.size() == 0 ? null : list.get(0);
    }

}
