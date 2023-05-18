<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 08.05.2023
  Time: 1:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Edit profile</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-aFq/bzH65dt+w6FI2ooMVUpc+21e0SRygnTpmBvdBgSdnuTN7QbdgL+OapgHtvPp" crossorigin="anonymous">
    <%--    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">--%>
</head>
<body>
<jsp:include page="header.jsp"/>

<div class="container h-100 mt-3">
    <div class="row h-100 justify-content-center align-items-center">
        <div class="card">
            <div class="d-flex justify-content-end">
                <c:url value="/user/account" var="userProfileURL">
                    <c:param name="username" value="${sessionScope.user.username}"/>
                </c:url>
                <a href='<c:out value="${userProfileURL}"/>'>[x]close</a>
            </div>
            <br>
            <div class="text-center">
                <c:choose>
                    <c:when test="${sessionScope.user.avatar != null}">
                        <img class="rounded-circle border border-dark" width="150" height="150"
                             src="${sessionScope.user.avatar}">
                    </c:when>
                    <c:otherwise>
                        <img src="https://upload.wikimedia.org/wikipedia/commons/thumb/5/59/User-avatar.svg/2048px-User-avatar.svg.png"
                             class="rounded-circle border border-dark" width="150" height="150">
                    </c:otherwise>
                </c:choose>
                <h2 class="card-text">@${sessionScope.user.username}</h2>
                <p class="card-text">${sessionScope.user.name}</p>
                <p class="card-text">${sessionScope.user.email}</p>
            </div>
            <div class="card-body">
                <form method="post">
                    <label for="newName" class="form-label">New name:</label>
                    <input name="newName" type="text" class="form-control" id="newName"><br>
                    <label for="newEmail" class="form-label">New e-mail:</label>
                    <input name="newEmail" type="text" class="form-control" id="newEmail"><br>
                    <label for="newImage" class="form-label">New profile's image:</label>
                    <input name="newImage" type="text" class="form-control" id="newImage"><br>
                    <p>Change password:</p>
                    <label for="newPassword" class="form-label">New password:</label>
                    <input name="newPassword" type="password" class="form-control" id="newPassword"><br>
                    <label for="repeatNewPassword" class="form-label">Repeat new password:</label>
                    <input name="repeatNewPassword" type="password" class="form-control" id="repeatNewPassword"><br>

                    <button type="submit" formaction="/user/account/edit" class="btn btn-primary">Edit</button>
                    <button type="submit" formaction="/user/account/delete" class="btn btn-danger">Delete</button>
                </form>
            </div>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-qKXV1j0HvMUeCBQ+QVp7JcfGl760yU08IQ+GpUo5hlbpg51QRiuqHAJz8+BrxE/N"
        crossorigin="anonymous"></script>
</body>
</html>
