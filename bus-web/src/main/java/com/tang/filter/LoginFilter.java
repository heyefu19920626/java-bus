package com.tang.filter;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tang.entity.Config;
import com.tang.error.ErrorCode;
import com.tang.response.RestCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 登录过滤器
 *
 * @author heyefu
 * Create in: 2020-07-12
 * Time: 下午8:20
 **/
@Slf4j
public class LoginFilter implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
            Object handler) throws Exception {
        if (checkUser(request.getSession().getAttribute("user"))) {
            return true;
        }
        returnJson(response);
        return false;
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
        if (!(user instanceof Config)) {
            return false;
        }
        return "123456".equals(((Config) user).getValue());
    }

    private void returnJson(HttpServletResponse response) {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        try (PrintWriter writer = response.getWriter()) {
            RestCode restCode = new RestCode(ErrorCode.ERROR, "未认证", null);
            ObjectMapper mapper = new ObjectMapper();
            mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            String result = mapper.writeValueAsString(restCode);
            writer.print(result);
        } catch (IOException e) {
            log.error("login interceptor return response error", e);
        }
    }
}