package servlets;

import database.DBWorker;
import database.interfaces.TTAttrObjectTypeInterface;
import utils.Utils;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Vlad on 25.06.2016.
 */
public class LoginServlet extends HttpServlet implements javax.servlet.Servlet {

    @EJB
    private TTAttrObjectTypeInterface object;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        DBWorker dbWorker = new DBWorker();

        String username = Utils.toUTF8Request(request.getParameter("username"));

        if(dbWorker.getObjectIdForValue(username)==null) {
            request.getSession().setAttribute("result", "error1");
            RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
            rd.forward(request, response);
            return;
        }

        String password = Utils.md5Custom(Utils.toUTF8Request(request.getParameter("password")));

        if(dbWorker.getPasswordForUsername(username)!=null && !dbWorker.getPasswordForUsername(username).equals(password)) {
            request.getSession().setAttribute("result", "error2");
            RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
            rd.forward(request, response);
            return;
        }

        Cookie loginCookie = new Cookie("user",username);
        Cookie adminCookie = new Cookie("admin",""+dbWorker.isAdmin(username));
        loginCookie.setMaxAge(30*60);
        adminCookie.setMaxAge(30*60);
        response.addCookie(loginCookie);
        response.addCookie(adminCookie);
        response.sendRedirect("index.jsp");
    }

}
