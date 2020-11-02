<%-- 
    Document   : Register
    Created on : Sep 15, 2020, 11:27:11 AM
    Author     : ASUS
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
        <title>Register Page</title>
    </head>
    <body>

        <c:set var="error" value="${requestScope.ERRORS}"/>        
        <div class="container">            
            <div class="col-md-6 col-md-offset-3">
                <div class="row">
                    <div class="col-md-6 col-md-offset-3" style="text-align: center; display: block">
                        <h1>REGISTER</h1>
                    </div>
                </div>    
                <form class="form-horizontal" action='DispatchController' method="POST">
                    <fieldset>                                       
                        <div class="control-group">
                            <!-- Email -->
                            <label class="control-label"  for="email">Email</label>
                            <div class="controls">
                                <input class="form-control" type="text" name="txtEmail" value="${param.txtEmail}" placeholder="Enter your email" maxlength="50">                                
                                <c:if test="${not empty error.emailLengthError}">
                                    <font color="red">${error.emailLengthError}</font><br>
                                </c:if>
                                <c:if test="${not empty error.emailTypeError}">
                                    <font color="red">${error.emailTypeError}</font><br>
                                </c:if>    
                                <c:if test="${empty error.emailTypeError && empty error.emailLengthError}">
                                    <p class="help-block">Ex: something@x.y</p>
                                </c:if>   
                            </div>
                        </div>

                        <div class="control-group">
                            <!-- Username -->
                            <label class="control-label" for="name">Name</label>
                            <div class="controls">
                                <input class="form-control" type="text" name="txtName" value="${param.txtName}" placeholder="Enter your name" maxlength="50">                                
                                <c:if test="${not empty error.nameLengthError}">
                                    <font color="red">${error.nameLengthError}</font><br/>
                                </c:if>
                                <c:if test="${empty error.nameLengthError}">
                                    <p class="help-block">Please provide your name</p>
                                </c:if>
                            </div>
                        </div>

                        <div class="control-group">
                            <label class="control-label" for="password">Password</label>
                            <div class="controls">
                                <input class="form-control" type="password" name="txtPassword" value="" placeholder="Enter your password" maxlength="20">
                                <c:if test="${not empty error.passwordLengthError}">
                                    <font color="red">${error.passwordLengthError}</font><br/>
                                </c:if>
                                <c:if test="${empty error.passwordLengthError}">
                                    <p class="help-block">Password should be at least 6 characters</p>
                                </c:if>
                            </div>
                        </div>

                        <c:if test="${not empty error.duplicateEmail}">
                            <div>
                                <p style="color: #ff3300">This email has been registered before</p>
                            </div>
                        </c:if>
                        <hr>
                        <div class="control-group">
                            <!-- Button -->
                            <div class="controls" style="text-align: center">
                                <input class="btn btn-success" type="submit" value="Register" name="btnAction"/>
                            </div>
                        </div>
                    </fieldset>
                </form>
            </div>
        </div>               
    </body>
</html>
