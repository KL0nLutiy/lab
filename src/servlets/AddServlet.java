package servlets;

import database.DBWorker;
import database.entities.TTAttrObjectTypes;
import database.entities.TTAttributes;
import database.entities.TTObject;
import database.entities.TTParams;
import database.entities.embeded.AttrObject;
import database.entities.embeded.AttrObjectType;
import database.impl.TTAttributesImpl;
import database.interfaces.TTAttrObjectTypeInterface;
import database.interfaces.TTAttributeInterface;
import database.interfaces.TTObjectInterface;
import database.interfaces.TTParamsInterface;
import org.apache.log4j.Logger;
import utils.Utils;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;
import java.io.*;
import java.sql.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Vlad on 30.06.2016.
 */

@MultipartConfig
public class AddServlet extends HttpServlet implements javax.servlet.Servlet {

    private static final Logger log = Logger.getLogger(AddServlet.class);

    @EJB
    TTObjectInterface objectI;

    @EJB
    TTParamsInterface paramsI;

    @EJB
    TTAttributeInterface attributeI;

    @EJB
    TTAttrObjectTypeInterface attrObjectTypeI;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String selectedType = Utils.toUTF8Request(request.getParameter("goodsType"));
        DBWorker dbWorker = new DBWorker();
        List<String> list = dbWorker.getAttrsForGoods(Long.parseLong(selectedType));
        dbWorker.close();

        request.getSession().setAttribute("list", list);
        request.getSession().setAttribute("goodsType", selectedType);
        response.sendRedirect("index.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DBWorker dbWorker = new DBWorker();
        Map<String, String[]> parameters = request.getParameterMap();

        Long objectTypeId = Long.parseLong(parameters.get("goodsType")[0]);

        TTObject object = new TTObject();
        object.setObjectTypeId(objectTypeId);
        object.setName(Utils.toUTF8Request(parameters.get("name")[0]));
        Long objectId = objectI.create(object);

        String imgPath = getServletConfig().getServletContext().getRealPath("");

        Part filePart = request.getPart("file");
        InputStream fileContent = filePart.getInputStream();
        OutputStream outputStream = null;

        try {

            outputStream =
                    new FileOutputStream(new File(imgPath+"\\img\\"+objectId+".jpg"));

            int read = 0;
            byte[] bytes = new byte[1024];

            while ((read = fileContent.read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
            }

            log.info("Img for object id: "+objectId+" added success");

        } catch (IOException e) {
            e.printStackTrace();
            log.error("Can't add img for object id: "+objectId+" "+e.getMessage());
        } finally {
            if (fileContent != null) {
                try {
                    fileContent.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }

        paramsI.create(new TTParams(new AttrObject(40L,objectId),dbWorker.getAttrAccessType(40L),"img/"+objectId+".jpg"));



        for(String parameter : parameters.keySet()) {
            if(parameter.equals("action") || parameter.equals("name") || parameter.equals("file") || parameter.equals("goodsType")) {
                continue;
            }

            Long attrId;

            if((attrId=dbWorker.getAttrIdForName(parameter))!=0L) {
                if(Utils.toUTF8Request(parameters.get(parameter)[0]).equals("")) {
                    continue;
                }
                AttrObject attrObject = new AttrObject(attrId,objectId);
                paramsI.create(new TTParams(attrObject,dbWorker.getAttrAccessType(attrId),Utils.toUTF8Request(parameters.get(parameter)[0])));
            } else {
                Long newAttrId = attributeI.create(new TTAttributes(parameter,0L));

                TTAttrObjectTypes attrObjectTypes = new TTAttrObjectTypes();
                attrObjectTypes.setAttrObjectEmbedded(new AttrObjectType(newAttrId,objectTypeId));
                attrObjectTypeI.create(attrObjectTypes);

                AttrObject attrObject = new AttrObject(newAttrId,objectId);
                paramsI.create(new TTParams(attrObject,0L,Utils.toUTF8Request(parameters.get(parameter)[0])));
            }
        }

        String userName = null;
        Cookie[] cookies = request.getCookies();
        if(cookies !=null){
            for(Cookie cookie : cookies){
                if(cookie.getName().equals("user")) {
                    userName = cookie.getValue();
                }
            }
        }

        paramsI.create(new TTParams(new AttrObject(36L,objectId),dbWorker.getAttrAccessType(36L),new Date(Utils.getCurrentTimeLong())));
        paramsI.create(new TTParams(new AttrObject(37L,objectId),dbWorker.getAttrAccessType(37L),new Date(Utils.getCurrentTimeLong())));
        paramsI.create(new TTParams(new AttrObject(38L,objectId),dbWorker.getAttrAccessType(38L),""+dbWorker.getObjectIdForValue(userName)));
        paramsI.create(new TTParams(new AttrObject(39L,objectId),dbWorker.getAttrAccessType(39L),""+dbWorker.getObjectIdForValue(userName)));
        response.sendRedirect("index.jsp");



        dbWorker.close();
    }
}
