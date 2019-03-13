package org.game.web.interceptor;

import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.game.common.annotation.Login;
import org.game.common.exception.BizException;
import org.game.common.util.DateUtils;
import org.game.common.util.JwtUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Objects;

@Slf4j
@Component
public class AuthorizationInterceptor extends HandlerInterceptorAdapter {
    public static final String USER_KEY = "userId";

//    @Autowired
//    private ValueOperations<String, String> valueOperations;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (!(handler instanceof HandlerMethod))
            return true;

        if (!needLogin((HandlerMethod) handler))
            return true;

        String token = getToken(request);
        if (StringUtils.isEmpty(token))
            throw new BizException("token不能为空");

        Claims claims;
        try {
            claims = JwtUtils.parseToken(token);
        } catch (Exception e) {
            log.info("无效的token ==> {}", token);
            throw new BizException("无效的token");
        }

        if (new Date().after(claims.getExpiration()))
            throw new BizException("token已过期");

        log.info("token解析成功 ==> sub={}, exp={}", claims.getSubject(), DateUtils.format(claims.getExpiration()));
//        String redisToken = valueOperations.get("userId:" + claims.getSubject());
//        if (StringUtils.isEmpty(redisToken))
//            throw new BizException("token已过期");

//        if (!Objects.equals(token, redisToken))
//            throw new BizException("已在另一处登录");

        //设置userId到request里，后续根据userId，获取用户信息
        Integer userId = Integer.valueOf(claims.getSubject());
        request.setAttribute(USER_KEY, userId);
//        valueOperations.set("userId:" + userId, token, 30, TimeUnit.MINUTES);// 更新redis中token的有效时间

        return true;
    }

    private boolean needLogin(HandlerMethod handlerMethod) {
        return Objects.nonNull(handlerMethod.getMethodAnnotation(Login.class));
    }

    private String getToken(HttpServletRequest request) {
        String authorization = request.getHeader("authorization");
        return StringUtils.isEmpty(authorization) ? null : authorization.replaceFirst("[B|b][E|e][A|a][R|r][E|e][R|r]", "").trim();
    }
}
