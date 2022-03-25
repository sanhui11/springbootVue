package com.fjb.springbootvue.util;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fjb.springbootvue.entity.User;
import com.fjb.springbootvue.service.UserService;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 付建波
 * @Date: 2022/03/11/16:06
 * @Description:
 */
@Component
public class TokenUtils {

    //在静态方法中获取静态对象
    private static UserService staticUserService;

    @Resource
    private UserService userService;

    @PostConstruct
    public void setUserService() {
        staticUserService = userService;
    }

    public static String genToken(String userId, String sign) {
        return JWT.create().withAudience(userId) //将userid保存到token里面 作为载荷
                .withExpiresAt(DateUtil.offsetHour(new Date(), 2))  //两小时后token过期
                .sign(Algorithm.HMAC256(sign));  //以password作为token的密钥
    }

    /**
     * @Description:
     * @Param:
     * @return: 获取当前的登录对象信息
     * @Author: 付建波
     * @Date: 2022/3/11
     */

    public static User getCurrentUser() {
        try {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            String token = request.getHeader("token");
            if (StrUtil.isNotBlank(token)) {
                String userId = JWT.decode(token).getAudience().get(0);
                return staticUserService.getById(Integer.valueOf(userId));
            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }

}
