<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Home</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha2/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-aFq/bzH65dt+w6FI2ooMVUpc+21e0SRygnTpmBvdBgSdnuTN7QbdgL+OapgHtvPp" crossorigin="anonymous">
</head>
<body>
<jsp:include page="header.jsp"/>

<div class="container">
  <div class="row justify-content-center">
    <div class="col-5 mt-5">


      <ul>
        <c:forEach items="${postList}" var="operation" end="4">
          <fmt:parseDate value="${post.createdAt}" var="parsedTime" pattern="yyyy-MM-dd'T'HH:mm" type="date"/>
          <fmt:formatDate value="${#}" pattern="dd.MM.yyyy HH:mm" var="formattedTime"/>

        </c:forEach>


        <!--            1. Фото-->
        <div class="card mt-5" style="width: 30rem;">
          <p style="margin-left: 20px"><a href="#" class="link-body-emphasis link-offset-2 link-underline-opacity-25 link-underline-opacity-75-hover fs-5 fw-bold font-monospace">test_test</a></p>
          <img src="#" alt="...">   <%--<-- Фото поста--%>
          <div class="card-body">
            <p class="card-text">#</p>  <%--<-- Текст поста--%>
          </div>
        </div>



        <!--            2. Фото-->
        <div class="card mt-5" style="width: 30rem;">
          <p style="margin-left: 20px"><a href="#" class="link-body-emphasis link-offset-2 link-underline-opacity-25 link-underline-opacity-75-hover fs-5 fw-bold font-monospace">test_test</a></p>
          <img src="#" alt="...">   <%--<-- Фото поста--%>
          <div class="card-body">
            <p class="card-text">#</p>  <%--<-- Текст поста--%>
          </div>
        </div>


        <p class="fs-6">    </p>

        <!--            3. Фото-->
        <div class="card mt-5" style="width: 30rem;">
          <p style="margin-left: 20px"><a href="#" class="link-body-emphasis link-offset-2 link-underline-opacity-25 link-underline-opacity-75-hover fs-5 fw-bold font-monospace">test_test</a></p>
          <img src="#" alt="...">   <%--<-- Фото поста--%>
          <div class="card-body">
            <p class="card-text">#</p>  <%--<-- Текст поста--%>
          </div>
        </div>



        <!--            4. Фото-->
        <div class="card mt-5" style="width: 30rem;">
          <p style="margin-left: 20px"><a href="#" class="link-body-emphasis link-offset-2 link-underline-opacity-25 link-underline-opacity-75-hover fs-5 fw-bold font-monospace">test_test</a></p>
          <img src="#" alt="...">   <%--<-- Фото поста--%>
          <div class="card-body">
            <p class="card-text">#</p>  <%--<-- Текст поста--%>
          </div>
        </div>


        <!--            5. Фото-->
        <div class="card mt-5" style="width: 30rem;">
          <p style="margin-left: 20px"><a href="#" class="link-body-emphasis link-offset-2 link-underline-opacity-25 link-underline-opacity-75-hover fs-5 fw-bold font-monospace">test_test</a></p>
          <img src="#" alt="...">   <%--<-- Фото поста--%>
          <div class="card-body">
            <p class="card-text">#</p>  <%--<-- Текст поста--%>
          </div>
        </div>


      </ul>


      <!--           Pagination-->
      <p class="fs-6">    </p>
      <c:if test="${offset != 0}">
        <form action="/user/home" method="post">
          <button name="offset" class="btn btn-secondary" type="submit" value="${offset-5}">&laquo;</button>
        </form>
      </c:if>
      <c:if test="${size == 6}">
        <form action="/user/home" method="post">
          <button name="offset" class="btn btn-secondary" type="submit" value="${offset+5}">&raquo;</button>
        </form>
      </c:if>




    </div>
  </div>
</div>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-qKXV1j0HvMUeCBQ+QVp7JcfGl760yU08IQ+GpUo5hlbpg51QRiuqHAJz8+BrxE/N"
        crossorigin="anonymous"></script>
</body>
</html>
