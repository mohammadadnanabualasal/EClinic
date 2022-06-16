<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css"
      integrity="sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn" crossorigin="anonymous">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="/css/home.css"/>

<c:import url="header.jsp"/>
<div class="row main-container">
    <div class="col-md-4"></div>
    <div class="col-md-4">
        <form role="form" class="login-form" action="/addNewInsuranceCompany" method="post">
            <p class="warning">${error}</p>
            <div class="form-group">
                <label for="name">
                    Name
                </label>
                <input type="text" class="form-control" id="name" name="name" maxlength="50" required/>
            </div>
            <div class="form-group">
                <label for="address">
                    Address
                </label>
                <input type="text" class="form-control" id="address" name="address" maxlength="150" required/>
            </div>
            <div class="form-group">
                <label for="accreditationNumber">
                    Accreditation Number
                </label>
                <input type="number" class="form-control" id="accreditationNumber" name="accreditationNumber" maxlength="12" required/>
            </div>
            <div class="form-group">
                <label for="sessionPrice">
                    Session Price
                </label>
                <input type="number" class="form-control" id="sessionPrice" name="sessionPrice" required/>
            </div>
            <div class="form-group">
                <label for="fax">
                    Fax
                </label>
                <input type="text" class="form-control" id="fax" name="fax" maxlength="50" required/>
            </div>
            <div class="form-group">
                <label for="phone">
                    phone
                </label>
                <input type="number" class="form-control" id="phone" name="phone" maxlength="15" required/>
            </div>
            <div class="form-group">
                <label for="keyPersonName">
                    Key Person Name
                </label>
                <input type="text" class="form-control" id="keyPersonName" name="keyPersonName" maxlength="50" required/>
            </div>
            <div class="form-group">
                <label for="keyPersonPhone">
                    Key Person Phone Number
                </label>
                <input type="number" class="form-control" id="keyPersonPhone" name="keyPersonPhone" maxlength="15" required/>
            </div>
            <button type="submit" class="btn btn-primary">
                Add Insurance Company
            </button>
        </form>

    </div>
    <div class="col-md-4"></div>
</div>
<c:import url="footer.jsp"/>
