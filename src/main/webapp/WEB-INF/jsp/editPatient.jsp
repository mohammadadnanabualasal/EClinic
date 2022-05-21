<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css"
      integrity="sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn" crossorigin="anonymous">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="/css/home.css"/>

<c:import url="header.jsp"/>
<div class="row main-container">
    <div class="col-md-4"></div>
    <div class="col-md-4">
        <form role="form" class="login-form" action="/updatePatient/${patient.id}" method="post">
            <div class="form-group">
                <label for="name">
                    Name
                </label>
                <input type="text" class="form-control" id="name" name="name" value="${patient.name}"/>
            </div>
            <c:set var="isMale">
                <c:if test="${patient.gender eq 'male'}">
                    selected
                </c:if>
            </c:set>
            <c:set var="isFemale">
                <c:if test="${patient.gender eq 'female'}">
                    selected
                </c:if>
            </c:set>
            <div class="form-group">
                <label for="gender">
                    Gender
                </label>
                <select name="gender" id="gender" class="form-select form-select-lg mb-3"
                        aria-label="Gender">
                    <option value="male" ${isMale}>Male</option>
                    <option value="female" ${isFemale}>Female</option>
                </select>
            </div>
            <div class="form-group">
                <label for="weight">
                    Weight
                </label>
                <input type="number" class="form-control" id="weight" name="weight" value="${patient.weight}"/>
            </div>
            <div class="form-group">
                <label for="height">
                    Height
                </label>
                <input type="number" class="form-control" id="height" name="height" value="${patient.height}"/>
            </div>
            <div class="form-group">
                <label for="diagnosis">
                    Diagnosis
                </label>
                <input type="text" class="form-control" id="diagnosis" name="diagnosis" value="${patient.diagnosis}"/>
            </div>
            <div class="form-group">
                <label for="company">Insurance Company</label>
                <select name="insuranceCompanyId" id="company" class="form-select form-select-lg mb-3"
                        aria-label="Company">
                    <c:forEach var="company" items="${insurances}">
                        <c:choose>
                            <c:when test="${company.id eq patient.insuranceCompanyId}">
                                <option value="${company.id}" selected>${company.name}</option>
                            </c:when>
                            <c:otherwise>
                                <option value="${company.id}">${company.name}</option>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </select>
            </div>
            <div class="form-group">
                <label for="doctor">Doctor</label>
                <select name="doctorId" id="doctor" class="form-select form-select-lg mb-3"
                        aria-label="Doctor">
                    <c:forEach var="doctor" items="${doctors}">
                        <c:choose>
                            <c:when test="${doctor.id eq patient.doctorId}">
                                <option value="${doctor.id}" selected>${doctor.name}</option>
                            </c:when>
                            <c:otherwise>
                                <option value="${doctor.id}">${doctor.name}</option>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </select>
            </div>
            <div class="form-group">
                <label for="phone">
                    phone
                </label>
                <input type="text" class="form-control" id="phone" name="phone" value="${patient.phone}"/>
            </div>
            <button type="submit" class="btn btn-primary">
                Update Patient
            </button>
        </form>

    </div>
    <div class="col-md-4"></div>
</div>
<c:import url="footer.jsp"/>
