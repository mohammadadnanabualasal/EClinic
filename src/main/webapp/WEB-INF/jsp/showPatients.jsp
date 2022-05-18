<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css"
      integrity="sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn" crossorigin="anonymous">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="/css/home.css"/>
<c:import url="header.jsp"/>
<div class="row main-container">
    <div class="col-md-4"></div>
    <div class="col-md-4">
        <c:forEach items="${patients}" var="insurance">
            <div class="card">
                <div   class="card-header" style="display: block;font-size: smaller;">
                    <div class="row">
                        <div class="col-md-6 left-alignment"><h5>${insurance.name}</h5></div>
                        <div class="col-md-6 right-alignment">
                            <a href="/removePatient/${insurance.id}">Remove</a> / <a href="/editPatient/${insurance.id}">Edit</a>
                        </div>
                    </div>
                </div>
                <div class="user-card-info">
                    <div class="alignment">
                        <p style="margin: 10px">
                            <span style="display: block"><b>Phone:</b> ${insurance.phone}</span>
                            <span style="display: block"><b>Gender:</b> ${insurance.gender}</span>
                            <span style="display: block"><b>Height:</b> ${insurance.height}</span>
                            <span style="display: block"><b>Weight:</b> ${insurance.weight}</span>
                            <span style="display: block"><b>Diagnosis:</b> ${insurance.diagnosis}</span>
                            <span style="display: block"><b>Insurance Company:</b> ${insurance.getInsurance().name}</span>
                            <span style="display: block"><b>Doctor:</b> ${insurance.getDoctor().name}</span>
                        </p>
                    </div>
                </div>
            </div>

        </c:forEach>
    </div>
    <div class="col-md-4"></div>
</div>
<c:import url="footer.jsp"/>