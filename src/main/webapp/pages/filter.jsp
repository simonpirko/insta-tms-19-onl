<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Restrict page</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-aFq/bzH65dt+w6FI2ooMVUpc+21e0SRygnTpmBvdBgSdnuTN7QbdgL+OapgHtvPp" crossorigin="anonymous">
</head>
<body>
<jsp:include page="header.jsp"/>


<div class="row justify-content-center">
    <div class="card mt-5" style="width: 25rem;">
        <div class="card-body">
            <p class="fw-bold">
                User not found! Please, <a href="/login" class="link-body-emphasis link-offset-2 link-underline-opacity-25 link-underline-opacity-75-hover">login</a> or <a href="/register" class="link-body-emphasis link-offset-2 link-underline-opacity-25 link-underline-opacity-75-hover">register</a>!
            </p>
        </div>
    </div>
</div>
