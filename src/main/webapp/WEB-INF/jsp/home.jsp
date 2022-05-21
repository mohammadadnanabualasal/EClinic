<%@ page isELIgnored="false" contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css"
      integrity="sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn" crossorigin="anonymous">
<link href="/css/home.css" rel="stylesheet">

<c:import url="header.jsp"/>
<div class="container-fluid main-container">
    <div class="row">
        <div class="col-md-12">
            <div class="row">
                <div class="col-md-4">
                </div>
                <c:if test="${user != null and user.permission eq 'admin'}">
                    <div class="col-md-4">
                        <p>
                        <h3><a class="admin-link" href="/showUsers">Users</a></h3><br/>
                        <h3><a class="admin-link" href="/addNewUser">Add New User</a></h3><br/>
                        </p>
                    </div>
                </c:if>
                <c:if test="${user != null and user.permission eq 'secretary'}">
                    <div class="col-md-4">
                        <p>
                        <h3><a class="admin-link" href="/showPatients">Patients</a></h3><br/>
                        <h3><a class="admin-link" href="/addNewPatient">Add New Patient</a></h3><br/>
                        <h3><a class="admin-link" href="/showInsuranceCompanies">Insurance Companies</a></h3><br/>
                        <h3><a class="admin-link" href="/addNewInsuranceCompany">Add New Insurance Companie</a></h3><br/>
                        <h3><a class="admin-link" href="/showAppointments">Appointments</a></h3><br/>
                        <h3><a class="admin-link" href="/addNewAppointment">Add New Appointment</a></h3><br/>
                        </p>
                    </div>
                </c:if>
                <c:if test="${user != null and user.permission eq 'doctor'}">
                    <div class="col-md-4">
                        <p>
                        <h3><a class="admin-link" href="/myAppointments">My Appointments</a></h3>
                        <br/>
                        </p>
                    </div>
                </c:if>
                <div class="col-md-4">
                </div>
            </div>
        </div>
    </div>
</div>
<c:import url="footer.jsp"/>
