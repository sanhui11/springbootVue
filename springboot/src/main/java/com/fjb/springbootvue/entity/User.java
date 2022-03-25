package com.fjb.springbootvue.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 付建波
 * @Date: 2022/02/22/14:13
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {


    @TableId(type = IdType.AUTO)
    private Integer id;
    private String username;
    @JsonIgnore
    private String password;
    private String nickname;
    private String email;
    private String avatar;
    private String phone;
    private String address;
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
    @TableLogic
    @JsonIgnore //在json序列化时将pojo中的一些属性忽略掉，标记在属性或者方法上，返回的json数据即不包含该属性。
    @TableField(fill = FieldFill.INSERT)
    private Integer deleted;

    public User(String username, String password, String nickname, String email,
                String avatar, String phone, String address) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.email = email;
        this.avatar = avatar;
        this.phone = phone;
        this.address = address;
    }


}
