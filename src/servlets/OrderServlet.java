package servlets;

import database.DBWorker;
import database.entities.TTObject;
import database.entities.TTParams;
import database.entities.TTReferences;
import database.entities.embeded.AttrObject;
import database.entities.embeded.AttrObjectReference;
import database.interfaces.TTObjectInterface;
import database.interfaces.TTParamsInterface;
import database.interfaces.TTReferencesInterface;
import utils.Utils;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;

/**
 * Created by Vlad on 28.06.2016.
 */
public class OrderServlet extends HttpServlet implements javax.servlet.Servlet {

    @EJB
    TTReferencesInterface references;

    @EJB
    TTObjectInterface objectI;

    @EJB
    TTParamsInterface params;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie loginCookie = null;
        Cookie[] cookies = request.getCookies();
        if(cookies != null){
            for(Cookie cookie : cookies){
                if(cookie.getName().equals("user")){
                    loginCookie = cookie;
                }
            }
        }

        if(loginCookie==null){
            response.sendRedirect("login.jsp");
        }

        String objectId = Utils.toUTF8Request(request.getParameter("objectId"));
        String name = Utils.toUTF8Request(request.getParameter("name"));
        String price = Utils.toUTF8Request(request.getParameter("price"));
        String productId = Utils.toUTF8Request(request.getParameter("productId"));

        request.getSession().setAttribute("objectId", objectId);
        request.getSession().setAttribute("name", name);
        request.getSession().setAttribute("price", price);
        request.getSession().setAttribute("productId", productId);


        RequestDispatcher rd = request.getRequestDispatcher("order.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = null;
        Cookie[] cookies = request.getCookies();
        if(cookies !=null){
            for(Cookie cookie : cookies){
                if(cookie.getName().equals("user")) {
                    userName = cookie.getValue();
                }
            }
        }


        Long orderId = (Long) request.getSession().getAttribute("orderId");
        String amount = Utils.toUTF8Request(request.getParameter("amount"));
        String address = Utils.toUTF8Request(request.getParameter("address"));
        String comments = Utils.toUTF8Request(request.getParameter("comments"));
        Long itemObjectId = Long.parseLong((String) request.getSession().getAttribute("objectId"));

        TTObject object = new TTObject();
        object.setVersion(1L);
        object.setDescription("User order");
        object.setObjectTypeId(9L);
        object.setName(userName+":"+orderId);

        Long orderObjectId = objectI.create(object);

        DBWorker dbWorker = new DBWorker();
        Long userObjectId = dbWorker.getObjectIdForValue(userName);
        params.create(new TTParams(new AttrObject(32L,orderObjectId),dbWorker.getAttrAccessType(32L),""+orderId));
        params.create(new TTParams(new AttrObject(33L,orderObjectId),dbWorker.getAttrAccessType(33L),""+address));
        params.create(new TTParams(new AttrObject(34L,orderObjectId),dbWorker.getAttrAccessType(34L),""+comments));
        params.create(new TTParams(new AttrObject(35L,orderObjectId),dbWorker.getAttrAccessType(35L),"12"));
        params.create(new TTParams(new AttrObject(41L,orderObjectId),dbWorker.getAttrAccessType(41L),amount));
        params.create(new TTParams(new AttrObject(36L,orderObjectId),dbWorker.getAttrAccessType(36L),new Date(Utils.getCurrentTimeLong())));
        params.create(new TTParams(new AttrObject(37L,orderObjectId),dbWorker.getAttrAccessType(37L),new Date(Utils.getCurrentTimeLong())));
        params.create(new TTParams(new AttrObject(38L,orderObjectId),dbWorker.getAttrAccessType(38L),""+userObjectId));
        params.create(new TTParams(new AttrObject(39L,orderObjectId),dbWorker.getAttrAccessType(39L),""+userObjectId));
        dbWorker.close();

        AttrObjectReference attrObjectReference1 = new AttrObjectReference(32L,userObjectId,orderObjectId);
        references.create(new TTReferences(attrObjectReference1,1L));

        AttrObjectReference attrObjectReference2 = new AttrObjectReference(32L,itemObjectId,orderObjectId);
        references.create(new TTReferences(attrObjectReference2,1L));

        request.getSession().setAttribute("orderResult", "successful");
        RequestDispatcher rd = request.getRequestDispatcher("order.jsp");
        rd.forward(request, response);
    }
}
