<%--
  Created by IntelliJ IDEA.
  User: Vlad
  Date: 25.06.2016
  Time: 14:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <!--Import Google Icon Font-->
    <link href="http://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <!--Import materialize.css-->
    <link type="text/css" rel="stylesheet" href="css/materialize.min.css"  media="screen,projection"/>

    <!--Let browser know website is optimized for mobile-->
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>

    <script type="text/javascript" src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
    <script type="text/javascript" src="js/materialize.min.js"></script>

    <link href="css/style.css" rel="stylesheet"/>
</head>
<body>
<jsp:include page="header.jsp"/>
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

    if(userName!=null) {
        response.sendRedirect("index.jsp");
    }

    String value = (String) request.getSession().getAttribute("result");
%>
<form id="login" action="login" method="post">
    <div class="header">
        <h5>Login</h5>
        <%
            if(value != null && value.contains("error")) {
                request.getSession(false).invalidate();
                if(value != null && value.contains("error1")) {
        %>
        <div class="massage">
            Username does not exist.
        </div>
        <%
            }
            if(value != null && value.contains("error2")) {
        %>
        <div class="massage">
            Wrong password.
        </div>
        <%
                }
            }
        %>
    </div>
    <div class="row">
        <div class="input-field col s12">
            <input id="username" name="username" type="text" class="validate">
            <label for="username">Username</label>
        </div>
    </div>
    <div class="row">
        <div class="input-field col s12">
            <input id="password" name="password" type="password" class="validate">
            <label for="password">Password</label>
        </div>
    </div>
    <button id="loginSubmit" class="btn waves-effect waves-light" type="submit" name="action">Submit
        <i class="material-icons right">send</i>
    </button>
</form>
</body>
</html>
