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
                <div class="col-md-2">
                </div>
                <c:if test="${user != null and user.permission eq 'admin'}">
                    <div class="col-md-8">
                        <div class="row">
                            <div class="col-md-3"></div>
                            <div class="col-md-6">
                                <div class="card">
                                    <img src="/image/users" class="card-img-top" alt="users">
                                    <div class="card-body">
                                        <a href="/showUsers" class="">Users</a><br/>
                                        <a href="/addNewUser" class="">Add New User</a>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-3"></div>
                        </div>
                    </div>
                </c:if>
                <c:if test="${user != null and user.permission eq 'secretary'}">
                    <div class="col-md-8">
                        <div class="row">
                            <div class="col-md-4">
                                <div class="card">
                                    <img src="/image/appointment" class="card-img-top" alt="appointments">
                                    <div class="card-body">
                                        <a href="/showAppointments" class="">Appointments</a><br/>
                                        <a href="/addNewAppointment" class="">Add New Appointment</a>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-4">
                                <div class="card">
                                    <img src="/image/patient" class="card-img-top" alt="patient">
                                    <div class="card-body">
                                        <a href="/showPatients" class="">Patients</a><br/>
                                        <a href="/addNewPatient" class="">Add New Patient</a>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-4">
                                <div class="card">
                                    <img src="/image/insurance" class="card-img-top" alt="insurance">
                                    <div class="card-body">
                                        <a href="/showInsuranceCompanies" class="">Insurance Companies</a><br/>
                                        <a href="/addNewInsuranceCompany" class="">Add New Insurance Company</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:if>
                <c:if test="${user != null and user.permission eq 'doctor'}">
                    <div class="col-md-8">
                        <div class="row">
                            <div class="col-md-3"></div>
                            <div class="col-md-6">
                                <div class="card">
                                    <img src="/image/appointment" class="card-img-top" alt="appointments">
                                    <div class="card-body">
                                        <a href="/myAppointments" class="">My Appointments</a><br/>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-3"></div>
                        </div>
                    </div>
                </c:if>
                <div class="col-md-2">
                </div>
            </div>
        </div>
    </div>
</div>
<c:import url="footer.jsp"/>
