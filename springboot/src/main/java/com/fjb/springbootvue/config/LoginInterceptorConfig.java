//package com.fjb.springbootvue.config;
//
//import com.fjb.springbootvue.entity.User;
//import com.fjb.springbootvue.util.TokenUtils;
//import org.springframework.web.servlet.AsyncHandlerInterceptor;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
///**
// * Created with IntelliJ IDEA.
// *
// * @Author: 付建波
// * @Date: 2022/03/12/8:44
// * @Description:
// */
//public class LoginInterceptorConfig implements AsyncHandlerInterceptor {
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        User currentUser = TokenUtils.getCurrentUser();
//        if (currentUser==null){
//            response.sendRedirect("/home");
//        }
//        return false;
//    }
//}
