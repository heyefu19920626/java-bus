package com.heyefu.filter;

import com.heyefu.user.entity.User;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录过滤器
 *
 * @author heyefu
 * Create in: 2020-07-12
 * Time: 下午8:20
 **/
public class LoginFilter implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
            Object handler) throws Exception {
        return checkUser(request.getSession().getAttribute("user"));
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
            Exception ex) throws Exception {

    }

    private boolean checkUser(Object user) {
        if (!(user instanceof User)) {
            return false;
        }
        return "123456".equals(((User) user).getPassword());
    }
}
