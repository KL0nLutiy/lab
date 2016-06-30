<%--
  Created by IntelliJ IDEA.
  User: Vlad
  Date: 24.06.2016
  Time: 18:32
  To change this template use File | Settings | File Templates.
--%>
<!--<script>
    $('#registration').hide();
</script>-->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Registration</title>
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
<form id="registration" action="registration" method="post">
    <div class="header">
        <h5>Registration</h5>
    </div>
    <%
        if(value != null && value.contains("error")) {
            request.getSession(false).invalidate();
        if(value != null && value.contains("error1")) {
    %>
        <div class="massage">
        This username already exists. Please try another one.
        </div>
        <br>
    <%
        }
        if(value != null && value.contains("error2")) {
    %>
        <div class="massage">
        This email already exists. Please try another one.
        </div>
    <%
        }
        }
    %>
    <div class="row">
        <div class="input-field col s6">
            <input id="username" name="username" type="text" class="validate" required>
            <label for="username">Username</label>
        </div>
        <div class="input-field col s6">
            <input id="password" name="password" type="password" class="validate" pattern=".{8,}" required title="8 characters minimum">
            <label for="password">Password</label>
        </div>
    </div>
    <div class="row">
        <div class="input-field col s6">
            <input id="firstname" name="firstname" type="text" class="validate" required>
            <label for="firstname">Firstname</label>
        </div>
        <div class="input-field col s6">
            <input id="secondname" name="secondname" type="text" class="validate" required>
            <label for="secondname">Secondname</label>
        </div>
    </div>
    <div class="row">
        <div class="input-field col s6">
            <input id="email" name="email" type="email" class="validate" required>
            <label for="email">Email</label>
        </div>
        <div class="input-field col s6">
            <input id="phone" name="phone" type="text" class="validate" required>
            <label for="phone">Phone</label>
        </div>
    </div>
    <button id="loginSubmit" class="btn waves-effect waves-light" type="submit" name="action">Submit
        <i class="material-icons right">send</i>
    </button>
</form>
<%
    if(value != null && value.contains("successful")) {
        request.getSession(false).invalidate();
%>
<script>
    alert("Registration successful. You will be redirected to login page.");
    window.location.replace("login.jsp");
</script>
<%
    }
%>

</body>
</html>
