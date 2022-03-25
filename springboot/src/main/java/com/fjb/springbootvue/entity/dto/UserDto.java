package com.fjb.springbootvue.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 付建波
 * @Date: 2022/03/07/21:43
 * @Description: 接口接受前端请求数据的
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private String username;
    private String password;
    private String nickname;
    private String avatar;
    private String token;
}
