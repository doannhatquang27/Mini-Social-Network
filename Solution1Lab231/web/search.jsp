<%-- 
    Document   : search
    Created on : Sep 16, 2020, 9:43:25 AM
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
        <title>Search Page</title>
    </head>
    <body>
        <c:set var="username" value="${sessionScope.USERNAME}"/>
        <c:set var="email" value="${sessionScope.EMAIL}"/>
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

            <c:set var="list" value="${requestScope.LIST}"/>
            <c:if test="${not empty list}">
                <div class="container" style="top: 60px; position: relative;">
                    <div class="col-md-8 col-md-offset-2">
                        <!----------------------------------------------------------------------------->
                        <form action="DispatchController" method="POST">
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
                                                        <input class="form-control" type="text" placeholder="Title" aria-label="Search" name="txtTitle" maxlength="100">
                                                    </div>
                                                </div>
                                            </div>
                                        </div>             
                                    </section>
                                    <section class="post-body">
                                        <br>
                                        <textarea name="txtDescription" rows="3" maxlength="1000" class="form-control" placeholder="What do you feel today?" style="resize: none;"></textarea>
                                        <br>
                                        <input type="file" name="imageFile"/>
                                    </section>
                                    <section class="post-footer">
                                        <hr>
                                        <input class="btn btn-success form-control btn" type="submit" value="PostArticle" name="btnAction"/>
                                    </section>

                                </div>
                            </div>   
                        </form>
                        <!----------------------------------------------------------------------------->
                        <c:forEach var="dto" items="${list}">
                            <div class="panel panel-default">
                                <div class="panel-body">
                                    <section class="post-heading">
                                        <div class="row">
                                            <div class="col-md-11">
                                                <div class="media">
                                                    <div class="media-left">
                                                        <a href="#">
                                                            <img class="media-object photo-profile" src="https://image.ibb.co/jw55Ex/def_face.jpg" width="40" height="40" alt="...">
                                                        </a>
                                                    </div>
                                                    <div class="media-body">
                                                        <c:url var="detailLink" value="ViewDetailServlet">
                                                            <c:param name="articleId" value="${dto.articleId}"/>
                                                        </c:url>
                                                        <a href="${detailLink}" class="anchor-username"><h4 class="media-heading">${dto.title}</h4></a> 
                                                        <p>Created by ${dto.email} on ${dto.publishedDate}</p>
                                                    </div>
                                                </div>
                                            </div> 
                                            <div class="col-md-1">
                                                <div class="dropdown" style="text-align: right">
                                                    <c:set var="email" value="${sessionScope.EMAIL}"/>
                                                    <c:if test="${dto.email == email}">                                                                            
                                                        <button class="btn btn-light btn-sm dropdown-toggle" type="button" data-toggle="dropdown"><span class="caret"></span></button>
                                                        </c:if>
                                                        <c:if test="${dto.email != email}">                                                                        
                                                        <button class="btn btn-light btn-sm dropdown-toggle" disabled type="button" data-toggle="dropdown"><span class="caret"></span></button>
                                                        </c:if>                                                    
                                                    <ul class="dropdown-menu">
                                                        <li><a href="#" data-toggle="modal" data-target="#exampleModal${dto.articleId}">Delete</a></li>
                                                    </ul>
                                                </div>

                                                <!-- Modal -->
                                                <div class="modal" tabindex="-1" role="dialog" id="exampleModal${dto.articleId}">                                                    
                                                    <div class="modal-dialog" role="document">
                                                        <div class="modal-content">
                                                            <div class="modal-header">
                                                                <h5 class="modal-title">Delete Confirm</h5>
                                                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                                    <span aria-hidden="true">&times;</span>
                                                                </button>
                                                            </div>

                                                            <div class="modal-body">
                                                                <p>Do you want to delete this post?</p>
                                                            </div>
                                                            <div class="modal-footer">
                                                                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>                                                                  
                                                                <form action="DispatchController" style="display: inline-block">                                                                    
                                                                    <input type="hidden" name="articleId" value="${dto.articleId}" />
                                                                    <input type="hidden" name="txtSearchValue" value="${searchValue}" />
                                                                    <input type="submit" class="btn btn-danger" value="Delete" name="btnAction">
                                                                </form>                                                                
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>             
                                    </section>
                                    <section class="post-body">
                                        <p>${dto.description}</p>
                                        <c:if test="${not empty dto.artImage}">
                                            <a>
                                                <img src="${dto.artImage}" width="100%">
                                            </a>
                                        </c:if>
                                    </section>
                                    <section class="post-footer">
                                        <hr>
                                        <div class="post-footer-option container">
                                            <ul class="list-unstyled">  
                                                <c:set var="userReactList" value="${requestScope.USERREACTLIST}"/>
                                                <c:set var="reactStatus" value=""/>
                                                <c:forEach var="reactItem" items="${userReactList}">
                                                    <c:if test="${reactItem.articleId == dto.articleId}">
                                                        <c:set var="reactStatus" value="${reactItem.reactStatus}"/>
                                                    </c:if>
                                                </c:forEach>       
                                                <li style="float: left; margin-right: 50px; padding-bottom: 15px">                                                 
                                                    <c:url var="likeLink" value="EmotionServlet">
                                                        <c:param name="status" value="LIKE"/>
                                                        <c:param name="articleId" value="${dto.articleId}"/>
                                                        <c:param name="txtSearchValue" value="${searchValue}"/>
                                                        <c:param name="returnTo" value="SearchPage"/>
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
                                                        <c:param name="articleId" value="${dto.articleId}"/>
                                                        <c:param name="txtSearchValue" value="${searchValue}"/>
                                                        <c:param name="returnTo" value="SearchPage"/>
                                                    </c:url>
                                                    <c:if test="${not empty reactStatus && reactStatus == false}">                                                        
                                                        <a href="${dislikeLink}" style="color: #ff3300; font-weight:bold;"><i class="glyphicon glyphicon-thumbs-down"></i> Dislike</a>
                                                    </c:if>
                                                    <c:if test="${reactStatus == true || empty reactStatus}">
                                                        <a href="${dislikeLink}" style="color: #666666"><i class="glyphicon glyphicon-thumbs-down"></i> Dislike</a>
                                                    </c:if>  
                                                </li>
                                                <li style="float: left; margin-right: 50px; padding-bottom: 15px">
                                                    <a href="#myCmtOf${dto.articleId}" style="color: #666666"><i class="glyphicon glyphicon-comment"></i> Comment</a>
                                                </li>

                                            </ul>
                                        </div>
                                        <div class="post-footer-comment-wrapper">
                                            <div class="comment">
                                                <div class="comment">
                                                    <c:set var="commentList" value="${requestScope.FIRSTCOMMENTLIST}"/>
                                                    <c:if test="${not empty commentList}">
                                                        <c:forEach var="comment" items="${commentList}" >
                                                            <c:if test="${comment.articleId == dto.articleId}">
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
                                                                                                <c:set var="currentPage" value="${requestScope.CURRENTPAGE}"/>
                                                                                                <form action="DispatchController" style="display: inline-block">
                                                                                                    <input type="hidden" name="commentID" value="${comment.commentId}" />
                                                                                                    <input type="hidden" name="articleId" value="${dto.articleId}" />
                                                                                                    <input type="hidden" name="page" value="${currentPage}" />
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
                                                            </c:if>
                                                        </c:forEach>
                                                    </c:if>
                                                    <c:set var="currentPage" value="${requestScope.CURRENTPAGE}"/>
                                                    <form action="DispatchController" method="POST" id="myCmtOf${dto.articleId}">
                                                        <div class="form-group">
                                                            <label for="comment">Your Comment</label>
                                                            <textarea name="txtContent" class="form-control" rows="3"></textarea>
                                                        </div>
                                                        <input type="hidden" name="articleId" value="${dto.articleId}" />
                                                        <input type="hidden" name="page" value="${currentPage}" />
                                                        <button type="submit" class="btn btn-default" value="Comment" name="btnAction">Send</button>
                                                    </form>
                                                </div>                                                
                                            </div>
                                        </div>
                                    </section>

                                </div>
                            </div> 
                        </c:forEach>

                        <c:set var="artCount" value="${requestScope.COUNT}"/>
                        <nav aria-label="Page navigation">
                            <ul class="pagination">
                                <c:url var="paging" value="SearchServlet">
                                    <c:param name="page" value="${i}"/>
                                    <c:param name="txtSearchValue" value="${searchValue}"/>
                                </c:url>

                                <c:set var="currentPage" value="${requestScope.CURRENTPAGE}"/>

                                <c:url var="prePage" value="SearchServlet">
                                    <c:param name="page" value="${currentPage - 1}"/>
                                    <c:param name="txtSearchValue" value="${searchValue}"/>
                                </c:url>
                                <c:if test="${currentPage > 1}">
                                    <li class="page-item">
                                        <a class="page-link" href="${prePage}" tabindex="-1">Previous</a>
                                    </li>
                                </c:if>
                                <c:if test="${currentPage == 1}">
                                    <li class="page-item disabled">
                                        <a class="page-link" tabindex="-1">Previous</a>
                                    </li>
                                </c:if>

                                <li class="page-item"><a class="page-link">${currentPage}</a></li>

                                <c:url var="postPage" value="SearchServlet">
                                    <c:param name="page" value="${currentPage + 1}"/>
                                    <c:param name="txtSearchValue" value="${searchValue}"/>
                                </c:url>
                                <c:if test="${currentPage < artCount}">
                                    <li class="page-item">
                                        <a class="page-link" href="${postPage}">Next</a>
                                    </li>
                                </c:if>
                                <c:if test="${currentPage == artCount}">
                                    <li class="page-item disabled">
                                        <a class="page-link">Next</a>
                                    </li>
                                </c:if>                    
                            </ul>
                        </nav>
                    </div>
                </div>
            </c:if>
        </c:if>
        <c:if test="${empty username}">
            <c:redirect url="Login.jsp"/>
        </c:if>
    </body>
</html>
