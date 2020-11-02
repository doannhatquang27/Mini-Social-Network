<%-- 
    Document   : Notification
    Created on : Sep 20, 2020, 10:53:07 AM
    Author     : ASUS
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Notification Page</title>        
        <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css2">
        <link href="./css/notificationPage.css" rel="stylesheet">
        <link href="./css/searchPage.css" rel="stylesheet">
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
        <script src="https://code.jquery.com/jquery-1.11.1.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    </head>
    <body>
        <c:set var="username" value="${sessionScope.USERNAME}"/>
        <c:if test="${not empty username}">
            <header>
                <div class="container-fluid">
                    <div class="navbar-header">
                        <div class="newsfeed-nav"><a href="SearchServlet">日光</a></div>
                    </div>
                    <form class="form-inline col-md-6" style="top: 5px;" action="DispatchController">
                        <c:set var="searchValue" value="${param.txtSearchValue}"/>
                        <input class="form-control mr-sm-2" type="text" placeholder="Search" aria-label="Search" name="txtSearchValue" value="${searchValue}">
                        <input class="btn btn-primary" type="submit" value="Search" name="btnAction">
                    </form>
                    <div>
                        <ul class="nav navbar-nav navbar-right">                            
                            <li>
                                <a><span class="glyphicon glyphicon-user"></span> ${username}</a>
                            </li>
                            <li>
                                <c:url var="notificationLink" value="DispatchController">
                                    <c:param name="btnAction" value="Notification"/>                                    
                                </c:url>
                                <a href="${notificationLink}"><span class="glyphicon glyphicon-bell"></span> Notifications</a>                                
                            </li>
                            <li>
                                <c:url var="logoutLink" value="DispatchController">
                                    <c:param name="btnAction" value="Logout"/>                                    
                                </c:url>
                                <a href="${logoutLink}" ><span class="glyphicon glyphicon-log-in"></span> Logout</a>                                
                            </li>
                        </ul>
                    </div>
                </div>
            </header>

            <div class="container" style="top: 60px; position: relative;">
                <div class="col-md-8 col-md-offset-2">
                    <h1 class="page-header">Notification</h1>
                    <h4>Notification Message</h4>
                    <c:set var="list" value="${requestScope.LIST}"/>
                    <c:if test="${not empty list}"> 
                        <c:forEach var="item" items="${list}" varStatus="counter">
                            <div class="alert alert-success">
                                <c:url var="viewDetail" value="ViewDetailServlet">
                                    <c:param name="articleId" value="${item.articleId}"/>
                                </c:url>
                                <a class="alert-link" href="${viewDetail}">
                                    <c:if test="${item.type == true}">
                                        <div>${item.email} react your article</div>
                                    </c:if>
                                    <c:if test="${item.type == false}">
                                        <div>${item.email} comment to your article</div>
                                    </c:if>
                                    <div>on ${item.date}</div>
                                </a> 
                            </div>   
                        </c:forEach>
                    </c:if>
                </div> 
            </div>
        </c:if>

        <c:if test="${empty username}">
            <c:redirect url="Login.jsp"/>
        </c:if> 
    </body>
</html>
