package com.library.util;

import com.library.model.User;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class AuthUtil {
    public static boolean isLoggedIn(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        return session != null && session.getAttribute("user") != null;
    }
    
    public static User getCurrentUser(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        return session != null ? (User) session.getAttribute("user") : null;
    }
    
    public static boolean isAdmin(HttpServletRequest request) {
        User user = getCurrentUser(request);
        return user != null && "ADMIN".equals(user.getRole());
    }
    
    public static boolean isLibrarian(HttpServletRequest request) {
        User user = getCurrentUser(request);
        return user != null && ("ADMIN".equals(user.getRole()) || "LIBRARIAN".equals(user.getRole()));
    }
}