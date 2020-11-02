<%-- 
    Document   : Login
    Created on : Sep 26, 2020, 5:04:14 PM
    Author     : ASUS
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
        <title>Login Page</title>
    </head>
    <body>
        <div class="container">
            <div class="row">
                <div class="col-md-6 col-md-offset-3">
                    <div class="panel panel-login">
                        <div class="panel-heading">
                            <div class="row">
                                <div class="col-md-6 col-md-offset-3" style="text-align: center;">
                                    <h1>Login</h1>
                                </div>
                            </div>
                            <hr>
                        </div>
                        <div class="panel-body">
                            <div class="row">
                                <div class="col-lg-12">
                                    <form id="login-form" action="DispatchController" method="POST" role="form" style="display: block;">
                                        <div class="form-group">
                                            <label>User Name: </label>
                                            <input type="text" name="txtEmail" tabindex="1" class="form-control" placeholder="Username" value="">
                                        </div>
                                        <div class="form-group">
                                            <label>Password: </label>
                                            <input type="password" name="txtPassword" id="password" tabindex="2" class="form-control" placeholder="Password">
                                        </div>
                                        <div class="form-group">
                                            <c:set var="loginError" value="${requestScope.LOGINERROR}"/>
                                            <c:if test="${not empty loginError.loginError}">
                                                <p>
                                                    <font color="red">${loginError.loginError}</font>
                                                </p>
                                            </c:if>
                                        </div>                                       
                                        <div class="form-group">
                                            <div class="row">
                                                <div class="col-sm-6 col-sm-offset-3">
                                                    <input class="btn btn-primary form-control btn btn-login" type="submit" value="Login" name="btnAction"></input>
                                                </div>
                                            </div>
                                        </div>
                                    </form>                                    
                                    <div class="col-xs-6 col-xs-offset-3" style="text-align: center">
                                        <p>Don't have an account? <a href="Register.jsp" id="register-form-link">Sign up</a></p>                                        
                                    </div>						
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </body>
</html>
