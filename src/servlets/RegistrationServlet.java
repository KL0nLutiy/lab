package servlets;

import database.DBWorker;
import database.entities.TTObject;
import database.entities.TTParams;
import database.entities.embeded.AttrObject;
import database.interfaces.TTObjectInterface;
import database.interfaces.TTParamsInterface;
import utils.Utils;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;

/**
 * Created by Vlad on 24.06.2016.
 */
public class RegistrationServlet extends HttpServlet implements javax.servlet.Servlet {

    @EJB
    private TTObjectInterface objectI;

    @EJB
    private TTParamsInterface paramsI;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

            DBWorker dbWorker = new DBWorker();

            String username = Utils.toUTF8Request(request.getParameter("username"));
            String email = Utils.toUTF8Request(request.getParameter("email"));

            String errors = "";

            if(!username.isEmpty() && dbWorker.getAttrIdForValue(username)!=null) {
                    errors+="error1";
            }

            if(!email.isEmpty() && dbWorker.getAttrIdForValue(email)!=null){
                    errors+="error2";
            }

            if(!errors.isEmpty()) {
                    request.getSession().setAttribute("result", errors);
                    RequestDispatcher rd = request.getRequestDispatcher("/registration.jsp");
                    rd.forward(request, response);
                    return;
            }


            TTObject object = new TTObject();
            object.setVersion(1L);
            object.setDescription("Shop customer");
            object.setObjectTypeId(2L);
            object.setName(username);

            long id = objectI.create(object);
            long userId = dbWorker.getId();

            paramsI.create(new TTParams(new AttrObject(7L,id),dbWorker.getAttrAccessType(7L),""+userId));
            paramsI.create(new TTParams(new AttrObject(2L,id),dbWorker.getAttrAccessType(2L),username));
            paramsI.create(new TTParams(new AttrObject(3L,id),dbWorker.getAttrAccessType(3L),Utils.md5Custom(request.getParameter("password"))));
            paramsI.create(new TTParams(new AttrObject(4L,id),dbWorker.getAttrAccessType(4L),Utils.toUTF8Request(request.getParameter("firstname"))));
            paramsI.create(new TTParams(new AttrObject(5L,id),dbWorker.getAttrAccessType(5L),Utils.toUTF8Request(request.getParameter("secondname"))));
            paramsI.create(new TTParams(new AttrObject(6L,id),dbWorker.getAttrAccessType(6L),email));
            paramsI.create(new TTParams(new AttrObject(8L,id),dbWorker.getAttrAccessType(8L),Utils.toUTF8Request(request.getParameter("phone"))));
            paramsI.create(new TTParams(new AttrObject(36L,id),dbWorker.getAttrAccessType(36L),new Date(Utils.getCurrentTimeLong())));
            paramsI.create(new TTParams(new AttrObject(37L,id),dbWorker.getAttrAccessType(37L),new Date(Utils.getCurrentTimeLong())));
            paramsI.create(new TTParams(new AttrObject(38L,id),dbWorker.getAttrAccessType(38L),""+userId));
            paramsI.create(new TTParams(new AttrObject(39L,id),dbWorker.getAttrAccessType(39L),""+userId));

            request.getSession().setAttribute("result", "Registration successful");
            RequestDispatcher rd = request.getRequestDispatcher("registration.jsp");
            rd.forward(request, response);
    }
}
