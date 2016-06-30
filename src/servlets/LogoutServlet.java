package servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Vlad on 25.06.2016.
 */

public class LogoutServlet extends HttpServlet implements javax.servlet.Servlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Cookie loginCookie = null;
        Cookie adminCookie = null;
        Cookie[] cookies = request.getCookies();
        if(cookies != null){
            for(Cookie cookie : cookies){
                if(cookie.getName().equals("user")){
                    loginCookie = cookie;
                }
                if(cookie.getName().equals("admin")){
                    adminCookie = cookie;
                }
            }
        }

        if(loginCookie != null){
            loginCookie.setMaxAge(0);
            response.addCookie(loginCookie);
        }

        if(adminCookie != null) {
            adminCookie.setMaxAge(0);
            response.addCookie(adminCookie);
        }

        response.sendRedirect("login.jsp");

    }
}
