package com.fjb.springbootvue.config.interceptor;

import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.fjb.springbootvue.common.Constants;
import com.fjb.springbootvue.entity.User;
import com.fjb.springbootvue.exception.ServiceException;
import com.fjb.springbootvue.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 付建波
 * @Date: 2022/03/11/16:23
 * @Description:
 */

public class JwtInterceptor implements HandlerInterceptor {

    @Autowired
    private UserService userService;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token=request.getHeader("token");
        //如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod)){
            return true;
        }
        //执行认证
        if(StrUtil.isBlank(token)){
            throw new ServiceException(Constants.CODE_401,"无token请重新登录");
        }
        //获取token中的user id
        String userId;
        try{
            userId= JWT.decode(token).getAudience().get(0);
        }catch (JWTDecodeException j){
            throw new ServiceException(Constants.CODE_401,"token，验证失败，请重新登陆");
        }
        //根据token中的userId查询数据库
        User user = userService.getById(userId);
        if (user==null){
            throw new ServiceException(Constants.CODE_401,"无token，请重新登录");
        }
        //用户密码加签验证 token
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(user.getPassword())).build();
        try{
            jwtVerifier.verify(token);  //验证token
        }catch (JWTVerificationException e){
            throw new ServiceException(Constants.CODE_401,"用户未登录，请重新登录");
        }
        return true;
    }
}
