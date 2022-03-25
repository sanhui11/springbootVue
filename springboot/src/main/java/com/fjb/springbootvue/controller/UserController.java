package com.fjb.springbootvue.controller;

import cn.hutool.core.util.StrUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fjb.springbootvue.common.Constants;
import com.fjb.springbootvue.common.Result;
import com.fjb.springbootvue.entity.User;
import com.fjb.springbootvue.entity.dto.UserDto;
import com.fjb.springbootvue.service.UserService;


import com.fjb.springbootvue.util.TokenUtils;
import com.sun.deploy.net.URLEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 付建波
 * @Date: 2022/02/25/13:34
 * @Description:
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;


    /**
     * 登录接口
     *
     * @param userDto
     * @return 400参数错误  接口的返回结果要进行同一的包装 请求接口其实也可以
     */
    @PostMapping("/login")
    public Result login(@RequestBody UserDto userDto) {
        String username = userDto.getUsername();
        String password = userDto.getPassword();
        if (StrUtil.isBlank(username) || StrUtil.isBlank(password)) {
            return Result.error(Constants.CODE_400, "参数错误");
        }
        UserDto dto = userService.login(userDto);
        return Result.success(dto);
    }

    @PostMapping("/register")
    public Result register(@RequestBody UserDto userDto) {
        String username = userDto.getUsername();
        String password = userDto.getPassword();
        if (StrUtil.isBlank(username) || StrUtil.isBlank(password)) {
            return Result.error(Constants.CODE_400, "参数错误");
        }
        UserDto dto = userService.register(userDto);
        return Result.success(dto);
    }


    //新增修改数据
    @PostMapping
    public Result saveUser(@RequestBody User user) {
        Boolean flag = userService.saveUser(user);
        return Result.success(flag);
    }

    //@pathVariable 传的是路径上的值  测试只能写在url上
    @DeleteMapping("/{id}")
    public Result deleteUser(@PathVariable Integer id) {
        return Result.success(userService.deleteUserById(id));
    }


    @PostMapping("/del/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        return Result.success(userService.removeBatchByIds(ids));
    }


    //前端怎么取数据，具体看传过去的参数   pageHelper进行物理分页
//    @GetMapping("/page")
//    public Map<String, Object> selectPage(@RequestParam Integer pageNum,
//                                          @RequestParam Integer pageSize) {
//        Long total = userService.selectTotal();
//        PageHelper.startPage(pageNum, pageSize);
//        List<User> users = userService.listUser();
//        PageInfo<User> pageInfo = new PageInfo<>(users);
//        Map<String, Object> res = new HashMap<>();
//        res.put("data", pageInfo);
//        res.put("total", total);
//        return res;
//    }

    //传统的java实现分页  使用mp之后使用pagehelper插件，依赖可能和springboot版本有关，没有该代码换了版本就能用
//    @GetMapping("/page")
//    public Map<String,Object> findPage(@RequestParam Integer pageNum,
//                                   @RequestParam Integer pageSize) {
//        pageNum=(pageNum-1)*pageSize;
//        HashMap<String,Object> res = new HashMap<>();
//        Integer total = userService.selectTotal();
//        List<User> data = userService.selectUser(pageNum, pageSize);
//        res.put("data", data);
//        res.put("total",total);
//        return  res;
//    }

    @GetMapping("/person/{username}")
    public Result getPerson(@PathVariable String username) {
        if (StrUtil.isBlank(username)) {
            return Result.error(Constants.CODE_400, "参数错误");
        }
        User user = userService.getPerson(username);
        return Result.success(user);
    }

    @GetMapping("/page")
    public Result pageTest(@RequestParam Integer pageNum,
                           @RequestParam Integer pageSize,
                           @RequestParam(defaultValue = "") String username,
                           @RequestParam(defaultValue = "") String email,
                           @RequestParam(defaultValue = "") String address) {
        IPage<User> page = new Page<>(pageNum, pageSize);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        //自动拼接and  当前端没有传值时，传入的值为%%，能够匹配到数据库中不为null的所有纪录
        //数据库中的列属性值有两种为空，一种是null  一种是空（被删除后的） 就有一种情况，一开始是没有属性的时候传来的值为空是就匹配不到
        //为空就不写
        if (!"".equals(username)) {
            queryWrapper.like("username", username);
        }
        if (!"".equals(email)) {
            queryWrapper.like("email", email);
        }
        if (!"".equals(address)) {
            queryWrapper.like("address", address);
        }
        //获取当前的用户信息 测试tokenUtil里面的方法
        User currentUser = TokenUtils.getCurrentUser();
        System.out.println("+++++++++"+currentUser.getUsername()+"+++++++++");
        //timestamp  更新时间可以直接排序  驼峰命名这里好像不起作用
        queryWrapper.orderByDesc("update_time");
        return Result.success(userService.page(page, queryWrapper));
    }


    @GetMapping("/export")
    public void export(HttpServletResponse response) throws Exception {
        List<User> list = userService.listUser();
//        //写到对应的磁盘路径
        //ExcelWriter writer = ExcelUtil.getWriter("d:/writeTest.xlsx");
        ExcelWriter writer = ExcelUtil.getWriter(true);
//        writer.addHeaderAlias("id","用户id");
//        writer.addHeaderAlias("username","用户名");
//        writer.addHeaderAlias("password","密码");
//        writer.addHeaderAlias("nickname","昵称");
//        writer.addHeaderAlias("email","邮箱");
//        writer.addHeaderAlias("phone","手机号");
//        writer.addHeaderAlias("address","地址");
//        writer.addHeaderAlias("createTime","创建时间");
//        writer.addHeaderAlias("avatarUrl","头像地址");
//        writer.addHeaderAlias("updateTime","更新时间");

        writer.write(list, true);

        //设置浏览器的响应格式
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        String fileName = URLEncoder.encode("用户信息", "utf-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ".xlsx");

        ServletOutputStream stream = response.getOutputStream();
        writer.flush(stream, true);
        stream.close();
        writer.close();
    }

    @PostMapping("/import")
    public Result imp(MultipartFile file) throws Exception {
        InputStream inputStream = file.getInputStream();
        ExcelReader reader = ExcelUtil.getReader(inputStream);

        //按照指定的方式进行插入  设置特定的值
        List<List<Object>> read = reader.read(1);
//        ArrayList<User> users = CollUtil.newArrayList();
//        for (List<Object> row:read){
//            User user = new User();
//            user.setUsername(row.get(0).toString());
//            user.setUsername(row.get(0).toString());
//            user.setUsername(row.get(0).toString());
//            user.setUsername(row.get(0).toString());
//            user.setUsername(row.get(0).toString());
//            users.add(user);
//        }
//        boolean b1 = userService.saveBatch(users);
        //通过javabean的方式获取excel内的对象 通过标头的内容对应具体的字段
        List<User> list = reader.readAll(User.class);
        System.out.println(list);
//        reader.read(1,3);
        //excel所有的空都是空字符串
        boolean b = userService.saveBatch(list);
        return Result.success(b);
    }

}
