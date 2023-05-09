<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 08.05.2023
  Time: 0:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit post</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-aFq/bzH65dt+w6FI2ooMVUpc+21e0SRygnTpmBvdBgSdnuTN7QbdgL+OapgHtvPp" crossorigin="anonymous">
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
            <img class="card-img-top" src="${requestScope.post.image}" alt="Card image cap">
            <div class="card-body">
                <p class="card-text">${requestScope.post.description}</p><br>
                <form method="post">
                    <label for="newDescription" class="form-label">New description:</label>
                    <input name="newDescription" type="text" class="form-control" id="newDescription"><br>
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
