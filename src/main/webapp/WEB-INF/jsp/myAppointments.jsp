<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css"
      integrity="sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn" crossorigin="anonymous">
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="/css/home.css"/>
<c:import url="header.jsp"/>
<div class="row main-container">
    <div class="col-md-4"></div>
    <div class="col-md-4">
        <c:forEach items="${appointments}" var="appointment">
            <div class="card">
                <div class="user-card-info">
                    <div class="alignment">
                        <p style="margin: 10px">
                            <span style="display: block"><b>Patient:</b> ${appointment.getPatient().name}</span>
                            <span style="display: block"><b>Diagnosis:</b> ${appointment.getPatient().diagnosis}</span>
                            <span style="display: block"><b>Gender:</b> ${appointment.getPatient().gender}</span>
                            <span style="display: block"><b>Patient Phone:</b> ${appointment.getPatient().phone}</span>
                            <span style="display: block"><b>Doctor:</b> ${appointment.getDoctor().name}</span>
                            <span style="display: block"><b>Doctor Phone:</b> ${appointment.getDoctor().phone}</span>
                            <span style="display: block"><b>Date:</b> ><fmt:formatDate pattern = "yyyy/MMM/dd" value = "${appointment.date}"/></span>
                            <span style="display: block"><b>Time:</b> <fmt:formatDate pattern = "hh:mm a" value = "${appointment.time}"/></span>
                        </p>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
    <div class="col-md-4"></div>
</div>
<c:import url="footer.jsp"/>