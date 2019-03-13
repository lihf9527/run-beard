package org.game.common.util;

import org.game.entity.User;
import org.game.service.UserService;
import org.game.web.interceptor.AuthorizationInterceptor;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class WebUtils {
    private WebUtils() {
    }

    private static RequestAttributes getRequestAttributes() {
        return RequestContextHolder.currentRequestAttributes();
    }

    private static ServletRequestAttributes getServletRequestAttributes() {
        return (ServletRequestAttributes) getRequestAttributes();
    }

    public static HttpServletRequest getRequest() {
        return getServletRequestAttributes().getRequest();
    }

    public static HttpServletResponse getResponse() {
        return getServletRequestAttributes().getResponse();
    }

    public static HttpSession getSession() {
        return getRequest().getSession();
    }

    public static Integer getUserId() {
        return (Integer) getRequestAttributes().getAttribute(AuthorizationInterceptor.USER_KEY, RequestAttributes.SCOPE_REQUEST);
    }

    public static User getLoginUser() {
        Integer userId = getUserId();
        Assert.notNull(userId, "用户未登录");
        return SpringUtils.getBean(UserService.class).findById(userId);
    }
}
