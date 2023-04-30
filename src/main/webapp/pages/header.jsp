
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <title>Header</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha2/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-aFq/bzH65dt+w6FI2ooMVUpc+21e0SRygnTpmBvdBgSdnuTN7QbdgL+OapgHtvPp" crossorigin="anonymous">
</head>
<body>
<nav class="navbar navbar-expand-lg bg-body-tertiary justify-content-center sticky-top" data-bs-theme="dark">
  <div class="container justify-content-center">
    <div class="container-fluid d-flex align-items-center">

      <a class="navbar-brand fst-italic fw-bolder fs-4" href="/">Photogram</a>
      <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
              data-bs-target="#navbarSupportedContent"
              aria-controls="navbarSupportedContent" aria-expanded="false">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav me-auto mb-2 mb-lg-0">
          <c:if test="${sessionScope.user != null}">
            <a class="nav-link" aria-current="page" href="/#">Home</a>   <--- Вставить ссылку на страницу по готовности!
          </c:if>
        </ul>

        <ul class="navbar-nav me-auto mb-2 mb-lg-0">
          <c:if test="${sessionScope.user != null}">
            <a class="nav-link" aria-current="page" href="/#">Explore</a>    <--- Вставить ссылку на страницу по готовности!
          </c:if>
        </ul>

        <ul class="navbar-nav me-auto mb-2 mb-lg-0">
          <c:if test="${sessionScope.user != null}">
            <a class="nav-link" aria-current="page" href="/#">Create post</a>    <--- Вставить ссылку на страницу по готовности!
          </c:if>
        </ul>

        <ul class="navbar-nav me-auto mb-2 mb-lg-0">
          <c:if test="${sessionScope.user != null}">
            <a class="nav-link" aria-current="page" href="/#">Profile</a>     <--- Вставить ссылку на страницу по готовности!
          </c:if>
        </ul>


        <ul class="nav justify-content-end">
          <c:if test="${sessionScope.user == null}">
            <li class="nav-item">
              <a class="nav-link" aria-current="page" href="/register">Register</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" aria-current="page" href="/login">Log in</a>
            </li>
          </c:if>

          <c:if test="${sessionScope.user != null}">
            <li class="nav-item">
              <a class="nav-link" aria-current="page" href="/#">Log out</a>      <--- Вставить ссылку на страницу по готовности!
            </li>
          </c:if>

        </ul>
      </div>
    </div>
  </div>
</nav>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-qKXV1j0HvMUeCBQ+QVp7JcfGl760yU08IQ+GpUo5hlbpg51QRiuqHAJz8+BrxE/N"
        crossorigin="anonymous"></script>
</body>
</html>
