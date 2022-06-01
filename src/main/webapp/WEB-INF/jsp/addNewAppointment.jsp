<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css"
      integrity="sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn" crossorigin="anonymous">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="/css/home.css"/>

<c:import url="header.jsp"/>
<div class="row main-container">
    <div class="col-md-4"></div>
    <div class="col-md-4">
        <form role="form" class="login-form" action="/addNewAppointment" method="post">
            <div class="form-group">
                <label for="phone">
                    Search by phone number
                </label>
                <input name="phone" type="text" class="form-control" id="phone" oninput="patientAjaxSearch()"/>
            </div>
            <div class="form-group">
                <label>
                    Patient
                </label>
                <div id="patientsSection">

                </div>
                <a href="/addNewPatient">Add new Patient</a>
            </div>
            <div class="form-group">
                <label for="doctor">
                    Doctor
                </label>
                <select name="doctor" id="doctor" class="form-select form-select-lg mb-3">
                    <c:forEach items="${doctors}" var="doctor">
                        <option value="${doctor.id}">${doctor.name}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-group" inline="true">
                <label for="date">the Date</label>
                <input placeholder="Select date" type="date" id="date" class="form-control" name="date">
            </div>
            <div class="form-group">
                <label for="time">
                    the Time
                </label>
                <input name="time" type="time" class="form-control" id="time" required/>
            </div>
            <button type="submit" class="btn btn-primary">
                Add New Appointment
            </button>
        </form>
    </div>
    <div class="col-md-4"></div>
</div>
<c:import url="footer.jsp"/>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/select2@4.0.13/dist/js/select2.min.js"></script>
<script>$(document).ready(function () {
    $("select").select();
});</script>

<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js" type="text/javascript"></script>
<script>
    function patientAjaxSearch(){
        document.getElementById("patientsSection").innerHTML = '';
        $.ajax({
            type: "GET",
            contentType: "application/json",
            url: "/patientSearch",
            data: {'term': document.getElementById("phone").value},
            dataType: 'json',
            cache: false,
            timeout: 600000,
            success: function (res) {
                document.getElementById("patientsSection").innerHTML = res;
            },
            error: function (e) {
                document.getElementById("patientsSection").innerHTML = res;
            }
        });
    }
</script>