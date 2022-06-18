<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.eclinic.entities.UserEntity" %>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css"
      integrity="sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn" crossorigin="anonymous">
<script src='https://kit.fontawesome.com/a076d05399.js' crossorigin='anonymous'></script>
<%
    pageContext.setAttribute("user", (UserEntity) session.getAttribute("user"));
%>
<link href="/css/header.css" rel="stylesheet">
<div class="row">
    <div class="col-md-12">
        <div class="row header">
            <div class="col-md-4">
                <ul class="nav">
                    <li class="nav-item">
                        <a class="nav-link" href="/home">Home</a>
                    </li>
                    <li class="nav-item">
                        <c:choose>
                            <c:when test="${user != null}">
                                <a class="nav-link" href="/logout">Logout</a>
                            </c:when>
                            <c:otherwise>
                                <a class="nav-link" href="/login">Login</a>
                            </c:otherwise>
                        </c:choose>
                    </li>
                </ul>
            </div>
            <div class="col-md-4 center-alignment">
                <ul class="nav" style="margin-top: -35px;">
                    <li class="nav-item">
                        <div class="input-group rounded">
                            <img class="profile-img-rounded-circle" style="width: 150px;height: 150px;"
                                 alt="Bootstrap Image Preview" src="/image/logo"/>
                        </div>
                    </li>
                </ul>
            </div>
            <div class="col-md-4 right-alignment">
                <c:if test="${user != null and user.permission eq 'secretary'}">
                    <ul class="nav">
                        <li class="nav-item">
                            <a class="nav-link" href="/calendar"><i class="fas fa-calendar-alt fa-2x" style="color: #319a9e;"></i></a>
                        </li>
                    </ul>
                </c:if>

            </div>
        </div>
    </div>
</div>