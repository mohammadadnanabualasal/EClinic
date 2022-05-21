<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css"
      integrity="sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn" crossorigin="anonymous">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="/css/home.css"/>
<c:import url="header.jsp"/>
<div class="row main-container">
    <div class="col-md-4"></div>
    <div class="col-md-4">
        <c:forEach items="${insuranceEntities}" var="appointment">
            <div class="card">
                <div   class="card-header" style="display: block;font-size: smaller;">
                    <div class="row">
                        <div class="col-md-6 left-alignment"><h5>${appointment.name}</h5></div>
                        <div class="col-md-6 right-alignment">
                            <a href="/removeInsuranceCompany/${appointment.id}">Remove</a> / <a href="/editInsurance/${appointment.id}">Edit</a>
                        </div>
                    </div>
                </div>
                <div class="user-card-info">
                    <div class="alignment">
                        <p style="margin: 10px">
                            <span style="display: block"><b>Phone:</b> ${appointment.phone}</span>
                            <span style="display: block"><b>Address:</b> ${appointment.address}</span>
                            <span style="display: block"><b>Accreditation Number:</b> ${appointment.accreditationNumber}</span>
                            <span style="display: block"><b>Session Price:</b> ${appointment.sessionPrice} JOD</span>
                            <span style="display: block"><b>Fax:</b> ${appointment.fax}</span>
                            <span style="display: block"><b>Key Person Name:</b> ${appointment.keyPersonName}</span>
                            <span style="display: block"><b>Key Person Phone:</b> ${appointment.keyPersonPhone}</span>
                        </p>
                    </div>
                </div>
            </div>

        </c:forEach>
    </div>
    <div class="col-md-4"></div>
</div>
<c:import url="footer.jsp"/>