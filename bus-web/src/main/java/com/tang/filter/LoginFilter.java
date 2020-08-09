package com.tang.filter;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tang.entity.Config;
import com.tang.response.ErrorCode;
import com.tang.response.RestResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;

/**
 * 登录过滤器
 *
 * @author heyefu
 * Create in: 2020-07-12
 * Time: 下午8:20
 **/
@Slf4j
public class LoginFilter implements HandlerInterceptor {
    /**
     * 是否需要开启登录过滤
     */
    private static final String REQUIRE = "bus.filter.login";

    private static final String TOKEN = "user";

    private static final String TRUE = "true";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
        Object handler) throws Exception {
        // filter中使用@Value不能获取值
        // Spring中，web应用启动的顺序是：listener->filter->servlet，先初始化listener，然后再来就filter的初始化，再接着才到我们的dispathServlet的初始化，因此，当我们需要在filter里注入一个注解的bean时，就会注入失败，因为filter初始化时，注解的bean还没初始化，没法注入。
        // 简答理解：过滤器的生命优先级比较高，还没加载后面的bean之类的东西，spring无法帮你注入
        Environment environment =
            Objects.requireNonNull(WebApplicationContextUtils.getWebApplicationContext(request.getServletContext()))
                .getBean(Environment.class);
        if (TRUE.equals(environment.getProperty(REQUIRE))) {
            if (checkUser(request.getSession().getAttribute(TOKEN))) {
                return true;
            }
            returnJson(response);
        }
        return true;
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
            RestResponse<Objects> restCode = new RestResponse<>(ErrorCode.ERROR, "未认证");
            ObjectMapper mapper = new ObjectMapper();
            mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            String result = mapper.writeValueAsString(restCode);
            writer.print(result);
        } catch (IOException e) {
            log.error("login interceptor return response error", e);
        }
    }
}