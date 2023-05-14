<%--
  Created by IntelliJ IDEA.
  User: garan
  Date: 09.05.2023
  Time: 10:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
</head>
<body>
<jsp:include page="header.jsp"/>
<div class="container mt-5">
    <div class="card mb-3 rounded-0">
        <div class="row g-0" style="height: 800px; background-color: black">
            <div class="col-sm-7 align-self-center" style="text-align: center">
                <img src="${post.image}"
                     class="img-fluid" style="max-height: 800px" alt="post image">
            </div>
            <div class="col-sm-5" style="background-color: white">
                <div class="card-header" style="height: 80px">
                    <div class="row g-0">
                        <div class="col-sm-6 text-start">
                            <div class="row g-0">
                                <form action="/profile" class="col-sm-2 align-self-center text-center">
                                    <button class="btn border-0" name="profile" type="submit" value="${post.author}" style="padding: unset">
                                    <img class="img-fluid rounded-5" src="${post.author.avatar}"
                                         style="padding: unset; height: 24px; width: 24px" alt="profile image">
                                    </button>
                                </form>
                                <form action="/profile" class="col-sm-10 align-self-center text-start">
                                    <button class="btn border-0" name="profile" type="submit" value="${post.author}" style="padding: unset">
                                    ${post.author.username}
                                    </button>
                                </form>
                            </div>
                            <fmt:parseDate value="${post.createdAt}" var="parsedPostTime"
                                           pattern="yyyy-MM-dd'T'HH:mm"
                                           type="date"/>
                            <fmt:formatDate value="${parsedPostTime}" pattern="dd.MM.yyyy HH:mm"
                                            var="formattedPostTime"/>
                            <small class="text-body-secondary">${formattedTime}</small>
                        </div>
                        <div class="col-sm-2 align-self-center text-center">
                            <c:if test="${sessionScope.user.userId == requestScope.post.author.userId}">
                                <form action="/editpost">
                                    <button class="btn border-0" name="profile" type="submit" value="${post}" style="padding: unset">
                                            edit post
                                    </button>
                                </form>
                            </c:if>
                        </div>
                        <div class="col-sm-3 align-self-center text-end">
                            ${likes}
                        </div>
                        <form action="/like" method="post" class="col-sm-1 align-self-center text-center">
                            <c:if test="${sessionScope.user.userId != post.like.userId}">
                                <button type="submit" name="like" value="1" class="btn border-0"
                                        style="padding: unset; --bs-btn-hover-color: red; transition: 0.3s">
                                    <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" fill="currentColor"
                                         class="bi bi-heart-fill object-fit-cover" viewBox="0 0 16 16">
                                        <path fill-rule="evenodd"
                                              d="M8 1.314C12.438-3.248 23.534 4.735 8 15-7.534 4.736 3.562-3.248 8 1.314z"></path>
                                    </svg>
                                </button>
                            </c:if>
                            <c:if test="${sessionScope.user.userId == post.like.userId}">
                                <button type="submit" name="like" value="0" class="btn border-0"
                                        style="padding: unset; --bs-btn-hover-color: black; transition: 0.3s">
                                    <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" fill="currentColor"
                                         class="bi bi-heart-fill object-fit-cover" viewBox="0 0 16 16">
                                        <path fill-rule="evenodd"
                                              d="M8 1.314C12.438-3.248 23.534 4.735 8 15-7.534 4.736 3.562-3.248 8 1.314z"></path>
                                    </svg>
                                </button>
                            </c:if>
                        </form>
                    </div>
                </div>
                <div class="card-body" style="height: 650px">
                    <p class="card-text overflow-y-scroll" style="height: 200px">
                        ${requestScope.post.description}
                    </p>
                    <div class="container-sm-5 border-top overflow-y-scroll" style="height: 380px; border-color: black">
                        <ul>
                            <c:forEach items="${commentList}" var="comment" end="4">
                                <fmt:parseDate value="${comment.createdAt}" var="parsedCommentTime"
                                               pattern="yyyy-MM-dd'T'HH:mm"
                                               type="date"/>
                                <fmt:formatDate value="${parsedCommentTime}" pattern="dd.MM.yyyy HH:mm"
                                                var="formattedCommentTime"/>
                                <li>
                                    <div class="container border-bottom mt-2">
                                        <div class="row">
                                            <div class="col-sm-8 align-self-center">
                                                <form action="/profile" class="col-sm-2 align-self-center text-center">
                                                    <button class="btn border-0" name="profile" type="submit" value="${comment.author}" style="padding: unset">
                                                        <img class="img-fluid rounded-5" src="${comment.author.avatar}"
                                                             style="padding: unset; height: 24px; width: 24px" alt="profile image">
                                                    </button>
                                                </form>
                                                <form action="/profile" class="col-sm-10 align-self-center text-start">
                                                    <button class="btn border-0" name="profile" type="submit" value="${comment.author}" style="padding: unset">
                                                            ${comment.author.username}
                                                    </button>
                                                </form>
                                            </div>
                                            <div class="col-sm-4 text-end">
                                                <c:if test="${sessionScope.user.userId == comment.author.userId}">
                                                    <form action="/editcomment" class="col-sm-4 align-self-center text-start">
                                                        <button class="btn border-0" name="profile" type="submit" value="${comment}" style="padding: unset">
                                                                edit
                                                        </button>
                                                    </form>
                                                </c:if>
                                            </div>
                                        </div>
                                        <small>
                                                ${comment.message}
                                        </small>
                                        <div>
                                            <small class="text-body-secondary align-self-end">
                                                    ${formattedCommentTime}
                                            </small>
                                        </div>
                                    </div>
                                </li>
                            </c:forEach>
                        </ul>
                    </div>
                    <div class="container">
                        <div class="row justify-content-center">
                            <c:if test="${paginationOffset != 0}">
                                <form action="/viewpost" method="post">
                                    <button name="DAOPagination" type="submit" value="${paginationOffset-5}">&laquo;
                                    </button>
                                </form>
                            </c:if>
                            <c:if test="${listSize == 6}">
                                <form action="/viewpost" method="post">
                                    <button name="DAOPagination" type="submit" value="${paginationOffset+5}">&raquo;
                                    </button>
                                </form>
                            </c:if>
                        </div>
                    </div>
                </div>
                <div class="card-footer" style="height: 70px">
                    <form action="/createcomment" method="post" class="row g-2">
                        <div class="col-sm-11 align-self-center">
                            <input type="text" class="form-control" placeholder="create comment"
                                   aria-label="create comment" style="height: 50px;">
                        </div>
                        <div class="col-1 align-self-center">
                            <button type="submit" class="btn border-0"
                                    style="padding: unset; --bs-btn-hover-color: blue; transition: 0.3s">
                                <svg xmlns="http://www.w3.org/2000/svg" width="45" height="45" fill="currentColor"
                                     class="bi bi-arrow-up-right-square-fill" viewBox="0 0 16 16" size="cover">
                                    <path d="M14 0a2 2 0 0 1 2 2v12a2 2 0 0 1-2 2H2a2 2 0 0 1-2-2V2a2 2 0 0 1 2-2h12zM5.904 10.803 10 6.707v2.768a.5.5 0 0 0 1 0V5.5a.5.5 0 0 0-.5-.5H6.525a.5.5 0 1 0 0 1h2.768l-4.096 4.096a.5.5 0 0 0 .707.707z"></path>
                                </svg>
                            </button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe"
        crossorigin="anonymous"></script>
</body>
</html>
