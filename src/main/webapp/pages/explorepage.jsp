<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Explore page</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-aFq/bzH65dt+w6FI2ooMVUpc+21e0SRygnTpmBvdBgSdnuTN7QbdgL+OapgHtvPp" crossorigin="anonymous">
</head>
<body>
<jsp:include page="header.jsp"/>


<div class="row justify-content-center">
    <div class="col-4">
        <nav class="navbar bg-body-tertiary">
            <div class="container-fluid">
                <form class="d-flex" role="search">
                    <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
                    <button class="btn btn-outline-success" type="submit">Search</button>
                </form>
            </div>
        </nav>
    </div>
</div>

1.
<div class="card-group">
    <div class="card m-5" style="width: 30rem;">
        <p style="margin-left: 20px"><a href="#" class="link-body-emphasis link-offset-2 link-underline-opacity-25 link-underline-opacity-75-hover fs-5 fw-bold font-monospace">#</a></p>
        <img src="#" alt="...">
        <div class="card-body">
            <p class="card-text">#</p>
        </div>
    </div>

    <div class="card m-5" style="width: 30rem;">
        <p style="margin-left: 20px"><a href="#" class="link-body-emphasis link-offset-2 link-underline-opacity-25 link-underline-opacity-75-hover fs-5 fw-bold font-monospace">#</a></p>
        <img src="#" alt="...">
        <div class="card-body">
            <p class="card-text">#</p>
        </div>
    </div>

    <div class="card m-5" style="width: 30rem;">
        <p style="margin-left: 20px"><a href="#" class="link-body-emphasis link-offset-2 link-underline-opacity-25 link-underline-opacity-75-hover fs-5 fw-bold font-monospace">#</a></p>
        <img src="#" alt="...">
        <div class="card-body">
            <p class="card-text">#</p>
        </div>
    </div>
</div>

2.
<div class="card-group">
    <div class="card m-5" style="width: 30rem;">
        <p style="margin-left: 20px"><a href="#" class="link-body-emphasis link-offset-2 link-underline-opacity-25 link-underline-opacity-75-hover fs-5 fw-bold font-monospace">#</a></p>
        <img src="#" alt="...">
        <div class="card-body">
            <p class="card-text">#</p>
        </div>
    </div>

    <div class="card m-5" style="width: 30rem;">
        <p style="margin-left: 20px"><a href="#" class="link-body-emphasis link-offset-2 link-underline-opacity-25 link-underline-opacity-75-hover fs-5 fw-bold font-monospace">#</a></p>
        <img src="#" alt="...">
        <div class="card-body">
            <p class="card-text">#</p>
        </div>
    </div>

    <div class="card m-5" style="width: 30rem;">
        <p style="margin-left: 20px"><a href="#" class="link-body-emphasis link-offset-2 link-underline-opacity-25 link-underline-opacity-75-hover fs-5 fw-bold font-monospace">#</a></p>
        <img src="#" alt="...">
        <div class="card-body">
            <p class="card-text">#</p>
        </div>
    </div>

</div>

3.
<div class="card-group">
    <div class="card m-5" style="width: 30rem;">
        <p style="margin-left: 20px"><a href="#" class="link-body-emphasis link-offset-2 link-underline-opacity-25 link-underline-opacity-75-hover fs-5 fw-bold font-monospace">#</a></p>
        <img src="#" alt="...">
        <div class="card-body">
            <p class="card-text">#</p>
        </div>
    </div>

    <div class="card m-5" style="width: 30rem;">
        <p style="margin-left: 20px"><a href="#" class="link-body-emphasis link-offset-2 link-underline-opacity-25 link-underline-opacity-75-hover fs-5 fw-bold font-monospace">#</a></p>
        <img src="#" alt="...">
        <div class="card-body">
            <p class="card-text">#</p>
        </div>
    </div>

    <div class="card m-5" style="width: 30rem;">
        <p style="margin-left: 20px"><a href="#" class="link-body-emphasis link-offset-2 link-underline-opacity-25 link-underline-opacity-75-hover fs-5 fw-bold font-monospace">#</a></p>
        <img src="#" alt="...">
        <div class="card-body">
            <p class="card-text">#</p>
        </div>
    </div>
</div>

<!--           Pagination-->
<p class="fs-6">    </p>
<c:if test="${offset != 0}">
    <form action="/user/explorepage" method="post">
        <button name="offset" class="btn btn-secondary" type="submit" value="${offset-5}">&laquo;</button>
    </form>
</c:if>
<c:if test="${size == 6}">
    <form action="/user/explorepage" method="post">
        <button name="offset" class="btn btn-secondary" type="submit" value="${offset+5}">&raquo;</button>
    </form>
</c:if>



<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-qKXV1j0HvMUeCBQ+QVp7JcfGl760yU08IQ+GpUo5hlbpg51QRiuqHAJz8+BrxE/N"
        crossorigin="anonymous"></script>
</body>
</html>