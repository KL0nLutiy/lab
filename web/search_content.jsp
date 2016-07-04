<%@ page import="utils.Utils" %>
<%@ page import="java.util.*" %>
<%@ page import="database.DBWorker" %><%--
  Created by IntelliJ IDEA.
  User: Vlad
  Date: 25.06.2016
  Time: 14:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript" src="js/script.js"></script>
<div class="search">
    <form onkeyup="Search()">
        <div class="input-field">
            <i class="material-icons prefix">search</i>
            <input id="icon_prefix" type="text" name="search" class="validate">
            <label for="icon_prefix">Search</label>
        </div>
    </form>
    <div class="content">
        <div class="row">
            <%
                List<String> list = (List<String>) request.getSession().getAttribute("list");
                String goodsType = (String) request.getSession().getAttribute("goodsType");
                String uri = request.getRequestURI();

                String pageName = uri.substring(uri.lastIndexOf("/") + 1);

                DBWorker dbWorker = new DBWorker();

                Map<Long, Map<String, String>> map = null;

                switch (pageName) {
                    case "phones.jsp":
                        map = dbWorker.getGoods(3L);
                        break;
                    case "headphones.jsp":
                        map = dbWorker.getGoods(4L);
                        break;
                    case "usbcables.jsp":
                        map = dbWorker.getGoods(5L);
                        break;
                    case "chargers.jsp":
                        map = dbWorker.getGoods(6L);
                        break;
                    case "batteries.jsp":
                        map = dbWorker.getGoods(7L);
                        break;
                    case "screensandsensors.jsp":
                        map = dbWorker.getGoods(8L);
                        break;
                    default:
                        map = dbWorker.getGoods(0L);
                }

                long objectId = 0L;

                boolean isAdmin = false;
                Cookie[] cookies = request.getCookies();
                if (cookies != null) {
                    for (Cookie cookie : cookies) {
                        if (cookie.getName().equals("admin"))
                            isAdmin = Boolean.parseBoolean(cookie.getValue());
                    }
                }

                for (Map.Entry<Long, Map<String, String>> pair : map.entrySet()) {
                    //.out.println(pair.getKey());
            %>
            <div class="col s4 m4">
                <div class="card large">
                    <%
                        objectId = pair.getKey();
                        String url = "";
                        String name = "";
                        String price = "";
                        String createdBy = "1";
                        String changeBy = "1";
                        String createdTime = "";
                        String changeTime = "";
                        String productId = "";
                        Map<String, String> map2 = new HashMap<>();
                        for (Map.Entry<String, String> pair2 : pair.getValue().entrySet()) {
                            //System.out.println(pair2.getKey()+" "+pair2.getValue());
                            switch (pair2.getKey()) {
                                case "img_url":
                                    url = pair2.getValue();
                                    break;
                                case "name":
                                    name = pair2.getValue();
                                    break;
                                case "price":
                                    price = pair2.getValue();
                                    break;
                                case "create_by":
                                    createdBy = pair2.getValue();
                                    break;
                                case "change_by":
                                    changeBy = pair2.getValue();
                                    break;
                                case "create_date":
                                    createdTime = pair2.getValue();
                                    break;
                                case "change_date":
                                    changeTime = pair2.getValue();
                                    break;
                                default:
                                    if (!pair2.getKey().contains("_id")) {
                                        map2.put(pair2.getKey(), pair2.getValue());
                                    } else {
                                        productId = pair2.getValue();
                                    }

                            }
                        }

                    %>
                    <div class="card-image">
                        <img src="<%=url%>" width="380px" height="350px">
                    </div>
                    <div class="card-content">
                        <h6><%=name%>
                        </h6><br>
                        <h6>Price: <%=price%> UAH</h6><br>
                    </div>
                    <div class="card-action">
                        <a class="modal-trigger waves-effect waves-light btn" href="#<%=objectId%>">Description</a>
                        <a href="order?objectId=<%=objectId%>&name=<%=name%>&price=<%=price%>&productId=<%=productId%>"
                           class="modal-action modal-close waves-effect waves-green btn-flat card-action-right">Buy
                            now</a>
                        <%
                            if (isAdmin) {
                        %>
                        <br>
                        <br>
                        <a href="delete?objectId=<%=objectId%>&pageName=<%=pageName%>"
                           class="waves-effect waves-light btn"><i class="material-icons left ">delete</i>Delete</a>
                        <a class="waves-effect waves-light btn modal-trigger" href="#<%=objectId%>"><i
                                class="material-icons left">mode_edit</i>Edit</a>
                        <%
                            }
                        %>
                    </div>
                </div>
            </div>
            <!-- Modal Structure -->
            <div id="<%=objectId%>" class="modal modal-fixed-footer">
                <div class="modal-content">
                    <%
                        if (!isAdmin) {
                    %>
                    <h4><%=name%></h4>
                    <%
                        } else {
                    %>
                        <form id="<%=productId%>" action="edit" method="post" onsubmit="BeforeSubmit(this)">
                    <%
                        }
                    %>
                    <table class="striped">
                        <thead>
                        <tr>
                            <th>Feature</th>
                            <th>Description</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td><%="Product id"%>
                            </td>
                            <td>
                                <%=productId%>
                            </td>
                        </tr>
                        <%
                            if(isAdmin) {
                        %>
                        <tr>
                            <td>name
                            </td>
                            <td><input name="name" type="text" class="validate"
                                       value="<%=name%>">
                            </td>
                        </tr>
                        <%
                            }
                            for (Map.Entry<String, String> pair2 : map2.entrySet()) {
                                if (!isAdmin) {
                        %>
                        <tr>
                            <td><%=Utils.attributeFormat(pair2.getKey())%>
                            </td>
                            <td><%=pair2.getValue()%>
                            </td>
                        </tr>
                        <%
                        } else {
                        %>
                        <tr>
                            <td><%=pair2.getKey()%>
                            </td>
                            <td>
                                <input name="<%=pair2.getKey()%>" type="text" class="validate"
                                       value="<%=pair2.getValue()%>">
                            </td>
                        </tr>
                        <%
                                }
                            }
                            if (!isAdmin) {
                        %>
                        <tr>
                            <td>Price</td>
                            <td><%=price%> UAH</td>
                        </tr>
                        <%
                        } else {
                        %>
                        <tr>
                            <td>price</td>
                            <td>
                                <input name="price" type="text" class="validate" value="<%=price%>">
                            </td>
                        </tr>
                        <%
                            }
                        %>
                        </tbody>
                    </table>
                        <%
                            if(isAdmin) {
                        %>
                            <br>
                            <button class="btn waves-effect waves-light" type="button" name="action" onclick="NewField(this)">New field
                                <i class="material-icons left">add</i>
                            </button>
                            <button id="loginSubmit" class="btn waves-effect waves-light" type="submit" name="action">Submit
                                <i class="material-icons right">send</i>
                            </button>
                        </form>
                        <%
                            }
                        %>
                    <br>
                    Create date: <%=createdTime%><br>
                    Change date: <%=changeTime%><br>
                    Create by: <%=dbWorker.getAdminById(Long.parseLong(createdBy))%><br>
                    Change by: <%=dbWorker.getAdminById(Long.parseLong(changeBy))%><br>
                </div>
                <div class="modal-footer">
                    <a href="#!" class="modal-action modal-close waves-effect waves-green btn-flat ">OK</a>
                    <a href="order?objectId=<%=objectId%>&name=<%=name%>&price=<%=price%>&productId=<%=productId%>"
                       class="modal-action modal-close waves-effect waves-green btn-flat ">Buy now</a>
                </div>
            </div>
            <%
                }
            %>

        </div>
    </div>
    <%
        if(isAdmin) {
    %>
    <div class="fixed-action-btn" style="bottom: 45px; right: 24px;">
        <a class="btn-floating btn-large red waves-effect waves-light modal-trigger" href="#add">
            <i class="large material-icons">add</i>
        </a>
    </div>
    <%
        }
    %>
    <div id="add" class="modal modal-fixed-footer">
        <div class="modal-content">
            <div class="input-field col s12">
                <form action="add" method="get">
                <select id="goodsType" name="goodsType" onchange="this.form.submit()">
                    <option value="" disabled selected>Choose item type to add</option>
                    <option value="3">Phone</option>
                    <option value="4">Headphone</option>
                    <option value="5">USB cable</option>
                    <option value="6">Charger</option>
                    <option value="7">Battery</option>
                    <option value="8">Screen or sensor</option>
                </select>
                </form>
                <form action="add" method="post" enctype="multipart/form-data" onsubmit="BeforeAdd(this)">
                    <%
                        if(list!=null) {
                    %>
                    <div class="row">
                        <div class="input-field col s12">
                            <input id="name" name="name" type="text" class="validate" required>
                            <label for="name">Name</label>
                        </div>
                    </div>
                    <%
                            for(String s: list) {
                                if(s.equals("phone_id") || s.equals("accsesories_id") || s.equals("component_id") || s.equals("create_date") || s.equals("change_date") || s.equals("create_by") || s.equals("change_by")) {
                                    continue;
                                }

                                if(s.equals("img_url")) {
                    %>
                    <div class="file-field input-field">
                        <div class="btn">
                            <span>Image</span>
                            <input name="file" type="file" required>
                        </div>
                        <div class="file-path-wrapper">
                            <input class="file-path validate" type="text">
                        </div>
                    </div>
                    <%
                            continue;
                        }
                    %>
                    <div class="row">
                        <div class="input-field col s12">
                            <input id="<%=s%>" name="<%=s%>" type="text" class="validate">
                            <label for="<%=s%>"><%=Utils.attributeFormat(s)%></label>
                        </div>
                    </div>
                    <%
                            }
                    %>
                    <button id="fieldAdd" class="btn waves-effect waves-light" type="button" name="action" onclick="AddFields(this)">New field
                        <i class="material-icons left">add</i>
                    </button>
                    <button id="loginSubmit" class="btn waves-effect waves-light" type="submit" name="action">Submit
                        <i class="material-icons right">send</i>
                    </button>
                    <%
                        }
                    %>
                </form>
            </div>
        </div>
        <div class="modal-footer">
            <a href="#!" class=" modal-action modal-close waves-effect waves-green btn-flat">OK</a>
        </div>
    </div>
    <%
        if(list!=null) {
            request.getSession(false).invalidate();
    %>
    <script>
        $('#add').openModal();
        $("#goodsType").val('<%=goodsType%>');
    </script>
    <%
        }
    %>
</div>
