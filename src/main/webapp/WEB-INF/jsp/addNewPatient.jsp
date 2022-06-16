<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css"
      integrity="sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn" crossorigin="anonymous">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="/css/home.css"/>

<c:import url="header.jsp"/>
<div class="row main-container">
    <div class="col-md-4"></div>
    <div class="col-md-4">
        <form role="form" class="login-form" action="/addNewPatient" method="post">
            <p class="warning">${error}</p>
            <div class="form-group">
                <label for="name">
                    Name
                </label>
                <input type="text" class="form-control" id="name" name="name" maxlength="50" required/>
            </div>
            <div class="form-group">
                <label for="gender">
                    Gender
                </label>
                <select name="gender" id="gender" class="form-select form-select-lg mb-3"
                        aria-label="Gender" required>
                    <option value="male" selected>Male</option>
                    <option value="female" selected>Female</option>
                </select>
            </div>
            <div class="form-group">
                <label for="weight">
                    Weight
                </label>
                <input type="number" class="form-control" id="weight" name="weight" required/>
            </div>
            <div class="form-group">
                <label for="height">
                    Height
                </label>
                <input type="number" class="form-control" id="height" name="height" required/>
            </div>
            <div class="form-group">
                <label for="diagnosis">
                    Diagnosis
                </label>
                <input type="text" class="form-control" id="diagnosis" name="diagnosis" maxlength="200" required/>
            </div>
            <div class="form-group">
                <label for="company">Insurance Company</label>
                <select name="insuranceCompanyId" id="company" class="form-select form-select-lg mb-3"
                        aria-label="Company">
                    <option value="${company.id}" selected>${company.name}</option>
                    <c:forEach var="company" items="${companies}">
                        <option value="${company.id}" selected>${company.name}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-group">
                <label for="doctor">Doctor</label>
                <select name="doctorId" id="doctor" class="form-select form-select-lg mb-3"
                        aria-label="Doctor">
                    <c:forEach var="doctor" items="${doctors}">
                        <option value="${doctor.id}" selected>${doctor.name}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-group">
                <label for="phone">
                    phone
                </label>
                <input type="text" class="form-control" id="phone" name="phone" maxlength="14" required/>
            </div>
            <button type="submit" class="btn btn-primary">
                Add Patient
            </button>
        </form>

    </div>
    <div class="col-md-4"></div>
</div>
<c:import url="footer.jsp"/>
