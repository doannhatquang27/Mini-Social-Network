<%-- 
    Document   : ShowArticleDetails
    Created on : Sep 18, 2020, 9:33:47 AM
    Author     : ASUS
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">        
        <link href="./css/searchPage.css" rel="stylesheet">

        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
        <script src="https://code.jquery.com/jquery-1.11.1.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
        <title>Article Details</title>
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
                        <input class="btn btn-primary" type="submit" value="Search" name="btnAction"></input>
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

            <c:set var="article" value="${requestScope.ARTICLE}"/>
            <c:if test="${not empty article}">
                <div class="container" style="top: 60px; position: relative;">
                    <div class="col-md-8 col-md-offset-2">
                        <!-- Begin of panel-->
                        <div class="panel panel-default">
                            <div class="panel-body">
                                <section class="post-heading">
                                    <div class="row">
                                        <div class="col-md-11">
                                            <div class="media">
                                                <div class="media-left">
                                                    <a>
                                                        <img class="media-object photo-profile" src="https://image.ibb.co/jw55Ex/def_face.jpg" width="40" height="40" alt="...">
                                                    </a>
                                                </div>
                                                <div class="media-body">
                                                    <c:url var="detailLink" value="ViewDetailServlet">
                                                        <c:param name="articleId" value="${article.articleId}"/>
                                                    </c:url>
                                                    <a href="${detailLink}" class="anchor-username">
                                                        <h4 class="media-heading">${article.title}</h4>
                                                    </a> 
                                                    <p>Created by ${article.email} on ${article.publishedDate}</p>
                                                </div>
                                            </div>
                                        </div>
                                    </div>             
                                </section>
                                <section class="post-body">
                                    <p>${article.description}</p>
                                    <c:if test="${not empty article.artImage}">
                                        <a>
                                            <img src="${article.artImage}" width="100%">
                                        </a>
                                    </c:if>
                                </section>
                                <section class="post-footer">
                                    <hr>
                                    <div>
                                        <div>
                                            <c:set var="numOfLike" value="${requestScope.LIKE}"/>
                                            <c:set var="numOfDislike" value="${requestScope.DISLIKE}"/>
                                            <c:set var="numOfComment" value="${requestScope.NUMOFCOMMENT}"/>
                                            <span class="col-md-2" style="color: blue">${numOfLike} Like</span>
                                            <span class="col-md-4" style="color: red">${numOfDislike} Dislike</span>
                                            <span class="col-md-6" style="text-align: right;">${numOfComment} Comment</span>
                                        </div>
                                    </div>
                                    <hr><hr>
                                    <div class="post-footer-option container">
                                        <ul class="list-unstyled">  
                                            <c:set var="userReactList" value="${requestScope.USERREACTLIST}"/>
                                            <c:set var="reactStatus" value=""/>
                                            <c:forEach var="reactItem" items="${userReactList}">
                                                <c:if test="${reactItem.articleId == article.articleId}">
                                                    <c:set var="reactStatus" value="${reactItem.reactStatus}"/>
                                                </c:if>
                                            </c:forEach>       
                                            <li style="float: left; margin-right: 50px; padding-bottom: 15px">                                                 
                                                <c:url var="likeLink" value="EmotionServlet">
                                                    <c:param name="status" value="LIKE"/>
                                                    <c:param name="articleId" value="${article.articleId}"/>
                                                    <c:param name="txtSearchValue" value="${searchValue}"/>
                                                    <c:param name="returnTo" value="ShowDetail"/>
                                                </c:url>
                                                <c:if test="${not empty reactStatus && reactStatus == true}">
                                                    <a href="${likeLink}" style="color: #0099ff; font-weight:bold;"><i class="glyphicon glyphicon-thumbs-up"></i> Like</a>
                                                </c:if>
                                                <c:if test="${reactStatus == false || empty reactStatus}">
                                                    <a href="${likeLink}" style="color: #666666"><i class="glyphicon glyphicon-thumbs-up"></i> Like</a>
                                                </c:if>                                           
                                            </li>
                                            <li style="float: left; margin-right: 50px; padding-bottom: 15px">
                                                <c:url var="dislikeLink" value="EmotionServlet">
                                                    <c:param name="status" value="DISLIKE"/>
                                                    <c:param name="articleId" value="${article.articleId}"/>
                                                    <c:param name="txtSearchValue" value="${searchValue}"/>
                                                    <c:param name="returnTo" value="ShowDetail"/>
                                                </c:url>
                                                <c:if test="${not empty reactStatus && reactStatus == false}">                                                        
                                                    <a href="${dislikeLink}" style="color: #ff3300; font-weight:bold;"><i class="glyphicon glyphicon-thumbs-down"></i> Dislike</a>
                                                </c:if>
                                                <c:if test="${reactStatus == true || empty reactStatus}">
                                                    <a href="${dislikeLink}" style="color: #666666"><i class="glyphicon glyphicon-thumbs-down"></i> Dislike</a>
                                                </c:if>  
                                            </li>
                                            <li style="float: left; margin-right: 50px; padding-bottom: 15px">
                                                <a href="#commentHere" style="color: #666666"><i class="glyphicon glyphicon-comment"></i> Comment</a>
                                            </li>

                                        </ul>
                                    </div>
                                    <div class="post-footer-comment-wrapper">
                                        <div class="comment">
                                            <c:set var="commentList" value="${requestScope.LIST}"/>
                                            <c:if test="${not empty commentList}">
                                                <c:forEach var="comment" items="${commentList}" varStatus="counter">
                                                    <div class="card">
                                                        <div class="card-body">
                                                            <div class="row">
                                                                <!-- show image -->
                                                                <div class="col-md-2" style="text-align: center">
                                                                    <img src="https://image.ibb.co/jw55Ex/def_face.jpg" width="30" style="text-align: center"/>
                                                                    <p class="text-secondary text-center">${comment.cmtDate}</p>
                                                                </div>

                                                                <!-- show content -->
                                                                <div class="col-md-9">
                                                                    <p>
                                                                        <a class="float-left"><strong>${comment.email}</strong></a>
                                                                    </p>
                                                                    <div class="clearfix"></div>
                                                                    <p>${comment.commentContent}</p>
                                                                </div>

                                                                <!-- delete comment -->
                                                                <div class="col-md-1">
                                                                    <div class="dropdow" style="text-align: left;">
                                                                        <c:set var="email" value="${sessionScope.EMAIL}"/>
                                                                        <c:if test="${comment.email == email}">                                                                            
                                                                            <button class="btn btn-secondary dropdown-toggle btn-sm" type="button" data-toggle="dropdown"><span class="caret"></span></button>
                                                                            </c:if>
                                                                            <c:if test="${comment.email != email}">                                                                        
                                                                            <button class="btn btn-secondary dropdown-toggle btn-sm" disabled type="button" data-toggle="dropdown"><span class="caret"></span></button>
                                                                            </c:if>                                                                           
                                                                        <ul class="dropdown-menu">
                                                                            <li><a href="#" data-toggle="modal" data-target="#deleteCmtModal${comment.commentId}">Delete</a></li>
                                                                        </ul>
                                                                    </div>

                                                                    <!-- Modal -->
                                                                    <div class="modal" tabindex="-1" role="dialog" id="deleteCmtModal${comment.commentId}">
                                                                        <div class="modal-dialog" role="document">
                                                                            <div class="modal-content">
                                                                                <div class="modal-header">
                                                                                    <h5 class="modal-title">Delete Confirm</h5>
                                                                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                                                        <span aria-hidden="true">&times;</span>
                                                                                    </button>
                                                                                </div>
                                                                                <div class="modal-body">
                                                                                    <p>Do you want to delete this comment?</p>
                                                                                </div>
                                                                                <div class="modal-footer">
                                                                                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                                                                    <form action="DispatchController" style="display: inline-block">
                                                                                        <input type="hidden" name="commentID" value="${comment.commentId}" />
                                                                                        <input type="hidden" name="articleId" value="${article.articleId}" />
                                                                                        <input type="hidden" name="returnTo" value="ShowDetail" />
                                                                                        <input type="submit" class="btn btn-primary" value="DeleteComment" name="btnAction">
                                                                                    </form>                                                                         
                                                                                </div>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </c:forEach>                                                
                                            </c:if>
                                            <form action="DispatchController" method="POST" id="commentHere">
                                                <div class="form-group">
                                                    <label for="comment">Your Comment</label>
                                                    <textarea name="txtContent" class="form-control" rows="3"></textarea>
                                                </div>
                                                <input type="hidden" name="articleId" value="${article.articleId}" />
                                                <input type="hidden" name="returnTo" value="ShowDetail" />
                                                <button type="submit" class="btn btn-default" value="Comment" name="btnAction">Send</button>
                                            </form>
                                        </div>
                                    </div>
                                </section>

                            </div>
                        </div>  
                        <!-- End of panel -->
                    </div>
                </div>
            </c:if>
        </c:if>

        <c:if test="${empty username}">
            <c:redirect url="Login.jsp"/>
        </c:if>
    </body>
</html>