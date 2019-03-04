package com.codesquad.blackjack.web.security;

import com.codesquad.blackjack.web.domain.WebUser;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpSession;

public class HttpSessionUtils {
    public static final String USER_SESSION_KEY = "loginedUser";

    public static boolean isLoginUser(NativeWebRequest webRequest) {
        Object loginedUser = webRequest.getAttribute(USER_SESSION_KEY, WebRequest.SCOPE_SESSION);
        return loginedUser != null;
    }

    public static WebUser getUserFromSession(NativeWebRequest webRequest) {
        if (!isLoginUser(webRequest)) {
            return WebUser.GUEST_USER;
        }
        return (WebUser) webRequest.getAttribute(USER_SESSION_KEY, WebRequest.SCOPE_SESSION);
    }

    public static boolean isLoginUser(HttpSession session) {
        Object sessionedUser = session.getAttribute(USER_SESSION_KEY);
        if (sessionedUser == null) {
            return false;
        }
        return true;
    }

    public static WebUser getUserFromSession(HttpSession session) {
        if (!isLoginUser(session)) {
            return null;
        }

        return (WebUser) session.getAttribute(USER_SESSION_KEY);
    }
}
