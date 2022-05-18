<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css"
      integrity="sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn" crossorigin="anonymous">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="/css/home.css"/>
<c:import url="header.jsp"/>
<div class="row main-container">
    <div class="col-md-4"></div>
    <div class="col-md-4">
        <c:forEach items="${users}" var="insurance">
            <div class="card">
                <div   class="card-header" style="display: block;font-size: smaller;">
                    <div class="row">
                        <div class="col-md-6 left-alignment"><h5>${insurance.name}</h5></div>
                        <div class="col-md-6 right-alignment">
                            <a href="/removeUser/${insurance.id}">Remove</a> / <a href="/editUser/${insurance.id}">Edit</a>
                        </div>
                    </div>
                </div>
                <div class="user-card-info">
                    <div class="alignment">
                        <p style="margin: 10px">
                            <span style="display: block">${insurance.email}</span>
                            <span style="display: block">${insurance.address}</span>
                            <span style="display: block">${insurance.phone}</span>
                            <span style="display: block">${insurance.permission}</span>
                        </p>
                    </div>
                </div>
            </div>

        </c:forEach>
    </div>
    <div class="col-md-4"></div>
</div>
<c:import url="footer.jsp"/>