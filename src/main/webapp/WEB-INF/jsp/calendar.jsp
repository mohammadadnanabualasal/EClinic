<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css"
      integrity="sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn" crossorigin="anonymous">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="/css/home.css"/>

<c:import url="header.jsp"/>
<div class="row main-container">
    <div class="col-md-12">
        <div>
            <div class="row">
                <div class="col-md-4"></div>
                <div class="col-md-4">
                    <form role="form" class="login-form" action="/calendar" method="get">
                        <div class="form-group" inline="true">
                            <label for="date">Date</label>
                            <input name="date" id="date" class="form-control" type="date" data-date-inline-picker="true"
                                   value="${date}"/>
                        </div>
                        <div class="row">
                            <div class="col-md-4"></div>
                            <div class="col-md-4">
                                <button type="submit" class="btn btn-primary">
                                    Update
                                </button>
                            </div>
                            <div class="col-md-4"></div>
                        </div>
                    </form>
                </div>
                <div class="col-md-4"></div>
                <div class="scrollmenu">
                    <table class="table" style="max-width: 100%">
                        <thead>

                        <tr>
                            <th>Doctor Name</th>
                            <th class="ccc">00:00</th>

                            <th class="ccc">00:30</th>

                            <th class="ccc">01:00</th>

                            <th class="ccc">00:30</th>

                            <th class="ccc">02:00</th>

                            <th class="ccc">02:30</th>

                            <th class="ccc">03:00</th>

                            <th class="ccc">03:30</th>

                            <th class="ccc">04:00</th>

                            <th class="ccc">04:30</th>

                            <th class="ccc">05:00</th>

                            <th class="ccc">05:30</th>

                            <th class="ccc">06:00</th>

                            <th class="ccc">06:30</th>

                            <th class="ccc">07:00</th>

                            <th class="ccc">07:30</th>

                            <th class="ccc">08:00</th>

                            <th class="ccc">08:30</th>

                            <th class="ccc">09:00</th>

                            <th class="ccc">09:30</th>

                            <th class="ccc">10:00</th>

                            <th class="ccc">10:30</th>

                            <th class="ccc">11:00</th>

                            <th class="ccc">11:30</th>

                            <th class="ccc">12:00</th>

                            <th class="ccc">12:30</th>

                            <th class="ccc">13:00</th>

                            <th class="ccc">13:30</th>

                            <th class="ccc">14:00</th>

                            <th class="ccc">14:30</th>

                            <th class="ccc">15:00</th>

                            <th class="ccc">15:30</th>

                            <th class="ccc">16:00</th>

                            <th class="ccc">16:30</th>

                            <th class="ccc">17:00</th>

                            <th class="ccc">17:30</th>

                            <th class="ccc">18:00</th>

                            <th class="ccc">18:30</th>

                            <th class="ccc">19:00</th>

                            <th class="ccc">19:30</th>

                            <th class="ccc">20:00</th>

                            <th class="ccc">20:30</th>

                            <th class="ccc">21:00</th>

                            <th class="ccc">21:30</th>

                            <th class="ccc">22:00</th>

                            <th class="ccc">22:30</th>

                            <th class="ccc">23:00</th>

                            <th class="ccc">23:30</th>

                        </tr>
                        </thead>
                        <c:forEach var="doctor" items="${doctors}">
                            <tr>
                                <td class="doctorFiled">${doctor.name}</td>
                                <c:forEach var="col" begin="0" end="47">
                                    <c:choose>
                                        <c:when test="${doctor.haveReservationAt(col, date)}">
                                            <td class="reserved" onclick="popUp('${doctor.id}-${col}_p')"
                                                id="${doctor.id}-${col}">
                                                <div class="popup">.
                                                    <p class="popuptext" id="${doctor.id}-${col}_p">
                                                        <span class="popUpItem"><b>Patient:</b> ${doctor.getReservationAt(col, date).getPatient().name}</span>
                                                        <span class="popUpItem"><b>Diagnosis:</b> ${doctor.getReservationAt(col, date).getPatient().diagnosis}</span>
                                                        <span class="popUpItem"><b>Gender:</b> ${doctor.getReservationAt(col, date).getPatient().gender}</span>
                                                        <span class="popUpItem"><b>Patient Phone:</b> ${doctor.getReservationAt(col, date).getPatient().phone}</span>
                                                        <span class="popUpItem"><b>Doctor:</b> ${doctor.getReservationAt(col, date).getDoctor().name}</span>
                                                        <span class="popUpItem"><b>Doctor Phone:</b> ${doctor.getReservationAt(col, date).getDoctor().phone}</span>
                                                    </p>
                                                </div>
                                            </td>
                                        </c:when>
                                        <c:otherwise>
                                            <td id="${doctor.id}-${col}">
                                                <div class="popup">.
                                                    <span class="popuptext" id="${doctor.id}-${col}_p">

                                        </span>
                                                </div>
                                            </td>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
<c:import url="footer.jsp"/>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/select2@4.0.13/dist/js/select2.min.js"></script>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js" type="text/javascript"></script>
<script>
    function getAppointments() {
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

<script>
    // When the user clicks on div, open the popup
    function popUp(id) {
        var popup = document.getElementById(id);
        popup.classList.toggle("show");
    }
</script>
