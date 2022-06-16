<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css"
      integrity="sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn" crossorigin="anonymous">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="/css/home.css"/>

<c:import url="header.jsp"/>
<div class="row main-container">
    <div class="col-md-4"></div>
    <div class="col-md-4">
        <form role="form" class="login-form" action="/updateInsurance/${insurance.id}" method="post">
            <p class="warning">${error}</p>
            <div class="form-group">
                <label for="name">
                    Name
                </label>
                <input type="text" class="form-control" id="name" name="name" value="${insurance.name}" maxlength="50" required/>
            </div>
            <div class="form-group">
                <label for="address">
                    Address
                </label>
                <input type="text" class="form-control" id="address" name="address" value="${insurance.address}" maxlength="150" required/>
            </div>
            <div class="form-group">
                <label for="accreditationNumber">
                    Accreditation Number
                </label>
                <input type="number" class="form-control" id="accreditationNumber" name="accreditationNumber" value="${insurance.accreditationNumber}"/>
            </div>
            <div class="form-group">
                <label for="sessionPrice">
                    Session Price
                </label>
                <input type="number" class="form-control" id="sessionPrice" name="sessionPrice" value="${insurance.sessionPrice}" required/>
            </div>
            <div class="form-group">
                <label for="fax">
                    Fax
                </label>
                <input type="text" class="form-control" id="fax" name="fax" value="${insurance.fax}" maxlength="12" required/>
            </div>
            <div class="form-group">
                <label for="keyPersonName">
                    Key Person Name
                </label>
                <input type="text" class="form-control" id="keyPersonName" name="keyPersonName" value="${insurance.keyPersonName}" maxlength="50" required/>
            </div>
            <div class="form-group">
                <label for="keyPersonPhone">
                    Key Person Phone Number
                </label>
                <input type="number" class="form-control" id="keyPersonPhone" name="keyPersonPhone" value="${insurance.keyPersonPhone}" maxlength="14" required/>
            </div>
            <div class="form-group">
                <label for="phone">
                    phone
                </label>
                <input type="number" class="form-control" id="phone" name="phone" value="${insurance.phone}" maxlength="14" required/>
            </div>
            <button type="submit" class="btn btn-primary">
                Update Information
            </button>
        </form>

    </div>
    <div class="col-md-4"></div>
</div>
<c:import url="footer.jsp"/>
