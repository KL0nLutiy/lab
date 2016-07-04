<%@ page import="java.util.List" %>
<%@ page import="database.OrderInfo" %>
<%@ page import="database.DBWorker" %><%--
  Created by IntelliJ IDEA.
  User: Vlad
  Date: 25.06.2016
  Time: 14:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<nav>
    <div class="nav-wrapper">
        <a href="index.jsp" class="brand-logo">Online Shop</a>
        <ul id="nav-mobile" class="right hide-on-med-and-down">
            <%
                String userName = null;
                boolean isAdmin = false;
                Cookie[] cookies = request.getCookies();
                if(cookies !=null){
                    for(Cookie cookie : cookies){
                        if(cookie.getName().equals("user")) userName = cookie.getValue();
                        if(cookie.getName().equals("admin")) isAdmin = Boolean.parseBoolean(cookie.getValue());
                    }
                }
                if(userName == null) {
            %>
            <li><a href="login.jsp">Login</a></li>
            <li><a href="registration.jsp">Registration</a></li>
            <%
                } else {
            %>
            <li>
                <!-- Dropdown Trigger -->
                <a class='dropdown-button btn' href='#' data-activates='dropdown1'>
                    Welcome <%=userName%>
                    <%if(isAdmin) {%>
                    (Administrator)
                    <%} %>
                </a>
            </li>
            <li><a href="logout">Logout</a></li>
            <%
                }
            %>
        </ul>
    </div>
</nav>
<% if(userName != null) {%>
<!-- Dropdown Structure -->
<ul id='dropdown1' class='dropdown-content'>
    <li><a class="modal-trigger" href="#orders">My orders</a></li>
</ul>
<div id="orders" class="modal">
<div class="modal-content">
    <h4>Orders</h4>
    <table class="striped">
        <thead>
        <tr>
            <th>Product id</th>
            <th>Name</th>
            <th>Order id</th>
            <th>Price</th>
            <th>Data</th>
        </tr>
        </thead>
        <tbody>
            <%
                DBWorker dbWorker = new DBWorker();
                List<OrderInfo> orderList = dbWorker.getOrderInfoForUser(userName);
                for(OrderInfo info: orderList){
            %>
            <tr>
                <td><%=info.getProductId()%></td>
                <td><%=info.getName()%></td>
                <td><%=info.getOrderId()%></td>
                <td><%=info.getPrice()%></td>
                <td><%=info.getDate()%></td>
            </tr>
            <%
                }
            %>
        </tbody>
        </table>
</div>
<div class="modal-footer">
    <a href="#!" class=" modal-action modal-close waves-effect waves-green btn-flat">Agree</a>
</div>
</div>
<%
    }
%>

