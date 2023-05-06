<%--
  Created by IntelliJ IDEA.
  User: andrlis
  Date: 4.05.23
  Time: 23:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>User</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-aFq/bzH65dt+w6FI2ooMVUpc+21e0SRygnTpmBvdBgSdnuTN7QbdgL+OapgHtvPp" crossorigin="anonymous">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <%--    <link rel="stylesheet" href="valid.css">--%>
</head>
<body>
<jsp:include page="header.jsp"/>

<div class="container-fluid bg-3 text-center border-bottom">
    <div class="row">
        <div class="col-md d-flex align-items-center justify-content-center">
            <img src="${requestScope.user.avatar}"
                 class="img-circle border border-dark" width="150" height="150">
        </div>
        <div class="col-md text-md-start">
            <h3>${requestScope.user.name}</h3>
            <h4>@${requestScope.user.username}</h4>

            <c:if test="${sessionScope.user.id == requestScope.user.id}">
                <div class="alert alert-danger" role="alert">
                    <a href="#" class="btn btn-secondary active" role="button" aria-pressed="true">Edit</a>
                </div>
            </c:if>
        </div>
        <div class="col-md">
            <a href="#">${requestScope.followersCnt} Followers</a>
            <a href="#">${requestScope.followingCnt} Following</a>
        </div>
    </div>
    <br>
</div>
<br>


<div class="border-bottom">
    <div class="container-fluid text-center">
        <div class="row">
            <div class="col-sm-4">
                <c:if test="${requestScope.posts[0].image != null}">
                    <a href="#"><img src="${requestScope.posts[2].image}" class="img-responsive"
                                     style="width:100%" alt="Image"></a><br>
                </c:if>
                <c:if test="${requestScope.posts[3].image != null}">
                    <a href="#"><img src="${requestScope.posts[2].image}" class="img-responsive"
                                     style="width:100%" alt="Image"></a><br>
                </c:if>
                <c:if test="${requestScope.posts[6].image != null}">
                    <a href="#"><img src="${requestScope.posts[2].image}" class="img-responsive"
                                     style="width:100%" alt="Image"></a><br>
                </c:if>
            </div>
            <div class="col-sm-4">
                <c:if test="${requestScope.posts[1].image != null}">
                    <a href="#"><img src="${requestScope.posts[2].image}" class="img-responsive"
                                     style="width:100%" alt="Image"></a><br>
                </c:if>
                <c:if test="${requestScope.posts[4].image != null}">
                    <a href="#"><img src="${requestScope.posts[2].image}" class="img-responsive"
                                     style="width:100%" alt="Image"></a><br>
                </c:if>
                <c:if test="${requestScope.posts[7].image != null}">
                    <a href="#"><img src="${requestScope.posts[2].image}" class="img-responsive"
                                     style="width:100%" alt="Image"></a><br>
                </c:if>
            </div>
            <div class="col-sm-4">
                <c:if test="${requestScope.posts[2].image != null}">
                    <a href="#"><img src="${requestScope.posts[2].image}" class="img-responsive"
                                     style="width:100%" alt="Image"></a><br>
                </c:if>
                <c:if test="${requestScope.posts[5].image != null}">
                    <a href="#"><img src="${requestScope.posts[2].image}" class="img-responsive"
                                     style="width:100%" alt="Image"></a><br>
                </c:if>
                <c:if test="${requestScope.posts[8].image != null}">
                    <a href="#"><img src="${requestScope.posts[2].image}" class="img-responsive"
                                     style="width:100%" alt="Image"></a><br>
                </c:if>
            </div>
        </div>
    </div>
</div>
<br>

<%--TODO--%>
<div class="text-center">
    <ul class="pagination">
        <li class="page-item"><a class="page-link" href="#">Previous</a></li>
        <li class="page-item"><a class="page-link" href="#">1</a></li>
        <li class="page-item"><a class="page-link" href="#">2</a></li>
        <li class="page-item"><a class="page-link" href="#">3</a></li>
        <li class="page-item"><a class="page-link" href="#">Next</a></li>
    </ul>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-qKXV1j0HvMUeCBQ+QVp7JcfGl760yU08IQ+GpUo5hlbpg51QRiuqHAJz8+BrxE/N"
        crossorigin="anonymous"></script>

</body>
</html>
