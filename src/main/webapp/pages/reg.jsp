<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Наташа
  Date: 20.04.2023
  Time: 14:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-aFq/bzH65dt+w6FI2ooMVUpc+21e0SRygnTpmBvdBgSdnuTN7QbdgL+OapgHtvPp" crossorigin="anonymous">
    <link rel="stylesheet" href="valid.css">
</head>
<body>
<jsp:include page="header.jsp"/>
<div class="row justify-content-center">
    <div class="col-4">
        <form class="mt-3 needs-validation" action="/register" method="post">
            <div class="row mb-3">
                <div class="col">
                    <label for="name" class="form-label">Full name</label>
                    <input name="name" type="text" class="form-control" id="name" required pattern="(^[A-Za-z]{3,16})([ ]{0,1})([A-Za-z]{3,16})?([ ]{0,1})?([A-Za-z]{3,16})?([ ]{0,1})?([A-Za-z]{3,16})">
                </div>
            </div>
            <div class="mb-3">
                <label for="Email" class="form-label">Email address</label>
                <input name="email" type="email" class="form-control" id="Email" required pattern="^[\w-\.]+@([\w-]+\.)+[\w-]{2,4}$">
                <c:if test="${emailUsed != null}">
                    <div class="alert alert-danger" role="alert">
                            ${emailUsed}
                    </div>
                </c:if>
            </div>
            <div class="mb-3">
                <label for="Username" class="form-label">Username</label>
                <input name="username" type="text" class="form-control" id="Username" required pattern="\w*">
                <c:if test="${usernameUsed != null}">
                    <div class="alert alert-danger" role="alert">
                            ${usernameUsed}
                    </div>
                </c:if>
            </div>
            <div class="mb-3">
                <label for="Password" class="form-label">Password</label>
                <input name="password" type="password" class="form-control" id="Password" required pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$">
            </div>
            <div class="d-grid gap-2 col-6 mx-auto">
                <button class="btn btn-primary" type="submit">Sign In</button>
            </div>

            <p class="fs-6">    </p>

            <p class="text-body-secondary">
                Already have an account? <a href="/login">Log in</a>
            </p>


            <%--      <div class="text-center">--%>
            <%--      <button type="submit" class="btn btn-primary mx-auto">Sign In</button>--%>

        </form>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-qKXV1j0HvMUeCBQ+QVp7JcfGl760yU08IQ+GpUo5hlbpg51QRiuqHAJz8+BrxE/N"
        crossorigin="anonymous"></script>
</body>
</html>



