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
          <fmt:formatDate value="${parsedTime}" pattern="dd.MM.yyyy HH:mm" var="formattedTime"/>

          <div class="card mt-5" style="width: 30rem;">
            <p style="margin-left: 20px"><a href="/user/profile?id=${requestScope.post.author.userId}" class="link-body-emphasis link-offset-2 link-underline-opacity-25 link-underline-opacity-75-hover fs-5 fw-bold font-monospace">${requestScope.post.author.username}</a></p>
            <a href="/user/profile?id=${requestscope.post.postId}">
              <img src="${post.image}" alt="post image">
            </a>
            <div class="card-body">
              <p class="card-text">${post.description}</p>
            </div>
          </div>
        </c:forEach>
      </ul>


      <div class="container">
        <div class="row justify-content-center">
          <ul class="pagination">
            <c:choose>
              <c:when test="${requestScope.page == 1}">
                <li class="page-item disable">
                  <a class="page-link" href="/user/home?page=${1}"
                     aria-label="First">
                    <span aria-hidden="true">First</span>
                  </a>
                </li>
              </c:when>
              <c:when test="${requestScope.page != 1}">
                <li class="page-item">
                  <a class="page-link" href="/user/home?page=${1}"
                     aria-label="First">
                    <span aria-hidden="true">First</span>
                  </a>
                </li>
              </c:when>
            </c:choose>
            <c:choose>
              <c:when test="${requestScope.page == 1}">
                <li class="page-item disable">
                  <a class="page-link" href="/user/home?page=${requestScope.page-1}"
                     aria-label="Previous">
                    <span aria-hidden="true">&laquo;</span>
                  </a>
                </li>
              </c:when>
              <c:when test="${requestScope.page != 1}">
                <li class="page-item">
                  <a class="page-link" href="/user/home?page=${requestScope.page-1}"
                     aria-label="Previous">
                    <span aria-hidden="true">&laquo;</span>
                  </a>
                </li>
              </c:when>
            </c:choose>
            <c:if test="${requestScope.countOfPages > 1}">
              <div class="btn-group">
                <button class="btn btn-secondary dropdown-toggle" type="button"
                        data-bs-toggle="dropdown" aria-expanded="false">
                    ${requestScope.page}
                </button>
                <ul class="dropdown-menu">
                  <c:forEach begin="1" var="i" end="${requestScope.countOfPages}" step="1">
                    <li><a class="dropdown-item" href="/user/home?page=${i}">${i}</a></li>
                  </c:forEach>
                </ul>
              </div>
            </c:if>
            <c:choose>
              <c:when test="${requestScope.page == requestScope.countOfPages}">
                <li class="page-item disable">
                  <a class="page-link" href="/user/home?page=${requestScope.page+1}"
                     aria-label="Next">
                    <span aria-hidden="true">&raquo;</span>
                  </a>
                </li>f
              </c:when>
              <c:when test="${requestScope.page != requestScope.countOfPages}">
                <li class="page-item">
                  <a class="page-link" href="/user/home?page=${requestScope.page+1}"
                     aria-label="Next">
                    <span aria-hidden="true">&raquo;</span>
                  </a>
                </li>
              </c:when>
            </c:choose>
            <c:choose>
              <c:when test="${requestScope.page == requestScope.countOfPages}">
                <li class="page-item disable">
                  <a class="page-link" href="/user/home?page=${requestScope.countOfPages}"
                     aria-label="Last">
                    <span aria-hidden="true">Last</span>
                  </a>
                </li>
              </c:when>
              <c:when test="${requestScope.page != requestScope.countOfPages}">
                <li class="page-item">
                  <a class="page-link" href="/user/home?page=${requestScope.countOfPages}"
                     aria-label="Last">
                    <span aria-hidden="true">Last</span>
                  </a>
                </li>
              </c:when>
            </c:choose>
          </ul>
        </div>
      </div>

    </div>
  </div>
</div>



<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-qKXV1j0HvMUeCBQ+QVp7JcfGl760yU08IQ+GpUo5hlbpg51QRiuqHAJz8+BrxE/N"
        crossorigin="anonymous"></script>
</body>
</html>
