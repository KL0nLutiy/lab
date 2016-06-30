<%@ page import="database.DBWorker" %><%--
  Created by IntelliJ IDEA.
  User: Vlad
  Date: 28.06.2016
  Time: 18:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Online store</title>
    <!--Import Google Icon Font-->
    <link href="http://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <!--Import materialize.css-->
    <link type="text/css" rel="stylesheet" href="css/materialize.min.css"  media="screen,projection"/>

    <!--Let browser know website is optimized for mobile-->
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>

    <script type="text/javascript" src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
    <script type="text/javascript" src="js/materialize.min.js"></script>
    <script type="text/javascript" src="js/script.js"></script>
    <link href="css/style.css" rel="stylesheet"/>
    <%
        String value = (String) request.getSession().getAttribute("orderResult");
        if(value != null && value.contains("successful")) {
            request.getSession(false).invalidate();
    %>
    <script>
        alert("Congratulation your order has been accepted. Wait for shop assistant call.");
        window.location.replace("login.jsp");
    </script>
    <%
        }
    %>
    <%
        String userName = null;
        Cookie[] cookies = request.getCookies();
        if(cookies !=null){
            for(Cookie cookie : cookies){
                if(cookie.getName().equals("user")) {
                    userName = cookie.getValue();
                }
            }
        }

        if(userName==null) {
            response.sendRedirect("index.jsp");
        }

        DBWorker dbWorker = new DBWorker();
        Long orderId = dbWorker.getId();

        String name = (String) request.getSession().getAttribute("name");
        String price = (String) request.getSession().getAttribute("price");
        String productId = (String) request.getSession().getAttribute("productId");

        String firstSecondname = dbWorker.getUsersName(userName);

        request.getSession().setAttribute("orderId",orderId);
    %>
    <title>Order - <%=orderId%></title>
</head>
<body>
<jsp:include page="header.jsp"/>
<form id="order" action="order" method="post">
    <div class="header">
        <h5>Order - <%=orderId%></h5>
    </div>
    <h6><%=firstSecondname%> thank you for your purchase. Please fill the form.</h6><br>
    <h6>You want to buy <%=name%>(product id: <%=productId%>) for <%=price%> UAH with 12 month of guarantee.</h6><br>
    <div class="row">
        <div class="input-field col s6">
            <input id="amount" name="amount" type="number" class="validate" min="1" value="1" required>
            <label for="amount">Amount</label>
        </div>
    </div>
    <div class="row">
        <div class="input-field col s12">
            <input id="address" name="address" type="text" class="validate" required>
            <label for="address">Delivery address</label>
        </div>
    </div>
    <div class="row">
        <div class="input-field col s12">
            <input id="comments" name="comments" type="text" class="validate" required>
            <label for="address">Comments</label>
        </div>
    </div>
    <button id="loginSubmit" class="btn waves-effect waves-light" type="submit" name="action">Submit
        <i class="material-icons right">send</i>
    </button>
</form>
</body>
</html>
