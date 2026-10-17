package com.suguna.breeder_revamp.config;

import com.suguna.breeder_revamp.service.UserSessionService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.IOException;

@Component
public class SingleSessionInterceptor implements HandlerInterceptor {

    public static final String USER_CODE_HEADER = "X-User-Code";
    public static final String SESSION_TOKEN_HEADER = "X-Session-Token";

    private final UserSessionService userSessionService;

    public SingleSessionInterceptor(UserSessionService userSessionService) {
        this.userSessionService = userSessionService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            return true;
        }

        String userCode = request.getHeader(USER_CODE_HEADER);
        String sessionToken = request.getHeader(SESSION_TOKEN_HEADER);

        if (userSessionService.isValidSession(userCode, sessionToken)) {
            return true;
        }

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(
                "{\"status\":\"SESSION_EXPIRED\",\"statusCode\":401,"
                        + "\"message\":\"You have been logged out because your account was signed in on another device\"}"
        );
        return false;
    }
}
