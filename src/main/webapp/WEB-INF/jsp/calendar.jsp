<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css"
      integrity="sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn" crossorigin="anonymous">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="/css/home.css"/>

<c:import url="header.jsp"/>
<div class="row main-container">

    <div class="col-md-4"></div>
    <div class="col-md-4">
        <div class="form-group" inline="true">
            <label for="date">Date</label>
            <input id="date" class="form-control" type="date" data-date-inline-picker="true" onchange="getAppointments()" value="${today}"/>
        </div>
        <div class="form-group" style="margin-bottom: 50px;">
            <label for="doctor">
                Doctor
            </label>
            <select name="doctor" id="doctor" class="form-select form-select-lg mb-3" onchange="getAppointments()">
                <option value="-1" selected>All Doctors</option>
                <c:forEach items="${doctors}" var="doctor" >
                    <option value="${doctor.id}">${doctor.name}</option>
                </c:forEach>
            </select>
        </div>
        <div id="appointments">
            ${appointments}
        </div>
    </div>
    <div class="col-md-4"></div>
</div>
<c:import url="footer.jsp"/>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/select2@4.0.13/dist/js/select2.min.js"></script>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js" type="text/javascript"></script>
<script>
    function getAppointments(){
        document.getElementById("appointments").innerHTML = '';
        $.ajax({
            type: "GET",
            contentType: "application/json",
            url: "/getAppointmentsForDate",
            data: {'date': document.getElementById("date").value, 'doctor': document.getElementById("doctor").value},
            dataType: 'text',
            cache: false,
            timeout: 600000,
            success: function (res) {
                document.getElementById("appointments").innerHTML = res;
            },
            error: function (e) {
                document.getElementById("appointments").innerHTML = res;
            }
        });
    }
</script>