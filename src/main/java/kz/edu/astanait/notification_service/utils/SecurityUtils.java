package kz.edu.astanait.notification_service.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Map;

public class SecurityUtils {

    public static Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public static Long getCurrentId() {
        var authInfo = getAuthentication();
        return (Long) ((Map<String, Object>) authInfo.getDetails()).get("id");
    }
}
