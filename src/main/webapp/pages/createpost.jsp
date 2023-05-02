<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 29.04.2023
  Time: 20:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create Post</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-aFq/bzH65dt+w6FI2ooMVUpc+21e0SRygnTpmBvdBgSdnuTN7QbdgL+OapgHtvPp" crossorigin="anonymous">
</head>
<body>
<jsp:include page="header.jsp"/>
<div class="row justify-content-center">
    <div class="col-4">
        <div class="container">
            <form action="/createpost" method="post">
                <p class="fs-6">    </p>
                <h4>
                    <div class="text-center mb-3" style="width: calc(100% - 0px);">Create new post</div>
                </h4>
                <div class="input-group flex-nowrap mb-3">
                    <span class="input-group-text" id="addon-wrapping">Enter a photo link</span>
                    <input type="text" class="form-control" placeholder="link" aria-label="link" aria-describedby="addon-wrapping" required
                           pattern="(http)?s?:?(\/\/[^']*\.(?:png|jpg|jpeg))">
                </div>
                <div class="form-floating">
                    <textarea class="form-control" placeholder="Leave a description here" id="floatingTextarea" maxlength="2200" required></textarea>
                    <label for="floatingTextarea">Description</label>
                </div>
                <p class="fs-6">    </p>
                <div class="d-grid gap-2 col-6 mx-auto">
                    <button class="btn btn-primary" type="submit">Create</button>
                </div>
            </form>
        </div>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-qKXV1j0HvMUeCBQ+QVp7JcfGl760yU08IQ+GpUo5hlbpg51QRiuqHAJz8+BrxE/N"
        crossorigin="anonymous"></script>
</body>
</html>