<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 08.05.2023
  Time: 1:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
                <a href="#">[x]close</a>
            </div>
            <br>
            <div class="text-center">
                <img class="rounded-circle border border-dark" width="150" height="150"
                     src="${requestScope.user.avatar}" alt="Card image cap">
                <h2 class="card-text">@${requestScope.user.username}</h2>
                <p class="card-text">${requestScope.user.name}</p>
                <p class="card-text">${requestScope.user.email}</p>
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

                    <button type="submit" formaction="/editpost" class="btn btn-primary">Edit</button>
                    <button type="submit" formaction="/delitpost" class="btn btn-danger">Delete</button>
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
